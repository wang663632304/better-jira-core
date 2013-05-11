package pl.edu.amu.wmi.betterjira.api.function.test;

import java.net.MalformedURLException;
import java.net.URL;

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

public class SearchForIssuesTest extends ActivityInstrumentationTestCase2<TestActivity>{

    public SearchForIssuesTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
	
	
	
    }
    
    public void testSearch() throws MalformedURLException, LoginException, BadResponse, InvalidJQLCommand{
	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login(UserInfo.login, UserInfo.password);
	assertNotNull(session);

	String JQL = new String("status=\"open\"");
	SearchForIssues issues = new SearchForIssues(session);
	assertNotNull(issues);
	IssueList issueList1 = (IssueList) issues.search(JQL, 0, 5);
	assertNotNull(issueList1);
	assertTrue(issueList1.getTotal()<=5);
	assertTrue(issueList1.getMaxResults()<=5);
	

    }
    
    public void testGetFunctionName() throws LoginException, BadResponse, MalformedURLException{
	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login(UserInfo.login, UserInfo.password);
	assertNotNull(session);
	SearchForIssues issues = new SearchForIssues(session);
	assertEquals("/rest/api/2/search", issues.getFunctionName());
    }
}
