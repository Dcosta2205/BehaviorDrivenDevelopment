package app.lloyd.com.mocktestapp.loginorsignin;


import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import java.util.Objects;

import app.lloyd.com.mocktestapp.MockBaseFragment;
import app.samyad.com.mocktestapp.R;

public class ResetPasswordFragment extends MockBaseFragment implements View.OnClickListener {
    private EditText etNewPassword;
    private EditText etConfirmPassword;
    private Button btnReset;

    public static ResetPasswordFragment getInstance() {
        return new ResetPasswordFragment();
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_reset_password, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initViewsAndInstances(view);
        setupViewListeners();
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.btn_reset_password) {
            if (etNewPassword.getText().toString().isEmpty()) {
                etNewPassword.setError("Field is empty");
            } else if (etConfirmPassword.getText().toString().isEmpty()) {
                etConfirmPassword.setError("Field is empty");
            } else if ((etNewPassword.getText().toString().equals(etConfirmPassword.getText().toString()))) {
                showShortToast("Password has been reset successfully");
                Objects.requireNonNull(getActivity()).onBackPressed();
            } else {
                showShortToast(getString(R.string.text_password_mismatch));
            }

        }
    }

    @Override
    protected void initViewsAndInstances(View view) {
        etNewPassword = view.findViewById(R.id.et_new_password);
        etConfirmPassword = view.findViewById(R.id.et_confirm_password);
        btnReset = view.findViewById(R.id.btn_reset_password);
    }

    @Override
    protected void setupViewListeners() {
        btnReset.setOnClickListener(this);
    }

}
