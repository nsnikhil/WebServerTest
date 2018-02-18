package com.nsnik.nrs.webservertest.dagger.modules;

import android.support.annotation.NonNull;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.nsnik.nrs.webservertest.dagger.qualifiers.BaseUrl;
import com.nsnik.nrs.webservertest.model.AutoValueGsonFactory;

import org.jetbrains.annotations.Contract;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

@Module
public class NetworkModule {

    private static final String BASE_URL = "http://35.196.127.245";

    @Contract(pure = true)
    @Provides
    @BaseUrl
    String getBaseUrl() {
        return BASE_URL;
    }

    @Provides
    HttpLoggingInterceptor getHttpLoggingInterceptor() {
        final HttpLoggingInterceptor httpLoggingInterceptor = new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        return httpLoggingInterceptor;
    }

    @NonNull
    @Provides
    OkHttpClient getOkHttpClient(HttpLoggingInterceptor httpLoggingInterceptor) {
        return new OkHttpClient.Builder().addInterceptor(httpLoggingInterceptor).build();
    }

    @NonNull
    @Provides
    GsonConverterFactory getGsonConverterFactory(){
        return GsonConverterFactory.create(new GsonBuilder().setLenient().registerTypeAdapterFactory(AutoValueGsonFactory.create()).create());
    }

    @NonNull
    @Provides
    Retrofit getRetrofitClient(final @BaseUrl String baseUrl, final OkHttpClient okHttpClient,final GsonConverterFactory gsonConverterFactory) {
        return new Retrofit.Builder()
                .baseUrl(baseUrl)
                .client(okHttpClient)
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(gsonConverterFactory)
                .build();
    }

}