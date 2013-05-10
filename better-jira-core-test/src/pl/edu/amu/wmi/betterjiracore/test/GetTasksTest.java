package pl.edu.amu.wmi.betterjiracore.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.SearchForIssues;
import pl.edu.amu.wmi.betterjira.api.function.data.IssueList;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.InvalidJQLCommand;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import pl.edu.amu.wmi.betterjira.utils.UserInfo;
import android.test.ActivityInstrumentationTestCase2;

public class GetTasksTest extends
	ActivityInstrumentationTestCase2<TestActivity> {

    public GetTasksTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
    }

    public void testSimple() throws URISyntaxException,
	    ClientProtocolException, IOException, LoginException, BadResponse,
	    InvalidJQLCommand {

	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login(UserInfo.login, UserInfo.password);
	System.out.println("Session name: " + session.getName());
	System.out.println("Session value: " + session.getValue());

	System.out.println("Session login count: "
		+ session.getLoginInfo().getLoginCount());
	System.out.println("Session login date: "
		+ session.getLoginInfo().getPreviousLoginTime());

	String JQL = new String("status=\"open\"");
	SearchForIssues issues = new SearchForIssues(session);
	issues.search(JQL, 0, 5);

	System.out
		.println("=====================================================");
    }
}
