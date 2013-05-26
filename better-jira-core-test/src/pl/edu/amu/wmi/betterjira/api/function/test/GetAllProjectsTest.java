package pl.edu.amu.wmi.betterjira.api.function.test;

import java.net.URL;
import java.util.ArrayList;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.BasicAuthentication;
import pl.edu.amu.wmi.betterjira.api.function.GetAllProjects;
import pl.edu.amu.wmi.betterjira.api.function.data.Project;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.data.test.TestActivity;
import pl.edu.amu.wmi.betterjira.utils.UserInfo;
import android.test.ActivityInstrumentationTestCase2;
import android.util.Log;

public class GetAllProjectsTest extends
		ActivityInstrumentationTestCase2<TestActivity> {
	public GetAllProjectsTest() {
		super(TestActivity.class.getPackage().toString(), TestActivity.class);
	}

	public void testGetAllVisibleProjects() throws Exception {
		ServerConnector.setServerURL(new URL("https://jira.wmi.amu.edu.pl/"));

		BasicAuthentication basic = new BasicAuthentication();
		Session session = basic.login(UserInfo.login, UserInfo.password);
		assertNotNull(session);

		GetAllProjects getAllProjects = new GetAllProjects(session);
		ArrayList<Project> projects = getAllProjects.getAllVisibleProjects();
		assertNotNull(projects);
		Log.d("projekty", projects.toString());
		assertEquals(1, projects.size());
		assertNotNull(projects.get(0));
		assertEquals("BetterJira", projects.get(0).getName());
		assertEquals("BET", projects.get(0).getKey());

	}
}
