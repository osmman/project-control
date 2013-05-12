package project_control.core;

import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.extensions.appengine.auth.oauth2.AppEngineCredentialStore;
import com.google.api.client.extensions.appengine.http.UrlFetchTransport;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleClientSecrets;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.google.api.client.util.Preconditions;
import com.google.api.services.calendar.Calendar;
import com.google.api.services.calendar.CalendarScopes;
import com.google.api.services.drive.Drive;
import com.google.api.services.drive.DriveScopes;
import com.google.api.services.oauth2.Oauth2;
import com.google.api.services.oauth2.Oauth2Scopes;
import com.google.api.services.oauth2.model.Userinfo;
import com.google.appengine.api.users.UserServiceFactory;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

public class OAuth2Utils {

	/** Global instance of the HTTP transport. */
	static final HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();

	/** Global instance of the JSON factory. */
	static final JsonFactory JSON_FACTORY = new JacksonFactory();

	//static final String CLIENTSECRETS_LOCATION = "/client_secrets.json";

	static final String CLIENTSECRETS_LOCATION = "/client_secrets_production.json";
	
	private static final List<String> SCOPES = Arrays.asList(
			DriveScopes.DRIVE_FILE,
			Oauth2Scopes.USERINFO_EMAIL,
			Oauth2Scopes.USERINFO_PROFILE,
			CalendarScopes.CALENDAR);

	private static GoogleClientSecrets clientSecrets = null;

	public static GoogleClientSecrets getClientCredential() throws IOException {
		if (clientSecrets == null) {

			clientSecrets = GoogleClientSecrets.load(JSON_FACTORY,
					OAuth2Utils.class
							.getResourceAsStream(CLIENTSECRETS_LOCATION));
			Preconditions
					.checkArgument(
							!clientSecrets.getDetails().getClientId()
									.startsWith("Enter ")
									&& !clientSecrets.getDetails()
											.getClientSecret()
											.startsWith("Enter "),
							"Download client_secrets.json file from https://code.google.com/apis/console/?api=calendar "
									+ "into /src/main/resources/client_secrets.json");
		}
		return clientSecrets;
	}

	public static String getRedirectUri(HttpServletRequest req) {
		GenericUrl url = new GenericUrl(req.getRequestURL().toString());
		url.setRawPath("/oauth2callback");
		return url.build();
	}

	public static GoogleAuthorizationCodeFlow newFlow() throws IOException {
		return new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT,
				JSON_FACTORY, getClientCredential(), SCOPES)
				.setCredentialStore(new AppEngineCredentialStore())
				.setAccessType("offline").build();
	}

	public static Calendar getCalendarService() throws IOException {
		String userId = UserServiceFactory.getUserService().getCurrentUser()
				.getUserId();
		return getCalendarService(userId);
	}

	public static Calendar getCalendarService(String userId) throws IOException {
		Credential credential = newFlow().loadCredential(userId);
		return new Calendar.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.build();
	}

	public static Drive getDriveService() throws IOException {
		String userId = UserServiceFactory.getUserService().getCurrentUser()
				.getUserId();
		Credential credential = newFlow().loadCredential(userId);
		return new Drive.Builder(HTTP_TRANSPORT, JSON_FACTORY, credential)
				.build();
	}

	public static Userinfo getUserInfo(String userId)
			throws IOException {
		Credential credential = newFlow().loadCredential(userId);
		Oauth2 userInfoService = new Oauth2.Builder(HTTP_TRANSPORT,
				JSON_FACTORY, credential).build();
		return userInfoService.userinfo().get().execute();
	}

	private OAuth2Utils() {
	}
}
