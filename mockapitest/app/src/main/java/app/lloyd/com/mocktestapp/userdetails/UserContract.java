package app.lloyd.com.mocktestapp.userdetails;

import android.os.Bundle;

import app.lloyd.com.mocktestapp.MockBasePresenter;
import app.lloyd.com.mocktestapp.MockBaseView;
import app.lloyd.com.mocktestapp.loginorsignin.LoginResponse;
import app.lloyd.com.mocktestapp.loginorsignin.Users;

public final class UserContract {

    public interface UserView extends MockBaseView {

        void setUserData(LoginResponse loginResponse);

        void noUserDataError(String errorMsg);

        void setUserDetails(Users user);

        void errorMsg(String errorMsg);

    }

    public interface UserPresent extends MockBasePresenter {

        void checkData(Bundle bundle);

        void getUserDetails(String userId);

        void setUserDetails(Users user);

        void setUserError(String errorMsg);

    }

    public interface UserMockInteractor {

        void getUserDetails(String userId);

    }

}
