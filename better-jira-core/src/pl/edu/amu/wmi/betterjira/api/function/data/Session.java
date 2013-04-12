package pl.edu.amu.wmi.betterjira.api.function.data;

public class Session {
    private String name;
    private String value;

    public Session(String name, String value) {
	this.name = name;
	this.value = value;
    }

    public final String getName() {
	return name;
    }

    public final void setName(String name) {
	this.name = name;
    }

    public final String getValue() {
	return value;
    }

    public final void setValue(String value) {
	this.value = value;
    }

}
