package net.simplifiedcoding.activeandroid;

import android.app.Application;

import com.activeandroid.ActiveAndroid;

/**
 * Created by Belal on 5/26/2016.
 */

public class MyApplication extends Application {

    @Override
    public void onCreate() {
        super.onCreate();

        //Initializing Active Android
        ActiveAndroid.initialize(this);
    }
}