package app.lloyd.com.mocktestapp;

import android.content.Context;
import android.os.Bundle;
import android.support.v4.content.ContextCompat;
import android.view.Window;
import android.widget.ProgressBar;

import app.samyad.com.mocktestapp.R;

public class MockProgressDialog extends MockBaseDialog {

    MockProgressDialog(Context context, boolean cancelable) {
        super(context);
        setCancelable(cancelable);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(R.layout.mock_progress_dialog);
        requestLayoutParams();
        ProgressBar progressBar = findViewById(R.id.main_progress_bar);
        progressBar.setIndeterminateDrawable(ContextCompat.getDrawable(getContext(),
                R.drawable.custom_progress));
    }

}
