package pl.edu.amu.wmi.betterjira.api.function.data;

public class Priority {
    private String name;
    private int id;
    private String iconUrl;
    public final String getName() {
        return name;
    }
    public final void setName(String name) {
        this.name = name;
    }
    public final int getId() {
        return id;
    }
    public final void setId(int id) {
        this.id = id;
    }
    public final String getIconUrl() {
        return iconUrl;
    }
    public final void setIconUrl(String iconUrl) {
        this.iconUrl = iconUrl;
    }
    
}
