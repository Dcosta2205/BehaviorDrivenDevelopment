package app.lloyd.com.mocktestapp;

import android.app.Activity;
import android.content.Intent;
import android.support.test.InstrumentationRegistry;
import android.support.test.espresso.matcher.ViewMatchers;
import android.support.test.rule.ActivityTestRule;
import android.support.test.uiautomator.UiDevice;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.reflect.TypeToken;

import org.hamcrest.Description;
import org.hamcrest.Matcher;
import org.junit.Rule;
import org.junit.Test;
import org.junit.internal.matchers.TypeSafeMatcher;

import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.Type;
import java.util.List;

import app.lloyd.com.mocktestapp.loginorsignin.LoginOrSignInActivity;
import app.lloyd.com.mocktestapp.loginorsignin.LoginResponse;
import app.lloyd.com.utils.AppConstants;
import app.lloyd.com.utils.Delay;
import app.lloyd.com.utils.UiAutomatorTools;
import app.samyad.com.mocktestapp.R;
import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;

import static android.support.test.espresso.Espresso.onView;
import static android.support.test.espresso.action.ViewActions.click;
import static android.support.test.espresso.action.ViewActions.closeSoftKeyboard;
import static android.support.test.espresso.action.ViewActions.typeText;
import static android.support.test.espresso.assertion.ViewAssertions.matches;
import static android.support.test.espresso.matcher.RootMatchers.withDecorView;
import static android.support.test.espresso.matcher.ViewMatchers.isDisplayed;
import static android.support.test.espresso.matcher.ViewMatchers.isRoot;
import static android.support.test.espresso.matcher.ViewMatchers.withContentDescription;
import static android.support.test.espresso.matcher.ViewMatchers.withId;
import static android.support.test.espresso.matcher.ViewMatchers.withText;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.not;
import static org.junit.Assert.assertNotNull;


public class StepDefinitions {

    private static final String TAG = StepDefinitions.class.getSimpleName();

    @Rule
    public ActivityTestRule<LoginOrSignInActivity> activityTestRule = new ActivityTestRule<>(LoginOrSignInActivity.class);
    private Activity mActivity;
    private String username;
    private String password;
    private String userId;
    private UiDevice device;
    private String newPassword;
    private String confirmPassword;
    private List<LoginResponse> loginResponse;
    private static final String ASSET_BASE_PATH = "/assets/";


    /**
     * This Method runs at the beginning of each test case.In this method activity instance has been initialized.
     */
    @Before()
    public void setUp() {
        Log.d("LoginActivitySteps", "called");
        activityTestRule.launchActivity(new Intent());
        mActivity = activityTestRule.getActivity();
        this.device = UiDevice.getInstance(InstrumentationRegistry.getInstrumentation());
    }

    /**
     * This Method runs at the end of each test case. In this method we finish the opened activity.
     */
    @After()
    public void tearDown() {
        activityTestRule.finishActivity();
    }

    /**
     * This method is used to map the line "Given I am on login screen" in the login.feature file.
     */
    @Given("^I am on login screen$")
    public void im_On_a_Login_Screen() {
        assertNotNull(mActivity);
        onView(isRoot()).perform(Delay.waitFor());
    }

    /**
     * This Method is used to map the line "When I input username <username>" in the login.feature file
     * and its written in regular Expresion which matches the feature scenario.
     *
     * @param username is specified in the login.feature file example
     */
    @When("^I input username (\\S+)$")
    public void i_Input_Username(final String username) {
        onView(ViewMatchers.withId(R.id.et_user_name)).perform(typeText(username));
        onView(isRoot()).perform(Delay.waitFor());
    }

    /**
     * This Method is used to map the line "When I input password "<password>"" in the login.feature file
     * and its written in regular Expresion which matches the feature scenario.
     *
     * @param password is specified in the login.feature file example
     */
    @When("^I input password \"(.*?)\"$")
    public void I_input_password(final String password) {
        onView(withId(R.id.et_pwd)).perform(typeText(password), closeSoftKeyboard());
        onView(isRoot()).perform(Delay.waitFor());
    }

    /**
     * This method is used to map the line "When I press login button" in the login.feature file.
     */
    @When("^I press login button$")
    public void I_press_login_buttons() {
        onView(withId(R.id.bt_login)).perform(click());
        onView(isRoot()).perform(Delay.waitFor());
    }

    /**
     * This method is used to map the line "Then I should see error on the Login Screen" in the login.feature file.
     */
    @Then("^I should see error on the Login Screen$")
    public void I_should_see_error_on_the_Login_Screen() {
        onView(withId(R.id.tv_login_error)).check(matches(hasErrorText(mActivity.getString(R.string.username_or_password_mismatch))));
        onView(isRoot()).perform(Delay.waitFor());
    }

    /**
     * This method is used to map the line "Then I should navigate to User Details Screen" in the login.feature file.
     */
    @Then("^I should navigate to User Details Screen$")
    public void I_Should_Navigate_To_User_Details_Screen() {
        onView(withId(R.id.tv_form_title)).check(matches(withText(R.string.user_details)));
        onView(isRoot()).perform(Delay.waitFor());
    }

    @Given("^A login screen$")
    public void A_Login_Screen() {
        assertNotNull(mActivity);
        onView(isRoot()).perform(Delay.waitFor());
    }

    @When("^I Enter username \"(\\S+)\"$")
    public void I_Enter_Username(final String username) {
        this.username = username;
        onView(ViewMatchers.withId(R.id.et_user_name)).perform(typeText(username));
        onView(isRoot()).perform(Delay.waitFor());
    }

    @When("^I Enter password \"(\\S+)\"$")
    public void I_Enter_Password(final String password) {
        this.password = password;
        onView(withId(R.id.et_pwd)).perform(typeText(password), closeSoftKeyboard());
        onView(isRoot()).perform(Delay.waitFor());
    }


    @Given("^I'm on UserDetailsScreen$")
    public void On_UserDetailsScreen() {
        onView(withId(R.id.tv_form_title)).check(matches(withText(R.string.user_details)));
        Log.d(TAG, "UserDetailsScreen");

        String loginResponseJson = "";
        onView(isRoot()).perform(Delay.waitFor());


        try {
            loginResponseJson = inputStreamStringApi19Above();
        } catch (IOException e) {
            e.printStackTrace();
        }
        checkLoginResponse(loginResponseJson, username, password);
    }

    /**
     * This Method is used to get list of users from the loginResponse Json
     *
     * @param userName is specified in the userdetails.feature file example section.
     * @param password is specified in the userdetails.feature file example section.
     */
    private void checkLoginResponse(String loginResponseJson, final String userName, final String password) {
        try {
            JsonObject jsonObject = new Gson().fromJson(loginResponseJson, JsonObject.class);
            JsonElement yourJson = jsonObject.get("loginResponse");
            Type listType = new TypeToken<List<LoginResponse>>() {
            }.getType();
            loginResponse = new Gson().fromJson(yourJson, listType);
            if (loginResponse != null) {
                checkIfUserExist(loginResponse, userName, password);
            }
        } catch (Exception e) {
            Log.d(TAG, e.getMessage());
        }
    }

    /**
     * This method is used to check if the user exist and only if the user exist the test case will pass.
     */
    private void checkIfUserExist(List<LoginResponse> loginResponseList, final String username, final String password) {
        Log.d(TAG, "checkIfUserExist : " + username);
        for (int i = 0; i < loginResponseList.size(); i++) {
            Log.d(TAG, "value is " + loginResponseList.get(i).getUserName() + " " + i);
            if ((username.equals(loginResponseList.get(i).getUserName())) && (password.equals(loginResponseList.get(i).getPassword()))) {
                userId = loginResponseList.get(i).getUserId();
                onView(withText(username)).check(matches(withText(loginResponseList.get(i).getUserName())));
                onView(withText(password)).check(matches(withText(loginResponseList.get(i).getPassword())));
                break;
            }
        }
    }

    /**
     * This Method is used to get the json response.
     *
     * @return the json response.
     */
    private String inputStreamStringApi19Above() throws IOException {
        try (InputStream is = getClass().getResourceAsStream(ASSET_BASE_PATH + "login_response.json")) {
            int size = is.available();
            byte[] buffer = new byte[size];
            final int noOfBytesRead = is.read(buffer);
            Log.d(TAG, "noOfBytesRead : " + noOfBytesRead);
            return new String(buffer, "UTF-8");
        }
    }

    @Then("Verify first name and last name$")
    public void Verify_first_Name_Last_Name() {
        if (loginResponse != null) {
            for (int i = 0; i < loginResponse.size(); i++) {
                if (loginResponse.get(i).getUserId().equals(userId)) {
                    onView(withId(R.id.tv_user_first_name)).check(matches(withText(loginResponse.get(i).getFirstName())));
                    onView(withId(R.id.tv_user_last_name)).check(matches(withText(loginResponse.get(i).getLastName())));
                    onView(isRoot()).perform(Delay.waitFor());
                }
            }
        }

    }

    /**
     * This method is used to map the line "Then I click phone number" in the permission.feature file.
     */
    @Then("^I click phone number$")
    public void I_Click_Phone_Number() {
        onView(withId(R.id.tv_user_phone_no)).perform(click());
    }

    /**
     * This method is used to map the line "Then I Should See Runtime Permission Dialog" in the permission.feature file.
     */
    @Test
    @Then("^I Should See Runtime Permission Dialog$")
    public void I_Should_See_Runtime_Permission_Dialog() {
        UiAutomatorTools.assertViewWithTextIsVisible(device, AppConstants.TEXT_ALLOW);

    }


    /**
     * This Method is used to map the line "And I Click Allow Permission then Permission must be granted" in the permission.feature file
     */
    @Test
    @And("^I Click Allow Permission then Permission must be granted$")
    public void I_Click_Allow_Permission() {
        UiAutomatorTools.allowPermission(device, AppConstants.TEXT_ALLOW);
        onView(isRoot()).perform(Delay.waitFor());

    }

    @Then("^I click on forgot password$")
    public void I_Click_Forgot_password() {
        onView(withId(R.id.text_forgot_password)).perform(click());
    }

    @Then("^I should see a reset password screen$")
    public void Reset_Password_screen() {
        onView(withId(R.id.fragment_reset_password)).check(matches(withContentDescription(R.string.reset_content_description)));
    }

    @And("^I enter new password (\\S+)$")
    public void I_Enter_new_password(String newPassword) {
        this.newPassword = newPassword;
        onView(withId(R.id.et_new_password)).perform(typeText(newPassword));
        onView(isRoot()).perform(Delay.waitFor());
    }

    @Then("^I enter confirm password (\\S+)$")
    public void I_Enter_confirm_password(final String confirmPassword) {
        this.confirmPassword = confirmPassword;
        onView(withId(R.id.et_confirm_password)).perform(typeText(confirmPassword));
        onView(isRoot()).perform(Delay.waitFor());

    }

    @And("^I press reset button$")
    public void reset_button_press() {
        onView(withId(R.id.btn_reset_password)).perform(click());
    }

    @Then("^I should see error message$")
    public void I_should_see_error_message() {
        if (confirmPassword.equals(newPassword)) {

        } else {
            onView(withText(R.string.text_password_mismatch)).inRoot(withDecorView(not(is(activityTestRule.getActivity().getWindow().getDecorView())))).check(matches(isDisplayed()));
            onView(isRoot()).perform(Delay.waitFor());


        }
    }

    private static Matcher<? super View> hasErrorText(String expectedError) {
        return new StepDefinitions.ErrorTextMatcher(expectedError);
    }

    /**
     * Custom matcher to assert equal EditText.setError();
     */
    private static class ErrorTextMatcher extends TypeSafeMatcher<View> {

        private final String mExpectedError;

        private ErrorTextMatcher(String expectedError) {
            mExpectedError = expectedError;
        }

        @Override
        public boolean matchesSafely(View view) {
            if (!(view instanceof TextView)) {
                return false;
            }

            TextView textView = (TextView) view;

            return mExpectedError.equals(textView.getError().toString());
        }

        @Override
        public void describeTo(Description description) {
            description.appendText("with error: " + mExpectedError);
        }
    }

}
