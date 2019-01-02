package app.lloyd.com.mocktestapp;

import android.content.Context;
import android.support.v7.app.AppCompatDialog;
import android.util.DisplayMetrics;
import android.view.Display;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;

public class MockBaseDialog extends AppCompatDialog {

    private static final double DIALOG_WIDTH_MIN_MAX = .9;

    MockBaseDialog(Context context) {
        super(context);
    }

    public MockBaseDialog(Context context, int theme) {
        super(context, theme);
    }

    protected MockBaseDialog(Context context, boolean cancelable, OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    final void requestLayoutParams() {
        WindowManager windowManager = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
        Display display = windowManager.getDefaultDisplay();
        DisplayMetrics metrics = new DisplayMetrics();
        display.getMetrics(metrics);
        Double width = metrics.widthPixels * DIALOG_WIDTH_MIN_MAX;
        Window dialogWindow = getWindow();
        if (dialogWindow != null) {
            dialogWindow.setLayout(width.intValue(), ViewGroup.LayoutParams.WRAP_CONTENT);
            dialogWindow.setDimAmount(0.0f);
            View v = dialogWindow.getDecorView();
            if (v != null) {
                v.setBackgroundResource(android.R.color.transparent);
            }
        }
    }
}
