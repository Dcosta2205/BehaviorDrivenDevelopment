package app.lloyd.com.mocktestapp.loginorsignin;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.Objects;

import app.lloyd.com.mocktestapp.MockBaseActivity;
import app.lloyd.com.mocktestapp.MockBaseFragment;
import app.samyad.com.mocktestapp.R;

public class LoginOrSignInFragment extends MockBaseFragment implements
        View.OnClickListener,
        TextView.OnEditorActionListener,
        TextWatcher,
        LoginContract.LoginView {

    private static final String TAG = LoginOrSignInActivity.class.getSimpleName();
    private Button loginBtn;
    private EditText etUserName;
    private EditText etPassword;
    private TextView tvLoginError;
    private TextView tvForgotPassword;
    private LoginPresenter loginPresenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_login_or_signin, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsAndInstances(view);
        Log.d("LoginOrSiginFragment", "called");
        initPresenter();
    }

    private void initPresenter() {
        loginPresenter = new LoginPresenter(new LoginInteractor());
        loginPresenter.subscribeView(this);
    }

    @Override
    protected void initViewsAndInstances(View view) {
        loginBtn = view.findViewById(R.id.bt_login);
        etUserName = view.findViewById(R.id.et_user_name);
        etPassword = view.findViewById(R.id.et_pwd);
        tvLoginError = view.findViewById(R.id.tv_login_error);
        tvForgotPassword = view.findViewById(R.id.text_forgot_password);
//        etUserName.requestFocus();
//        showKeyPad();
        setupViewListeners();
    }

    @Override
    protected void setupViewListeners() {
        loginBtn.setOnClickListener(this);
        tvForgotPassword.setOnClickListener(this);
        etPassword.addTextChangedListener(this);
        etPassword.setOnEditorActionListener(this);
        etUserName.addTextChangedListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.bt_login) {
            callLogin();
            hideKeyPad();
        } else if (v.getId() == R.id.text_forgot_password) {
            ((MockBaseActivity) Objects.requireNonNull(getActivity())).navigateTo(14, null);
        }
    }

    private void callLogin() {
        String userName = etUserName.getText().toString();
        String password = etPassword.getText().toString();
        if (loginPresenter != null && !TextUtils.isEmpty(userName) &&
                !TextUtils.isEmpty(password)) {
            loginPresenter.callLogin(userName, password);
        }
    }

    private void checkTextAndSetBtn() {
        tvLoginError.setVisibility(View.INVISIBLE);
        loginBtn.setEnabled(etUserName != null && etPassword != null &&
                !TextUtils.isEmpty(etUserName.getText()) && !TextUtils.isEmpty(etPassword.getText()));
    }

    @Override
    public void beforeTextChanged(CharSequence s, int start, int count, int after) {
        //not used
    }

    @Override
    public void onTextChanged(CharSequence s, int start, int before, int count) {
        //not used
    }

    @Override
    public void afterTextChanged(Editable s) {
        checkTextAndSetBtn();
    }

    @Override
    public void onDetach() {
        if (loginPresenter != null) {
            loginPresenter.onDestroyPresenter();
        }
        super.onDetach();
    }

    @Override
    public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
        Log.d(TAG, "action Id : " + actionId);
        if (actionId == EditorInfo.IME_ACTION_DONE) {
            tvLoginError.setVisibility(View.INVISIBLE);
            hideKeyPad();
            callLogin();
        }
        return true;
    }

    @Override
    public void loginSuccess(LoginResponse loginResponse) {
        Log.d(TAG, "Login Success!");
        if (getActivity() instanceof MockBaseActivity) {
            //TODO FingerPrint Flow
//            if (PreferenceHelper.getBoolPreference(PreferenceHelper.FINGER_AUTHENTICATED)) {
//                ((MockBaseActivity) getActivity()).navigateTo(13, loginResponse);
//            } else {
            ((MockBaseActivity) getActivity()).navigateTo(13, loginResponse);
//            }
        }
    }

    @Override
    public void loginFailure(final int errorMsg) {
        Log.d(TAG, "Login Failed : " + errorMsg);
        if (isAdded()) {
            tvLoginError.setVisibility(View.VISIBLE);
            loginBtn.setEnabled(false);
            tvLoginError.setError(getString(R.string.username_or_password_mismatch));
        }
    }

    @Override
    public void showKeyPad() {
        if (getContext() != null && getActivity() instanceof MockBaseActivity && !getActivity().isFinishing()) {
            ((MockBaseActivity) getActivity()).showSoftKeyboard(etUserName, getContext());
        }
    }

    @Override
    public void hideKeyPad() {
        if (getContext() != null && getActivity() instanceof MockBaseActivity && !getActivity().isFinishing()) {
            ((MockBaseActivity) getActivity()).hideSoftKeyboard(getContext(), etUserName);
        }
    }

    @Override
    public void loadProgressIndicator() {
        showDialog();
    }

    @Override
    public void stopProgressIndicator() {
        hideDialog();
    }
}
