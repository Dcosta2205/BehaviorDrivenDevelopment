//package app.samyad.com.steps;
//
//
//import android.util.Log;
//
//import app.samyad.com.mockapi.LoginUserDetails;
//import cucumber.api.java.en.Then;
//import cucumber.api.java.en.When;
//
//public class MockApiStepDefinition {
//
//    private LoginUserDetails loginUserDetails;
//    private static final String TAG = MockApiStepDefinition.class.getSimpleName();
//
//    @When("^ the user wants to login$")
//    public void showLogin() {
//        Log.d(TAG, "login screen");
//    }
//
//    @Then("^ user must enter his username \"([^\"]*)\" and password \"([^\"]*)\"$")
//    public void login(String username, String password) {
//        loginUserDetails = new LoginUserDetails(username, password);
//    }
//}
