package app.lloyd.com.mocktestapp;

import android.app.Application;

import app.lloyd.com.utils.PreferenceHelper;

public class MockApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.initPreferences(this);
    }
}
