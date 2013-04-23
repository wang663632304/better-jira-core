package pl.edu.amu.wmi.betterjira.api.function.test;


import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import android.test.ActivityInstrumentationTestCase2;

public class BasicAuthenticationTest extends
	ActivityInstrumentationTestCase2<TestActivity> {

    public BasicAuthenticationTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);

    }

    public void testLogin() throws URISyntaxException,
    ClientProtocolException, IOException, LoginException, BadResponse {
	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	/* Twoje dane do logowania */
	Session session = basic.login("login", "password");
	assertNotNull(session.getName());
	assertNotNull(session.getValue());
	assertNotNull(session.getLoginInfo());
	assertNotNull(session.getLoginInfo().getLoginCount());
	assertEquals("JSESSIONID", session.getName());
	
	basic.logout(session);
	assertNull(session.getName());
	assertNull(session.getValue());
	assertNull(session.getLoginInfo());

    }

}
