package com.github.theimplementer.twittercache.auth;

import android.content.Context;
import android.net.ConnectivityManager;

import com.github.theimplementer.twittercache.preferences.TwitterPreferences;

public class RemoteLoginHandler implements LoginHandler {

    private final ConnectivityManager connectivityManager;
    private final Context context;

    public RemoteLoginHandler(ConnectivityManager connectivityManager,
                              Context context) {
        this.connectivityManager = connectivityManager;
        this.context = context;
    }

    @Override
    public void login() {
        final LoginTask loginTask = new LoginTask(connectivityManager, context);
        loginTask.execute();
    }
}
