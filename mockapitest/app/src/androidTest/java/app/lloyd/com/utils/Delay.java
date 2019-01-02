package app.lloyd.com.utils;

import android.support.test.espresso.UiController;
import android.support.test.espresso.ViewAction;
import android.view.View;

import org.hamcrest.Matcher;

import static android.support.test.espresso.matcher.ViewMatchers.isRoot;

public class Delay {

    /**
     * Perform action of waiting for a specific time.
     */
    public static ViewAction waitFor() {
        return new ViewAction() {
            @Override
            public Matcher<View> getConstraints() {
                return isRoot();
            }

            /**
             * Returns a description of the view action. The description should not be overly long and should
             * fit nicely in a sentence like: "performing %description% action on view with id ..."
             */
            @Override
            public String getDescription() {
                return "Wait for " + "1s" + " milliseconds.";
            }

            /**
             * Performs this action on the given view.
             *
             * @param uiController the controller to use to interact with the UI.
             * @param view the view to act upon. never null.
             */
            @Override
            public void perform(UiController uiController, final View view) {
                uiController.loopMainThreadForAtLeast(1000);
            }
        };
    }
}
