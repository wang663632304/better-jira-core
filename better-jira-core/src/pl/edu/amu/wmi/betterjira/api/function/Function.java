package pl.edu.amu.wmi.betterjira.api.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;

public abstract class Function {

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
	StringBuilder total = new StringBuilder((int) entity.getContentLength());
	String line;
	while ((line = r.readLine()) != null) {
	    total.append(line);
	}

	return new JSONObject(total.toString());
    }
}
