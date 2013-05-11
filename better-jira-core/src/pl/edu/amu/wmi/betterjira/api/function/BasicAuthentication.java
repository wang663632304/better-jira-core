package pl.edu.amu.wmi.betterjira.api.function;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.DeleteMethod;
import pl.edu.amu.wmi.betterjira.api.PostMethod;
import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.LoginInfo;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class BasicAuthentication extends Function {

    public BasicAuthentication() {
	super(null);
    }

    public Session login(String login, String password) throws LoginException,
	    BadResponse {

	PostMethod postMethod = new PostMethod(getFunctionName());
	JSONObject jsonObject = new JSONObject();
	try {
	    jsonObject.put("username", login);
	    jsonObject.put("password", password);

	    postMethod.setEntity(jsonObject);

	    JSONObject response = (JSONObject) response(postMethod);
	    return parseLogin(response);
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
	    case 401:
		throw new LoginException("Invalid user or password");
	    case 403:
		throw new LoginException("Can't login please try again");
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	}
	return null;
    }

    /*-
     * Example response
     * 
    {
    "session": {
        "name": "JSESSIONID",
        "value": "12345678901234567890"
    },
    "loginInfo": {
        "failedLoginCount": 10,
        "loginCount": 127,
        "lastFailedLoginTime": "2013-04-02T07:24:17.468-0500",
        "previousLoginTime": "2013-04-02T07:24:17.468-0500"
    }
    }
     */
    private Session parseLogin(JSONObject loginJSON) throws JSONException {

	Session session = new Session();
	DataParser.parse(session, loginJSON.getJSONObject("session"));

	// Login info parsing

	LoginInfo loginInfo = new LoginInfo();
	DataParser.parse(loginInfo, loginJSON.getJSONObject("loginInfo"));

	session.setLoginInfo(loginInfo);

	return session;
    }

    public void sessionInfo() {
	// TODO Implement
	throw new UnsupportedOperationException("Not implemented");
    }

    public void logout(Session session) throws LoginException, BadResponse {
	DeleteMethod deleteMethod = new DeleteMethod(getFunctionName());
	try {
	    response(deleteMethod, 204);
	    session.destroy();
	} catch (ClientProtocolException e) {
	    e.printStackTrace();
	} catch (IllegalStateException e) {
	    e.printStackTrace();
	} catch (StatusCode e) {
	    e.printStackTrace();
	    switch (e.getStatusCode()) {
	    case 401:
		throw new LoginException("You are not logged in");
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	} catch (IOException e) {
	    e.printStackTrace();
	} catch (NoStatusLine e) {
	    e.printStackTrace();
	} catch (EmptyResponse e) {
	    e.printStackTrace();
	} catch (JSONException e) {
	    e.printStackTrace();
	}
    }

    @Override
    public String getFunctionName() {
	return "/rest/auth/1/session";
    }
}
