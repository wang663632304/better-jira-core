package pl.edu.amu.wmi.betterjira.api.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URISyntaxException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.ServerMethod;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;
import android.util.Log;

public abstract class Function implements FunctionInterface {

    private static String TAG = "WEB";
    private Session session;

    public Function(Session session) {
	this.session = session;
    }

    protected static int getStatusCode(HttpResponse httpResponse)
	    throws NoStatusLine {

	StatusLine statusLine = httpResponse.getStatusLine();
	if (statusLine == null) {
	    throw new NoStatusLine();
	}
	return statusLine.getStatusCode();
    }

    protected void setSession(Session session) {
	this.session = session;
    }

    /**
     * @param httpResponse
     * @return can be JSONObject or JSONArray what you need :D
     * @throws EmptyResponse
     * @throws IllegalStateException
     * @throws IOException
     * @throws JSONException
     */
    protected static Object parseResponse(HttpResponse httpResponse)
	    throws EmptyResponse, IllegalStateException, IOException,
	    JSONException {

	HttpEntity entity = httpResponse.getEntity();

	// Delete method such logout return empty response
	if (entity == null) {
	    return new JSONObject();
	}

	InputStream content = entity.getContent();
	BufferedReader r = new BufferedReader(new InputStreamReader(content));
	StringBuilder total = new StringBuilder(128);
	String line;
	while ((line = r.readLine()) != null) {
	    total.append(line);
	}

	Log.d(TAG, "Server response:\n" + total.toString());

	if (total.charAt(0) == '[') {
	    return new JSONArray(total.toString());
	} else {
	    return new JSONObject(total.toString());
	}
    }

    protected static ArrayList<String> getErrorMessage(JSONObject jsonObject)
	    throws JSONException {

	JSONArray jsonArray = jsonObject.getJSONArray("errorMessages");
	ArrayList<String> errors = new ArrayList<String>();
	for (int i = 0; i < jsonArray.length(); ++i) {
	    errors.add(jsonArray.get(i).toString());
	}

	return errors;
    }

    /**
     * 
     * @param serverMethod
     * @return can be JSONObject or JSONArray what you need :D
     * @throws StatusCode
     * @throws ClientProtocolException
     * @throws IOException
     * @throws NoStatusLine
     * @throws IllegalStateException
     * @throws EmptyResponse
     * @throws JSONException
     */
    protected Object response(ServerMethod serverMethod, int correctStatusCode)
	    throws StatusCode, ClientProtocolException, IOException,
	    NoStatusLine, IllegalStateException, EmptyResponse, JSONException {
	HttpResponse httpResponse = null;
	try {
	    httpResponse = ServerConnector.execute(serverMethod, session);
	} catch (URISyntaxException e) {
	    return new JSONException(e.getMessage());
	}

	int statusCode = getStatusCode(httpResponse);

	Object parseResponse = parseResponse(httpResponse);

	if (statusCode != correctStatusCode) {
	    throw new StatusCode(statusCode,
		    getErrorMessage((JSONObject) parseResponse));
	}

	return parseResponse;
    }

    protected Object response(ServerMethod serverMethod) throws StatusCode,
	    ClientProtocolException, IOException, NoStatusLine,
	    IllegalStateException, EmptyResponse, JSONException {
	return response(serverMethod, 200);
    }
}
