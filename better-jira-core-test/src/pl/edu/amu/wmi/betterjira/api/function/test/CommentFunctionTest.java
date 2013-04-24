package pl.edu.amu.wmi.betterjira.api.function.test;

import java.net.MalformedURLException;
import java.net.URL;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.CommentFunction;
import pl.edu.amu.wmi.betterjira.api.function.data.CommentsList;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import android.test.ActivityInstrumentationTestCase2;

public class CommentFunctionTest extends
ActivityInstrumentationTestCase2<TestActivity>{

    public CommentFunctionTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
    }
    
    public void testGetAllComments() throws MalformedURLException, LoginException, BadResponse{
	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login("login", "password");

	CommentFunction commentFunction = new CommentFunction(session, "BET-36");
	assertNotNull(commentFunction);
	CommentsList allComments = commentFunction.getAllComments();
	assertNotNull(allComments);
	assertTrue(allComments.getMaxResults()>0);
	assertTrue(allComments.getTotal()>0);
	
    }

}
