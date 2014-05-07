package com.github.theimplementer.twittercache.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.theimplementer.twittercache.R;

import java.text.SimpleDateFormat;

import twitter4j.Status;
import twitter4j.User;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;
import static java.lang.String.format;

public class TweetsAdapter extends ArrayAdapter<Status> {

    private static final String AUTHOR_FORMAT = "%s (@%s)";
    private static final SimpleDateFormat DATE_FORMAT = new SimpleDateFormat("dd/MM - HH:mm");

    public TweetsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if (view == null) {
            final LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.tweet_item, null);
        }

        final Status status = getItem(position);

        final TextView tweetAuthor = (TextView) view.findViewById(R.id.tweet_author);
        final TextView tweetDate = (TextView) view.findViewById(R.id.tweet_date);
        final TextView tweetContent = (TextView) view.findViewById(R.id.tweet_content);
        final User author = status.getUser();
        tweetAuthor.setText(format(AUTHOR_FORMAT, author.getName(), author.getScreenName()));
        tweetDate.setText(DATE_FORMAT.format(status.getCreatedAt()));
        tweetContent.setText(status.getText());

        return view;
    }
}
