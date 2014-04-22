package com.github.theimplementer.twittercache;


import android.app.AlertDialog;
import android.app.Fragment;
import android.content.DialogInterface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import static android.content.Context.CONNECTIVITY_SERVICE;


public class LoginFragment extends Fragment {

    private Button loginButton;

    public LoginFragment() {
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
                    displayConnectivityError();
                    return;
                }
            }
        });

        return view;
    }

    private void displayConnectivityError() {
        final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(getActivity());
        final AlertDialog alertDialog = dialogBuilder.
                setMessage(R.string.connection_error).
                setTitle(R.string.connection_error_title).
                setNeutralButton(R.string.ok, new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialogInterface, int i) {
                        dialogInterface.dismiss();
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
