package app.lloyd.com.utils;

import android.support.test.uiautomator.UiDevice;
import android.support.test.uiautomator.UiObject;
import android.support.test.uiautomator.UiObjectNotFoundException;
import android.support.test.uiautomator.UiSelector;

/**
 * This class is used to Grant and Deny Permissions Automatically
 */
public class UiAutomatorTools {

    private UiAutomatorTools() {
        // no instances
    }

    /**
     * This Method is used to assert when the requested text is visible , if its not visible then an exception is thrown.
     *
     * @param text text here is Allow, Deny or  Never Show Again.
     */
    public static void assertViewWithTextIsVisible(UiDevice device, String text) {
        UiObject allowButton = device.findObject(new UiSelector().text(text));
        if (!allowButton.exists()) {
            throw new AssertionError("View with text <" + text + "> not found!");
        }
    }

    /**
     * This Method is used to Deny the Permission
     *
     * @param device with device instance we can stimulate user actions like pressing on allow, deny etc.
     * @throws UiObjectNotFoundException
     */
    public static void denyCurrentPermission(UiDevice device) throws UiObjectNotFoundException {
        UiObject denyButton = device.findObject(new UiSelector().text(AppConstants.TEXT_DENY));
        denyButton.click();
    }

    /**
     * This Method is used to Allow the Permission
     *
     * @param device with device instance we can stimulate user actions like pressing on allow, deny etc.
     * @param text   The text here is Allow using which we access allow button.
     */
    public static void allowPermission(UiDevice device, String text) {
        UiObject allowButton = device.findObject(new UiSelector().text(text));
        if (!allowButton.exists()) {
            throw new AssertionError("View with text <" + text + "> not found!");
        } else {
            try {
                allowButton.click();
            } catch (UiObjectNotFoundException e) {
                e.printStackTrace();
            }
        }
    }

    //TODO Never show again Dialog
//    public static void denyCurrentPermissionPermanently(UiDevice device, String text) throws UiObjectNotFoundException {
//        UiObject neverAskAgainCheckBox = device.findObject(new UiSelector().text(text));
//        if (!neverAskAgainCheckBox.exists()) {
//            throw new AssertionError("View with text <" + text + "> not found!");
//        } else {
//            neverAskAgainCheckBox.click();
//            denyCurrentPermission(device);
//        }
//    }
}
