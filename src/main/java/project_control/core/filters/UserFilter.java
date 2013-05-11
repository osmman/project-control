package project_control.core.filters;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import project_control.core.OAuth2Utils;

import com.google.api.client.auth.oauth2.AuthorizationCodeFlow;
import com.google.api.client.auth.oauth2.AuthorizationCodeRequestUrl;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.http.HttpResponseException;
import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;

public class UserFilter implements Filter {

	@Override
	public void destroy() {
	}

	@Override
	public void doFilter(ServletRequest arg0, ServletResponse arg1,
			FilterChain chain) throws IOException, ServletException {

		HttpServletRequest req = (HttpServletRequest) arg0;
		UserService userService = UserServiceFactory.getUserService();
		User user = userService.getCurrentUser();

		if (userService.isUserLoggedIn()) {
			req.setAttribute("user", user);
			req.setAttribute("logoutUrl", userService.createLogoutURL("/"));
		}

		chain.doFilter(arg0, arg1);

	}
	
	@Override
	public void init(FilterConfig config) throws ServletException {
	}


}
