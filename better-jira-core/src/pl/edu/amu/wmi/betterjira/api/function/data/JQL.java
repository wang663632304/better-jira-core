package pl.edu.amu.wmi.betterjira.api.function.data;

public class JQL {

    private String command;

    public JQL(String command) {
	this.setCommand(command);
    }

    // TODO parsing and other cool stuff

    public String getCommand() {
	return command;
    }

    public void setCommand(String command) {
	this.command = command;
    }
}
