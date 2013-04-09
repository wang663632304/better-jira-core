package pl.edu.amu.wmi.betterjira.api;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

public abstract class ServerMethod {
    private long m_cacheTime;

    abstract public HttpResponse call() throws ClientProtocolException, IOException;
}
