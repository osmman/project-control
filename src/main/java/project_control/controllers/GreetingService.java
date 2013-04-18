package project_control.controllers;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
 
@Path("info") /* Maps the service on url localhost:8888/api/info/ */
public class GreetingService {
 
    @GET /* Response on the GET request */
    @Produces("text/plain") /* The result of response is plain text */
    public String helloWorld() {
    	return "Hello world!!!";
    }
}
