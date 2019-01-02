Setup:

Step1:

The goal is to make your environment ready to be able to write and run Cucumber tests. 
Below are the steps for the setup:

1. Install Gherkin plugin (Android Studio ->File ->Settings -> Plugin)
2. Add Cucumber and Espresso dependencies.
3. Create a custom Cucumber instrumentation runner.
4. Add an test application ID and Runner.
5. Add sourceSets.

Step2:

Add Cucumber and Espresso dependencies
Add these lines in dependencies block in app/build.gradle

     //Expresso library
     androidTestImplementation 'com.android.support.test.espresso:espresso-core:3.0.2'
     androidTestImplementation 'com.android.support.test.espresso:espresso-contrib:2.0'

    //cucumber framework libraries
    androidTestImplementation 'info.cukes:cucumber-android:1.2.5@jar'
    androidTestImplementation 'info.cukes:cucumber-picocontainer:1.2.4'
  
Step3:

Create a custom instrumentation runner.

Step4:

Add an test application ID and Runner
Add these lines in android > defaultConfig block in app/build.gradle.

testApplicationId "com.your.app.test"
testInstrumentationRunner "com.your.app.test.Instrumentation"

Modify testApplicationId and testInstrumentationRunner to match with your app name and package name that you placed the Instrumentation class from the previous step in.

Step 5:

Add sourceSets
Add this block inside android block.

sourceSets {        
    androidTest {            
        assets.srcDirs = ['src/androidTest/assets']        
    }    
}
