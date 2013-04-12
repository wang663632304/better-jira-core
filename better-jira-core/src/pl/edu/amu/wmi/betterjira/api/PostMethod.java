package pl.edu.amu.wmi.betterjira.api;

import java.io.UnsupportedEncodingException;
import java.net.URISyntaxException;
import java.net.URL;

import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpRequestBase;
import org.apache.http.entity.StringEntity;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;
import org.json.JSONObject;

import android.util.Log;

public class PostMethod extends ServerMethod {

    private HttpPost m_httpPost;
    private String m_functionName;
    private BasicHttpParams m_httpHttpParams;
    private static final int HTTP_BUFFER_SIZE = 8192;

    public PostMethod(String functionName) {
	this.m_functionName = functionName;
	m_httpPost = new HttpPost();
	m_httpHttpParams = new BasicHttpParams();
	HttpConnectionParams.setSoTimeout(m_httpHttpParams, 0);
	HttpConnectionParams.setSocketBufferSize(m_httpHttpParams,
		HTTP_BUFFER_SIZE);
    }

    @Override
    protected HttpParams getHttpParams() {
	return m_httpHttpParams;
    }

    public void setEntity(JSONObject jsonObject)
	    throws UnsupportedEncodingException {
	m_httpPost.setHeader("Content-type", "application/json");

	Log.d(this.toString(), "Setting entity:\n" + jsonObject.toString());
	m_httpPost.setEntity(new StringEntity(jsonObject.toString()));
    }

    @Override
    protected HttpRequestBase getRequest() {
	return m_httpPost;
    }

    @Override
    public void setURL(URL url) throws URISyntaxException {
	m_httpPost.setURI(url.toURI());
    }

    @Override
    public String getFunctionName() {
	return m_functionName;
    }

}
