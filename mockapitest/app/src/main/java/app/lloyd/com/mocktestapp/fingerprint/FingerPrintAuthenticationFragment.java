package app.lloyd.com.mocktestapp.fingerprint;


import android.app.KeyguardManager;
import android.hardware.fingerprint.FingerprintManager;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.annotation.RequiresApi;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import app.lloyd.com.mocktestapp.MockBaseFragment;
import app.lloyd.com.mocktestapp.MockBaseActivity;
import app.lloyd.com.mocktestapp.MockBaseFragment;
import app.samyad.com.mocktestapp.R;

import static android.content.Context.FINGERPRINT_SERVICE;
import static android.content.Context.KEYGUARD_SERVICE;


public class FingerPrintAuthenticationFragment extends MockBaseFragment implements FingerPrintContract.View, FingerprintHandler.FingerPrintAuthenticatedListener {

    private FingerPrintPresenter fingerPrintPresenter;
    private TextView textView;
    private KeyguardManager keyguardManager;
    private FingerprintManager fingerprintManager;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_finger_print_authentication, container, false);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initPresenter();
        initViewsAndInstances(view);
        authenticateFingerPrint();

    }

    public static FingerPrintAuthenticationFragment getInstance(Bundle bundle) {
        FingerPrintAuthenticationFragment fingerPrintAuthenticationFragment = new FingerPrintAuthenticationFragment();
        fingerPrintAuthenticationFragment.setArguments(bundle);
        return fingerPrintAuthenticationFragment;
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    private void authenticateFingerPrint() {
        // Check whether the device has a Fingerprint sensor.
        if (!fingerprintManager.isHardwareDetected()) {
            textView.setText(getString(R.string.message_no_finger_print_hardware_found));
        } else {
            // Check whether at least one fingerprint is registered
            if (!fingerprintManager.hasEnrolledFingerprints()) {
                textView.setText(getString(R.string.message_enroll_finger_prints));
            } else {
                // Checks whether lock screen security is enabled or not
                if (!keyguardManager.isKeyguardSecure()) {
                    textView.setText(getString(R.string.message_lock_screen_security_not_enabled));
                } else {

                    if (fingerPrintPresenter != null) {
                        fingerPrintPresenter.generateKeys();
                    }

                    if (fingerPrintPresenter != null) {
                        if (fingerPrintPresenter.cipherInit()) {
                            FingerprintManager.CryptoObject cryptoObject = new FingerprintManager.CryptoObject(fingerPrintPresenter.getCipherObject());
                            FingerprintHandler helper = new FingerprintHandler(getContext(), this);
                            helper.startAuth(fingerprintManager, cryptoObject);
                        }
                    }
                }
            }
        }
    }


    private void initPresenter() {
        fingerPrintPresenter = new FingerPrintPresenter();
        fingerPrintPresenter.subscribeView(this);
    }


    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initViewsAndInstances(View view) {
        // Initializing both Android Keyguard Manager and Fingerprint Manager
        keyguardManager = (KeyguardManager) getActivity().getSystemService(KEYGUARD_SERVICE);
        fingerprintManager = (FingerprintManager) getActivity().getSystemService(FINGERPRINT_SERVICE);
        textView = view.findViewById(R.id.errorText);

    }

    @Override
    protected void setupViewListeners() {
        //not required
    }

    @Override
    public void showKeyPad() {
        //not required
    }

    @Override
    public void hideKeyPad() {
        //not required
    }

    @Override
    public void loadProgressIndicator() {
        // not required
    }

    @Override
    public void stopProgressIndicator() {
        // not required
    }

    @Override
    public void fingerAuthenticated() {
        Bundle bundle = getArguments();
        if (getActivity() instanceof MockBaseActivity) {
            ((MockBaseActivity) getActivity()).navigateTo(10, bundle);
        }
    }
}
