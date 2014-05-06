package com.github.theimplementer.twittercache.view;

import android.os.AsyncTask;

import com.github.theimplementer.twittercache.TwitterInstance;

import java.util.List;

import twitter4j.Twitter;
import twitter4j.TwitterException;

import static com.github.theimplementer.twittercache.TwitterInstance.getInstance;

public class TweetFetchTask extends AsyncTask<Void, Void, List<twitter4j.Status>> {

    private final Updatable<twitter4j.Status> updatable;

    public TweetFetchTask(Updatable<twitter4j.Status> updatable) {
        this.updatable = updatable;
    }

    @Override
    protected List<twitter4j.Status> doInBackground(Void... params) {
        final Twitter twitter = getInstance().getTwitter();
        try {
            return twitter.getHomeTimeline();
        } catch (TwitterException ex) {
            throw new RuntimeException(ex);
        }
    }

    @Override
    protected void onPostExecute(List<twitter4j.Status> tweets) {
        updatable.add(tweets);
    }
}
