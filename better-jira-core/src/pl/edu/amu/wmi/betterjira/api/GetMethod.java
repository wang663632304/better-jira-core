package pl.edu.amu.wmi.betterjira.api;

import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

public class GetMethod extends ServerMethod {

    private static final int HTTP_BUFFER_SIZE = 8192;

    private String m_methodName;
    private HttpGet m_httpGet;
    private HttpParams m_httpHttpParams;

    public GetMethod(String methodName) {
	this.m_methodName = methodName;
	m_httpGet = new HttpGet();
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
	return m_httpGet;
    }

    @Override
    public void setURL(URL url) throws URISyntaxException {
	m_httpGet.setURI(url.toURI());
    }

    @Override
    public String getFunctionName() {
	return m_methodName;
    }
}
