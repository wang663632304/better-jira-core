package pl.edu.amu.wmi.betterjira.api.function.project;

import java.io.IOException;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.GetMethod;
import pl.edu.amu.wmi.betterjira.api.function.Function;
import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.Project;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.NotFoundException;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class GetProject extends Function {

    public GetProject(Session session) {
	super(session);
    }

    @Override
    public String getFunctionName() {
	return "/rest/api/2/project/";
    }

    public Project getProject(String projectKey)
	    throws ClientProtocolException, IllegalStateException, IOException,
	    NoStatusLine, EmptyResponse, JSONException, NotFoundException {

	StringBuilder builder = new StringBuilder(getFunctionName());
	builder.append(projectKey);

	GetMethod getMethod = new GetMethod(builder.toString());

	JSONObject response;
	try {
	    response = (JSONObject) response(getMethod);
	} catch (StatusCode e) {
	    e.printStackTrace();
	    throw new NotFoundException();
	}

	Project project = new Project();
	DataParser.parse(project, response);

	return project;
    }

    /**
     * 
     * @param project
     *            object witch will be updated (it overrides all fields)
     * @return updated project object
     * @throws ClientProtocolException
     * @throws IllegalStateException
     * @throws StatusCode
     * @throws IOException
     * @throws NoStatusLine
     * @throws EmptyResponse
     * @throws JSONException
     * @throws NotFoundException
     */
    public Project updateProject(Project project)
	    throws ClientProtocolException, IllegalStateException, IOException,
	    NoStatusLine, EmptyResponse, JSONException, NotFoundException {

	if (project.getKey().length() < 1) {
	    throw new RuntimeException("Project key can't be empty");
	}

	StringBuilder builder = new StringBuilder(getFunctionName());
	builder.append(project.getKey());

	GetMethod getMethod = new GetMethod(builder.toString());

	JSONObject response;
	try {
	    response = (JSONObject) response(getMethod);
	} catch (StatusCode e) {
	    e.printStackTrace();
	    throw new NotFoundException();
	}

	DataParser.parse(project, response);

	return project;
    }
}
