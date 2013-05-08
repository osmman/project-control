package project_control.core;

import java.util.Arrays;
import java.util.List;
import java.util.logging.Level;

import com.google.api.client.extensions.appengine.http.urlfetch.UrlFetchTransport;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;

public interface Constant {

	final static Level LOG_LEVEL = Level.FINE;
	
	/* session attributes */
	final static String TARGET_URI = "TargetUri";
	
	final static String AUTH_USER_ID = "UserEmail";
	
	final static String AUTH_USER_EMAIL = "UserEmail";
	
	final static String AUTH_USER_NICKNAME = "UserNickname";
	
	final static String GOOG_CREDENTIAL_STORE = "GoogleCredentialStore";
	
	final static String GOOG_CREDENTIAL = "GoogleCredentialL";
	
	final static String VIDEO_FAVS = "VideoFavs";
	/* end of session attributes */
	
	final static String GOOGLE_YOUTUBE_FEED = "https://gdata.youtube.com/feeds/api/users/default/favorites/";
	
	final static String GOOGLE_SPREADSHEET_FEED = "https://docs.google.com/feeds/default/private/full/-/spreadsheet";
	
	final static String GOOGLE_RESOURCE = "https://gdata.youtube.com/";
	
	final static HttpTransport HTTP_TRANSPORT = new UrlFetchTransport();
	
	final static JsonFactory JSON_FACTORY = new JacksonFactory();
	
	final static String AUTH_RESOURCE_LOC = "/client_secrets.json";
	
  final static List<String> SCOPES = Arrays.asList(
      "https://gdata.youtube.com/feeds/api/users/default/favorites/");
	
	// Use for running on GAE
	//final static String OATH_CALLBACK = "http://tennis-coachrx.appspot.com/authSub";
	
	// Use for local testing
	final static String OATH_CALLBACK = "http://localhost:8888/authSub";
	
}
