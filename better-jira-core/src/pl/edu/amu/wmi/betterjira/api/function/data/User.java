package pl.edu.amu.wmi.betterjira.api.function.data;

public class User {
    private String name;
    private String emailAddress;
    private String displayName;
    private Image avatarUrls;

    public final String getName() {
	return name;
    }

    public final void setName(String name) {
	this.name = name;
    }

    public final String getEmailAddress() {
	return emailAddress;
    }

    public final void setEmailAddress(String emailAddress) {
	this.emailAddress = emailAddress;
    }

    public final String getDisplayName() {
	return displayName;
    }

    public final void setDisplayName(String displayName) {
	this.displayName = displayName;
    }

    public Image getAvatarUrls() {
	return avatarUrls;
    }

    public void setAvatarUrls(Image avatarUrls) {
	this.avatarUrls = avatarUrls;
    }

}
