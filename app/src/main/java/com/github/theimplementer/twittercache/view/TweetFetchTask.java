package com.github.theimplementer.twittercache.view;

import android.os.AsyncTask;

import com.github.theimplementer.twittercache.preferences.TwitterPreferences;

public class TweetFetchTask extends AsyncTask<Void, Void, Tweet> {

    private final TwitterPreferences preferences;

    public TweetFetchTask(TwitterPreferences preferences) {
        this.preferences = preferences;
    }

    @Override
    protected Tweet doInBackground(Void... params) {
        return null;
    }
}
