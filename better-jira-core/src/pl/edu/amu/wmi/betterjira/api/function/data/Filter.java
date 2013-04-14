package pl.edu.amu.wmi.betterjira.api.function.data;

public class Filter {
    /*-
     * Example JSON filter
     *     {
            "self": "http://www.example.com/jira/rest/api/2/filter/10000",
            "id": "10000",
            "name": "All Open Bugs",
            "description": "A sample filter description",
            "owner": {
                "self": "http://www.example.com/jira/rest/api/2/user?username=fred",
                "name": "fred",
                "avatarUrls": {
                    "16x16": "http://www.example.com/jira/secure/useravatar?size=small&ownerId=fred",
                    "48x48": "http://www.example.com/jira/secure/useravatar?size=large&ownerId=fred"
                },
                "displayName": "Fred F. User",
                "active": false
            },
            "jql": "type = Bug and resolution is empty",
            "viewUrl": "http://www.example.com/jira/secure/IssueNavigator.jspa?mode=hide&requestId=10000",
            "searchUrl": "http://www.example.com/jira/rest/api/2/search?jql=type%20%3D%20Bug%20and%20resolutino%20is%20empty",
            "favourite": true,
            "sharePermissions": [],
            "subscriptions": {
                "size": 0,
                "items": []
            }
        },
     */

    private int id;
    private String name;
    private String description;
    private User owner;
    private JQL jql;
    private boolean favourite;

    public final int getId() {
	return id;
    }

    public final void setId(int id) {
	this.id = id;
    }

    public final String getName() {
	return name;
    }

    public final void setName(String name) {
	this.name = name;
    }

    public final String getDescription() {
	return description;
    }

    public final void setDescription(String description) {
	this.description = description;
    }

    public final User getOwner() {
	return owner;
    }

    public final void setOwner(User owner) {
	this.owner = owner;
    }

    public final JQL getJql() {
	return jql;
    }

    public final void setJql(JQL jql) {
	this.jql = jql;
    }

    public final boolean isFavourite() {
	return favourite;
    }

    public final void setFavourite(boolean favourite) {
	this.favourite = favourite;
    }

}
