package com.github.theimplementer.twittercache.view;

import android.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.github.theimplementer.twittercache.R;

import java.text.SimpleDateFormat;

import twitter4j.Status;
import twitter4j.User;

import static java.lang.String.format;
import static java.lang.String.valueOf;

public class TweetDetailsFragment extends Fragment {

    public static final String TWEET_STATUS_KEY = "tweet_status";
    private Status status;

    public TweetDetailsFragment() {
    }

    public static Fragment newInstance(Status status) {
        final Fragment fragment = new TweetDetailsFragment();
        final Bundle args = new Bundle();
        args.putSerializable(TWEET_STATUS_KEY, status);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        final Bundle arguments = getArguments();
        status = (Status) arguments.getSerializable(TWEET_STATUS_KEY);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.tweet_details, container, false);

        final TextView authorTextField = (TextView) view.findViewById(R.id.tweet_details_author);
        final TextView dateTextField = (TextView) view.findViewById(R.id.tweet_details_date);
        final TextView contentTextField = (TextView) view.findViewById(R.id.tweet_details_content);
        final TextView retweetTextField = (TextView) view.findViewById(R.id.tweet_details_rt_count);
        final TextView favouriteTextField = (TextView) view.findViewById(R.id.tweet_details_fv_count);

        final User user = status.getUser();
        authorTextField.setText(format("%s (@%s)", user.getName(), user.getScreenName()));
        final SimpleDateFormat dateFormat = new SimpleDateFormat("dd/MM/yyy - HH:mm:ss");
        dateTextField.setText(dateFormat.format(status.getCreatedAt()));
        contentTextField.setText(status.getText());
        retweetTextField.setText(valueOf(status.getRetweetCount()));
        favouriteTextField.setText(valueOf(status.getFavoriteCount()));

        return view;
    }
}
