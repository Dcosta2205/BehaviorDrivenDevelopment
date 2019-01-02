package app.samyad.com.mockapi;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;

import static org.junit.Assert.assertEquals;

@RunWith(AndroidJUnit4.class)
public class MockApiResponsesTest {

    private static final String TAG = "samyadrjain";
    private static final String ASSET_BASE_PATH = "/assets/";

    @Test
    public void loginResponse_isCorrect() {
        String loginResponse = MockApiResponse.getInstance().getLoginResponse();
        String loginJson = "";
        try {
            loginJson = inputStreamStringApi19Above("login_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "loginJson : " + loginJson);
        assertEquals(loginResponse, loginJson);
    }

    @Test
    public void userResponse_isCorrect() {
        String userResponse = MockApiResponse.getInstance().getUserResponse();
        String userJson = "";
        try {
            userJson = inputStreamStringApi19Above("user_places_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        Log.d(TAG, "userJson : " + userJson);
        assertEquals(userResponse, userJson);
    }

    private String inputStreamStringApi19Above(String filename) throws IOException {
        try (InputStream is = getClass().getResourceAsStream(ASSET_BASE_PATH + filename)) {
            int size = is.available();
            byte[] buffer = new byte[size];
            final int noOfBytesRead = is.read(buffer);
            Log.d(TAG, "noOfBytesRead : " + noOfBytesRead);
            return new String(buffer, "UTF-8");
        }
    }

}
