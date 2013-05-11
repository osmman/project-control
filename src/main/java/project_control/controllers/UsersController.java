package project_control.controllers;

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
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import project_control.core.*;
import project_control.models.*;

import com.google.appengine.api.datastore.Key;
import com.google.appengine.api.datastore.KeyFactory;
import com.sun.jersey.api.view.Viewable;

@Path("users")
public class UsersController {

	
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() {
		Map<String, Object> map = new HashMap<String, Object>();
		
		map.put("users", getUsers());
		map.put("activePage", 2);
		map.put("title", "Users list");
		map.put("page", "/users/index.jsp");
		return Response.ok(new Viewable("/users/router", map)).build();
	}
	
	@GET
	@Path("new")
	@Produces(MediaType.TEXT_HTML)
	public Response new_item() {
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("activePage", 2);
		map.put("title", "Users list - New");
		map.put("page", "/users/new.jsp");
		return Response.ok(new Viewable("/users/router", map)).build();
	}
	
	@POST
	@Path("create")
	@Produces(MediaType.TEXT_HTML)
	public Response create(@FormParam("name") String name,
		      @FormParam("email") String email,
		      @FormParam("phone") String phone,
		      @Context HttpServletResponse servletResponse) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		User user = new User();
		try {
			user.setName(name);
			user.setEmail(email);
			user.setPhone(phone);
			user.setCreatedAt(new Date());
			pm.makePersistent(user);
		} finally {
			pm.close();
		}
		
		return index();
	}
	
	@GET
	@Path("edit")
	@Produces(MediaType.TEXT_HTML)
	public Response edit(@QueryParam("key") String key) {
		Map<String, Object> map = new HashMap<String, Object>();

		map.put("activePage", 2);
		map.put("user", getUser(key));
		map.put("title", "Users list - Edit");
		map.put("page", "/users/edit.jsp");
		return Response.ok(new Viewable("/users/router", map)).build();
	}
	
	@POST
	@Path("update")
	@Produces(MediaType.TEXT_HTML)
	public Response update(@FormParam("defEmail") String defEmail,
			  @FormParam("name") String name,
		      @FormParam("email") String email,
		      @FormParam("phone") String phone,
		      @Context HttpServletResponse servletResponse) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	        User u = pm.getObjectById(User.class, defEmail);
	        u.setName(name);
	        u.setPhone(phone);
	        pm.makePersistent(u);
		}catch(ConstraintViolationException e){
			return new_item();
		}
	    finally {
	        pm.close();
	    }
		
		return index();
	}

	@GET
	@Path("{task}")
	@Produces(MediaType.TEXT_HTML)
	public Response show(@PathParam("task") String taskId) {
		return Response.ok(new Viewable("/tasks/show")).build();
	}
	
	@POST
	@Path("delete")
	@Produces(MediaType.TEXT_HTML)
	public Response delete(@FormParam("key") String key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    try {
	        User u = pm.getObjectById(User.class, key);
	        pm.deletePersistent(u);
	    } finally {
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
	
	@GET
	@Path("{key}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public User getUser(@PathParam("key") String key) {
		PersistenceManager pm = PMF.get().getPersistenceManager();
	    User u = pm.getObjectById(User.class, key);
	    return u;
	}
	
}
