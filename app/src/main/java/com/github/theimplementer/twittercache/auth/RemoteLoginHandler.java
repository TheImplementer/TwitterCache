package com.github.theimplementer.twittercache.auth;

import android.net.ConnectivityManager;

import com.github.theimplementer.twittercache.preferences.TwitterPreferences;

public class RemoteLoginHandler implements LoginHandler {

    private final LoginTask loginTask;

    public RemoteLoginHandler(TwitterPreferences twitterPreferences,
                              ConnectivityManager connectivityManager,
                              LoginObserver loginObserver) {
        this.loginTask = new LoginTask(twitterPreferences, connectivityManager, loginObserver);
    }

    @Override
    public void login() {
        loginTask.execute();
    }
}
