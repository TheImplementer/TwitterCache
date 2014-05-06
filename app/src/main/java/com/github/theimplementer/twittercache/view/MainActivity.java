package com.github.theimplementer.twittercache.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.net.Uri;
import android.os.Bundle;

import com.github.theimplementer.twittercache.R;
import com.github.theimplementer.twittercache.preferences.TwitterSharedPreferences;

import twitter4j.auth.AccessToken;

import static com.github.theimplementer.twittercache.TwitterInstance.TWITTER_CALLBACK_URL;
import static com.github.theimplementer.twittercache.TwitterInstance.TWITTER_OAUTH_VERIFIER;
import static com.github.theimplementer.twittercache.TwitterInstance.getInstance;

public class MainActivity extends Activity implements AccessTokenUpdater {

    private TwitterSharedPreferences twitterPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        twitterPreferences = new TwitterSharedPreferences(this);

        final Uri data = getIntent().getData();
        if (data != null && data.toString().startsWith(TWITTER_CALLBACK_URL)) {
            final String oauthVerifier = data.getQueryParameter(TWITTER_OAUTH_VERIFIER);
            new AccessTokenRetriever(twitterPreferences, this).execute(oauthVerifier);
        } else {
            final String accessToken = twitterPreferences.getAccessToken();
            final String accessTokenSecret = twitterPreferences.getAccessTokenSecret();
            updateAccessToken(new AccessToken(accessToken, accessTokenSecret));
        }

        final FragmentManager fragmentManager = getFragmentManager();
        final Fragment container = fragmentManager.findFragmentById(R.id.tweet_list_container);
        if (container == null) {
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.tweet_list_container, TweetListFragment.newInstance());
            fragmentTransaction.commit();
        }
    }

    @Override
    public void updateAccessToken(AccessToken accessToken) {
        getInstance().setAccessToken(accessToken);
    }
}
