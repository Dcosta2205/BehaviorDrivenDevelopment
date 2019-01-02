package app.lloyd.com.mocktestapp.userdetails;

import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;

import app.lloyd.com.mocktestapp.loginorsignin.LoginResponse;
import app.lloyd.com.mocktestapp.loginorsignin.Users;
import yml.com.baselibrary.BasePresenter;

public final class UserDetailPresenter extends BasePresenter<UserContract.UserView, Object>
        implements UserContract.UserPresent {

    private static final String TAG = UserDetailPresenter.class.getSimpleName();
    private UserDetailInteractor userDetailInteractor;

    public UserDetailPresenter(UserDetailInteractor userDetailInteractor) {
        this.userDetailInteractor = userDetailInteractor;
    }

    @Override
    public void checkData(Bundle bundle) {
        if (getViewContract() != null && bundle != null &&
                !TextUtils.isEmpty(bundle.getString("loginResponse"))) {
            LoginResponse loginResponse = new Gson().fromJson(bundle.getString("loginResponse"),
                    LoginResponse.class);
            Log.d(TAG, "loginResponse : " + loginResponse);
            if (loginResponse != null) {
                getViewContract().setUserData(loginResponse);
            } else {
                getViewContract().noUserDataError("No user data to set!");
            }
        }
    }

    @Override
    public void getUserDetails(String userId) {
        if (userDetailInteractor != null) userDetailInteractor.getUserDetails(userId);
    }

    @Override
    public void setUserDetails(Users user) {
        if (getViewContract() != null) getViewContract().setUserDetails(user);
    }

    @Override
    public void setUserError(String errorMsg) {
        if (getViewContract() != null) getViewContract().errorMsg(errorMsg);
    }

    @Override
    public void onDestroyPresenter() {
        //not used
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(userDetailInteractor, this);
    }

    @Override
    public void onError(Throwable throwable) {
        //not used
    }

    @Override
    public void onNetworkError(Throwable throwable) {
        //not used
    }
}
