package pl.edu.amu.wmi.betterjira.api.function.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;
import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.GetIssue;
import pl.edu.amu.wmi.betterjira.api.function.data.Issue;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.utils.UserInfo;

@SuppressLint("SimpleDateFormat")
public class GetIssueTest extends
	ActivityInstrumentationTestCase2<TestActivity> {
    public GetIssueTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
    }

    /**
     * Test public Issue getIssue(String issueKey)
     * 
     * @throws Exception
     */
    public void testGetIssue() throws Exception {
	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login(UserInfo.login, UserInfo.password);
	assertNotNull(session);

	GetIssue getIssue = new GetIssue(session);
	DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
	df.setTimeZone(TimeZone.getTimeZone("GMT"));

	Issue issue = null;
	issue = getIssue.getIssue("BET-13");
	assertNotNull(issue);
	assertNotNull(issue.getAssignee());
	assertNotNull(issue.getCreated());
	assertNotNull(issue.getIssuetype());
	assertNotNull(issue.getPriority());
	assertNotNull(issue.getProject());
	assertNotNull(issue.getReporter());
	assertNotNull(issue.getStatus());
	assertNotNull(issue.getUpdated());
	assertEquals("BET-13", issue.getKey());
	assertEquals("s362631", issue.getReporter().getName());
	assertEquals("Major", issue.getPriority().getName());
	assertEquals("Wed Mar 13 13:21:33 2013", df.format(issue.getCreated()));
    }

    /**
     * Test public Issue getIssue(Issue issue)
     * 
     * @throws Exception
     */
    public void testGetIssue2() throws Exception {
	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login(UserInfo.login, UserInfo.password);
	assertNotNull(session);

	GetIssue getIssue = new GetIssue(session);
	DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
	df.setTimeZone(TimeZone.getTimeZone("GMT"));

	Issue issue = new Issue();
	issue.setKey("BET-13");
	issue = getIssue.getIssue(issue);
	assertNotNull(issue);
	assertNotNull(issue.getAssignee());
	assertNotNull(issue.getCreated());
	assertNotNull(issue.getIssuetype());
	assertNotNull(issue.getPriority());
	assertNotNull(issue.getProject());
	assertNotNull(issue.getReporter());
	assertNotNull(issue.getStatus());
	assertNotNull(issue.getUpdated());
	assertEquals("BET-13", issue.getKey());
	assertEquals("s362631", issue.getReporter().getName());
	assertEquals("Major", issue.getPriority().getName());
	assertEquals("Wed Mar 13 13:21:33 2013", df.format(issue.getCreated()));

	Issue noIssue = new Issue();
	noIssue.setKey("No such issue");
	try {
	    noIssue = getIssue.getIssue(issue);

	} catch (Exception e) {
	    assertEquals("No such issue", e.getMessage());
	}
	// Nie wiem czemu nie zwracasz nulla jeśli nie ma issue o danym kluczu
	// assertNull(noIssue);
    }
}
