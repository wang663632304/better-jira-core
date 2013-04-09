package pl.edu.amu.wmi.betterjira.api.methods;

import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpConnectionParams;
import org.apache.http.params.HttpParams;

import pl.edu.amu.wmi.betterjira.api.GetMethod;

public class BasicAuthentication extends GetMethod {
    private static final int HTTP_BUFFER_SIZE = 8192;

    public BasicAuthentication(URI uri) throws URISyntaxException {
	super(uri);

	BasicHttpParams basicHttpParams = new BasicHttpParams();
	HttpConnectionParams.setSoTimeout(basicHttpParams, 0);
	HttpConnectionParams.setSocketBufferSize(basicHttpParams,
		HTTP_BUFFER_SIZE);

	setParams(basicHttpParams);

    }

}
