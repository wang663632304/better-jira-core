package pl.edu.amu.wmi.betterjira.api.function.data;

import java.util.Date;

public class Comment {

    private int id;
    private User author;
    private String body;
    private User updateAuthor;
    private Date created;
    private Date updated;

    public final int getId() {
	return id;
    }

    public final void setId(int id) {
	this.id = id;
    }

    public final User getAuthor() {
	return author;
    }

    public final void setAuthor(User author) {
	this.author = author;
    }

    public final String getBody() {
	return body;
    }

    public final void setBody(String body) {
	this.body = body;
    }

    public final User getUpdateAuthor() {
	return updateAuthor;
    }

    public final void setUpdateAuthor(User updateAuthor) {
	this.updateAuthor = updateAuthor;
    }

    public final Date getCreated() {
	return created;
    }

    public final void setCreated(Date created) {
	this.created = created;
    }

    public final Date getUpdated() {
	return updated;
    }

    public final void setUpdated(Date updated) {
	this.updated = updated;
    }

}
