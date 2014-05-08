package com.github.theimplementer.twittercache.view;

import android.app.Activity;
import android.app.Fragment;
import android.app.FragmentManager;
import android.app.FragmentTransaction;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;

import com.github.theimplementer.twittercache.R;
import com.github.theimplementer.twittercache.preferences.TwitterPreferences;
import com.github.theimplementer.twittercache.preferences.TwitterSharedPreferences;

import twitter4j.Status;
import twitter4j.auth.AccessToken;

import static android.content.Intent.ACTION_MAIN;
import static android.content.Intent.CATEGORY_HOME;
import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static android.content.Intent.FLAG_ACTIVITY_NEW_TASK;
import static com.github.theimplementer.twittercache.TwitterInstance.TWITTER_CALLBACK_URL;
import static com.github.theimplementer.twittercache.TwitterInstance.TWITTER_OAUTH_VERIFIER;
import static com.github.theimplementer.twittercache.TwitterInstance.getInstance;

public class MainActivity extends Activity implements AccessTokenUpdater, TweetItemClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        final TwitterPreferences twitterPreferences = new TwitterSharedPreferences(this);

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
        final Fragment tweetsFragment = TweetListFragment.newInstance();
        final Fragment container = fragmentManager.findFragmentById(R.id.fragment_container);
        if (container == null) {
            final FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
            fragmentTransaction.add(R.id.fragment_container, tweetsFragment);
            fragmentTransaction.commit();
        }
    }

    @Override
    public void onBackPressed() {
        final FragmentManager fragmentManager = getFragmentManager();
        if (fragmentManager.getBackStackEntryCount() == 0) {
            final Intent intent = new Intent(ACTION_MAIN);
            intent.addCategory(CATEGORY_HOME);
            intent.addFlags(FLAG_ACTIVITY_NEW_TASK | FLAG_ACTIVITY_CLEAR_TOP);
            startActivity(intent);
        } else {
            fragmentManager.popBackStack();
        }
    }

    @Override
    public void updateAccessToken(AccessToken accessToken) {
        getInstance().setAccessToken(accessToken);
    }

    @Override
    public void displayDetailsFor(Status status) {
        final FragmentManager fragmentManager = getFragmentManager();
        final FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(R.id.fragment_container, TweetDetailsFragment.newInstance(status));
        transaction.addToBackStack(null);
        transaction.commit();
    }
}
