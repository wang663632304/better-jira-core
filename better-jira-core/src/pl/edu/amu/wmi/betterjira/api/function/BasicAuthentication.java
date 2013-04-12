package pl.edu.amu.wmi.betterjira.api.function;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.PostMethod;
import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.LoginException;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;

public class BasicAuthentication extends Function implements FunctionInterface {

    public BasicAuthentication() throws URISyntaxException {

    }

    public Session login(String login, String password) throws LoginException,
	    BadResponse {

	PostMethod postMethod = new PostMethod(getFunctionName());
	JSONObject jsonObject = new JSONObject();
	try {
	    jsonObject.put("username", login);
	    jsonObject.put("password", password);

	    postMethod.setEntity(jsonObject);

	    HttpResponse httpResponse = ServerConnector.execute(postMethod);
	    int statusCode = getStatusCode(httpResponse);

	    switch (statusCode) {
	    case 200:
		JSONObject loginJSON = parseResponse(httpResponse);
		return parseLogin(loginJSON);
	    case 401:
		throw new LoginException("Invalid user or password");
	    case 403:
		throw new LoginException("Can't login please try again");
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ statusCode);
	    }
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
	// TODO get login info
	JSONObject jsonObjectSession = loginJSON.getJSONObject("session");

	Session session = new Session(jsonObjectSession.getString("name"),
		jsonObjectSession.getString("value"));
	return session;
    }

    public void sessionInfo() {
	// TODO Implement
	throw new UnsupportedOperationException("Not implemented");
    }

    public void logout() {
	// TODO Implement
	throw new UnsupportedOperationException("Not implemented");
    }

    @Override
    public String getFunctionName() {
	return "/rest/auth/1/session";
    }
}
