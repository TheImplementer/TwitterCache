package com.github.theimplementer.twittercache.view;

import android.os.AsyncTask;

import com.github.theimplementer.twittercache.TwitterInstance;
import com.github.theimplementer.twittercache.preferences.TwitterPreferences;

import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.auth.AccessToken;
import twitter4j.auth.RequestToken;

import static com.github.theimplementer.twittercache.TwitterInstance.getInstance;

class AccessTokenRetriever extends AsyncTask<String, Void, AccessToken> {

    private final TwitterPreferences twitterPreferences;
    private final AccessTokenUpdater accessTokenUpdater;

    public AccessTokenRetriever(TwitterPreferences twitterPreferences,
                                AccessTokenUpdater accessTokenUpdater) {
        this.twitterPreferences = twitterPreferences;
        this.accessTokenUpdater = accessTokenUpdater;
    }

    @Override
    protected AccessToken doInBackground(String... params) {
        final TwitterInstance twitterInstance = getInstance();
        final RequestToken requestToken = twitterInstance.getRequestToken();
        final Twitter twitter = twitterInstance.getTwitter();
        try {
            return twitter.getOAuthAccessToken(requestToken, params[0]);
        } catch (TwitterException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void onPostExecute(AccessToken accessToken) {
        twitterPreferences.setUserLoggedIn(accessToken.getToken(), accessToken.getTokenSecret());
        accessTokenUpdater.updateAccessToken(accessToken);
    }
}
