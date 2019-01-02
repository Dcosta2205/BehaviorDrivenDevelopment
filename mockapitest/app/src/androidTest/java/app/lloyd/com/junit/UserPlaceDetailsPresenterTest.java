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

import app.lloyd.com.mocktestapp.loginorsignin.Users;
import app.samyad.com.mockapi.MockApiApplication;

@RunWith(AndroidJUnit4.class)
public class UserPlaceDetailsPresenterTest {

    private int expectedPlaceRating;
    private static final String TAG = UserPlaceDetailsPresenterTest.class.getSimpleName();

    @Before
    public void setUp() {
        //Not Required
    }

    @Test
    public void verfiyIfUser1PlaceRatingIsCorrect() {
        final String userId = "user1";
        final int actualPlaceRating = 4;
        final String userResponseJson = getUserResponse();
        getUserDetailsById(userResponseJson, userId);
        Assert.assertEquals(expectedPlaceRating, actualPlaceRating);
    }

    private void getUserDetailsById(String userResponseJson, String userId) {
        JsonObject jsonObject = new Gson().fromJson(userResponseJson, JsonObject.class);
        JsonElement yourJson = jsonObject.get("users");
        Type listType = new TypeToken<List<Users>>() {
        }.getType();
        List<Users> userResponses = new Gson().fromJson(yourJson, listType);
        Log.d(TAG, "userResponses : " + userResponses);
        if (userResponses != null && !userResponses.isEmpty()) {
            for (Users user : userResponses) {
                if (user.getUserId().equalsIgnoreCase(userId)) {
                    Log.d(TAG, "user exist and no of places visited : " + user.getNumOfPlaces());
                    verfiyPlaceRating(user);
                    break;
                }
            }
        }
    }

    private void verfiyPlaceRating(Users user) {
        if (user != null) {
            for (int i = 0; i < user.getNumOfPlaces(); i++) {
                if (user.getPlaces().get(i).getPlace().equals("Mysore Palace")) {
                    expectedPlaceRating = user.getPlaces().get(i).getRating();
                }
            }
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
