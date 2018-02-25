package com.nsnik.nrs.webservertest.dagger.modules;

import android.arch.persistence.room.Room;
import android.content.Context;

import com.nsnik.nrs.webservertest.dagger.qualifiers.ApplicationQualifier;
import com.nsnik.nrs.webservertest.dagger.qualifiers.DatabaseName;
import com.nsnik.nrs.webservertest.dagger.scopes.ApplicationScope;
import com.nsnik.nrs.webservertest.data.AppDatabase;

import org.jetbrains.annotations.NotNull;

import dagger.Module;
import dagger.Provides;

@Module(includes = {ContextModule.class})
public class DatabaseModule {

    private static final String DATABASE_NAME = "NA";

    @NotNull
    @Provides
    @DatabaseName
    String getDatabaseName() {
        return DATABASE_NAME;
    }

    @NotNull
    @Provides
    @ApplicationScope
    AppDatabase getAppDatabase(@NotNull @ApplicationQualifier Context context, @NotNull @DatabaseName @ApplicationScope String databaseName) {
        return Room.databaseBuilder(context, AppDatabase.class, databaseName).build();
    }

}
