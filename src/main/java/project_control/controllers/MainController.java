package project_control.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import project_control.core.OAuth2Utils;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.sun.jersey.api.view.Viewable;

@Path("/")
public class MainController extends AbstractController {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() throws ServletException, IOException, URISyntaxException {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
			com.google.api.services.calendar.Calendar calendar = OAuth2Utils.loadCalendarClient();
			System.out.println(calendar.calendarList().list().execute().size());
			map.put("calendar",calendar.calendarList().list().execute().size());
		} catch (IOException e) {
			return onAuthorization(request, response);
		}
		return Response.ok(new Viewable("/index",map)).build();
	}
	
	
	
}
