package app.lloyd.com.mocktestapp.loginorsignin;

import app.lloyd.com.mocktestapp.MockBasePresenter;
import app.lloyd.com.mocktestapp.MockBaseView;

public class LoginContract {

    public interface LoginView extends MockBaseView {

        void loginSuccess(LoginResponse loginResponse);

        void loginFailure(final int errorMsg);

    }

    public interface Presenter extends MockBasePresenter {

        void callLogin(String userName, String password);

        void loginSuccess(LoginResponse loginResponse);

        void loginFailure(int errorMsg);

    }

    public interface MockInteractor {

        void callLogin(String userName, String password);

    }
}
