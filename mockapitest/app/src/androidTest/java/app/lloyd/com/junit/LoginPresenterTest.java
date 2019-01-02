package app.lloyd.com.junit;


import android.support.test.InstrumentationRegistry;
import android.support.test.runner.AndroidJUnit4;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import app.lloyd.com.mocktestapp.MockApiObserver;
import app.lloyd.com.mocktestapp.loginorsignin.LoginResponse;
import app.lloyd.com.mocktestapp.loginorsignin.MockApiImplementor;
import app.samyad.com.mockapi.MockApiApplication;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(AndroidJUnit4.class)
public class LoginPresenterTest {


    private MockApiObserver mockApiObserver;
    private boolean userDetailsMatched;
    private static final String TAG = LoginPresenterTest.class.getSimpleName();

    //
    @Before
    public void setUp() {
        mockApiObserver = new MockApiObserver(new MockApiImplementor());
    }

    @Test
    public void loginSuccessTest() {
        final String userName = "sam";
        final String pswrd = "sam123";
        String loginResponseJson = null;
        try {
            loginResponseJson = inputStreamStringApi19Above("login_response.json");
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkLoginResponse(loginResponseJson, userName, pswrd);
        assertTrue(userDetailsMatched);
    }

    @Test
    public void loginFailureTest() {
        final String username = "lloyd";
        final String pswrd = "dcosta123";
        String loginResponseJson = mockApiObserver.getLoginResponse(username, pswrd);
        checkLoginResponse(loginResponseJson, username, pswrd);
        assertFalse(userDetailsMatched);
    }

    private void checkLoginResponse(String loginResponseJson, String userName, String password) {

        try {
            JsonObject jsonObject = new Gson().fromJson(loginResponseJson, JsonObject.class);
            JsonElement yourJson = jsonObject.get("loginResponse");
            Type listType = new TypeToken<List<LoginResponse>>() {
            }.getType();
            List<LoginResponse> loginResponse = new Gson().fromJson(yourJson, listType);
            if (loginResponse != null) {
                checkIfUserExist(loginResponse, userName, password);
            }
        } catch (Exception e) {
            //Not required
        }
    }

    private void checkIfUserExist(List<LoginResponse> loginResponseList, String userName, String password) {
        Log.d(TAG, "checkIfUserExist : " + loginResponseList);
        for (LoginResponse loginResponse : loginResponseList) {
            if (!TextUtils.isEmpty(loginResponse.getUserName()) &&
                    !TextUtils.isEmpty(loginResponse.getPassword()) &&
                    loginResponse.getUserName().equals(userName) &&
                    loginResponse.getPassword().equals(password)) {
                userDetailsMatched = true;
                break;
            } else {
                userDetailsMatched = false;
            }
        }
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
}
