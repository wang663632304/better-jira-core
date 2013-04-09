package pl.edu.amu.wmi.betterjira.api;

import java.io.IOException;
import java.net.URI;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.params.HttpParams;

public class GetMethod extends ServerMethod {

    private HttpGet m_httpGet;

    public GetMethod(URI uri) {

	m_httpGet = new HttpGet(uri);
    }

    public void setParams(HttpParams httpParams) {
	m_httpGet.setParams(httpParams);
    }

    @Override
    public HttpResponse call() throws ClientProtocolException, IOException {
	return new DefaultHttpClient().execute(m_httpGet);
    }
}
