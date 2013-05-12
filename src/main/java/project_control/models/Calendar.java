package project_control.models;

import java.io.IOException;
import java.util.Date;
import java.util.TimeZone;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import com.google.api.client.util.DateTime;
import com.google.api.services.calendar.model.Acl;
import com.google.api.services.calendar.model.AclRule;
import com.google.api.services.calendar.model.Event;
import com.google.api.services.calendar.model.EventDateTime;
import com.google.appengine.api.users.UserServiceFactory;

import project_control.core.OAuth2Utils;

public class Calendar {

	public static final String DEFAULT_NAME = "Project control";
	
	public static final String CALENDAR_ID = "kc5102hnd3vkrbukccp90qrebk@group.calendar.google.com";
	
	private com.google.api.services.calendar.Calendar service;
	
	public Calendar() throws IOException{
		service = OAuth2Utils.getCalendarService();
	}
	
	public com.google.api.services.calendar.model.Calendar getCalendar() throws IOException{
		com.google.api.services.calendar.Calendar service = OAuth2Utils.getCalendarService();
		return service.calendars().get(CALENDAR_ID).execute();
	}

	public String createCalentar() throws IOException{
		
		com.google.api.services.calendar.model.Calendar newCalendar = new com.google.api.services.calendar.model.Calendar();
		newCalendar.setSummary(DEFAULT_NAME);

		com.google.api.services.calendar.model.Calendar createdCalendar;
		createdCalendar = service.calendars().insert(newCalendar).execute();
		return createdCalendar.getId();
	}
	
	public String addUser(User user) throws IOException{
		AclRule rule = new AclRule();
		AclRule.Scope scope = new AclRule.Scope();
		
		scope.setType("user");
		scope.setValue(user.getEmail());
		rule.setRole("owner");
		rule.setScope(scope);
		
		com.google.api.services.calendar.Calendar service = OAuth2Utils.getCalendarService();
		AclRule createdRule = service.acl().insert(CALENDAR_ID, rule).execute();
		return createdRule.getId();
	}
	
	public void removeUser(User user) throws IOException{
		
		Acl acl = service.acl().list(CALENDAR_ID).execute();

		for (AclRule rule : acl.getItems()) {
			if(rule.getScope().getValue().equals(user.getEmail())){
				service.acl().delete(CALENDAR_ID, rule.getId()).execute();
			}
		}
	}
	
	public String addEvent(Task task) throws IOException{
		Event event = new Event();
		event.setSummary(task.getTitle());
		
		if(task.getStartAt() != null){
			DateTime start = new DateTime(task.getStartAt(), TimeZone.getTimeZone("UTC"));
			event.setStart(new EventDateTime().setDateTime(start));
		}else if(task.getDeadLineAt() != null){
			Date startDate = new Date();
			DateTime start = new DateTime(startDate, TimeZone.getTimeZone("UTC"));
			event.setStart(new EventDateTime().setDateTime(start));
		}
		if(task.getDeadLineAt() != null){
			DateTime end = new DateTime(task.getDeadLineAt(), TimeZone.getTimeZone("UTC"));
			event.setEnd(new EventDateTime().setDateTime(end));
		}
		Event createdEvent = service.events().insert(CALENDAR_ID, event).execute();
		return createdEvent.getId();
	}
	
	public String updateEvent(Task task) throws IOException{
		Event event = service.events().get(CALENDAR_ID, task.getCalendarEventId()).execute();
		
		Event updatedEvent = service.events().update(CALENDAR_ID, event.getId(), event).execute();
		
		return updatedEvent.getId();
	}
	
	public void removeEvent(String id) throws IOException{
		service.events().delete(CALENDAR_ID, id).execute();
	}

}
