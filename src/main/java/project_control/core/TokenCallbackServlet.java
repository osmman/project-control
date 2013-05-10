package project_control.core;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;
import com.google.api.client.auth.oauth2.TokenResponseException;
import com.google.api.client.extensions.jdo.auth.oauth2.JdoCredentialStore;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeTokenRequest;
import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class TokenCallbackServlet extends HttpServlet {

    public void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
    	
    	UserService userService = UserServiceFactory.getUserService();
    	
    	if(userService.isUserLoggedIn()){
    		User user = userService.getCurrentUser();
    		String email = user.getEmail();
    		
    		    StringBuffer fullUrlBuf = req.getRequestURL();
    		    if (req.getQueryString() != null) {
    		      fullUrlBuf.append('?').append(req.getQueryString());
    		    }

    		    AuthorizationCodeResponseUrl authResponse =
    		        new AuthorizationCodeResponseUrl(fullUrlBuf.toString());
    		    // check for user-denied error
    		    if (authResponse.getError() != null) {
    		      // authorization denied...
    		    	resp.sendRedirect("/");
    		    } else {
    		      // request access token using authResponse.getCode()...
    		    	String code = authResponse.getCode();
    		    	try {
    		    		// The clientId and clientSecret are copied from the API Access tab on
    				    // the Google APIs Console
    				    String clientId = "331684805094.apps.googleusercontent.com";
    				    String clientSecret = "aijzwOrDJI_ko6f1DOBzNrCP";

    				    // Or your redirect URL for web based applications.
    				    String redirectUrl = "https://wa2-project-control.appspot.com/tokencallback";
    				    //String redirectUrl = "http://localhost:8080/tokencallback";
    				    				    
    		    		GoogleTokenResponse response =
    		    	          new GoogleAuthorizationCodeTokenRequest(new NetHttpTransport(), new JacksonFactory(),
    		    	              clientId, clientSecret, code, redirectUrl)
    		    	              .execute();
    		    	      System.out.println("Access token: " + response.getAccessToken());
    		    	      
    		    	      GoogleCredential credential = new GoogleCredential().setFromTokenResponse(response);
    		    	      
    		    	      JdoCredentialStore cs = new JdoCredentialStore(PMF.get());
    		  			    		  			
    		  			  cs.store(email, credential);
    		  			
    		    	    } catch (TokenResponseException e) {
    		    	      if (e.getDetails() != null) {
    		    	        System.err.println("Error: " + e.getDetails().getError());
    		    	        if (e.getDetails().getErrorDescription() != null) {
    		    	          System.err.println(e.getDetails().getErrorDescription());
    		    	        }
    		    	        if (e.getDetails().getErrorUri() != null) {
    		    	          System.err.println(e.getDetails().getErrorUri());
    		    	        }
    		    	      } else {
    		    	        System.err.println(e.getMessage());
    		    	      }
    		    	    }
    		    }
    		 
    	}
        resp.sendRedirect("/");
    }
}