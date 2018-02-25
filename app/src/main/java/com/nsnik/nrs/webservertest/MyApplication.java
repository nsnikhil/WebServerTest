/*
 *    Copyright 2018 nsnikhil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.nsnik.nrs.webservertest;

import android.app.Application;
import android.content.Context;
import android.os.StrictMode;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatDelegate;

import com.github.moduth.blockcanary.BlockCanary;
import com.nsnik.nrs.webservertest.dagger.components.DaggerDatabaseComponent;
import com.nsnik.nrs.webservertest.dagger.components.DaggerNetworkComponent;
import com.nsnik.nrs.webservertest.dagger.components.DatabaseComponent;
import com.nsnik.nrs.webservertest.dagger.components.NetworkComponent;
import com.nsnik.nrs.webservertest.dagger.modules.ContextModule;
import com.nsnik.nrs.webservertest.util.AppBlockCanaryContext;
import com.nsnik.nrs.webservertest.util.DbUtil;
import com.nsnik.nrs.webservertest.util.NetworkUtil;
import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import timber.log.Timber;

/**
 * @author nikhil
 * @version 1.0
 * @since 14-02-2018
 */

public class MyApplication extends Application {

    static {
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
    }

    private RefWatcher refWatcher;
    private ContextModule mContextModule;
    private DbUtil mDbUtil;
    private NetworkUtil mNetworkUtil;

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
        BlockCanary.install(this, new AppBlockCanaryContext()).start();
        moduleSetter();
    }

    /**
     *
     */
    private void moduleSetter() {
        setContextModule();
        setNetworkModule();
        setDatabaseComponent();
    }

    private void setContextModule() {
        mContextModule = new ContextModule(this);
    }

    private void setNetworkModule() {
        NetworkComponent networkComponent = DaggerNetworkComponent.create();
        mNetworkUtil = networkComponent.getNetworkUtil();
    }

    private void setDatabaseComponent() {
        DatabaseComponent databaseComponent = DaggerDatabaseComponent.builder().contextModule(mContextModule).build();
        mDbUtil = databaseComponent.getDbUtil();
    }

    public DbUtil getDbUtil() {
        return mDbUtil;
    }

    public NetworkUtil getNetworkUtil() {
        return mNetworkUtil;
    }
}
