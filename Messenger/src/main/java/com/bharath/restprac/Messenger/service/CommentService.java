package com.bharath.restprac.Messenger.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import com.bharath.restprac.Messenger.database.DatabaseClass;
import com.bharath.restprac.Messenger.model.Comment;
import com.bharath.restprac.Messenger.model.Message;

public class CommentService {
	
	private static Map<Long, Message> messages=DatabaseClass.getMessages();
	
	public List<Comment> getAllComments(long messageId){
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		return new ArrayList<Comment>(comments.values());
	}
	
	public Comment getComment(long messageId,long commentId) {
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		return comments.get(commentId);
	}
	
	public Comment addComment(long messageId,Comment comment) {
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		comment.setId(comments.size()+1);
		comments.put(comment.getId(),comment);
		return comment;
	}
	
	public Comment updateComment(long messageId,Comment comment) {
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		if(comment.getId()<=0) {
			return null;
		}
		comments.put(comment.getId(), comment);
		return comment;
	}
	public void deleteComment(long messageId,long commentId) {
		Map<Long, Comment> comments=messages.get(messageId).getComments();
		comments.remove(commentId);
	}
}
