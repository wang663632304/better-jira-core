package pl.edu.amu.wmi.betterjira.api.function.data.test;

import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.Issue;
import pl.edu.amu.wmi.betterjira.api.function.data.IssueList;
import android.test.ActivityInstrumentationTestCase2;

public class DataParserTest extends
		ActivityInstrumentationTestCase2<TestActivity> {

	public DataParserTest() {
		super(TestActivity.class.getPackage().toString(), TestActivity.class);
	}

	public void testParse() throws JSONException {

		// issue list
		JSONObject jsonObject1 = new JSONObject(
				"{\"expand\":\"names,schema\",\"startAt\":0,\"maxResults\":1,\"total\":31,\"issues\":[{\"expand\":\"editmeta,renderedFields,transitions,changelog,operations\",\"id\":\"10420\",\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/10420\",\"key\":\"BET-31\",\"fields\":{\"summary\":\"Wylogować się z Jiry\",\"progress\":{\"progress\":0,\"total\":3600,\"percent\":0},\"issuetype\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issuetype/3\",\"id\":\"3\",\"description\":\"A task that needs to be done.\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/task.gif\",\"name\":\"Task\",\"subtask\":false},\"votes\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/BET-31/votes\",\"votes\":0,\"hasVoted\":false},\"resolution\":null,\"fixVersions\":[],\"resolutiondate\":null,\"timespent\":null,\"reporter\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s362617\",\"name\":\"s362617\",\"emailAddress\":\"dd32438@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&ownerId=s362617&avatarId=10203\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?ownerId=s362617&avatarId=10203\"},\"displayName\":\"Dawid Drozd\",\"active\":true},\"aggregatetimeoriginalestimate\":3600,\"created\":\"2013-04-14T11:02:47.000+0200\",\"updated\":\"2013-04-14T11:02:47.000+0200\",\"description\":\"Uzyć metody\r\n/rest/auth/1/session via DELETE\",\"priority\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/priority/3\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/priority_major.gif\",\"name\":\"Major\",\"id\":\"3\"},\"duedate\":null,\"customfield_10001\":null,\"issuelinks\":[],\"customfield_10004\":\"417\",\"watches\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/BET-31/watchers\",\"watchCount\":1,\"isWatching\":true},\"customfield_10000\":null,\"subtasks\":[],\"status\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/status/1\",\"description\":\"The issue is open and ready for the assignee to start work on it.\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/status_open.gif\",\"name\":\"Open\",\"id\":\"1\"},\"customfield_10006\":null,\"labels\":[],\"customfield_10005\":null,\"workratio\":0,\"assignee\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s362617\",\"name\":\"s362617\",\"emailAddress\":\"dd32438@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&ownerId=s362617&avatarId=10203\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?ownerId=s362617&avatarId=10203\"},\"displayName\":\"Dawid Drozd\",\"active\":true},\"aggregatetimeestimate\":3600,\"project\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/project/BET\",\"id\":\"10018\",\"key\":\"BET\",\"name\":\"BetterJira\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?size=small&pid=10018&avatarId=10002\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?pid=10018&avatarId=10002\"}},\"versions\":[],\"environment\":null,\"timeestimate\":3600,\"aggregateprogress\":{\"progress\":0,\"total\":3600,\"percent\":0},\"lastViewed\":\"2013-04-14T11:02:47.957+0200\",\"components\":[{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/component/10003\",\"id\":\"10003\",\"name\":\"lib\"}],\"customfield_10010\":null,\"timeoriginalestimate\":3600,\"aggregatetimespent\":null}}]}");

		IssueList issueList = new IssueList();
		DataParser.parse(issueList, jsonObject1);

		assertEquals(31, issueList.getTotal());
		assertEquals(1, issueList.getMaxResults());
		assertEquals(0, issueList.getStartAt());

		// issue 1 (from issueList)
		Issue issue1 = issueList.getIssue(0);
		assertEquals(10420, issue1.getId());
		assertEquals("BET-31", issue1.getKey());
		assertEquals("Sun Apr 14 09:02:47 GMT+00:00 2013", issue1.getCreated()
				.toString());
		assertEquals("Sun Apr 14 09:02:47 GMT+00:00 2013", issue1.getUpdated()
				.toString());
		assertEquals(3600, issue1.getAggregatetimeestimate());
		assertEquals("Uzyć metody\r\n/rest/auth/1/session via DELETE",
				issue1.getDescription());
		assertEquals(3600, issue1.getTimeestimate());
		assertEquals(0, issue1.getWorkratio());

		// issue2
		JSONObject jsonObject2 = new JSONObject(
				"{\"expand\":\"renderedFields,names,schema,transitions,operations,editmeta,changelog\",\"id\":\"10370\",\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/10370\",\"key\":\"BET-15\",\"fields\":{\"summary\":\"Stworzyć ekran logowania\",\"progress\":{\"progress\":0,\"total\":0},\"timetracking\":{},\"issuetype\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issuetype/3\",\"id\":\"3\",\"description\":\"A task that needs to be done.\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/task.gif\",\"name\":\"Task\",\"subtask\":false},\"votes\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/BET-15/votes\",\"votes\":0,\"hasVoted\":false},\"resolution\":null,\"fixVersions\":[],\"resolutiondate\":null,\"timespent\":null,\"reporter\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s362631\",\"name\":\"s362631\",\"emailAddress\":\"dj18125@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10118\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10118\"},\"displayName\":\"Dawid Jewko\",\"active\":true},\"aggregatetimeoriginalestimate\":null,\"created\":\"2013-04-09T09:30:20.182+0200\",\"updated\":\"2013-04-15T22:07:11.040+0200\",\"description\":null,\"priority\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/priority/2\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/priority_critical.gif\",\"name\":\"Critical\",\"id\":\"2\"},\"duedate\":null,\"customfield_10001\":null,\"issuelinks\":[],\"customfield_10004\":\"386\",\"watches\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/BET-15/watchers\",\"watchCount\":1,\"isWatching\":true},\"worklog\":{\"startAt\":0,\"maxResults\":0,\"total\":0,\"worklogs\":[]},\"customfield_10000\":null,\"subtasks\":[],\"status\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/status/1\",\"description\":\"The issue is open and ready for the assignee to start work on it.\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/status_open.gif\",\"name\":\"Open\",\"id\":\"1\"},\"customfield_10006\":null,\"labels\":[],\"customfield_10005\":null,\"workratio\":-1,\"assignee\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s369962\",\"name\":\"s369962\",\"emailAddress\":\"mm54312@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10122\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10122\"},\"displayName\":\"Marcin Skibicki\",\"active\":true},\"attachment\":[],\"aggregatetimeestimate\":null,\"project\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/project/BET\",\"id\":\"10018\",\"key\":\"BET\",\"name\":\"BetterJira\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?size=small&pid=10018&avatarId=10002\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?pid=10018&avatarId=10002\"}},\"versions\":[],\"environment\":null,\"timeestimate\":null,\"aggregateprogress\":{\"progress\":0,\"total\":0},\"lastViewed\":\"2013-04-09T09:30:20.491+0200\",\"components\":[{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/component/10005\",\"id\":\"10005\",\"name\":\"ui\"}],\"comment\":{\"startAt\":0,\"maxResults\":0,\"total\":0,\"comments\":[]},\"customfield_10010\":null,\"timeoriginalestimate\":null,\"aggregatetimespent\":null}}");
		
		Issue issue2 = new Issue();
		DataParser.parse(issue2, jsonObject2);
		assertEquals(10370, issue2.getId());
		assertEquals("BET-15", issue2.getKey());
		assertEquals("Tue Apr 09 07:30:20 GMT+00:00 2013", issue2.getCreated()
				.toString());
		System.out.println("XXX"+issue2.getDescription());
		assertEquals("Mon Apr 15 20:07:11 GMT+00:00 2013", issue2.getUpdated()
				.toString());
		assertEquals(0, issue2.getAggregatetimeestimate());
		assertNull(issue2.getDescription());
		assertEquals(0, issue2.getTimeestimate());
		assertEquals(-1, issue2.getWorkratio());

	}

}
