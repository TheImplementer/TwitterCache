package com.github.theimplementer.twittercache.view;


import android.app.Fragment;
import android.app.ListFragment;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import com.github.theimplementer.twittercache.R;
import com.github.theimplementer.twittercache.TwitterInstance;
import com.github.theimplementer.twittercache.preferences.TwitterSharedPreferences;

import java.util.List;

import twitter4j.Status;

import static android.content.Intent.FLAG_ACTIVITY_CLEAR_TOP;
import static com.github.theimplementer.twittercache.TwitterInstance.getInstance;

public class TweetListFragment extends ListFragment implements Updatable<Status> {

    private TweetsAdapter tweetsAdapter;
    private TweetsFetcher tweetsFetcher;
    private TwitterSharedPreferences twitterPreferences;

    public TweetListFragment() {
        this.tweetsFetcher = new RemoteTweetsFetcher(this);
    }

    public static Fragment newInstance() {
        return new TweetListFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setHasOptionsMenu(true);
        twitterPreferences = new TwitterSharedPreferences(getActivity());
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        tweetsAdapter = new TweetsAdapter(getActivity(), android.R.layout.simple_list_item_1);
        setListAdapter(tweetsAdapter);
        return super.onCreateView(inflater, container, savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        tweetsFetcher.fetch();
    }

    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        inflater.inflate(R.menu.main_menu, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.logout:
                twitterPreferences.setUserLoggedOut();
                getInstance().resetInstance();
                final Intent intent = new Intent(getActivity(), LoginActivity.class);
                intent.addFlags(FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    public void add(List<Status> tweets) {
        tweetsAdapter.addAll(tweets);
        tweetsAdapter.notifyDataSetChanged();
    }
}
