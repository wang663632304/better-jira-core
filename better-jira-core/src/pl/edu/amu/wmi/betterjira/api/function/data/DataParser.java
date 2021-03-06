package pl.edu.amu.wmi.betterjira.api.function.data;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.ParameterizedType;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;

//TODO User GSON if need more feature :)
public class DataParser {
    public static void parse(Object object, JSONObject jsonObject) {

	Field[] fields = object.getClass().getDeclaredFields();
	JSONObject jsonFields = null;
	for (int i = 0; i < fields.length; ++i) {
	    try {
		Annotation[] annotations = fields[i].getAnnotations();
		for (int z = 0; annotations != null && z < annotations.length; ++z) {
		    System.out.println("Mam" + annotations[z]);
		}

		if (fields[i].getAnnotation(ParseFields.class) != null) {

		    if (jsonFields == null) {
			jsonFields = jsonObject.getJSONObject("fields");
		    }
		    parseField(fields[i], jsonFields, object);
		} else {
		    parseField(fields[i], jsonObject, object);
		}

	    } catch (IllegalArgumentException e) {
		e.printStackTrace();
	    } catch (JSONException e) {
		e.printStackTrace();
	    } catch (IllegalAccessException e) {
		e.printStackTrace();
	    } catch (InstantiationException e) {
		e.printStackTrace();
	    } catch (ParseException e) {
		e.printStackTrace();
	    }
	}
    }

    private static void parseArrayElement(Object object, JSONObject jsonObject,
	    Field field) throws JSONException, IllegalAccessException,
	    InstantiationException {
	JSONArray jsonArray = jsonObject.getJSONArray(field.getName());

	ParameterizedType listType = (ParameterizedType) field.getGenericType();
	Class<?> listTypeClass = (Class<?>) listType.getActualTypeArguments()[0];
	Log.d("PARSER", "In array list type: " + listTypeClass.getName());

	ArrayList<Object> array = new ArrayList<Object>();

	for (int j = 0; j < jsonArray.length(); ++j) {
	    JSONObject jsonObjectInArray = jsonArray.getJSONObject(j);

	    Object objectInArray = listTypeClass.newInstance();
	    parse(objectInArray, jsonObjectInArray);

	    array.add(objectInArray);

	}
	field.set(object, array);
    }

    private static void parseField(Field field, JSONObject jsonObject,
	    Object object) throws JSONException, IllegalArgumentException,
	    IllegalAccessException, InstantiationException, ParseException {

	// If we don't have such field in JSON we skip it
	if (!jsonObject.has(field.getName())) {

	    Log.w("PARSER", "Skipping field: " + field.getName()
		    + "\nin class: " + object.getClass().getSimpleName());
	    return;
	}

	if (jsonObject.isNull(field.getName())) {
	    return;
	}

	String fieldType = field.getType().toString();
	boolean accessible = field.isAccessible();
	field.setAccessible(true);

	Log.d("PARSER", "I got field type: " + field.getType());

	if (fieldType.equals("int")) {
	    int fieldInt = jsonObject.getInt(field.getName());
	    field.setInt(object, fieldInt);

	} else if (fieldType.equals("boolean")) {
	    boolean fieldBoolean = jsonObject.getBoolean(field.getName());
	    field.setBoolean(object, fieldBoolean);

	} else if (fieldType.equals("double")) {
	    double fieldDouble = jsonObject.getDouble(field.getName());
	    field.setDouble(object, fieldDouble);

	} else if (fieldType.equals("long")) {
	    long fieldLong = jsonObject.getLong(field.getName());
	    field.setLong(object, fieldLong);

	} else if (fieldType.contains(String.class.getName())) {
	    String fieldString = jsonObject.getString(field.getName());
	    field.set(object, fieldString);

	} else if (fieldType.contains(ArrayList.class.getName())) {
	    parseArrayElement(object, jsonObject, field);

	} else if (fieldType.contains(Date.class.getName())) {
	    String date = jsonObject.getString(field.getName());
	    field.set(object, parseJiraDate(date));

	} else if (fieldType.contains(JQL.class.getName())) {
	    field.set(object, new JQL(jsonObject.getString(field.getName())));

	} else if (fieldType.contains(Image.class.getName())) {
	    Image image = new Image();

	    // i must parse in this way because i can't name fields like this
	    jsonObject = jsonObject.getJSONObject(field.getName());
	    if (jsonObject.has("48x48")) {
		image.setUrl48x48(jsonObject.getString("48x48"));
	    }
	    if (jsonObject.has("32x32")) {
		image.setUrl32x32(jsonObject.getString("32x32"));
	    }
	    if (jsonObject.has("24x24")) {
		image.setUrl24x24(jsonObject.getString("24x24"));
	    }
	    if (jsonObject.has("16x16")) {
		image.setUrl16x16(jsonObject.getString("16x16"));
	    }
	    field.set(object, image);
	} else {
	    Class<?> type = field.getType();
	    Object subObject = type.newInstance();
	    parse(subObject, jsonObject.getJSONObject(field.getName()));

	    field.set(object, subObject);
	}
	field.setAccessible(accessible);
    }

    /**
     * Jira time format
     * http://stackoverflow.com/questions/2597083/illegal-pattern
     * -character-t-when-parsing-a-date-string-to-java-date
     * 
     * @param date
     * @return parsed date
     * @throws ParseException
     */
    protected static Date parseJiraDate(String date) throws ParseException {
	return new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss.SSSZ").parse(date);
    }
}
