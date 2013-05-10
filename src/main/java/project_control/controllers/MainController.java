package project_control.controllers;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import project_control.models.Calendar;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.appengine.auth.oauth2.AbstractAppEngineAuthorizationCodeServlet;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.calendar.model.CalendarList;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.sun.jersey.api.view.Viewable;

@Path("/")
public class MainController {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() {
		UserService userService = UserServiceFactory.getUserService();
		Map<String, Object> map = new HashMap<String, Object>();
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			String email = user.getEmail();
			map.put("user", user);
			map.put("email", email);
			map.put("url", userService.createLogoutURL("/"));
		} else {
			map.put("url", userService.createLoginURL("/"));
		}
//		try {
//			CalendarList var = Calendar.getCalendars();
//			System.out.println(var);
//		} catch (IOException e) {
//			e.printStackTrace();
//		}
		
		return Response.ok(new Viewable("/index",map)).build();
	}
	
}
