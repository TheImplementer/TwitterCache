package com.github.theimplementer.twittercache.view;

import android.os.AsyncTask;

import java.util.ArrayList;
import java.util.List;

import twitter4j.Paging;
import twitter4j.Twitter;
import twitter4j.TwitterException;

import static com.github.theimplementer.twittercache.TwitterInstance.getInstance;

public class TweetFetchTask extends AsyncTask<Integer, Void, List<twitter4j.Status>> {

    private static final int DEFAULT_PAGE_SIZE = 20;

    private final Updatable<twitter4j.Status> updatable;
    private final Twitter twitter;

    public TweetFetchTask(Updatable<twitter4j.Status> updatable) {
        this.updatable = updatable;
        this.twitter = getInstance().getTwitter();
    }

    @Override
    protected List<twitter4j.Status> doInBackground(Integer... params) {
        int numberOfTweets = params[0];
        final ArrayList<twitter4j.Status> tweets = new ArrayList<twitter4j.Status>();
        for (int tweetCount = 0; tweetCount < numberOfTweets; tweetCount += DEFAULT_PAGE_SIZE) {
            tweets.addAll(getTweetsUsing(new Paging(tweetCount / DEFAULT_PAGE_SIZE + 1, DEFAULT_PAGE_SIZE)));
            if ((tweetCount + DEFAULT_PAGE_SIZE >= numberOfTweets)) {
                tweets.addAll(getTweetsUsing(new Paging(tweetCount / DEFAULT_PAGE_SIZE + 2, numberOfTweets - tweetCount)));
            }
        }
        return tweets;
    }

    @Override
    protected void onPostExecute(List<twitter4j.Status> tweets) {
        updatable.add(tweets);
    }

    private List<twitter4j.Status> getTweetsUsing(Paging paging) {
        try {
            return twitter.getHomeTimeline(paging);
        } catch (TwitterException ex) {
            throw new RuntimeException(ex);
        }
    }
}
