package project_control.controllers;

import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;

import javax.ws.rs.core.Response;

import com.google.appengine.api.users.User;
import com.google.appengine.api.users.UserService;
import com.google.appengine.api.users.UserServiceFactory;
import com.sun.jersey.api.view.Viewable;

@Path("/login")
public class LoginController extends AbstractController {
	@Context
	HttpServletRequest r;

	@GET
	@Produces(MediaType.TEXT_HTML)
	public Response login() {

		UserService userService = UserServiceFactory.getUserService();
		Map<String, Object> map = new HashMap<String, Object>();
		if (userService.isUserLoggedIn()) {
			User user = userService.getCurrentUser();
			String email = user.getEmail();
			map.put("user", user);
			map.put("email", email);
			map.put("url", userService.createLogoutURL("/"));
		} else {
			map.put("url", userService.createLoginURL("/"));
		}
		return Response.ok(new Viewable("/login", map)).build();
	}
}
//
//class Login extends HttpServlet {
//
//	public void doGet(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//
//		UserService userService = UserServiceFactory.getUserService();
//
//		if (userService.isUserLoggedIn()) {
//
//			User user = userService.getCurrentUser();
//			String email = user.getEmail();
//
//			JdoCredentialStore cs = new JdoCredentialStore(PMF.get());
//			GoogleCredential credential = new GoogleCredential();
//
//			try {
//				if (!cs.load(email, credential)
//						|| credential.getExpiresInSeconds() < 100) {
//					// Forward to TokenRequestServlet
//					RequestDispatcher rd = req
//							.getRequestDispatcher("/tokenrequest");
//					req.setAttribute("email", email);
//					rd.forward(req, resp);
//					return;
//				}
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
//
//			// vytvorim vazbu uzivatel-jmeno kalendare
//			// pozdeji nekde v nastaveni moznost zmeny calendarID...
//			PersistenceManager pm = PMF.get().getPersistenceManager();
//
//			// Receipt r = new Receipt("7r8a18apiqkul6bcd05bdcsu2s", 3, null,
//			// null, 333, 333333, "CZK", "Muj nazev", "muj super popis cesty");
//			// pm.makePersistent(r);
//
//			CalendarHelper query = null;
//			try {
//				query = pm.getObjectById(CalendarHelper.class,
//						user.getNickname());
//			} catch (Exception e) {
//				e.printStackTrace();
//			}
//			if (query == null) {
//				CalendarHelper calendarHelper = new CalendarHelper(user);
//				try {
//					pm.makePersistent(calendarHelper);
//				} finally {
//					pm.close();
//				}
//			} else {
//				pm.close();
//			}
//
//			// go to main
//			Main main = new Main();
//			main.doGet(req, resp);
//
//		} else {
//			req.setAttribute("url", userService.createLoginURL("/"));
//
//			RequestDispatcher rd = req.getRequestDispatcher("/login.jsp");
//			rd.forward(req, resp);
//		}
//
//	}
//
//	public void doPost(HttpServletRequest req, HttpServletResponse resp)
//			throws ServletException, IOException {
//		doGet(req, resp);
//	}
//
//}
