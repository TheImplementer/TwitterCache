package com.github.theimplementer.twittercache;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import twitter4j.TwitterException;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class LoginFragment extends Fragment {

    private static final String TAG = "LoginFragment";

    private Button loginButton;
    private TwitterLoginHandler twitterLoginHandler;

    public LoginFragment() {
        twitterLoginHandler = new TwitterLoginHandler(new TwitterSharedPreferences(getActivity()));
    }

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        final View view = inflater.inflate(R.layout.fragment_login, container, false);

        loginButton = (Button) view.findViewById(R.id.login_button);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (!isConnected()) {
                    displayErrorDialog(R.string.connection_error);
                    return;
                }
                try {
                    twitterLoginHandler.login();
                } catch (TwitterException ex) {
                    displayErrorDialog(R.string.login_error);
                    Log.d(TAG, ex.getErrorMessage());
                    return;
                }
            }
        });

        return view;
    }

    private void displayErrorDialog(int message) {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        final AlertDialog alertDialog = dialogBuilder.
                setMessage(message).
                setTitle(R.string.connection_error_title).
                setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                    }
                }).create();
        alertDialog.show();
    }

    private boolean isConnected() {
        final ConnectivityManager connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
