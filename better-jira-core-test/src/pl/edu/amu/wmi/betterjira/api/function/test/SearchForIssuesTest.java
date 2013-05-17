package pl.edu.amu.wmi.betterjira.api.function.test;

import java.net.MalformedURLException;
import java.net.URL;

import org.json.JSONException;
import org.json.JSONObject;

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
    
    public void testSearch() throws MalformedURLException, LoginException, BadResponse, InvalidJQLCommand, JSONException{
	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login(UserInfo.login, UserInfo.password);
	assertNotNull(session);

	String JQL = new String("status=\"open\"");
	SearchForIssues issues = new SearchForIssues(session);
	assertNotNull(issues);
	IssueList issueList1 = (IssueList) issues.search(JQL, 0, 5);
	assertNotNull(issueList1);
	assertEquals(JQL, issueList1.getRequest().get("jql"));
	assertEquals(0, issueList1.getRequest().get("startAt"));
	assertEquals(5, issueList1.getRequest().get("maxResults"));
	assertEquals(0, issueList1.getStartAt());
	assertTrue(issueList1.getMaxResults()<=5);
	

    }
    
    public void testNextResults() throws MalformedURLException, LoginException, BadResponse{
    	if(true) return;
    	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

    	BasicAuthentication basic = new BasicAuthentication();
    	Session session = basic.login(UserInfo.login, UserInfo.password);
    	assertNotNull(session);
    	
    	
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
