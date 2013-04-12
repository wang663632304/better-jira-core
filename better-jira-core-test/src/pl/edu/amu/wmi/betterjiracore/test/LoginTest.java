package pl.edu.amu.wmi.betterjiracore.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import android.test.ActivityInstrumentationTestCase2;

public class LoginTest extends ActivityInstrumentationTestCase2<TestActivity> {

    public LoginTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
    }

    public void testSimple() throws URISyntaxException,
	    ClientProtocolException, IOException, LoginException, BadResponse {

	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	basic.login("login", "password");

    }
}
