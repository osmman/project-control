package project_control.core;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;

public class TokenRequestServlet extends HttpServlet {

	public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

		System.err.println("aaaaaaaaaaaaaaaaaaaaaaaa");
		
		//UserService userService = UserServiceFactory.getUserService();

		String email = (String) req.getAttribute("email");
		if (! email.isEmpty()) {

			// The clientId and clientSecret are copied from the API Access tab on
		    // the Google APIs Console
		    String clientId = "331684805094.apps.googleusercontent.com";
		    String clientSecret = "aijzwOrDJI_ko6f1DOBzNrCP";

		    // Or your redirect URL for web based applications.
		    //String redirectUrl = "https://wa2-project-control.appspot.com/tokencallback";
		    String redirectUrl = "http://localhost:8080/tokencallback";
		    
		    ArrayList<String> scope = new ArrayList<String> ();
		    scope.add("https://www.googleapis.com/auth/calendar");
		    //scope.add("https://www.googleapis.com/auth/drive");
                    scope.add("https://www.googleapis.com/auth/drive.file");
		    
		    // Step 1: Authorize -->
		    String authorizationUrl = new GoogleAuthorizationCodeRequestUrl(clientId, redirectUrl, scope)
		        .build();
		    
		    resp.sendRedirect(authorizationUrl);
			  
		}
		else {
			resp.sendRedirect("/");
		}
	}
}