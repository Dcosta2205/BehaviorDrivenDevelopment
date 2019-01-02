package app.lloyd.com.mocktestapp;

import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.EditText;

import com.google.gson.Gson;

import app.lloyd.com.mocktestapp.loginorsignin.LoginOrSignInFragment;
import app.lloyd.com.mocktestapp.loginorsignin.LoginResponse;
import app.lloyd.com.mocktestapp.loginorsignin.Places;
import app.lloyd.com.mocktestapp.loginorsignin.ResetPasswordFragment;
import app.lloyd.com.mocktestapp.userdetails.PlaceDetailFragment;
import app.lloyd.com.mocktestapp.userdetails.UserDetailFragment;
import app.samyad.com.mocktestapp.R;
import yml.com.baselibrary.BaseActivity;

@SuppressWarnings("squid:MaximumInheritanceDepth")
public class MockBaseActivity extends BaseActivity {

    private MockProgressDialog mockProgressDialog;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mockProgressDialog = new MockProgressDialog(this, false);
    }

    public synchronized void showProgress() {
        if (!isFinishing() && mockProgressDialog != null && !mockProgressDialog.isShowing()) {
//            mockProgressDialog.show();
        }
    }

    public synchronized void hideProgress() {
        if (!isFinishing() && mockProgressDialog != null && mockProgressDialog.isShowing()) {
//            mockProgressDialog.cancel();
        }
    }

    public boolean isProgressShown() {
        return !isFinishing() && mockProgressDialog != null && mockProgressDialog.isShowing();
    }

    public void navigateTo(int to, Object data) {
        switch (to) {
            case 9:
                changeFragmentWithOutBackStack(R.id.frag_lay, new LoginOrSignInFragment(),
                        LoginOrSignInFragment.class.getSimpleName());
                break;
            //TODO FingerPrint Flow
//            case 10:
//                if (data instanceof Bundle) {
//                    Bundle bundle = (Bundle) data;
//                    UserDetailFragment userDetailFragment = UserDetailFragment.getInstance(bundle);
//                    changeFragmentWithOutBackStack(R.id.frag_lay, userDetailFragment,
//                            UserDetailFragment.class.getSimpleName());
//                }
//                break;
            case 11:
                if (data instanceof Places) {
                    Bundle bundle = new Bundle();
                    bundle.putString("places", new Gson().toJson(data));
                    PlaceDetailFragment placeDetailFragment = PlaceDetailFragment.getInstance(bundle);
                    changeFragmentWithBackStack(R.id.frag_lay, placeDetailFragment,
                            PlaceDetailFragment.class.getSimpleName());
                }
                break;
            //TODO FingerPrint Flow
//            case 12:
//                if (data instanceof LoginResponse) {
//                    Bundle bundle = new Bundle();
//                    bundle.putString("loginResponse", new Gson().toJson(data));
//                    FingerPrintAuthenticationFragment fingerPrintAuthenticationFragment = FingerPrintAuthenticationFragment.getInstance(bundle);
//                    changeFragmentWithOutBackStack(R.id.frag_lay,
//                            fingerPrintAuthenticationFragment, FingerPrintAuthenticationFragment.class.getSimpleName());
//                }
//                break;
            case 13:
                if (data instanceof LoginResponse) {
                    Bundle bundle = new Bundle();
                    bundle.putString("loginResponse", new Gson().toJson(data));
                    UserDetailFragment userDetailFragment = UserDetailFragment.getInstance(bundle);
                    changeFragmentWithOutBackStack(R.id.frag_lay, userDetailFragment,
                            UserDetailFragment.class.getSimpleName());
                }

            case 14:
                changeFragmentWithBackStack(R.id.frag_lay, ResetPasswordFragment.getInstance(),
                        ResetPasswordFragment.class.getSimpleName());
                break;
            default:
                break;
        }
    }

    public void hideSoftKeyboard(Context context, View view) {
        if (view != null) {
            InputMethodManager imm = (InputMethodManager) context.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (imm != null) {
                imm.hideSoftInputFromWindow(view.getWindowToken(), 0);
            }
        }
    }

    public void showSoftKeyboard(final EditText editText, final Context ctx) {
        if (editText == null || ctx == null) {
            return;
        }
        final InputMethodManager inputMethodManager = (InputMethodManager) ctx.getSystemService(Context.INPUT_METHOD_SERVICE);
        editText.postDelayed(
                () -> {
                    if (inputMethodManager != null) {
                        editText.requestFocus();
                        inputMethodManager.showSoftInput(editText, InputMethodManager.SHOW_IMPLICIT);
                    }
                }, 100);
    }

    @Override
    protected void onDestroy() {
        if (mockProgressDialog != null) {
            mockProgressDialog.dismiss();
        }
        super.onDestroy();
    }
}
