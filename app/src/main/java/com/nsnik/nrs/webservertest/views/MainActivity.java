package com.nsnik.nrs.webservertest.views;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.widget.Button;
import android.widget.Toast;

import com.nsnik.nrs.webservertest.R;
import com.nsnik.nrs.webservertest.views.fragments.HomeFragment;
import com.nsnik.nrs.webservertest.views.fragments.ListFragment;

import butterknife.BindView;
import butterknife.ButterKnife;
import timber.log.Timber;

/**
 * @author nikhil
 * @version 1.0
 * @since 14-02-2018
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainToolbar)
    Toolbar mMainToolbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        initialize();
    }

    /**
     *
     */
    private void initialize() {
        setSupportActionBar(mMainToolbar);
        getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, new HomeFragment()).commit();
    }

    private boolean checkConnection() {
        final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

}
