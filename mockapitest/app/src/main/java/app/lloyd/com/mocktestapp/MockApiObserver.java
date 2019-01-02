package app.lloyd.com.mocktestapp;

import app.lloyd.com.mocktestapp.loginorsignin.MockApiImplementor;
import app.lloyd.com.mocktestapp.loginorsignin.MockApiImplementor;
import yml.com.baselibrary.BaseObservable;

public class MockApiObserver extends BaseObservable {

    private MockApiImplementor mockApiImplementor;

    public MockApiObserver(MockApiImplementor mockApiImplementor) {
        //default constructor.
        this.mockApiImplementor = mockApiImplementor;
    }

    public String getLoginResponse(String userName, String password) {
        return mockApiImplementor.loginUser(userName, password);
    }

    public String getUserDetails() {
        return mockApiImplementor.userDetails();
    }

}
