package pl.edu.amu.wmi.betterjira.api.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
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

import pl.edu.amu.wmi.betterjira.api.ServerConnector;
import pl.edu.amu.wmi.betterjira.api.ServerMethod;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;
import android.util.Log;

public abstract class Function {

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

    protected static JSONObject parseResponse(HttpResponse httpResponse)
	    throws EmptyResponse, IllegalStateException, IOException,
	    JSONException {

	HttpEntity entity = httpResponse.getEntity();
	if (entity == null) {
	    throw new EmptyResponse();
	}

	InputStream content = entity.getContent();
	BufferedReader r = new BufferedReader(new InputStreamReader(content));
	StringBuilder total = new StringBuilder(128);
	String line;
	while ((line = r.readLine()) != null) {
	    total.append(line);
	}

	Log.d(TAG, "Server response:\n" + total.toString());

	return new JSONObject(total.toString());
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

    protected JSONObject response(ServerMethod serverMethod) throws StatusCode,
	    ClientProtocolException, IOException, NoStatusLine,
	    IllegalStateException, EmptyResponse, JSONException {
	HttpResponse httpResponse = ServerConnector.execute(serverMethod,
		session);

	int statusCode = getStatusCode(httpResponse);

	JSONObject parseResponse = parseResponse(httpResponse);

	if (statusCode != 200) {
	    throw new StatusCode(statusCode, getErrorMessage(parseResponse));
	}

	return parseResponse;
    }
}
