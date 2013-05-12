package project_control.controllers;

import java.sql.Time;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.servlet.http.HttpServletResponse;
import javax.validation.ConstraintViolationException;
import javax.ws.rs.DELETE;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import project_control.core.OAuth2Callback;
import project_control.core.OAuth2Servlet;
import project_control.core.OAuth2Utils;
import project_control.core.PMF;
import project_control.models.Task;
import project_control.models.User;

import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.sun.jersey.api.view.Viewable;

@Path("tasks")
public class TasksController extends AbstractController {

	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() {
		//create();
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tasks", getTasks());
		map.put("activePage", 3);
		map.put("posible", possibleUser());
		map.put("title", "Task list");
		map.put("page", "/tasks/index.jsp");
		return Response.ok(new Viewable("/tasks/router", map)).build();
	}
	
	@GET
	@Path("new")
	@Produces(MediaType.TEXT_HTML)
	public Response new_item() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activePage", 3);
		map.put("title", "Task list - New");
		map.put("posible", possibleUser());
		map.put("users", getUsers());
		map.put("page", "/tasks/new.jsp");
		return Response.ok(new Viewable("/users/router", map)).build();
	}
	
	@GET
	@Path("{task}")
	@Produces(MediaType.TEXT_HTML)
	public Response show(@PathParam("task") String taskId) {
		return Response.ok(new Viewable("/tasks/show")).build();
	}
	
	@POST
	@Path("create")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response create(@FormParam("title") String title,
		      @FormParam("startAt") String startAt,
		      @FormParam("deadLineAt") String deadLineAt,
		      @FormParam("assigned") String assigned,
		      @Context HttpServletResponse servletResponse) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Task task = new Task();
		try {
			task.setTitle(title);
			DateFormat formatter = new SimpleDateFormat("MM.dd.yy");
			task.setCreatedAt(new Date());
			task.setStartAt(formatter.parse(startAt));
			task.setDeadLineAt(formatter.parse(deadLineAt));
			UserService userService = UserServiceFactory.getUserService();
			com.google.appengine.api.users.User user = userService.getCurrentUser();
			task.setCreated(user.getEmail());
			task.setAssigned(assigned);
			pm.makePersistent(task);
		}catch(ConstraintViolationException e){
			errorMessage(e);
			return new_item();
		} catch (ParseException e) {
			errorMessage(e);
			return new_item();
		}
		finally {
			pm.close();
		}

		return index();
	}
	
//	@POST
//	@Path("{task}")
//	@Produces(MediaType.TEXT_HTML)
//	public Response update(){
//		return Response.ok(new Viewable("/tasks/index")).build();
//	}
//	
//	@DELETE
//	@Path("{task}")
//	public Response delete(){
//		return Response.ok(new Viewable("/tasks/index")).build();
//	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Task> getTasks() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Task.class);
		try {
			List<Task> results = (List<Task>) q.execute();
			System.err.println(results);
			return results;
		} finally {
			q.closeAll();
			pm.close();
		}
	}
	
	public boolean possibleUser(){
		UserService userService = UserServiceFactory.getUserService();
		com.google.appengine.api.users.User user = userService.getCurrentUser();
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(User.class);
		q.setFilter("email == '"+user.getEmail()+"'");
		try {
			List<User> results = (List<User>) q.execute();
			if(results.size() > 0) return true;
			return false;
			
		} finally {
			q.closeAll();
			pm.close();
		}
	}
	
	public List<User> getUsers() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(User.class);
		try {
			List<User> results = (List<User>) q.execute();
			System.err.println(results);
			return results;
		} finally {
			q.closeAll();
			pm.close();
		}
	}
}
