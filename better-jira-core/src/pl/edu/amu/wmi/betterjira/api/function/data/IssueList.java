package pl.edu.amu.wmi.betterjira.api.function.data;

import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class IssueList {

    private int total;
    private int maxResults;
    private int startAt;
    private ArrayList<Issue> issues = new ArrayList<Issue>();

    private JSONObject m_jsonObjectRequest;

    public void setRequest(JSONObject jsonObjectRequest) {
	if (m_jsonObjectRequest != null) {
	    throw new IllegalArgumentException("You can set it only once");
	}
	this.m_jsonObjectRequest = jsonObjectRequest;
    }

    public int getTotal() {
	return total;
    }

    public void setTotal(int total) {
	this.total = total;
    }

    public int getMaxResults() {
	return maxResults;
    }

    public void setMaxResults(int maxResults) {
	this.maxResults = maxResults;
    }

    public int getStartAt() {
	return startAt;
    }

    public void setStartAt(int startAt) {
	this.startAt = startAt;
    }

    public Issue getIssue(int index) {
	return issues.get(index);
    }

}
