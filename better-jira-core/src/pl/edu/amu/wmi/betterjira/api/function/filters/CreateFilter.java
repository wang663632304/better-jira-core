package pl.edu.amu.wmi.betterjira.api.function.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.InvalidParameterException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.PostMethod;
import pl.edu.amu.wmi.betterjira.api.function.Function;
import pl.edu.amu.wmi.betterjira.api.function.data.Filter;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class CreateFilter extends Function {

    public CreateFilter(Session session) {
	super(session);
    }

    @Override
    public String getFunctionName() {
	return "/rest/api/2/filter";
    }

    /**
     * 
     * @param filter
     *            partly filled filter
     * @return filled filter
     * @throws BadResponse
     * @throws JSONException
     * @throws EmptyResponse
     * @throws NoStatusLine
     * @throws IOException
     * @throws IllegalStateException
     * @throws ClientProtocolException
     */
    public Filter createFilter(Filter filter) throws BadResponse,
	    JSONException, ClientProtocolException, IllegalStateException, IOException, NoStatusLine, EmptyResponse {
	PostMethod postMethod = new PostMethod(getFunctionName());
	try {
	    JSONObject objectFilter = new JSONObject();
	    objectFilter.put("name", filter.getName());
	    objectFilter.put("description", filter.getDescription());
	    objectFilter.put("jql", filter.getJql().getCommand());
	    objectFilter.put("favourite", filter.isFavourite());

	    postMethod.setEntity(objectFilter);
	    response(postMethod);

	    return filter;

	} catch (StatusCode e) {
	    e.printStackTrace();
	    switch (e.getStatusCode()) {
	    case 400:
		throw new InvalidParameterException("Invalid input\n"
			+ e.getError(0));
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	}
    }
}
