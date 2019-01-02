package app.lloyd.com.mocktestapp.loginorsignin;

import android.app.Application;

import app.lloyd.com.utils.PreferenceHelper;
import app.lloyd.com.utils.PreferenceHelper;

public class MockApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();
        PreferenceHelper.initPreferences(this);
    }
}
