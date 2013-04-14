package pl.edu.amu.wmi.betterjira.api.function.exception;

public class InvalidJQLCommand extends Exception {

    private static final long serialVersionUID = 4865788200202046641L;
    private String errorMessage;

    public InvalidJQLCommand(String JQL, String errorMessage) {
	super(JQL);
	this.setErrorMessage(errorMessage);
    }

    public String getErrorMessage() {
	return errorMessage;
    }

    public void setErrorMessage(String errorMessage) {
	this.errorMessage = errorMessage;
    }
}
