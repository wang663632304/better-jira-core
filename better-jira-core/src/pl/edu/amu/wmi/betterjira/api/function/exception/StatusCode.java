package pl.edu.amu.wmi.betterjira.api.function.exception;

import java.util.ArrayList;

public class StatusCode extends Exception {

    private static final long serialVersionUID = -3572095626292243290L;
    private int m_statusCode;
    private ArrayList<String> m_errors;

    public StatusCode(int statusCode, ArrayList<String> errors) {
	super("Server returns status code: " + statusCode);
	m_statusCode = statusCode;
	this.m_errors = errors;
    }

    public int getStatusCode() {
	return m_statusCode;
    }

    public String getError(int index) {

	if (m_errors.size() <= index) {
	    return "SERVER_ERRORS_EMPTY";
	} else {
	    return m_errors.get(index);
	}
    }
}
