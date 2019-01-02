package app.lloyd.com.junit;

import android.support.test.runner.AndroidJUnit4;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import app.lloyd.com.mocktestapp.loginorsignin.LoginResponse;
import app.samyad.com.mockapi.MockApiApplication;

@RunWith(AndroidJUnit4.class)
public class UserDetailsPresenterTest {

    private String expectedFirstName;
    private static final String TAG = UserDetailsPresenterTest.class.getSimpleName();

    @Before
    public void setUp() {
        //Not Required
    }

    @Test
    public void verifyIfUser1DFirstNameIsCorrect() {
        final String username = "sam";
        final String pswrd = "sam123";
        final String actualFirstName = "Samyad";
        String loginResponseJson = null;
        try {
            loginResponseJson = inputStreamStringApi19Above("login_response.json");
            checkLoginResponse(loginResponseJson, username, pswrd);
        } catch (IOException e) {
            e.printStackTrace();
        }
        Assert.assertEquals(expectedFirstName, actualFirstName);
    }

    private String inputStreamStringApi19Above(String filename) throws IOException {
        try (InputStream is = MockApiApplication.getApplicationInstance().getAssets().open(filename)) {
            int size = is.available();
            byte[] buffer = new byte[size];
            final int noOfBytesRead = is.read(buffer);
            Log.d(TAG, "noOfBytesRead : " + noOfBytesRead);
            return new String(buffer, "UTF-8");
        }
    }

    private void checkLoginResponse(String loginResponseJson, String userName, String password) {

        try {
            JsonObject jsonObject = new Gson().fromJson(loginResponseJson, JsonObject.class);
            JsonElement yourJson = jsonObject.get("loginResponse");
            Type listType = new TypeToken<List<LoginResponse>>() {
            }.getType();
            List<LoginResponse> loginResponse = new Gson().fromJson(yourJson, listType);
            if (loginResponse != null) {
                verifyUserDetails(loginResponse, userName, password);
            }
        } catch (Exception e) {
            //Not required
        }
    }

    private void verifyUserDetails(List<LoginResponse> loginResponse, String userName, String password) {
        if (loginResponse != null) {
            for (int i = 0; i < loginResponse.size(); i++) {
                if (userName.equals(loginResponse.get(i).getUserName())) {
                    expectedFirstName = loginResponse.get(i).getFirstName();
                }
            }
        }
    }
}
