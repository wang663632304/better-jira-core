package pl.edu.amu.wmi.betterjira.api;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.URL;
import java.security.KeyStore;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.conn.ssl.SSLSocketFactory;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.HttpParams;

/**
 * <b>Structure of the REST URIs</b><br>
 * 
 * JIRA's REST APIs provide access to resources (data entities) via URI paths.
 * To use a REST API, your application will make an HTTP request and parse the
 * response. The JIRA REST API uses JSON as its communication format, and the
 * standard HTTP methods like GET, PUT, POST and DELETE (see API descriptions
 * below for which methods are available for each resource). URIs for JIRA's
 * REST API resource have the following structure: <br>
 * <br>
 * http://host:port/context/rest/api-name/api-version/resource-name Currently
 * there are two API names available, which will be discussed further below: <br>
 * <br>
 * auth - for authentication-related operations, and api - for everything else.
 * The current API version is 2. However, there is also a symbolic version,
 * called latest, which resolves to the latest version supported by the given
 * JIRA instance. As an example, if you wanted to retrieve the JSON
 * representation of issue JRA-9 from Atlassian's public issue tracker, you
 * would access: <br>
 * <br>
 * https://jira.atlassian.com/rest/api/latest/issue/JRA-9 There is a WADL
 * document that contains the documentation for each resource in the JIRA REST
 * API. It is available here.
 * 
 * 
 * @author Dawid Drozd
 * @see http://docs.atlassian.com/jira/REST/latest/
 */
public class ServerConnector {
    public static final String API_VERSION = "2";
    public static final String API_LATEST = "latest";
    private static URL m_serverURL;

    public static int HTTP_PORT = 80;
    public static int HTTPS_PORT = 443;

    public static HttpResponse execute(ServerMethod method)
	    throws ClientProtocolException, IOException {

	HttpClient httpClient = getNewHttpClient(method.getHttpParams());

	StringBuilder stringBuilder = new StringBuilder(m_serverURL.toString());
	stringBuilder.append(method.getFunctionName());

	try {
	    method.setURL(new URL(stringBuilder.toString()));
	} catch (URISyntaxException e) {
	    e.printStackTrace();
	}
	return httpClient.execute(method.getRequest());
    }

    private static HttpClient getNewHttpClient(HttpParams httpParams) {
	try {
	    KeyStore trustStore = KeyStore.getInstance(KeyStore
		    .getDefaultType());
	    trustStore.load(null, null);

	    SSLSocketFactory sf = new MySSLSocketFactory(trustStore);
	    sf.setHostnameVerifier(SSLSocketFactory.ALLOW_ALL_HOSTNAME_VERIFIER);

	    SchemeRegistry registry = new SchemeRegistry();
	    registry.register(new Scheme("http", PlainSocketFactory
		    .getSocketFactory(), HTTP_PORT));
	    registry.register(new Scheme("https", sf, HTTPS_PORT));

	    ClientConnectionManager ccm = new ThreadSafeClientConnManager(
		    httpParams, registry);

	    return new DefaultHttpClient(ccm, httpParams);
	} catch (Exception e) {
	    e.printStackTrace();
	    return new DefaultHttpClient();
	}
    }

    public static URL getServerURL() {
	return m_serverURL;
    }

    public static void setServerURL(URL m_serverURL) {
	ServerConnector.m_serverURL = m_serverURL;
    }
}
