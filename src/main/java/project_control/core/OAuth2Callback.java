package project_control.core;

import com.google.api.client.auth.oauth2.AuthorizationCodeResponseUrl;


//import com.google.api.client.auth.oauth2.draft10.AccessTokenResponse;
//import com.google.api.client.extensions.appengine.auth.oauth2.AbstractAppEngineAuthorizationCodeCallbackServlet;


import java.io.IOException;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuth2Callback extends HttpServlet {

  private static final long serialVersionUID = 1L;
  private final static Logger LOGGER = Logger.getLogger(OAuth2Callback.class.getName());

  public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
  	
    StringBuffer fullUrlBuf = request.getRequestURL();

    if (request.getQueryString() != null) {
      fullUrlBuf.append('?').append(request.getQueryString());
    }
    
    LOGGER.info("requestURL is: " + fullUrlBuf);
    
    AuthorizationCodeResponseUrl authResponse = new AuthorizationCodeResponseUrl(fullUrlBuf.toString());
    
    // check for user-denied error
    if (authResponse.getError() != null) {
      LOGGER.info("User-denied access");
    } else {
      LOGGER.info("User granted oauth access");
      
      String authCode = authResponse.getCode();
      
      request.getSession().setAttribute("code", authCode);
      
      response.sendRedirect(authResponse.getState());
      
    }
  }
  
	@Override
	public void init() throws ServletException {
		super.init();
//		LOGGER.setLevel(Constant.LOG_LEVEL);
		
		LOGGER.info("Initializing FavoritesServlet Servlet");
		
	}
}
