package pl.edu.amu.wmi.betterjira.api;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;

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

    public static HttpResponse execute(ServerMethod method)
	    throws ClientProtocolException, IOException {
	return method.call();
    }
}
