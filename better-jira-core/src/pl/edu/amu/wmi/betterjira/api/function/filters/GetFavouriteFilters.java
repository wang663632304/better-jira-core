package pl.edu.amu.wmi.betterjira.api.function.filters;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.GetMethod;
import pl.edu.amu.wmi.betterjira.api.function.Function;
import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.Filter;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class GetFavouriteFilters extends Function {

    public GetFavouriteFilters(Session session) {
	super(session);
    }

    @Override
    public String getFunctionName() {
	return "/rest/api/2/filter/favourite";
    }

    public ArrayList<Filter> getFilters() throws BadResponse {
	GetMethod getMethod = new GetMethod(getFunctionName());
	try {
	    JSONArray response = (JSONArray) response(getMethod);

	    ArrayList<Filter> filters = new ArrayList<Filter>();

	    for (int i = 0; i < response.length(); ++i) {

		JSONObject jsonObject = response.getJSONObject(i);

		Filter filter = new Filter();

		DataParser.parse(filter, jsonObject);
		filters.add(filter);
	    }
	    return filters;

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
		throw new Error("Server error\n" + e.getError(0));
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	}
	return null;
    }
}
