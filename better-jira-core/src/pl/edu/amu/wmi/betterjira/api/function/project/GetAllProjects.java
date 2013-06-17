package pl.edu.amu.wmi.betterjira.api.function.project;

import java.io.IOException;
import java.util.ArrayList;

import org.apache.http.client.ClientProtocolException;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import pl.edu.amu.wmi.betterjira.api.GetMethod;
import pl.edu.amu.wmi.betterjira.api.function.Function;
import pl.edu.amu.wmi.betterjira.api.function.data.DataParser;
import pl.edu.amu.wmi.betterjira.api.function.data.Project;
import pl.edu.amu.wmi.betterjira.api.function.data.Session;
import pl.edu.amu.wmi.betterjira.api.function.exception.BadResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.EmptyResponse;
import pl.edu.amu.wmi.betterjira.api.function.exception.NoStatusLine;
import pl.edu.amu.wmi.betterjira.api.function.exception.StatusCode;

public class GetAllProjects extends Function {

    public GetAllProjects(Session session) {
	super(session);
    }

    @Override
    public String getFunctionName() {
	return "/rest/api/2/project";
    }

    public ArrayList<Project> getAllVisibleProjects() throws BadResponse,
	    ClientProtocolException, IllegalStateException, IOException,
	    NoStatusLine, EmptyResponse, JSONException {
	GetMethod getMethod = new GetMethod(getFunctionName());
	try {
	    JSONArray response = (JSONArray) response(getMethod);

	    ArrayList<Project> projects = new ArrayList<Project>();

	    for (int i = 0; i < response.length(); ++i) {

		JSONObject jsonObject = response.getJSONObject(i);

		Project project = new Project();

		DataParser.parse(project, jsonObject);
		projects.add(project);
	    }
	    return projects;

	} catch (StatusCode e) {
	    e.printStackTrace();
	    switch (e.getStatusCode()) {
	    case 500:
		throw new Error("Server error\n" + e.getError(0));
	    default:
		throw new BadResponse("Server returns unknown status: "
			+ e.getStatusCode());
	    }
	}
    }
}
