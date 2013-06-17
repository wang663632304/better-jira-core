package pl.edu.amu.wmi.betterjira.api.function.exception;

public class NotFoundException extends Exception {

    private static final long serialVersionUID = -1669846518063174498L;

    public NotFoundException() {
	super(
		"Thing you are asking for, doesn't exist or you don't have permission to see it");
    }

}
