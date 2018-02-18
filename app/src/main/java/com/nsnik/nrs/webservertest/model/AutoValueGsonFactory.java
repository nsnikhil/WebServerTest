package com.nsnik.nrs.webservertest.model;

import com.google.gson.TypeAdapterFactory;
import com.ryanharter.auto.value.gson.GsonTypeAdapterFactory;

/**
 * @author nikhil
 * @since 15-02-2018
 * @version 1.0
 */

@GsonTypeAdapterFactory
public abstract class AutoValueGsonFactory implements TypeAdapterFactory {

    public static TypeAdapterFactory create() {
        return new AutoValueGson_AutoValueGsonFactory();
    }
}
