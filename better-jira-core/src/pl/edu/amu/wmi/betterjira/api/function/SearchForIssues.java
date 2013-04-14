package pl.edu.amu.wmi.betterjira.api.function;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.PostMethod;
import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.data.IssueList;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.InvalidJQLCommand;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class SearchForIssues extends Function implements FunctionInterface {

    public SearchForIssues(Session session) {
	super(session);
    }

    @Override
    public String getFunctionName() {
	return "/rest/api/2/search";
    }

    /**
     * 
     * @param JQL
     *            jql command
     * @param startAt
     *            offset for issues
     * @param maxResults
     *            maximum number of issues
     * @param fields
     *            fields you want get
     * @throws InvalidJQLCommand
     * @throws BadResponse
     */
    public Object search(String JQL, int startAt, int maxResults)
	    throws InvalidJQLCommand, BadResponse {
	return search(JQL, startAt, maxResults, null);
    }

    /*-
     * Example
    {
        "jql": "project = HSP",
        "startAt": 0,
        "maxResults": 15,
        "fields": [
            "summary",
            "status",
            "assignee"
        ]
    }
     */
    /**
     * 
     * @param JQL
     *            jql command
     * @param startAt
     *            offset for issues
     * @param maxResults
     *            maximum number of issues
     * @param fields
     *            fields you want get
     * @throws InvalidJQLCommand
     * @throws BadResponse
     */
    public IssueList search(String JQL, int startAt, int maxResults,
	    ArrayList<String> fields) throws InvalidJQLCommand, BadResponse {
	PostMethod postMethod = new PostMethod(getFunctionName());
	JSONObject jsonObjectRequest = new JSONObject();
	try {
	    jsonObjectRequest.put("jql", JQL);
	    jsonObjectRequest.put("startAt", startAt);
	    jsonObjectRequest.put("maxResults", maxResults);

	    if (fields != null) {
		JSONArray jsonArrayFields = new JSONArray();
		for (String field : fields) {
		    jsonArrayFields.put(field);
		}
		jsonObjectRequest.put("fields", jsonArrayFields);
	    }

	    postMethod.setEntity(jsonObjectRequest);
	    JSONObject response = response(postMethod);
	    return parseIssueList(response, jsonObjectRequest);

	} catch (UnsupportedEncodingException e1) {
	    e1.printStackTrace();
	} catch (JSONException e) {
	    e.printStackTrace();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (IllegalStateException e) {
	    e.printStackTrace();
	} catch (EmptyResponse e) {
	    e.printStackTrace();
	} catch (NoStatusLine e) {
	    e.printStackTrace();
	} catch (StatusCode e) {
	    e.printStackTrace();
	    switch (e.getStatusCode()) {
	    case 400:
		throw new InvalidJQLCommand(JQL, e.getError(0));
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	}
	return null;
    }

    private IssueList parseIssueList(JSONObject jsonObjectResponse,
	    JSONObject jsonObjectRequest) throws JSONException {

	IssueList issueList = new IssueList();
	//issueList.setRequest(jsonObjectRequest);
	issueList.setTotal(jsonObjectResponse.getInt("total"));
	issueList.setMaxResults(jsonObjectResponse.getInt("maxResults"));
	issueList.setStartAt(jsonObjectResponse.getInt("startAt"));

	return null;
    }

    public Object parseObject(Class<?> clazz, JSONObject jsonObject) {

	Method[] declaredMethods = clazz.getDeclaredMethods();
	

	return jsonObject;
    }
}
