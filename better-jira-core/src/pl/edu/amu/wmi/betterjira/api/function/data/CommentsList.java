package pl.edu.amu.wmi.betterjira.api.function.data;

import java.util.ArrayList;

public class CommentsList {

    private ArrayList<Comment> comments;
    private int maxResults;
    private int total;
    private int startAt;

    public Comment getComment(int index) {
	return comments.get(index);
    }

    public final int getMaxResults() {
	return maxResults;
    }

    public final void setMaxResults(int maxResults) {
	this.maxResults = maxResults;
    }

    public final int getTotal() {
	return total;
    }

    public final void setTotal(int total) {
	this.total = total;
    }

    public final int getStartAt() {
	return startAt;
    }

    public final void setStartAt(int startAt) {
	this.startAt = startAt;
    }

}
