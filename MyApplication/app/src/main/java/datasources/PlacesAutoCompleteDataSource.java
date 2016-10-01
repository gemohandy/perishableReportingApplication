package datasources;

import android.content.Context;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import interfaces.JsonIF;
import interfaces.SpecifiedObjectType;
import values.keys;

/**
 * Created by Jeremy on 15/01/2016.
 */
public class PlacesAutoCompleteDataSource extends NetworkDataSource implements JsonIF, SpecifiedObjectType, keys.PlacesPredictions.localKeySetIF {
    private static final String PLACES_API_BASE = "https://maps.googleapis.com/maps/api/place/autocomplete/json?";
    private Context context = null;
    private static final String TAG = "PlacesAutoComplete";
    private ArrayList<String> suggestions = new ArrayList<>();

    public PlacesAutoCompleteDataSource(Context context) {
        this.context = context;
    }

    @Override
    public ArrayList<Object> getHttpGETInputStream() throws IOException {
        ArrayList<Object> objects = new ArrayList<>();
        InputStream is = null;

        NetHttpTransport transport = null;
        String string = "";
        HttpRequest request = null;
        HttpResponse resp = null;
        try {
            transport = new NetHttpTransport();
            HttpRequestFactory factory = transport.createRequestFactory();
            request = factory.buildGetRequest(new GenericUrl(PLACES_API_BASE));
            request.getUrl().putAll(getParams());
            resp = request.execute();
            is = resp.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    string = getJSONFromInputStream(is);
                    JSONObject json = new JSONObject(string);
                    if (json.has("predictions")) {
                        objects = processJsonArray(json.getJSONArray("predictions"));
                        addSpecifiedObjectTypeLocally(objects);
                    }
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        transport.shutdown();
        return objects;
    }

    @Override
    public ArrayList<Object> processJsonArray(JSONArray array) throws JSONException {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            objects.add((String) processJsonObject(array.getJSONObject(i)));
        }
        return objects;
    }

    @Override
    public Object processJsonObject(JSONObject jo) throws JSONException {
        String pred = "";
        if (jo.has("description") && !jo.isNull("description"))
            pred = jo.getString("description");
        return pred;
    }

    @Override
    public List<Object> getData() {
        return null;
    }

    protected String getJSONFromInputStream(InputStream is) {
        if (is == null)
            throw new NullPointerException();
        //instantiates a reader with max size
        BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8 * 1024);

        StringBuilder sb = new StringBuilder();

        try {
            //reads the response line by line (and separates by a line-break)
            String line;
            while ((line = reader.readLine()) != null) {
                sb.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                //closes the inputStream
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return sb.toString();
    }

    @Override
    public void addSpecifiedObjectTypeLocally(ArrayList<Object> objects) {
        for (Object o : objects) {
            if (o instanceof String) {
                suggestions.add((String) o);
            }
        }
    }

    @Override
    public void addSpecifiedObjectTypeLocally(Object object) {
        if (object instanceof String)
            suggestions.add((String) object);
    }

    @Override
    public void makeUnchangeableKeys() {
        getParams().put(keys.PlacesPredictions.KEY.getKey(), keys.PlacesPredictions.KEY.getValue());
    }

    @Override
    public void makeLocationKey(double lat, double lon) {
        getParams().put(keys.PlacesPredictions.LOCATION.getKey(), lat + "," + lon);
    }

    @Override
    public void makeRadiusKey(int radInMeters) {
        getParams().put(keys.PlacesPredictions.RADIUS.getKey(), Integer.toString(radInMeters));
    }

    @Override
    public void makeLanguageKey(String value) {
        getParams().put(keys.PlacesPredictions.LANGUAGE.getKey(), value);
    }

    @Override
    public void makeInputKey(String value) {
        getParams().put(keys.PlacesPredictions.INPUT.getKey(), value);
    }

    @Override
    public void makeGeocodeKey() {
        getParams().put(keys.PlacesPredictions.TYPES.getKey(), keys.PlacesPredictions.TYPES.getValue());
    }

    @Override
    public void makeSensorKey() {
        getParams().put(keys.PlacesPredictions.SENSOR.getKey(), keys.PlacesPredictions.SENSOR.getValue());
    }

    public ArrayList<String> getSuggestions() {
        return suggestions;
    }

    public void setSuggestions(ArrayList<String> suggestions) {
        this.suggestions = suggestions;
    }
}
