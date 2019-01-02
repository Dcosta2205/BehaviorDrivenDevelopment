package app.lloyd.com.mocktestapp.loginorsignin;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import app.lloyd.com.mocktestapp.MockBaseActivity;
import app.samyad.com.mocktestapp.R;
import app.lloyd.com.utils.PreferenceHelper;

@SuppressWarnings("squid:MaximumInheritanceDepth")
public class LoginOrSignInActivity extends MockBaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_or_signin);
        PreferenceHelper.initPreferences(this);
        navigateTo(9,null);
    }

}
