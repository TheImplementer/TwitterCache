package com.github.theimplementer.twittercache.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.github.theimplementer.twittercache.R;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

import twitter4j.Status;
import twitter4j.User;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static java.lang.String.format;

public class TweetsAdapter extends BaseAdapter {

    private static final String AUTHOR_FORMAT = "%s (@%s)";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM - HH:mm");

    private final List<Status> tweets = new ArrayList<Status>();
    private final Context context;

    public TweetsAdapter(Context context) {
        this.context = context;
    }

    @Override
    public int getCount() {
        return tweets.size();
    }

    @Override
    public Object getItem(int position) {
        return tweets.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater = (LayoutInflater) context.getSystemService(LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.tweet_item, parent, false);
        }

        final Status status = (Status) getItem(position);

        final TextView tweetAuthor = (TextView) view.findViewById(R.id.tweet_author);
        final TextView tweetDate = (TextView) view.findViewById(R.id.tweet_date);
        final TextView tweetContent = (TextView) view.findViewById(R.id.tweet_content);
        final User author = status.getUser();
        tweetAuthor.setText(format(AUTHOR_FORMAT, author.getName(), author.getScreenName()));
        tweetDate.setText(DATE_FORMAT.format(status.getCreatedAt()));
        tweetContent.setText(status.getText());

        return view;
    }

    public void addTweets(Collection<Status> newTweets) {
        final List<Status> statuses = new LinkedList<Status>(newTweets);
        for (int count = 0; count < getCount(); count++) {
            statuses.add((Status) getItem(count));
        }
        tweets.clear();
        tweets.addAll(newTweets);
    }
}
