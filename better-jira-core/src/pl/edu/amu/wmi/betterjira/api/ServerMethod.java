package pl.edu.amu.wmi.betterjira.api;

import java.io.IOException;
import java.net.HttpURLConnection;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.HttpParams;

public abstract class ServerMethod {
    private long cacheTime;
    private String functionName;

    public ServerMethod(String functionName) {
	this.functionName = functionName;
    }

    protected HttpURLConnection openConnection(URL url) throws IOException {
	return (HttpURLConnection) url.openConnection();
    }

    protected abstract HttpParams getHttpParams();

    protected abstract HttpRequestBase getRequest();

    public String getFunctionName() {
	return functionName;
    }

    public abstract void setURL(URL url) throws URISyntaxException;

}
