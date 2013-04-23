package pl.edu.amu.wmi.betterjira.api;

import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.HttpParams;

public class DeleteMethod extends ServerMethod {
    private HttpDelete httpDelete;

    public DeleteMethod(String functionName) {
	super(functionName);
    }

    @Override
    protected HttpParams getHttpParams() {
	return null;
    }

    @Override
    protected HttpRequestBase getRequest() {
	return httpDelete;
    }

    @Override
    public void setURL(URL url) throws URISyntaxException {
	httpDelete.setURI(url.toURI());
    }
}
