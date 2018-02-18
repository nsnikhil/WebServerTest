package com.nsnik.nrs.webservertest.dagger.modules;

import android.content.Context;

import com.nsnik.nrs.webservertest.dagger.qualifiers.ApplicationQualifier;
import com.nsnik.nrs.webservertest.dagger.scopes.ApplicationScope;

import dagger.Module;
import dagger.Provides;

/**
 * @author nikhil
 * @since 15-02-2018
 * @version 1.0
 */

@Module
public class ContextModule {

    private final Context mContext;

    public ContextModule(Context mContext) {
        this.mContext = mContext;
    }

    @Provides
    @ApplicationQualifier
    @ApplicationScope
    Context provideContext() {
        return this.mContext;
    }

}
