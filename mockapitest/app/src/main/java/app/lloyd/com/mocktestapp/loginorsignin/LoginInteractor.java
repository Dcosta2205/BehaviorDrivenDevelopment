package app.lloyd.com.mocktestapp.loginorsignin;

import android.text.TextUtils;
import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import java.lang.reflect.Type;
import java.util.List;

import app.lloyd.com.mocktestapp.MockApiObserver;
import app.lloyd.com.mocktestapp.MockApiObserver;
import app.samyad.com.mocktestapp.R;
import app.lloyd.com.utils.PreferenceHelper;
import yml.com.baselibrary.BaseInteractor;

public final class LoginInteractor extends BaseInteractor<String> implements
        LoginContract.MockInteractor {

    private static final String TAG = LoginInteractor.class.getSimpleName();
    private final MockApiObserver mockApiObserver;

    public LoginInteractor() {
        mockApiObserver = new MockApiObserver(new MockApiImplementor());
    }

    @Override
    public void callLogin(final String userName, final String password) {
        String loginResponseJson =
                mockApiObserver.getLoginResponse(userName, password);
        if (getPresenter() != null) {
            if (!TextUtils.isEmpty(loginResponseJson)) {
                checkLoginResponse(loginResponseJson, userName, password);
            } else {
                getPresenter().loginFailure(R.string.no_records_found_error);
            }
        }
    }

    private void checkLoginResponse(String loginResponseJson, String userName, String password) {
        if (getPresenter() != null) {
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
                getPresenter().loginFailure(R.string.something_went_wrong_error);
            }
        }
    }

    private void checkIfUserExist(List<LoginResponse> loginResponseList, String userName, String password) {
        Log.d(TAG, "checkIfUserExist : " + loginResponseList);
        boolean noMatch = true;
        for (LoginResponse loginResponse : loginResponseList) {
            if (!TextUtils.isEmpty(loginResponse.getUserName()) &&
                    !TextUtils.isEmpty(loginResponse.getPassword()) &&
                    loginResponse.getUserName().equals(userName) &&
                    loginResponse.getPassword().equals(password)) {
                loginSuccessful(loginResponse);
                noMatch = false;
                break;
            } else {
                noMatch = true;
            }
        }
        if (noMatch) {
            loginFailed();
        }
    }

    private void loginFailed() {
        if (getPresenter() != null) {
            getPresenter().loginFailure(R.string.msg_password_error);
        }
    }

    private void loginSuccessful(LoginResponse loginResponse) {
        if (getPresenter() != null) {
            getPresenter().loginSuccess(loginResponse);
        }
    }

    private LoginPresenter getPresenter() {
        return (LoginPresenter) getPresenterContract();
    }

}
