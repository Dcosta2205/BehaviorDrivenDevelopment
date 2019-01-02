package app.lloyd.com.mocktestapp.loginorsignin;

import app.lloyd.com.mocktestapp.MockApi;
import app.samyad.com.mockapi.MockApiInteractor;

public class MockApiImplementor implements MockApi {

    @Override
    public String loginUser(String userName, String password) {
        return MockApiInteractor.getLoginResponse();
    }

    @Override
    public String userDetails() {
        return MockApiInteractor.getUserData();
    }
}
