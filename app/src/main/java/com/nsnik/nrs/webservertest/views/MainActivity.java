package com.nsnik.nrs.webservertest.views;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import com.nsnik.nrs.webservertest.BuildConfig;
import com.nsnik.nrs.webservertest.MyApplication;
import com.nsnik.nrs.webservertest.R;
import com.nsnik.nrs.webservertest.views.fragments.HomeFragment;
import com.squareup.leakcanary.RefWatcher;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * @author nikhil
 * @version 1.0
 * @since 14-02-2018
 */
public class MainActivity extends AppCompatActivity {

    @BindView(R.id.mainToolbar)
    Toolbar mMainToolbar;

    @BindView(R.id.mainLayout)
    ConstraintLayout mMainLayout;

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
        if (checkConnection())
            getSupportFragmentManager().beginTransaction().add(R.id.mainContainer, new HomeFragment()).commit();
        else
            Snackbar.make(mMainLayout, getResources().getString(R.string.networkErrorNoNetwork), Snackbar.LENGTH_INDEFINITE)
                    .setAction(getResources().getString(R.string.retry), v -> Toast.makeText(MainActivity.this, "Test", Toast.LENGTH_SHORT).show())
                    .show();
    }

    private boolean checkConnection() {
        final ConnectivityManager cm = (ConnectivityManager) this.getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = null;
        if (cm != null) activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork != null && activeNetwork.isConnectedOrConnecting();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (BuildConfig.DEBUG) {
            RefWatcher refWatcher = MyApplication.getRefWatcher(this);
            refWatcher.watch(this);
        }
    }

}
