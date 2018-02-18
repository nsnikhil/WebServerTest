package com.nsnik.nrs.webservertest.dagger.scopes;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * @author nikhil
 * @since 15-02-2018
 * @version 1.0
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface ApplicationScope {
}

