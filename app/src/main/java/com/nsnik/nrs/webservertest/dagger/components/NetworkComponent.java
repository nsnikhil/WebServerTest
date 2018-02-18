package com.nsnik.nrs.webservertest.dagger.components;

import com.nsnik.nrs.webservertest.dagger.modules.NetworkModule;

import dagger.Component;
import retrofit2.Retrofit;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    Retrofit getRetrofit();
}
