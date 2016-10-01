package datasources;

import android.util.Log;

import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpContent;
import com.google.api.client.http.HttpMethods;
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
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.List;

import data.Login;
import interfaces.JsonIF;
import interfaces.SpecifiedObjectType;

public class LoginDataSource extends NetworkDataSource implements JsonIF, SpecifiedObjectType {
    private static final String API_BASE = "http://perishableapp20160930072857.azurewebsites.net/api/tblLogins";

    public LoginDataSource() {
    }

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

    public int insertLoginData(Login login) {
        Gson gson = new Gson();
        String insertData = gson.toJson(login);
        InputStream is = null;
        NetHttpTransport transport = null;
        HttpRequest request = null;
        HttpContent content = null;
        HttpResponse resp = null;
        String respCont = "";
        int id = -1;
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
                JSONObject jo = new JSONObject(respCont);
                if (jo.has("Id") && !jo.isNull("Id")) {
                    id = jo.getInt("Id");
                }
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
        return id;
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

    public boolean authenticate(String username, String pw) {
        InputStream is = null;
        NetHttpTransport transport = null;
        String string = "";
        HttpRequest request = null;
        HttpResponse resp = null;
        HttpContent cont = null;
        JSONObject jUserName = new JSONObject();

        String contentString = "";
        try {
            jUserName.put("Username", username);
            jUserName.put("Password", pw);
            transport = new NetHttpTransport();
            HttpRequestFactory factory = transport.createRequestFactory();
            cont = new JsonHttpContent(new JacksonFactory(), jUserName.toString());
            request = factory.buildRequest(HttpMethods.GET, new GenericUrl(API_BASE), cont);
            request.getUrl().putAll(getParams());
            resp = request.execute();
            is = resp.getContent();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            try {
                if (is != null) {
                    string = getJSONFromInputStream(is);
                    Log.i("LDS", string);
                }
                transport.shutdown();
                if (resp.getStatusCode() == 200) {
                    return true;
                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;

    }
}
