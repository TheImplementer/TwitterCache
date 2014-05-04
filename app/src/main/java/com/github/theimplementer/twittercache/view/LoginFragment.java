package com.github.theimplementer.twittercache.view;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.github.theimplementer.twittercache.R;
import com.github.theimplementer.twittercache.auth.LoginHandler;
import com.github.theimplementer.twittercache.auth.RemoteLoginHandler;
import com.github.theimplementer.twittercache.preferences.TwitterSharedPreferences;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class LoginFragment extends Fragment {

    private Button loginButton;
    private LoginHandler loginHandler;
    private TwitterSharedPreferences twitterPreferences;
    private ConnectivityManager connectivityManager;

    public LoginFragment() {
    }

    public static Fragment newInstance() {
        return new LoginFragment();
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.connectivityManager = (ConnectivityManager) getActivity().getSystemService(CONNECTIVITY_SERVICE);
        this.twitterPreferences = new TwitterSharedPreferences(getActivity());
        this.loginHandler = new RemoteLoginHandler(connectivityManager, getActivity());
//        this.loginHandler = new FakeLoginHandler(this);
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
                    displayErrorDialog(R.string.device_offline_message);
                    return;
                }
                loginHandler.login();
            }
        });

        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        if (!isConnected()) {
            displayErrorDialog(R.string.device_offline_message);
            return;
        }

        if (twitterPreferences.isUserLoggedIn()) {
            final Intent intent = new Intent(getActivity(), MainActivity.class);
            startActivity(intent);
        }
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
        final NetworkInfo activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
        return activeNetworkInfo != null && activeNetworkInfo.isConnectedOrConnecting();
    }
}
