package datasources;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import data.Charity;
import data.Place;
import interfaces.JsonIF;
import interfaces.SpecifiedObjectType;

public class CharityDatasource extends NetworkDataSource implements JsonIF, SpecifiedObjectType {
    private static final String API_BASE = "http://perishableapp20160930072857.azurewebsites.net/api/tblCharity";
    private ArrayList<Charity> places = new ArrayList<>();
    
    @Override
    public ArrayList<Object> getHttpGETInputStream() throws IOException {
        return null;
    }

    @Override
    public ArrayList<Object> processJsonArray(JSONArray array) throws JSONException {
        return null;
    }

    @Override
    public Object processJsonObject(JSONObject jo) throws JSONException {
        return null;
    }

    @Override
    public List<Object> getData() {
        return null;
    }

    @Override
    public void addSpecifiedObjectTypeLocally(ArrayList<Object> objects) {

    }

    @Override
    public void addSpecifiedObjectTypeLocally(Object object) {

    }
}
