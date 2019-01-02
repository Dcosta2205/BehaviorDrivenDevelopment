package app.samyad.com.mockapi;

import android.app.Application;
import android.content.Context;

public class MockApiApplication extends Application {

    private static Context context;

    @Override
    public void onCreate() {
        super.onCreate();
        context = getApplicationContext();

    }

    public static Context getApplicationInstance() {
        return context;
    }

}
