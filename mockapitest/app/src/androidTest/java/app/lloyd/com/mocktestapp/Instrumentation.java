package app.lloyd.com.mocktestapp;

import android.os.Bundle;
import android.support.test.runner.MonitoringInstrumentation;

import cucumber.api.CucumberOptions;
import cucumber.api.android.CucumberInstrumentationCore;

/**
 * This Class is a custom Instrumentation Runner to run Cucumber Test cases and we need to specify
 * this class path in the gradle.
 */
@CucumberOptions(
        features = "features",
        tags = {" @reset-feature"},
        glue = "app.lloyd.com.mocktestapp")
public class Instrumentation extends MonitoringInstrumentation {

    private final CucumberInstrumentationCore instrumentationCore = new CucumberInstrumentationCore(this);

    @Override
    public void onCreate(Bundle arguments) {
        super.onCreate(arguments);
        instrumentationCore.create(arguments);
        start();
    }

    @Override
    public void onStart() {
        super.onStart();
        waitForIdleSync();
        instrumentationCore.start();
    }
}