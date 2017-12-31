package com.bharath.restprac.Messenger.resources;

import javax.ws.rs.Consumes;
import javax.ws.rs.CookieParam;
import javax.ws.rs.GET;
import javax.ws.rs.HeaderParam;
import javax.ws.rs.MatrixParam;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.UriInfo;

@Path("/inject")
@Consumes(MediaType.TEXT_PLAIN)
@Produces(MediaType.TEXT_PLAIN)
public class InjectDemoResource {
	
	@GET
	@Path("annotations")
	public String testMethod(@MatrixParam("param") String value,@HeaderParam("header") String header,
							@CookieParam("cookiename") String cookieName) {
		return "Matrix value="+ value +"\n"+"Header Value= "+header+"\n"+"Cookie Name= "+cookieName ;
		
	}
	
	@GET
	@Path("context")
	public String contextTestMethod(@Context UriInfo uriInfo,@Context HttpHeaders httpHeaders) {
		String uri=uriInfo.getRequestUri().toString();
		String cookies=httpHeaders.getCookies().toString();
		return uri + "\n" + cookies;
	}

}
