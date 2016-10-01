package datasources;
/*
import android.content.Context;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.os.AsyncTask;
import android.util.Log;

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
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import ca.useful.newview.R;
import data.Place;
import interfaces.ARDataIF;
import interfaces.JsonIF;
import interfaces.SpecifiedObjectType;
import interfaces.ToMarkerIF;
import values.CompanyEnum;
import values.keys;
/*
public class PlacesDataSource extends NetworkDataSource implements JsonIF, SpecifiedObjectType, keys.PlacesKeys.LocalKeySetIF, ToMarkerIF, ARDataIF
{
	private static final String TAG = "PlacesDataSource";
	private String BASEURL = "https://maps.googleapis.com/maps/api/place/search/json?";
	private String BASENEARBYURL = "https://maps.googleapis.com/maps/api/place/nearbysearch/json?";
	private ArrayList<Place> places = new ArrayList<Place>();
	private double altitude = 0;
	private static Bitmap icon = null;
	private boolean isMap = true;

	public PlacesDataSource(Context context)
	{
		try
		{
			createIcon(context.getResources());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	public PlacesDataSource(Resources res) {
        if (res==null) throw new NullPointerException();
        try
        {
        	createIcon(res);
        }
        catch(Exception ex)
        {
        	Log.e(TAG, "Error creating icon");
        }
	}

	public PlacesDataSource(Resources res, boolean isMap) {
        if (res==null) throw new NullPointerException();
        try
        {
        	setMap(isMap);
        	createIcon(res);
        }
        catch(Exception ex)
        {
        	Log.e(TAG, "Error creating icon");
        }
	}
       public boolean isMap() {
		return isMap;
	}

	public void setMap(boolean isMap) {
		this.isMap = isMap;
	}

//creates the thumbnail from an image provided
   protected void createIcon(Resources res) {
       if (res==null) throw new NullPointerException();

       icon=BitmapFactory.decodeResource(res, R.drawable.house);
   }

	public Bitmap getPhotoFromURL(String strUrl) throws MalformedURLException
	{
		Bitmap photo = null;
		InputStream in = null;
		URL url = new URL(strUrl);
		URLConnection conn = null;
		try
		{
			conn = url.openConnection();
			HttpURLConnection httpConn = (HttpURLConnection) conn;
            httpConn.setRequestMethod("GET");
            httpConn.connect();
            if (httpConn.getResponseCode() == HttpURLConnection.HTTP_OK) {
                in = httpConn.getInputStream();
            }
		photo = BitmapFactory.decodeStream(in);
		
		}
		
		catch(Exception ex)
		{
			Log.e(TAG, "Error generating photo from URL: " + ex.getMessage());
		}
		finally
		{
			try
			{
				in.close();
			}
			catch(Exception ex)
			{
				//ex.printstacktrace();
			}
		}
		return photo;
	}
	protected String getJSONFromInputStream(InputStream is)
	{
		if (is == null)
			throw new NullPointerException();
		//instantiates a reader with max size
		BufferedReader reader = new BufferedReader(new InputStreamReader(is), 8 * 1024);

		StringBuilder sb = new StringBuilder();

		try
		{
			//reads the response line by line (and separates by a line-break)
			String line;
			while ((line = reader.readLine()) != null)
			{
				sb.append(line + "\n");
			}
		}
		catch (IOException e)
		{
			e.printStackTrace();
		}
		finally
		{
			try
			{
				//closes the inputStream
				is.close();
			}
			catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return sb.toString();
	}

	@Override
	public ArrayList<Object> processJsonArray(JSONArray array) {
		ArrayList<Object> objects = new ArrayList<>();
		try
		{
			for (int i = 0; i < array.length(); i++)
			{
				objects.add(processJsonObject(array.getJSONObject(i)));
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return objects;
	}

	@Override
	public Object processJsonObject(JSONObject jo)
	{
		Place p = null;
		try
		{
			p = new Place();
			p.setId(jo.getString("place_id"));
			if (jo.has("geometry") && !jo.isNull("geometry")) {
				JSONObject j = jo.getJSONObject("geometry");
				if (j.has("location") && !j.isNull("location")) {
					JSONObject loc = j.getJSONObject("location");
					p.setLatlng(new Place.Geometry(loc.getDouble("lat"), loc.getDouble("lng")));
				}
			}

			if (jo.has("icon") && !jo.isNull("icon"))
				p.setPhotoData(jo.getString("icon"));
			if (jo.has("name") && !jo.isNull("name"))
				p.setName(jo.getString("name"));
			if (jo.has("rating") && !jo.isNull("rating"))
				p.setRating(Double.toString(jo.getDouble("rating")));

			if (jo.has("types") && !jo.isNull("types")) {
				JSONArray array = (JSONArray) jo.get("types");
				if (array != null) {
					int len = array.length();
					for (int i = 0; i < len; i++) {
						p.getTypes().add(array.get(i).toString());
					}
				}
			}
			if (jo.has("vicinity") && !jo.isNull("vicinity"))
				p.setVicinity(jo.getString("vicinity"));


			if (jo.has("opening_hours") && !jo.isNull("opening_hours"))
			{
				JSONObject j = jo.getJSONObject("opening_hours");
				p.processOpen(j.getBoolean("open_now"));

			}

		}
		catch(Exception ex)
		{
			Log.e(TAG, "Error processing JSON object: " + ex.getMessage());
		}
		return p;
	}
	public ArrayList<Object> getSpecificCompaniesWhoSubscribed() throws IOException {
		ArrayList<Object> objects = new ArrayList<>();
		InputStream is = null;
		NetHttpTransport transport = null;
		String string = "";
		HttpRequest request = null;
		HttpResponse resp = null;
		try {
			transport = new NetHttpTransport();
			HttpRequestFactory factory = transport.createRequestFactory();
			request = factory.buildGetRequest(new GenericUrl(BASEURL));
			request.getUrl().putAll(getParams());
			for (CompanyEnum c : CompanyEnum.values())
			{
				try {
					if (!request.getUrl().containsKey(keys.PlacesKeys.KEYWORD.getKey())) {
						request.getUrl().put(keys.PlacesKeys.KEYWORD.getKey(), c.getName());
					} else {
						request.getUrl().set(keys.PlacesKeys.KEYWORD.getKey(), c.getName());
					}
					resp = request.execute();
					is = resp.getContent();
					string = getJSONFromInputStream(is);
					JSONObject json = new JSONObject(string);
					if (json.has("results")) {
						objects = processJsonArray(json.getJSONArray("results"));
						addSpecifiedObjectTypeLocally(objects);
						addMarkerGlobally(objects);
						while (json.has("next_page_token") && (!json.isNull("next_page_token") || !json.getString("next_page_token").equals(""))) {
							try {
								Thread.sleep(4000);
								if (request.getUrl().containsKey("pagetoken"))
								{
									request.getUrl().remove("pagetoken");
								}
								request.getUrl().put("pagetoken", json.getString("next_page_token"));
								resp = request.execute();
								is = resp.getContent();
								string = getHttpInputString(is);
								json = new JSONObject(string);
								if (json.has("results"))
								{
									objects.clear();
									objects = processJsonArray(json.getJSONArray("results"));
									addMarkerGlobally(objects);
									addSpecifiedObjectTypeLocally(objects);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (is != null) {

				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		transport.shutdown();
		return objects;
	}
	@Override
	public ArrayList<Object> getHttpGETInputStream() throws IOException {
		places.clear();
		ArrayList<Object> objects = new ArrayList<>();
		InputStream is = null;
		NetHttpTransport transport = null;
		String string = "";
		HttpRequest request = null;
		HttpResponse resp = null;
		try {
			transport = new NetHttpTransport();
			HttpRequestFactory factory = transport.createRequestFactory();
			if (getParams().containsKey(keys.PlacesKeys.KEYWORD.getKey()))
				request = factory.buildGetRequest(new GenericUrl(BASEURL));
			else
				request = factory.buildGetRequest(new GenericUrl(BASENEARBYURL));
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
					if (json.has("results")) {
						objects = processJsonArray(json.getJSONArray("results"));
						addSpecifiedObjectTypeLocally(objects);
						addMarkerGlobally(objects);
						while (json.has("next_page_token") && (!json.isNull("next_page_token") || !json.getString("next_page_token").equals(""))) {
							try {
								Thread.sleep(4000);
								if (request.getUrl().containsKey("pagetoken"))
								{
									request.getUrl().remove("pagetoken");
								}
								request.getUrl().put("pagetoken", json.getString("next_page_token"));
								resp = request.execute();
								is = resp.getContent();
								string = getHttpInputString(is);
								json = new JSONObject(string);
								if (json.has("results"))
								{
									objects.clear();
									objects = processJsonArray(json.getJSONArray("results"));
									addMarkerGlobally(objects);
									addSpecifiedObjectTypeLocally(objects);
								}
							} catch (InterruptedException e) {
								e.printStackTrace();
							} catch (JSONException e) {
								e.printStackTrace();
							}
						}
					}
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
		transport.shutdown();
		logPlaces();
		return objects;
	}
	@Override
	public void setParameters(ArrayList<keys.PlacesKeys> keys) {
		for (values.keys.PlacesKeys k : keys)
		{
			getParams().put(k.getKey(), k.getValue());
		}
	}
	@Override
	public void setParameters() {
		List<keys.PlacesKeys> keys = Arrays.asList(values.keys.PlacesKeys.values());
		for (values.keys.PlacesKeys k : keys)
		{
			getParams().put(k.getKey(), k.getValue());
		}
	}

	public void logPlaces()
	{
		try
		{
			for (int i = 0; i < places.size(); i++)
			{
			Log.i(TAG, "Marker: " + places.get(i).getName());
			}
		}
		catch(Exception ex)
		{
			Log.e(TAG, "Error logging markers: " + ex.getMessage());
		}
	}

	public String formatTypes(ArrayList<String> types)
	{
		String strTypes = "";
		try
		{
			for (int i = 0; i < types.size(); i++)
			{
				if (i != types.size() - 1)
				strTypes.concat(types.get(i) + "|");
				else
					strTypes.concat(types.get(i));
			}
		}
		catch(Exception ex)
		{
			Log.e(TAG, "Error formatting search parameter types: " + ex.getMessage());
		}
		return strTypes;
	}


	public ArrayList<Place> getPlaces() {
		return places;
	}
	public void setPlaces(ArrayList<Place> places) {
		this.places = places;
	}

	@Override
	public List<Object> getData() {
		return null;
	}

	@Override
	public void addSpecifiedObjectTypeLocally(ArrayList<Object> objects) {
		for(Object o : objects)
		{
			if (o instanceof Place)
			{
				places.add((Place)o);
			}
		}
	}

	@Override
	public void addSpecifiedObjectTypeLocally(Object object) {
		if (object instanceof Place)
			places.add((Place)object);
	}

	@Override
	public void makeLocationKey(String value) {
		getParams().put(keys.PlacesKeys.LOCATION.getKey(), value);
	}

	@Override
	public void makeMinPriceKey(String value) {
		getParams().put(keys.PlacesKeys.MINPRICE.getKey(), value);
	}

	@Override
	public void makeMaxPriceKey(String value) {
		getParams().put(keys.PlacesKeys.MAXPRICE.getKey(), value);
	}

	@Override
	public void makeRadiusKey(String value) {
		getParams().put(keys.PlacesKeys.RADIUS.getKey(), value);
	}

	@Override
	public void makeOpenKey(String value) {
		getParams().put(keys.PlacesKeys.OPEN.getKey(), value);
	}

	@Override
	public void makeQueryKey(String value) {
		getParams().put(keys.PlacesKeys.KEYWORD.getKey(), value);
	}

	@Override
	public void makeTokenKey(String value) {
		getParams().put(keys.PlacesKeys.TOKEN.getKey(), value);
	}
	@Override
	public void makeUnchangeableKeys()
	{
		getParams().put(keys.PlacesKeys.KEY.getKey(), keys.PlacesKeys.KEY.getValue());
		getParams().put(keys.PlacesKeys.SENSOR.getKey(), keys.PlacesKeys.SENSOR.getValue());

	}

	@Override
	public List<Marker> getIconMarkersFromType(ArrayList<Object> items) throws MalformedURLException {
		ArrayList<Marker> markers = new ArrayList<>();
		for (Object p : items)
		{
			markers.add(getIconMarkerFromSingleton(p));
		}
		return markers;
	}

	@Override
	public List<Marker> getIconMarkersFromLocalData() throws MalformedURLException {
		ArrayList<Marker> markers = new ArrayList<>();
		for (Place p : places)
		{
			markers.add(getIconMarkerFromSingleton(p));
		}
		return markers;
	}

	@Override
	public Marker getIconMarkerFromSingleton(Object object) throws MalformedURLException {
		Marker ma = null;
		if (object instanceof Place) {
			Place p = (Place) object;
			try {
				ma = new IconMarker(
						p.getName(),
						p.getLatlng().getLocation().getLat(),
						p.getLatlng().getLocation().getLon(),
						663,
						Color.WHITE,
						p.toString(),
						p.getRating(),
						p.getWebsite(),
						getPhotoFromURL(p.getPhotoData()));
				((IconMarker) ma).setPlace(p);
			} catch (Exception e) {
				ma = new IconMarker(
						p.getName(),
						p.getLatlng().getLocation().getLat(),
						p.getLatlng().getLocation().getLon(),
						663,
						Color.WHITE,
						p.toString(),
						p.getRating(),
						p.getWebsite(),
						icon);
				((IconMarker) ma).setPlace(p);
			}
		}
		return ma;
	}

	@Override
	public void addMarkerGlobally(final ArrayList<Object> objects) {
		AsyncTask<Void, Void, Void> addTask = new AsyncTask<Void, Void, Void>() {
			@Override
			protected Void doInBackground(Void... params) {
				//TODO add markers to ARData class
				try {
					for (Object o : objects) {
						try {
							if (o instanceof Place) {
								PlaceDetailDataSource ds = new PlaceDetailDataSource((Place) o);
								ds.makeUnchangeableKeys();
								ds.makeIdKey(ds.getPlace().getId());
								ds.getHttpGETInputStream();
								Marker marker = getIconMarkerFromSingleton(ds.getPlace());
								if (marker != null)
									ARData.addMarkers(marker);

							}
						} catch (MalformedURLException e) {
							e.printStackTrace();
						}
					}
				}
				catch(Exception e)
				{
					e.printStackTrace();
				}
				return null;
			}
		}.execute();
	}

	public String getBASEURL() {
		return BASEURL;
	}

	public String getBASENEARBYURL() {
		return BASENEARBYURL;
	}
//method returns a marker that may be placed on GoogleMaps

}
*/