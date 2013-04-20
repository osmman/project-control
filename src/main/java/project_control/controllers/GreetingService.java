package project_control.controllers;

import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.jdo.PersistenceManager;
import javax.jdo.Query;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.google.appengine.repackaged.com.google.common.collect.Lists;
import com.sun.jersey.api.view.Viewable;

import project_control.core.PMF;
import project_control.models.Task;
 
@Path("info") /* Maps the service on url localhost:8888/api/info/ */
public class GreetingService {
 
//    @GET /* Response on the GET request */
//    @Produces("text/plain") /* The result of response is plain text */
//    public String helloWorld() {
//    	PersistenceManager pm = PMF.get().getPersistenceManager();
//    	return "Hello world!!!";
//    }
	{
		Logger.getLogger("com.sun.jersey").setLevel(Level.FINEST);
	}
    
	@GET
	@Produces(MediaType.TEXT_HTML)
	public Viewable index() {
		
		Map<String, Object> map = new HashMap<String, Object>();
		map.put("tasks", new LinkedList<Task>());
		System.out.println("###################################");
		return new Viewable("/tasks/index", map);
	}
}
