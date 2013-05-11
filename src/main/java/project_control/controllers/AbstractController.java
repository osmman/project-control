package project_control.controllers;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.Response;

abstract class AbstractController {
	
	@Context
	HttpServletRequest request;
	@Context
	HttpServletResponse response;
	@Context
	ServletContext context;
	
	Response onAuthorization(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
		try {
			return Response.temporaryRedirect(new URI("/oauth2request")).build();
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		return Response.serverError().build();
	}
	
	void errorMessage(Object o){
		request.setAttribute("error", o);
	}
	
	void infoMessage(Object o){
		request.setAttribute("info", o);
	}
}
