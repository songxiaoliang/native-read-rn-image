package com.imagetest;

import android.os.Bundle;

import com.facebook.react.ReactActivity;
import com.imagetest.splashscreen.RCTSplashScreen;

public class MainActivity extends ReactActivity {

    /**
     * Returns the name of the main component registered from JavaScript.
     * This is used to schedule rendering of the component.
     */
    @Override
    protected String getMainComponentName() {
        return "imagetest";
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        RCTSplashScreen.openSplashScreen(this);
        super.onCreate(savedInstanceState);
    }
}
