package interfaces;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

/**
 * Created by Jeremy on 06/12/2015.
 */
public interface JsonIF
{
    public abstract ArrayList<Object> processJsonArray(JSONArray array) throws JSONException;
    public abstract Object processJsonObject(JSONObject jObject) throws JSONException;
}
