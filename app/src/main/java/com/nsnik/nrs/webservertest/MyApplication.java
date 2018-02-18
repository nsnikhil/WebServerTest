package com.nsnik.nrs.webservertest;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.github.moduth.blockcanary.BlockCanary;
import com.nsnik.nrs.webservertest.dagger.components.DaggerNetworkComponent;
import com.nsnik.nrs.webservertest.dagger.components.NetworkComponent;
import com.nsnik.nrs.webservertest.dagger.modules.ContextModule;
import com.nsnik.nrs.webservertest.util.AppBlockCanaryContext;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * @author nikhil
 * @version 1.0
 * @since 14-02-2018
 */

public class MyApplication extends Application {

    private RefWatcher refWatcher;
    private ContextModule mContextModule;
    private Retrofit mRetrofitClient;

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    public static RefWatcher getRefWatcher(@NonNull Context context) {
        MyApplication application = (MyApplication) context.getApplicationContext();
        return application.refWatcher;
    }

    @Override
    public void onCreate() {
        super.onCreate();
        if (BuildConfig.DEBUG) {
            Timber.plant(new Timber.DebugTree() {
                @NonNull
                @Override
                protected String createStackElementTag(@NonNull StackTraceElement element) {
                    return super.createStackElementTag(element) + ":" + element.getLineNumber();
                }
            });
            refWatcher = LeakCanary.install(this);
            StrictMode.setThreadPolicy(new StrictMode.ThreadPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            StrictMode.setVmPolicy(new StrictMode.VmPolicy.Builder()
                    .detectAll()
                    .penaltyLog()
                    .build());
            BlockCanary.install(this, new AppBlockCanaryContext()).start();
        }
        if (LeakCanary.isInAnalyzerProcess(this)) {
            return;
        }
        moduleSetter();
    }

    /**
     *
     */
    private void moduleSetter() {
        setContextModule();
        setNetworkModule();
    }

    private void setContextModule() {
        mContextModule = new ContextModule(this);
    }

    private void setNetworkModule() {
        NetworkComponent networkComponent = DaggerNetworkComponent.create();
        mRetrofitClient = networkComponent.getRetrofit();
    }

    public Retrofit getRetrofitClient() {
        return mRetrofitClient;
    }

}
