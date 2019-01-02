package app.samyad.com.mockapi;

import android.util.Log;

import java.io.IOException;
import java.io.InputStream;

public class MockApiResponse {

    private static final String TAG = MockApiResponse.class.getSimpleName();
    private static MockApiResponse mockApiResponse;

    private MockApiResponse() {
        //default constructor.
    }

    public static MockApiResponse getInstance() {
        if (mockApiResponse == null) {
            mockApiResponse = new MockApiResponse();
        }
        return mockApiResponse;
    }

    public String getLoginResponse() {
        try {
            String res = inputStreamStringApi19Above("login_response.json");
            Log.d(TAG, "getLoginResponse : " + res);
            return res;
        } catch (IOException e) {
            Log.d(TAG, "getLoginResponse fail : " + e.getMessage());
            return "";
        }
    }

    public String getUserResponse() {
        try {
            String res = inputStreamStringApi19Above("user_places_response.json");
            Log.d(TAG, "getUserResponse : " + res);
            return res;
        } catch (IOException e) {
            Log.d(TAG, "getUserResponse fail : " + e.getMessage());
            return "";
        }
    }

    public String inputStreamStringApi19Above(String filename) throws IOException {
        try (InputStream is = MockApiApplication.getApplicationInstance().getAssets().open(filename)) {
            int size = is.available();
            byte[] buffer = new byte[size];
            final int noOfBytesRead = is.read(buffer);
            Log.d(TAG, "noOfBytesRead : " + noOfBytesRead);
            return new String(buffer, "UTF-8");
        }
    }

}
