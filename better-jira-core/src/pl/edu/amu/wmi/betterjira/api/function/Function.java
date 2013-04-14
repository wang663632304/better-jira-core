package pl.edu.amu.wmi.betterjira.api.function;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import android.util.Log;

public abstract class Function {

    private static String TAG = "WEB";

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

    /**
     * Jira time format
     * http://stackoverflow.com/questions/2597083/illegal-pattern
     * -character-t-when-parsing-a-date-string-to-java-date
     * 
     * @param date
     * @return parsed date
     * @throws ParseException
     */
    protected static Date parseJiraDate(String date) throws ParseException {
	return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse(date);
    }
}
