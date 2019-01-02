package app.lloyd.com.utils;

import android.content.Context;
import android.content.SharedPreferences;

import app.samyad.com.mocktestapp.BuildConfig;

public class PreferenceHelper {
    private static SharedPreferences sharedPreference;
    public static final String LOGIN_SUCCESSFULL = "LOGIN_SUCCESSFULL";
    public static final String FINGER_AUTHENTICATED = "FINGER_AUTHENTICATED";

    private PreferenceHelper() {
        //no instances
    }

    public static void initPreferences(Context applicationContext) {
        sharedPreference = applicationContext.getSharedPreferences(BuildConfig.APPLICATION_ID, Context.MODE_PRIVATE);
    }

    public static synchronized void setBoolPreference(String preference, boolean value) {
        sharedPreference.edit().putBoolean(preference, value).apply();
    }


    public static boolean getBoolPreference(String preference) {
        return sharedPreference != null && sharedPreference.getBoolean(preference, false);
    }

}
