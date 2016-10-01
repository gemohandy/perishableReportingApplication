package values;

/**
 * Created by Jeremy on 26/11/2015.
 */
public class keys {
    public static final String BrowserKey = "AIzaSyCwB02alr8OsgDUL7_5kdtc3-CobMQSAm4";
    public static enum PlaceDetailsKeys{
        PLACEID("placeid", ""),
        KEY("key", BrowserKey);
        PlaceDetailsKeys(String name, String defaultV)
        {
            this.key = name;
            this.value = defaultV;
        }
        private String key = "";
        private String value ="";

        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }

        public interface localKeySetIF
        {
            public abstract void makeIdKey(String value);
            public abstract void makeUnchangeableKeys();
        }
    }
    public static enum PlacesPredictions
    {
        KEY("key", BrowserKey),
        LANGUAGE("language", "en"),
        LOCATION("location", ""),
        RADIUS("radius", "1000"),
        TYPES("types", "geocode"),
        SENSOR("sensor", "false"),
        INPUT("input", "");
        PlacesPredictions(String key, String value)
        {
            this.key = key;
            this.value = value;
        }
        String key;
        String value;

        public interface localKeySetIF
        {
            public abstract void makeUnchangeableKeys();
            public abstract void makeLocationKey(double lat, double lon);
            public abstract void makeRadiusKey(int radInMeters);
            public abstract void makeLanguageKey(String value);
            public abstract void makeInputKey(String value);
            void makeGeocodeKey();
            void makeSensorKey();
        }
        public String getKey() {
            return key;
        }

        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
    }
    public static enum PlacesKeys{
        LOCATION("location", ""),
        MINPRICE("minprice", "0"),
        MAXPRICE("maxprice", "4"),
        RADIUS("radius", "0"),
        SENSOR("sensor", "true"),
        OPEN("opennow", "true"),
        KEY("key", BrowserKey),
        KEYWORD("keyword", ""),
        RANKBY("rankby", "distance"),
        TOKEN("pagetoken", "");
        PlacesKeys(String name, String defaultV)
        {
            this.key = name;
            this.value = defaultV;
        }
        String key = "";
        String value = "";
        public String getKey() {
            return key;
        }
        public interface LocalKeySetIF
        {
            public abstract void makeLocationKey(String value);
            public abstract void makeMinPriceKey(String value);
            public abstract void makeMaxPriceKey(String value);
            public abstract void makeRadiusKey(String value);
            public abstract void makeOpenKey(String value);
            public abstract void makeQueryKey(String value);
            public abstract void makeTokenKey(String value);
            public abstract void makeUnchangeableKeys();

        }
        public void setKey(String key) {
            this.key = key;
        }

        public String getValue() {
            return value;
        }

        public void setValue(String value) {
            this.value = value;
        }
        public static PlacesKeys makeCorrespondingKey(String key, String value)
        {
            for (PlacesKeys k : PlacesKeys.values())
            {
                if (k.getKey().equals(key))
                {
                    k.setValue(value);
                    return k;
                }
            }
            return null;
        }
    }}
