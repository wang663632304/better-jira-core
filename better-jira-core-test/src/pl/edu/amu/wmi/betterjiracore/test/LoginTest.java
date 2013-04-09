package pl.edu.amu.wmi.betterjiracore.test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.methods.BasicAuthentication;
import android.test.ActivityInstrumentationTestCase2;

public class LoginTest extends ActivityInstrumentationTestCase2<TestActivity> {

    public LoginTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
    }

    public void simpleTest() throws URISyntaxException,
	    ClientProtocolException, IOException {
	BasicAuthentication basicAuthentication = new BasicAuthentication(
		new URI("https://jira.wmi.amu.edu.pl/rest/auth/1/session"));

	HttpResponse execute = ServerConnector.execute(basicAuthentication);
    }

}
