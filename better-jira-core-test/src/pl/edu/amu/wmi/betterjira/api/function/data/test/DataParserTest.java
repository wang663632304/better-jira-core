package pl.edu.amu.wmi.betterjira.api.function.data.test;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.TimeZone;

import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.function.data.Comment;
import pl.edu.amu.wmi.betterjira.api.function.data.CommentsList;
import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.Issue;
import pl.edu.amu.wmi.betterjira.api.function.data.IssueList;
import pl.edu.amu.wmi.betterjira.api.function.data.IssueType;
import pl.edu.amu.wmi.betterjira.api.function.data.Priority;
import pl.edu.amu.wmi.betterjira.api.function.data.Project;
import pl.edu.amu.wmi.betterjira.api.function.data.Status;
import pl.edu.amu.wmi.betterjira.api.function.data.User;
import android.annotation.SuppressLint;
import android.test.ActivityInstrumentationTestCase2;

@SuppressLint("SimpleDateFormat")
public class DataParserTest extends
	ActivityInstrumentationTestCase2<TestActivity> {

    public DataParserTest() {
	super(TestActivity.class.getPackage().toString(), TestActivity.class);
    }

    public void testParse() throws JSONException {

	DateFormat df = new SimpleDateFormat("EEE MMM dd HH:mm:ss yyyy");
	df.setTimeZone(TimeZone.getTimeZone("GMT"));

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
	assertNotNull(issue1);
	assertEquals(10420, issue1.getId());
	assertEquals("BET-31", issue1.getKey());
	assertEquals("Sun Apr 14 09:02:47 2013", df.format(issue1.getCreated()));
	assertEquals("Sun Apr 14 09:02:47 2013", df.format(issue1.getUpdated()));
	assertEquals(3600, issue1.getAggregatetimeestimate());
	assertEquals("Uzyć metody\r\n/rest/auth/1/session via DELETE",
		issue1.getDescription());
	assertEquals(3600, issue1.getTimeestimate());
	assertEquals(0, issue1.getWorkratio());

	// issue1 - assignee user1
	User user1 = issue1.getAssignee();
	assertNotNull(user1);
	assertEquals("s362617", user1.getName());
	assertEquals("dd32438@st.amu.edu.pl", user1.getEmailAddress());
	assertEquals("Dawid Drozd", user1.getDisplayName());

	// issue1 - reporter user2
	User user2 = issue1.getReporter();
	assertNotNull(user2);
	assertEquals("s362617", user2.getName());
	assertEquals("dd32438@st.amu.edu.pl", user2.getEmailAddress());
	assertEquals("Dawid Drozd", user2.getDisplayName());

	// issue1 - issueType issueType1
	IssueType issueType1 = issue1.getIssuetype();
	assertNotNull(issueType1);
	assertEquals(3, issueType1.getId());
	assertEquals("A task that needs to be done.",
		issueType1.getDescription());
	assertEquals("https://jira.wmi.amu.edu.pl/images/icons/task.gif",
		issueType1.getIconUrl());
	assertEquals("Task", issueType1.getName());
	assertEquals(false, issueType1.isSubtask());

	// issue1 - priority priority1
	Priority priority1 = issue1.getPriority();
	assertNotNull(priority1);
	assertEquals(3, priority1.getId());
	assertEquals("Major", priority1.getName());
	assertEquals(
		"https://jira.wmi.amu.edu.pl/images/icons/priority_major.gif",
		priority1.getIconUrl());

	// issue1 - project project1
	Project project1 = issue1.getProject();
	assertNotNull(project1);
	assertEquals(10018, project1.getId());
	assertEquals("BET", project1.getKey());
	assertEquals("BetterJira", project1.getName());

	// issue1 - status status1
	Status status1 = issue1.getStatus();
	assertNotNull(status1);
	assertEquals(
		"The issue is open and ready for the assignee to start work on it.",
		status1.getDescription());
	assertEquals(
		"https://jira.wmi.amu.edu.pl/images/icons/status_open.gif",
		status1.getIconUrl());

	// issue2
	JSONObject jsonObject2 = new JSONObject(
		"{\"expand\":\"renderedFields,names,schema,transitions,operations,editmeta,changelog\",\"id\":\"10370\",\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/10370\",\"key\":\"BET-15\",\"fields\":{\"summary\":\"Stworzyć ekran logowania\",\"progress\":{\"progress\":0,\"total\":0},\"timetracking\":{},\"issuetype\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issuetype/3\",\"id\":\"3\",\"description\":\"A task that needs to be done.\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/task.gif\",\"name\":\"Task\",\"subtask\":false},\"votes\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/BET-15/votes\",\"votes\":0,\"hasVoted\":false},\"resolution\":null,\"fixVersions\":[],\"resolutiondate\":null,\"timespent\":null,\"reporter\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s362631\",\"name\":\"s362631\",\"emailAddress\":\"dj18125@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10118\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10118\"},\"displayName\":\"Dawid Jewko\",\"active\":true},\"aggregatetimeoriginalestimate\":null,\"created\":\"2013-04-09T09:30:20.182+0200\",\"updated\":\"2013-04-15T22:07:11.040+0200\",\"description\":null,\"priority\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/priority/2\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/priority_critical.gif\",\"name\":\"Critical\",\"id\":\"2\"},\"duedate\":null,\"customfield_10001\":null,\"issuelinks\":[],\"customfield_10004\":\"386\",\"watches\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/BET-15/watchers\",\"watchCount\":1,\"isWatching\":true},\"worklog\":{\"startAt\":0,\"maxResults\":0,\"total\":0,\"worklogs\":[]},\"customfield_10000\":null,\"subtasks\":[],\"status\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/status/1\",\"description\":\"The issue is open and ready for the assignee to start work on it.\",\"iconUrl\":\"https://jira.wmi.amu.edu.pl/images/icons/status_open.gif\",\"name\":\"Open\",\"id\":\"1\"},\"customfield_10006\":null,\"labels\":[],\"customfield_10005\":null,\"workratio\":-1,\"assignee\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s369962\",\"name\":\"s369962\",\"emailAddress\":\"mm54312@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10122\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10122\"},\"displayName\":\"Marcin Skibicki\",\"active\":true},\"attachment\":[],\"aggregatetimeestimate\":null,\"project\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/project/BET\",\"id\":\"10018\",\"key\":\"BET\",\"name\":\"BetterJira\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?size=small&pid=10018&avatarId=10002\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/projectavatar?pid=10018&avatarId=10002\"}},\"versions\":[],\"environment\":null,\"timeestimate\":null,\"aggregateprogress\":{\"progress\":0,\"total\":0},\"lastViewed\":\"2013-04-09T09:30:20.491+0200\",\"components\":[{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/component/10005\",\"id\":\"10005\",\"name\":\"ui\"}],\"comment\":{\"startAt\":0,\"maxResults\":0,\"total\":0,\"comments\":[]},\"customfield_10010\":null,\"timeoriginalestimate\":null,\"aggregatetimespent\":null}}");

	Issue issue2 = new Issue();
	DataParser.parse(issue2, jsonObject2);
	assertEquals(10370, issue2.getId());
	assertEquals("BET-15", issue2.getKey());
	assertEquals("Tue Apr 09 07:30:20 2013", df.format(issue2.getCreated()));
	assertEquals("Mon Apr 15 20:07:11 2013", df.format(issue2.getUpdated()));
	assertEquals(0, issue2.getAggregatetimeestimate());
	assertNull(issue2.getDescription());
	assertEquals(0, issue2.getTimeestimate());
	assertEquals(-1, issue2.getWorkratio());

	// issue2 - assignee user3
	User user3 = issue2.getAssignee();
	assertNotNull(user3);
	assertEquals("s369962", user3.getName());
	assertEquals("mm54312@st.amu.edu.pl", user3.getEmailAddress());
	assertEquals("Marcin Skibicki", user3.getDisplayName());

	// issue2 - reporter user4
	User user4 = issue2.getReporter();
	assertNotNull(user4);
	assertEquals("s362631", user4.getName());
	assertEquals("dj18125@st.amu.edu.pl", user4.getEmailAddress());
	assertEquals("Dawid Jewko", user4.getDisplayName());

	// issue2 - issueType issueType2
	IssueType issueType2 = issue2.getIssuetype();
	assertNotNull(issueType2);
	assertEquals(3, issueType2.getId());
	assertEquals("A task that needs to be done.",
		issueType2.getDescription());
	assertEquals("https://jira.wmi.amu.edu.pl/images/icons/task.gif",
		issueType2.getIconUrl());
	assertEquals("Task", issueType2.getName());
	assertEquals(false, issueType2.isSubtask());

	// commentsList1
	JSONObject jsonObject3 = new JSONObject(
		"{\"startAt\":0,\"maxResults\":2,\"total\":2,\"comments\":[{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/10220/comment/10136\",\"id\":\"10136\",\"author\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s369962\",\"name\":\"s369962\",\"emailAddress\":\"mm54312@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10122\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10122\"},\"displayName\":\"Marcin Skibicki\",\"active\":true},\"body\":\"umgnt \",\"updateAuthor\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s369962\",\"name\":\"s369962\",\"emailAddress\":\"mm54312@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10122\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10122\"},\"displayName\":\"Marcin Skibicki\",\"active\":true},\"created\":\"2013-03-20T14:37:22.807+0100\",\"updated\":\"2013-03-20T14:37:22.807+0100\"},{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/issue/10220/comment/10137\",\"id\":\"10137\",\"author\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s369962\",\"name\":\"s369962\",\"emailAddress\":\"mm54312@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10122\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10122\"},\"displayName\":\"Marcin Skibicki\",\"active\":true},\"body\":\"umgnt \",\"updateAuthor\":{\"self\":\"https://jira.wmi.amu.edu.pl/rest/api/2/user?username=s369962\",\"name\":\"s369962\",\"emailAddress\":\"mm54312@st.amu.edu.pl\",\"avatarUrls\":{\"16x16\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?size=small&avatarId=10122\",\"48x48\":\"https://jira.wmi.amu.edu.pl/secure/useravatar?avatarId=10122\"},\"displayName\":\"Marcin Skibicki\",\"active\":true},\"created\":\"2013-03-20T14:37:26.936+0100\",\"updated\":\"2013-03-20T14:37:26.936+0100\"}]}");
	CommentsList commentsList1 = new CommentsList();
	DataParser.parse(commentsList1, jsonObject3);
	assertEquals(2, commentsList1.getMaxResults());
	assertEquals(0, commentsList1.getStartAt());
	assertEquals(2, commentsList1.getTotal());

	// commentsList - comment comment1
	Comment comment1 = commentsList1.getComment(0);
	assertNotNull(comment1);
	assertEquals(10136, comment1.getId());
	assertEquals("umgnt ", comment1.getBody());
	assertEquals("Wed Mar 20 13:37:22 2013",
		df.format(comment1.getCreated()));
	assertEquals("Wed Mar 20 13:37:22 2013",
		df.format(comment1.getUpdated()));

	// comment1 - author user5
	User user5 = comment1.getAuthor();
	assertNotNull(user5);
	assertEquals("s369962", user5.getName());
	assertEquals("mm54312@st.amu.edu.pl", user5.getEmailAddress());
	assertEquals("Marcin Skibicki", user5.getDisplayName());

	// comment1 - updateAuthor user6
	User user6 = comment1.getUpdateAuthor();
	assertNotNull(user6);
	assertEquals("s369962", user6.getName());
	assertEquals("mm54312@st.amu.edu.pl", user6.getEmailAddress());
	assertEquals("Marcin Skibicki", user6.getDisplayName());

	// commentsList - comment comment2
	Comment comment2 = commentsList1.getComment(1);
	assertNotNull(comment2);
	assertEquals(10137, comment2.getId());
	assertEquals("umgnt ", comment2.getBody());
	assertEquals("Wed Mar 20 13:37:26 2013",
		df.format(comment2.getCreated()));
	assertEquals("Wed Mar 20 13:37:26 2013",
		df.format(comment2.getUpdated()));

	// comment2 - author user7
	User user7 = comment2.getAuthor();
	assertNotNull(user7);
	assertEquals("s369962", user7.getName());
	assertEquals("mm54312@st.amu.edu.pl", user7.getEmailAddress());
	assertEquals("Marcin Skibicki", user7.getDisplayName());

	// comment2 - updateAuthor user8
	User user8 = comment2.getUpdateAuthor();
	assertNotNull(user8);
	assertEquals("s369962", user8.getName());
	assertEquals("mm54312@st.amu.edu.pl", user8.getEmailAddress());
	assertEquals("Marcin Skibicki", user8.getDisplayName());
    }
}
