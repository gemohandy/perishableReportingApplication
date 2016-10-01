package datasources;


import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

//abstract class identifies an online dataSource (NetworkDataSource)
public abstract class NetworkDataSource extends DataSource {
    //Connection properties
    protected static final int MAX = 1000;
    protected static final int READ_TIMEOUT = 10000;
    protected static final int CONNECT_TIMEOUT = 10000;
    //Marker cache
    private Map<String, String> params = new HashMap<>();

    //Grabs the response string
    public abstract ArrayList<Object> getHttpGETInputStream() throws IOException;

    public abstract Object processJsonObject(JSONObject jo) throws JSONException;


    protected static InputStream getHttpGETInputStream(String urlStr) {
        if (urlStr == null)
            throw new NullPointerException();

        InputStream is = null;
        URLConnection conn = null;
        //if the response comes from a local source
        try {
            if (urlStr.startsWith("file://"))
                return new FileInputStream(urlStr.replace("file://", ""));
            //builds URL
            URL url = new URL(urlStr);
            //opens connection
            conn = url.openConnection();
            conn.setReadTimeout(READ_TIMEOUT);
            conn.setConnectTimeout(CONNECT_TIMEOUT);

            is = conn.getInputStream();

            return is;
        }
        //catches an error with the connection, closes, and disconnects
        catch (Exception ex) {
            try {
                is.close();
            } catch (Exception e) {
                // Ignore
            }
            try {
                if (conn instanceof HttpURLConnection)
                    ((HttpURLConnection) conn).disconnect();
            } catch (Exception e) {
                // Ignore
            }
            ex.printStackTrace();
        }
        return null;
    }

    protected String getHttpInputString(InputStream is) {
        if (is == null)
            throw new NullPointerException();
        //instantiates a reader
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

    public Map<String, String> getParams() {
        return params;
    }

    public void setParams(Map<String, String> params) {
        this.params = params;
    }
}

	    