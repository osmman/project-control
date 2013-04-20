package project_control.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.sun.jersey.api.view.Viewable;

@Path("/")
public class MainController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() {
		return Response.ok(new Viewable("/index")).build();
	}
	
}
