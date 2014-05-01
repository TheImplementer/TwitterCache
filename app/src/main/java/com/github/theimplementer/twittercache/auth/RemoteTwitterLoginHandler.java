package com.github.theimplementer.twittercache.auth;

import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;

import com.github.theimplementer.twittercache.preferences.TwitterPreferences;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.RequestToken;
import twitter4j.conf.Configuration;
import twitter4j.conf.ConfigurationBuilder;

public class RemoteTwitterLoginHandler extends AsyncTask<Void, Void, LoginResult> {

    private static final String TWITTER_CONSUMER_KEY = "bJ6MYWxDscwN0AC4vs6IwpoF0";
    private static final String TWITTER_CONSUMER_SECRET = "bAtwBczHSganT4ux6oCpxcMYIQpQ1gzvabVrdsWrDe0lA2mjU6";
    private static final String TWITTER_CALLBACK_URL = "oauth://tcache";

    private final TwitterPreferences twitterPreferences;
    private final ConnectivityManager connectivityManager;
    private final LoginObserver loginObserver;

    public RemoteTwitterLoginHandler(TwitterPreferences twitterPreferences,
                                     ConnectivityManager connectivityManager,
                                     LoginObserver loginObserver) {
        this.twitterPreferences = twitterPreferences;
        this.connectivityManager = connectivityManager;
        this.loginObserver = loginObserver;
    }

    @Override
    protected LoginResult doInBackground(Void... voids) {
        try {
            login();
        } catch (Throwable ex) {
            return LoginResult.FAILURE;
        }
        return LoginResult.SUCCESS;
    }

    @Override
    protected void onPostExecute(LoginResult loginResult) {
        if (loginResult == LoginResult.SUCCESS) {
            loginObserver.notifySuccess();
        } else{
            loginObserver.notifyFailure();
        }
    }

    private void login() throws TwitterException {
        if (!isConnected()) throw new OfflineDeviceException();

        if (twitterPreferences.isUserLoggedIn()) {
            return;
        }

        final Configuration configuration = new ConfigurationBuilder().
                setOAuthConsumerKey(TWITTER_CONSUMER_KEY).
                setOAuthConsumerSecret(TWITTER_CONSUMER_SECRET).
                build();

        final Twitter twitter = new TwitterFactory(configuration).getInstance();
        final RequestToken requestToken = twitter.getOAuthRequestToken(TWITTER_CALLBACK_URL);
        twitterPreferences.setUserLoggedIn(true);
        twitterPreferences.setAccessToken(requestToken.getToken());
        twitterPreferences.setAccessTokenSecret(requestToken.getTokenSecret());
    }

    private boolean isConnected() {
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
