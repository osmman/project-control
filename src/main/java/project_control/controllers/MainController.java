package project_control.controllers;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import project_control.core.OAuth2Utils;
import project_control.models.User;

import com.google.api.services.calendar.model.CalendarList;
import com.google.api.services.oauth2.model.Userinfo;
import com.google.appengine.api.users.UserServiceFactory;
import com.sun.jersey.api.view.Viewable;

@Path("/")
public class MainController extends AbstractController {
	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() throws Exception {
		Map<String, Object> map = new HashMap<String, Object>();
		
		try {
//			Drive drive = OAuth2Utils.getDriveService();
			//com.google.api.services.calendar.model.Calendar;
			
//			com.google.api.services.calendar.Calendar calendar = OAuth2Utils.getCalendarService();
//
//			com.google.api.services.calendar.Calendar.CalendarList.List request = calendar.calendarList().list();
//			request.setFields("items(id,summary,accessRole)");
//			CalendarList feed = request.execute();
//			
//			
//			
//			project_control.models.Calendar cal = new project_control.models.Calendar();
//			cal.setUser(user);
			
			//project_control.models.Calendar.createCalentar();
//			User us = new User();
//			us.setEmail("elianoss@gmail.com");
//			project_control.models.Calendar.addReaderUser(project_control.models.Calendar.CALENDAR_ID, us);
//			
//			
//			com.google.api.services.calendar.model.Calendar cal = project_control.models.Calendar.getCalendar(project_control.models.Calendar.CALENDAR_ID);
//			

			String userId = UserServiceFactory.getUserService().getCurrentUser().getUserId();
//			
			Userinfo userinfo = OAuth2Utils.getUserInfo(userId);
//			
			//map.put("calendars",cal);
			map.put("files",userinfo);
		} catch (IOException e) {
			return onAuthorization(request, response);
		}
		return Response.ok(new Viewable("/index",map)).build();
	}
	
	
	
}
