package pl.edu.amu.wmi.betterjiracore.test;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class ParseJsonDictionaryCategoryList {

    public Object parse(String httpReqTwo)
	    throws JSONException {
	JSONArray jsonArray;

	jsonArray = new JSONArray(httpReqTwo);

	JSONObject errorInfo = jsonArray.optJSONObject(0);
	if (errorInfo != null) {
	    String errorString = errorInfo.opt("status").toString();
	    if (errorString != null) {
		int errorCode = Integer.parseInt(errorString);
		switch (errorCode) {
		case 0:
		    // result set empty
		    return null;

		default:
		    break;
		}
	    }
	}

	jsonArray = jsonArray.getJSONArray(0);
	int length = jsonArray.length();


	for (Integer i = 0; i < length; i++) {
	    JSONArray catJsonArray = jsonArray.getJSONArray(i);
	    Integer id = catJsonArray.getInt(0);
	    String name = catJsonArray.getString(1);
	    Integer count = catJsonArray.getInt(2);
	    if (count > 0) { // dont add empty categories
	    }
	}

	return null;
    }

}
