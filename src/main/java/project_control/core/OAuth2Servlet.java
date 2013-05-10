package project_control.core;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.extensions.appengine.auth.oauth2.AbstractAppEngineAuthorizationCodeServlet;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class OAuth2Servlet extends AbstractAppEngineAuthorizationCodeServlet {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request,
			HttpServletResponse response) throws IOException, ServletException {


		response.setContentType("text/html");
	    response.setCharacterEncoding("UTF-8");
	    PrintWriter writer = response.getWriter();
	    writer.println("<!doctype html><html><head>");
	    writer.println("<meta http-equiv=\"content-type\" content=\"text/html; charset=UTF-8\">");
	    writer.println("<title></title>");
	    
	    UserService userService = UserServiceFactory.getUserService();
	    writer.println("<div class=\"header\"><b>" + request.getUserPrincipal().getName() + "</b> | "
	        + "<a href=\"" + userService.createLogoutURL(request.getRequestURL().toString())
	        + "\">Log out</a> | "
	        + "<a href=\"http://code.google.com/p/google-api-java-client/source/browse"
	        + "/calendar-appengine-sample?repo=samples\">See source code for "
	        + "this sample</a></div>");
	    writer.println("<div id=\"main\"/>");
	    writer.println("</body></html>");
	}

	@Override
	  protected String getRedirectUri(HttpServletRequest req) throws ServletException, IOException {
	    return OAuth2Utils.getRedirectUri(req);
	  }

	  @Override
	  protected AuthorizationCodeFlow initializeFlow() throws IOException {
	    return OAuth2Utils.newFlow();
	  }
}
