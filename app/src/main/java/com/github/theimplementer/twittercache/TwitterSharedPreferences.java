package com.github.theimplementer.twittercache;

import android.content.Context;
import android.content.SharedPreferences;

import static android.content.Context.MODE_PRIVATE;
import static android.content.SharedPreferences.Editor;

public class TwitterSharedPreferences implements TwitterPreferences {
    private static final String TCACHE_SHARED_KEY = "tcache_shared";
    private static final String USER_LOGGED_IN = "user_logged_in";

    private final SharedPreferences sharedPreferences;

    public TwitterSharedPreferences(Context context) {
        this.sharedPreferences = context.getSharedPreferences(TCACHE_SHARED_KEY, MODE_PRIVATE);
    }

    @Override
    public void setUserLoggedIn(boolean loggedIn) {
        final Editor preferencesEditor = sharedPreferences.edit();
        preferencesEditor.putBoolean(USER_LOGGED_IN, loggedIn);
        preferencesEditor.commit();
    }

    @Override
    public boolean isUserLoggedIn() {
        return sharedPreferences.getBoolean(USER_LOGGED_IN, false);
    }
}
