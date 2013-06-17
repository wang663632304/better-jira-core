package pl.edu.amu.wmi.betterjira.api.function.data;

public class Project {
    private int id;
    private String key;
    private String name;
    private String description;
    private User lead;
    private String url;
    private String email;
    private Image avatarUrls;

    public final int getId() {
	return id;
    }

    public final void setId(int id) {
	this.id = id;
    }

    public final String getKey() {
	return key;
    }

    public final void setKey(String key) {
	this.key = key;
    }

    public final String getName() {
	return name;
    }

    public final void setName(String name) {
	this.name = name;
    }

    public String getDescription() {
	return description;
    }

    public void setDescription(String description) {
	this.description = description;
    }

    public User getLead() {
	return lead;
    }

    public void setLead(User lead) {
	this.lead = lead;
    }

    public String getUrl() {
	return url;
    }

    public void setUrl(String url) {
	this.url = url;
    }

    public String getEmail() {
	return email;
    }

    public void setEmail(String email) {
	this.email = email;
    }

    public Image getAvatarUrls() {
	return avatarUrls;
    }

    public void setAvatarUrls(Image avatarUrls) {
	this.avatarUrls = avatarUrls;
    }

}
