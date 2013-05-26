package pl.edu.amu.wmi.betterjira.api.function.test;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.CommentFunction;
import pl.edu.amu.wmi.betterjira.api.function.data.Comment;
import pl.edu.amu.wmi.betterjira.api.function.data.CommentsList;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import pl.edu.amu.wmi.betterjira.utils.UserInfo;
import android.test.ActivityInstrumentationTestCase2;

public class CommentFunctionTest extends
		ActivityInstrumentationTestCase2<TestActivity> {

	public CommentFunctionTest() {
		super(TestActivity.class.getPackage().toString(), TestActivity.class);
	}

	public void testGetAllComments() throws MalformedURLException,
			LoginException, BadResponse {
		ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

		BasicAuthentication basic = new BasicAuthentication();
		Session session = basic.login(UserInfo.login, UserInfo.password);

		CommentFunction commentFunction = new CommentFunction(session, "BET-13");
		assertNotNull(commentFunction);
		CommentsList allComments = commentFunction.getAllComments();
		assertNotNull(allComments);
		assertTrue(allComments.getMaxResults() > 0);
		assertTrue(allComments.getTotal() > 0);
		ArrayList<Comment> comments = allComments.getComments();
		assertNotNull(comments);
		for(Comment comm : comments){
			assertNotNull(comm);
		}
		assertEquals("Marcin Skibicki", comments.get(0).getAuthor().getDisplayName());
		assertEquals("umgnt ", comments.get(0).getBody());
		assertEquals("s362631", comments.get(2).getAuthor().getName());
		assertEquals("test1", comments.get(2).getBody());

	}

	public void testSendComment() throws MalformedURLException, LoginException,
			BadResponse {
		ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

		BasicAuthentication basic = new BasicAuthentication();
		Session session = basic.login(UserInfo.login, UserInfo.password);
		// BET-13 to jest task do testów
		CommentFunction commentFunction = new CommentFunction(session, "BET-13");
		// Minuta przed wysłaniem
		Date dateBefore = new Date();
		dateBefore.setTime(dateBefore.getTime() - 60 * 1000);
		Comment comment1 = commentFunction.sendComment("test1");
		// Minuta po wysłaniu
		Date dateAfter = new Date();
		dateAfter.setTime(dateAfter.getTime() + 60 * 1000);
		assertNotNull(comment1);
		assertEquals("test1", comment1.getBody());
		assertEquals(UserInfo.login, comment1.getAuthor().getName());
		assertEquals(UserInfo.login, comment1.getUpdateAuthor().getName());

		System.out.println("Created: " + comment1.getCreated());
		System.out.println("dateBefore: " + dateBefore);
		System.out.println("dateAfter: " + dateAfter);
		assertTrue(comment1.getCreated().after(dateBefore));
		assertTrue(comment1.getCreated().before(dateAfter));
		assertTrue(comment1.getUpdated().after(dateBefore));
		assertTrue(comment1.getUpdated().before(dateAfter));

	}

}
