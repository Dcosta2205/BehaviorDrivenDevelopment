package app.lloyd.com.mocktestapp.loginorsignin;

import app.lloyd.com.utils.PreferenceHelper;
import yml.com.baselibrary.BasePresenter;

public final class LoginPresenter extends BasePresenter<LoginContract.LoginView, String>
        implements LoginContract.Presenter {

    private final LoginInteractor loginInteractor;

    LoginPresenter(LoginInteractor loginInteractor) {
        this.loginInteractor = loginInteractor;
    }

    @Override
    public void callLogin(String userName, String password) {
        getViewContract().loadProgressIndicator();
        loginInteractor.callLogin(userName, password);
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        PreferenceHelper.setBoolPreference(PreferenceHelper.LOGIN_SUCCESSFULL, true);
        if (getViewContract() != null) {
            getViewContract().stopProgressIndicator();
            getViewContract().loginSuccess(loginResponse);
        }
    }


    @Override
    public void loginFailure(int errorMsg) {
        if (getViewContract() != null) {
            getViewContract().stopProgressIndicator();
            getViewContract().loginFailure(errorMsg);
        }
    }

    @Override
    public void subscribeInteractor() {
        subscribeInteractor(loginInteractor, this);
    }

    @Override
    public void onError(Throwable throwable) {
        //not used
    }

    @Override
    public void onNetworkError(Throwable throwable) {
        //not used
    }

    @Override
    public void onDestroyPresenter() {
        unsubscribeView(getViewContract());
    }

}
