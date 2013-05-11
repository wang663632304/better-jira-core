package pl.edu.amu.wmi.betterjira.api.function;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.GetMethod;
import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.Issue;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class GetIssue extends Function {

    private Issue issue;

    public GetIssue(Session session) {
	super(session);
    }

    public Issue getIssue(Issue issue) throws Exception {
	this.issue = issue;
	GetMethod getMethod = new GetMethod(getFunctionName());
	try {
	    JSONObject response = (JSONObject) response(getMethod);

	    DataParser.parse(issue, response);
	    return issue;

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
	    case 404:
		throw new Exception("No such issue");
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	}
	return null;
    }

    public Issue getIssue(String issueKey) throws Exception {
	Issue issue = new Issue();
	issue.setKey(issueKey);
	return getIssue(issue);
    }

    @Override
    public String getFunctionName() {
	StringBuilder stringBuilder = new StringBuilder("/rest/api/2/issue/");
	stringBuilder.append(issue.getKey());
	return stringBuilder.toString();
    }
}
