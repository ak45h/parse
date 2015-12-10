package com.example.dell.parselogintutorial;

import android.app.Application;

import com.parse.Parse;
import com.parse.ParseACL;
import com.parse.ParseUser;

public class ParseApplication extends Application{

    @Override
    public void onCreate() {
        super.onCreate();

        // Add your initialization code here
        Parse.initialize(this, "Xhl2AnwwnEMGvjAj9g8zzbSBtuPYOfQ3lXsjpqgT", "V6DoIhhTnMHFpL9BwH2DJcLmhWuELQ1EA0haF2i0");

        ParseUser.enableAutomaticUser();
        ParseACL defaultACL = new ParseACL();

        // If you would like all objects to be private by default, remove this
        // line.
        defaultACL.setPublicReadAccess(true);

        ParseACL.setDefaultACL(defaultACL, true);
    }
}
