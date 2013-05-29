package pl.edu.amu.wmi.betterjira.api;

import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.methods.HttpDelete;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class DeleteMethod extends ServerMethod {

    private static final int HTTP_BUFFER_SIZE = 1024;

    private HttpDelete httpDelete;
    private HttpParams m_httpHttpParams;

    public DeleteMethod(String functionName) {
	super(functionName);
	httpDelete = new HttpDelete();
	m_httpHttpParams = new BasicHttpParams();
	HttpConnectionParams.setSoTimeout(m_httpHttpParams, 0);
	HttpConnectionParams.setSocketBufferSize(m_httpHttpParams,
		HTTP_BUFFER_SIZE);
    }

    @Override
    protected HttpParams getHttpParams() {
	return m_httpHttpParams;
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
