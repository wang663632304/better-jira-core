package pl.edu.amu.wmi.betterjira.api.function.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.apache.http.client.ClientProtocolException;

import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import pl.edu.amu.wmi.betterjira.utils.UserInfo;
import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

@SuppressLint("SimpleDateFormat")
public class BasicAuthenticationTest extends
		ActivityInstrumentationTestCase2<TestActivity> {

	public BasicAuthenticationTest() {
		super(TestActivity.class.getPackage().toString(), TestActivity.class);

	}

	public void testLogin() throws URISyntaxException, ClientProtocolException,
			IOException, LoginException, BadResponse {

		MockWebServer server = new MockWebServer();
		server.enqueue(new MockResponse()
				.setBody("{\"session\":{\"value\":\"4192C9DD35545EFFDAA9EAE196AFAC41\",\"name\":\"JSESSIONID\"},\"loginInfo\":{\"previousLoginTime\":\"2013-06-07T10:13:55.759+0200\",\"loginCount\":184}}"));
		server.play();
		
		DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
		df.setTimeZone(TimeZone.getTimeZone("GMT"));
		
		ServerConnector.setServerURL(server.getUrl("/"));
		BasicAuthentication basic = new BasicAuthentication();
		/* Twoje dane do logowania */
		Session session = basic.login(UserInfo.login, UserInfo.password);

		assertNotNull(session.getName());
		assertNotNull(session.getValue());
		assertNotNull(session.getLoginInfo());
		assertNotNull(session.getLoginInfo().getLoginCount());
		assertEquals("JSESSIONID", session.getName());
		assertEquals("4192C9DD35545EFFDAA9EAE196AFAC41", session.getValue());
		assertEquals(184, session.getLoginInfo().getLoginCount());
		assertEquals("Fri Jun 07 08:13:55 2013", df.format(session.getLoginInfo().getPreviousLoginTime()));
		
		server.shutdown();
		
		basic.logout(session);
		assertNull(session.getName());
		assertNull(session.getValue());
		assertNull(session.getLoginInfo());

	}

}
