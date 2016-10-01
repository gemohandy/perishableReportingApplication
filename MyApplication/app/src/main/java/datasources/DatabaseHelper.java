package datasources;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    private static String dbName = "dbPerishable";
    private static int dbVersion = 1;
    private Context context = null;

    public DatabaseHelper(Context context) {
        super(context, dbName, null, dbVersion);
        this.context = context;
    }
    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }

    private class tblPlace {
        private static final String TABLENAME = "tblPlace";
    }
}
