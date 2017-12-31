package com.bharath.restprac.Messenger.resources;

import java.net.URI;
import java.util.Set;

import javax.ws.rs.BeanParam;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.UriInfo;

import com.bharath.restprac.Messenger.model.Message;
import com.bharath.restprac.Messenger.resources.beans.MessageFilterBean;
import com.bharath.restprac.Messenger.service.MessageService;

@Path("/messages")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)

public class MessageResource {
	
	MessageService messageService=new MessageService();
	@GET
	public Set<Message> getAllMessages(@BeanParam MessageFilterBean filterBean) {
		/*if(filterBean.getYear()>0) {
			return messageService.getAllMessagesInAnYear(filterBean.getYear());
		}
		if(filterBean.getStart()>0 && filterBean.getSize()>0) {
			return messageService.getAllMessagesPaginated(filterBean.getStart(), filterBean.getSize());
		}*/
		return messageService.getAllMessages();
	}
	
	@POST
	public Response addMessage(Message message,@Context UriInfo uriInfo) {
		Message newMessage=messageService.addMessage(message);
		String newId=String.valueOf(newMessage.getId());
		URI uri=uriInfo.getAbsolutePathBuilder().path(newId).build();
		return Response.created(uri).entity(newMessage).build();
/*		return messageService.addMessage(message);
*/	}
	
	@PUT
	@Path("/{messageId}")
	public Message updateMessage(@PathParam("messageId") long messageId,Message message) {
		message.setId(messageId);
		return messageService.updateMessage(message);	
	}
	
	@DELETE
	@Path("/{messageId}")
	public void deleteMessage(@PathParam("messageId") long messageId) {
		messageService.removeMessage(messageId);
	}
	int i=0;
	@GET
	@Path("/{messageId}")
	public Message getMessage(@PathParam("messageId") long messageId,@Context UriInfo uriInfo) {
		
		Message localMessage=messageService.getMessage(messageId);
		localMessage.addLink(getSelfUri(uriInfo, localMessage), "self");
		localMessage.addLink(getProfileUri(uriInfo, localMessage), "profile");
		localMessage.addLink(getCommentsUri(uriInfo, localMessage), "comments");
		return localMessage;
	}

	private String getCommentsUri(UriInfo uriInfo, Message localMessage) {
		URI uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(MessageResource.class,"getCommentResource")
				.path(CommentResource.class)
				/*.path(Long.toString(localMessage.getId()))*/
				.resolveTemplate("messageId", localMessage.getId())
				.build();
				
				return uri.toString();
	}

	private String getProfileUri(UriInfo uriInfo, Message localMessage) {
		String uri=uriInfo.getBaseUriBuilder()
			.path(MessageResource.class)
			.path(localMessage.getAuthor())
			.build()
			.toString();
			return uri;
	}

	private String getSelfUri(UriInfo uriInfo, Message localMessage) {
		String uri=uriInfo.getBaseUriBuilder()
				.path(MessageResource.class)
				.path(Long.toString(localMessage.getId()))
				.build()
				.toString();
		return uri;
	}
	
	
	@Path("/{messageId}/comments")
	public CommentResource getCommentResource() {
		return new CommentResource();
		
	}
}
