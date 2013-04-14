package pl.edu.amu.wmi.betterjira.api.function.data;

import java.util.ArrayList;

import org.json.JSONObject;

public class IssueList {

    private int total;
    private int maxResults;
    private int startAt;
    private ArrayList<Issue> issues = new ArrayList<Issue>();

    private JSONObject jsonObjectRequest;

    public void setRequest(JSONObject jsonObjectRequest) {
	if (jsonObjectRequest != null) {
	    throw new IllegalArgumentException("You can set it only once");
	}
	this.jsonObjectRequest = jsonObjectRequest;
    }

    public JSONObject getRequest() {
	return jsonObjectRequest;
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
