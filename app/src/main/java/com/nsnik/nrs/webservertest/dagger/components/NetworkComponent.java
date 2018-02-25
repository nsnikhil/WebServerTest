package com.nsnik.nrs.webservertest.dagger.components;

import com.nsnik.nrs.webservertest.dagger.modules.NetworkModule;
import com.nsnik.nrs.webservertest.dagger.scopes.ApplicationScope;
import com.nsnik.nrs.webservertest.util.NetworkUtil;

import dagger.Component;

@ApplicationScope
@Component(modules = NetworkModule.class)
public interface NetworkComponent {
    NetworkUtil getNetworkUtil();
}
