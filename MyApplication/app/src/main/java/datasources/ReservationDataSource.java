package datasources;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpResponse;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.http.json.JsonHttpContent;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

import data.Order;
import data.OrderItem;
import data.Reservation;
import interfaces.JsonIF;
import interfaces.SpecifiedObjectType;

public class ReservationDataSource extends NetworkDataSource implements JsonIF, SpecifiedObjectType {
    private static final String API_BASE = "http://perishableapp20160930072857.azurewebsites.net/api/tblReservations";
    private ArrayList<Reservation> reservations = new ArrayList<>();
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
            request = factory.buildGetRequest(new GenericUrl(API_BASE));
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

    public void insertReservationData(Reservation reservation) {
        Gson gson = new Gson();
        String insertData = gson.toJson(reservation);
        InputStream is = null;
        NetHttpTransport transport = null;
        HttpRequest request = null;
        HttpContent content = null;
        HttpResponse resp = null;
        String respCont = "";
        try {
            transport = new NetHttpTransport();
            content = new JsonHttpContent(new JacksonFactory(), insertData);
            HttpRequestFactory factory = transport.createRequestFactory();

            request = factory.buildPostRequest(new GenericUrl(API_BASE), content);
            request.getUrl().putAll(getParams());
            resp = request.execute();
            is = resp.getContent();
            if (is != null) {
                respCont = getJSONFromInputStream(is);
                Log.i("LoginDS", respCont);
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {

                transport.shutdown();

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
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
    public ArrayList<Object> processJsonArray(JSONArray array) throws JSONException {
        ArrayList<Object> objects = new ArrayList<>();
        for (int i = 0; i < array.length(); i++) {
            objects.add((String) processJsonObject(array.getJSONObject(i)));
        }
        return objects;
    }

    @Override
    public Object processJsonObject(JSONObject jo) throws JSONException {
        Reservation reservation = new Reservation();
        if (jo.has("Id") && !jo.isNull("Id")) {
            reservation.setId(jo.getInt("Id"));
        }
        if (jo.has("isActive") && !jo.isNull("isActive")) {
            reservation.setActive(jo.getBoolean("isActive"));
        }
        if (jo.has("DateTime") && !jo.isNull("DateTime")) {
            reservation.setDateTime(jo.getString("DateTime"));
        }
        if (jo.has("fk_CharityID") && !jo.isNull("fk_CharityID")) {
            reservation.setFk_CharityID(jo.getInt("fk_CharityID"));
        }
        if (jo.has("fk_OrderID") && !jo.isNull("fk_OrderID")) {
            reservation.setFk_OrderID(jo.getInt("fk_OrderID"));
        }
        return reservation;
    }

    @Override
    public List<Object> getData() {
        return null;
    }
    @Override
    public void addSpecifiedObjectTypeLocally(ArrayList<Object> objects) {
        for (Object o : objects) {
            addSpecifiedObjectTypeLocally(o);
        }
    }

    @Override
    public void addSpecifiedObjectTypeLocally(Object object) {
        if (object instanceof Reservation) {
            reservations.add((Reservation) object);
        }
    }
}
