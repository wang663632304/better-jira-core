package pl.edu.amu.wmi.betterjira.api.function;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.GetMethod;
import pl.edu.amu.wmi.betterjira.api.function.data.CommentsList;
import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.Issue;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class CommentFunction extends Function implements FunctionInterface {

    private String issueKey;
    private int issueId;

    public CommentFunction(Session session, String issueKey) {
	super(session);
	this.issueKey = issueKey;
    }

    public CommentFunction(Session session, int issueId) {
	super(session);
	this.issueId = issueId;
    }

    public CommentFunction(Session session, Issue issue) {
	super(session);
	issueId = issue.getId();
	issueKey = issue.getKey();
    }

    @Override
    public String getFunctionName() {
	if (issueKey != null) {
	    return String.format("/rest/api/2/issue/%s/comment", issueKey);
	} else {
	    return String.format("/rest/api/2/issue/%d/comment", issueId);
	}
    }

    public CommentsList getAllComments() throws BadResponse {
	GetMethod getMethod = new GetMethod(getFunctionName());
	try {
	    JSONObject response = (JSONObject) response(getMethod);

	    CommentsList commentsList = new CommentsList();
	    DataParser.parse(commentsList, response);

	    return commentsList;

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
		throw new Error("Server error\n" + e.getError(0));
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	}
	return null;
    }
}
