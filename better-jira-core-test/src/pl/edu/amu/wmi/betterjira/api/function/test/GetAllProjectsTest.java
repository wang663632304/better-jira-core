package pl.edu.amu.wmi.betterjira.api.function.test;

import java.util.ArrayList;

import com.google.mockwebserver.MockResponse;
import com.google.mockwebserver.MockWebServer;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.GetAllProjects;
import pl.edu.amu.wmi.betterjira.api.function.data.Project;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.utils.UserInfo;
import android.test.ActivityInstrumentationTestCase2;

public class GetAllProjectsTest extends
		ActivityInstrumentationTestCase2<TestActivity> {
	public GetAllProjectsTest() {
		super(TestActivity.class.getPackage().toString(), TestActivity.class);
	}

	public void testGetAllVisibleProjects() throws Exception {
		MockWebServer server = new MockWebServer();
		server.enqueue(new MockResponse()
				.setBody("{\"session\":{\"value\":\"4192C9DD35545EFFDAA9EAE196AFAC41\",\"name\":\"JSESSIONID\"},\"loginInfo\":{\"previousLoginTime\":\"2013-06-07T10:13:55.759+0200\",\"loginCount\":184}}"));
		server.enqueue(new MockResponse()
				.setBody("[{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/project/BET\",\"id\":\"10018\",\"key\":\"BET\",\"name\":\"BetterJira\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?size=small&pid=10018&avatarId=10002\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?pid=10018&avatarId=10002\"}}]"));
		server.play();
		ServerConnector.setServerURL(server.getUrl("/"));

		BasicAuthentication basic = new BasicAuthentication();
		Session session = basic.login(UserInfo.login, UserInfo.password);
		assertNotNull(session);

		GetAllProjects getAllProjects = new GetAllProjects(session);
		ArrayList<Project> projects = getAllProjects.getAllVisibleProjects();
		assertNotNull(projects);
		assertEquals(1, projects.size());
		assertNotNull(projects.get(0));
		assertEquals("BetterJira", projects.get(0).getName());
		assertEquals("BET", projects.get(0).getKey());
		assertEquals(10018, projects.get(0).getId());
		server.shutdown();

	}
}
