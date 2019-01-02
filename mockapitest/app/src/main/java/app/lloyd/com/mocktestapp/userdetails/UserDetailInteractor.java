package app.lloyd.com.mocktestapp.userdetails;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.lloyd.com.mocktestapp.MockApiObserver;
import app.lloyd.com.mocktestapp.loginorsignin.MockApiImplementor;
import app.lloyd.com.mocktestapp.loginorsignin.Users;
import app.lloyd.com.mocktestapp.MockApiObserver;
import app.lloyd.com.mocktestapp.loginorsignin.MockApiImplementor;
import app.lloyd.com.mocktestapp.loginorsignin.Users;
import yml.com.baselibrary.BaseInteractor;

public final class UserDetailInteractor extends BaseInteractor<Object> implements
        UserContract.UserMockInteractor {

    private static final String TAG = UserDetailInteractor.class.getSimpleName();
    private final MockApiObserver mockApiObserver;

    UserDetailInteractor() {
        mockApiObserver = new MockApiObserver(new MockApiImplementor());
    }


    @Override
    public void getUserDetails(String userId) {
        String userResponse = mockApiObserver.getUserDetails();
        if (getPresenterContract() != null) {
            if (!TextUtils.isEmpty(userResponse)) {
                getUseDataById(userResponse, userId);
            } else {
                getPresenter().setUserError("No User detail found!");
            }
        }
    }

    private void getUseDataById(String userResponse, String userId) {
        JsonObject jsonObject = new Gson().fromJson(userResponse, JsonObject.class);
        JsonElement yourJson = jsonObject.get("users");
        Type listType = new TypeToken<List<Users>>() {
        }.getType();
        List<Users> userResponses = new Gson().fromJson(yourJson, listType);
        Log.d(TAG, "userResponses : " + userResponses);
        boolean isExist = true;
        if (getPresenter() != null) {
            if (userResponses != null && !userResponses.isEmpty()) {
                for (Users user : userResponses) {
                    if (user.getUserId().equalsIgnoreCase(userId)) {
                        Log.d(TAG, "user exist and no of places visited : " + user.getNumOfPlaces());
                        isExist = true;
                        getPresenter().setUserDetails(user);
                        break;
                    } else {
                        isExist = false;
                    }
                }
            }
            if (!isExist) {
                getPresenter().setUserError("No User detail found!");
            }
        }
    }

    private UserDetailPresenter getPresenter() {
        return (UserDetailPresenter) getPresenterContract();
    }
}
