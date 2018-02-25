package com.nsnik.nrs.webservertest.dagger.components;

import com.nsnik.nrs.webservertest.dagger.modules.DatabaseModule;
import com.nsnik.nrs.webservertest.dagger.scopes.ApplicationScope;
import com.nsnik.nrs.webservertest.util.DbUtil;

import dagger.Component;

@ApplicationScope
@Component(modules = DatabaseModule.class)
public abstract class DatabaseComponent {
    public abstract DbUtil getDbUtil();
}