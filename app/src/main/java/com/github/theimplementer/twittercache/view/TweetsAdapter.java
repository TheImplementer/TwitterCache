package com.github.theimplementer.twittercache.view;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.github.theimplementer.twittercache.R;

import twitter4j.Status;

import static android.content.Context.LAYOUT_INFLATER_SERVICE;

public class TweetsAdapter extends ArrayAdapter<Status> {

    public TweetsAdapter(Context context, int resource) {
        super(context, resource);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View view = convertView;

        if(view == null) {
            final LayoutInflater layoutInflater = (LayoutInflater) getContext().getSystemService(LAYOUT_INFLATER_SERVICE);
            view = layoutInflater.inflate(R.layout.tweet_item, null);
        }

        final Status status = getItem(position);

        final TextView tweetItem = (TextView) view.findViewById(R.id.tweet_item);
        tweetItem.setText(status.getText());

        return view;
    }
}
