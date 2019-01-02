package app.samyad.com.mockapi;

public final class MockApiInteractor {

    private MockApiInteractor() {
        //default constructor.
    }

    public static String getLoginResponse() {
        return MockApiResponse.getInstance().getLoginResponse();
    }

    public static String getUserData() {
        return MockApiResponse.getInstance().getUserResponse();
    }

}
