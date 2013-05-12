package project_control.models;

import java.io.IOException;

import javax.jdo.annotations.PersistenceCapable;
import javax.jdo.annotations.Persistent;

import project_control.core.OAuth2Utils;

@PersistenceCapable
public class Calendar {

	public static final String DEFAULT_NAME = "Project control";

	@Persistent
	private String id;

	@Persistent
	private String folder;

	@Persistent
	private User user;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public static String createCalentar() throws IOException{

		com.google.api.services.calendar.Calendar service = OAuth2Utils.getCalendarService();
		com.google.api.services.calendar.model.Calendar newCalendar = new com.google.api.services.calendar.model.Calendar();

		newCalendar.setSummary(DEFAULT_NAME);

		com.google.api.services.calendar.model.Calendar createdCalendar;
		createdCalendar = service.calendars().insert(newCalendar).execute();
		return createdCalendar.getId();
		

	}

}
