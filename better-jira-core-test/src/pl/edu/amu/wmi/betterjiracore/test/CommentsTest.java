package pl.edu.amu.wmi.betterjiracore.test;

import java.io.IOException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.ClientProtocolException;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.CommentFunction;
import pl.edu.amu.wmi.betterjira.api.function.data.CommentsList;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.InvalidJQLCommand;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import android.test.ActivityInstrumentationTestCase2;

public class CommentsTest extends
	ActivityInstrumentationTestCase2<TestActivity> {

    public CommentsTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
    }

    public void testSimple() throws URISyntaxException,
	    ClientProtocolException, IOException, LoginException, BadResponse,
	    InvalidJQLCommand {

	ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

	BasicAuthentication basic = new BasicAuthentication();
	Session session = basic.login("test", "test");

	CommentFunction commentFunction = new CommentFunction(session, "BET-36");

	CommentsList allComments = commentFunction.getAllComments();

	System.out.println("KURWAAAAAAAAAAAA"
		+ allComments.getComment(0).getBody());

	commentFunction.sendComment("Test komentarza wysłany z testu!!!!!!!!!");

	System.out
		.println("=====================================================");
    }
}
