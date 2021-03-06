package pl.edu.amu.wmi.betterjira.api.function.data;

import java.util.Date;

public class Issue {
    private int id;
    private String key;
    @ParseFields
    private Date created;
    @ParseFields
    private Date updated;
    @ParseFields
    private String description;
    @ParseFields
    private long workratio;
    @ParseFields
    private long aggregatetimeestimate;
    @ParseFields
    private long timeestimate;

    @ParseFields
    IssueType issuetype;
    @ParseFields
    User reporter;
    @ParseFields
    Priority priority;
    @ParseFields
    Status status;
    @ParseFields
    User assignee;
    @ParseFields
    Project project;

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

    public final String getDescription() {
	return description;
    }

    public final void setDescription(String description) {
	this.description = description;
    }

    public final long getWorkratio() {
	return workratio;
    }

    public final void setWorkratio(long workratio) {
	this.workratio = workratio;
    }

    public final long getAggregatetimeestimate() {
	return aggregatetimeestimate;
    }

    public final void setAggregatetimeestimate(long aggregatetimeestimate) {
	this.aggregatetimeestimate = aggregatetimeestimate;
    }

    public final long getTimeestimate() {
	return timeestimate;
    }

    public final void setTimeestimate(long timeestimate) {
	this.timeestimate = timeestimate;
    }

    public int getId() {
	return id;
    }

    public void setId(int id) {
	this.id = id;
    }

    public String getKey() {
	return key;
    }

    public void setKey(String key) {
	this.key = key;
    }

    public IssueType getIssuetype() {
        return issuetype;
    }

    public void setIssuetype(IssueType issuetype) {
        this.issuetype = issuetype;
    }

    public User getReporter() {
        return reporter;
    }

    public void setReporter(User reporter) {
        this.reporter = reporter;
    }

    public Priority getPriority() {
        return priority;
    }

    public void setPriority(Priority priority) {
        this.priority = priority;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    public User getAssignee() {
        return assignee;
    }

    public void setAssignee(User assignee) {
        this.assignee = assignee;
    }

    public Project getProject() {
        return project;
    }

    public void setProject(Project project) {
        this.project = project;
    }
}
