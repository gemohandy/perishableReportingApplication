package application;

import android.app.Application;
import android.content.Context;
import android.content.SharedPreferences;

import data.Login;

public class PreferencesApplication extends Application {

    private static String APPPREFERENCES = "PerishablePrefs";
    private Login login;

    public SharedPreferences getPreferences(Context context) {
        return context.getSharedPreferences(APPPREFERENCES, 0);
    }
}
