package project_control.controllers;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

import project_control.core.PMF;
import project_control.models.Task;

import com.sun.jersey.api.view.Viewable;

@Path("tasks")
public class TasksController {

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response index() {

		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tasks", getTasks());
		return Response.ok(new Viewable("/tasks/index", map)).build();
	}

	@GET
	@Path("{task}")
	@Produces(MediaType.TEXT_HTML)
	public Response show(@PathParam("task") String taskId) {
		return Response.ok(new Viewable("/tasks/show")).build();
	}

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Task> getTasks() {
		PersistenceManager pm = PMF.get().getPersistenceManager();
		Query q = pm.newQuery(Task.class);
		try {
			List<Task> results = (List<Task>) q.execute();
			return results;
		} finally {
			q.closeAll();
			pm.close();
		}
	}
}
