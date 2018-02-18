package com.nsnik.nrs.webservertest.model;

import android.support.annotation.NonNull;

import com.google.auto.value.AutoValue;
import com.google.gson.Gson;
import com.google.gson.TypeAdapter;
import com.google.gson.annotations.SerializedName;

/**
 * @author nikhil
 * @since 15-02-2018
 * @version 1.0
 */

@AutoValue
public abstract class UserModel {

    @SerializedName("id")
    public abstract int id();

    @SerializedName("name")
    public abstract String name();

    @SerializedName("phone")
    public abstract int phone();

    @NonNull
    static TypeAdapter<UserModel> typeAdapter(Gson gson) {
        return new AutoValue_UserModel.GsonTypeAdapter(gson);
    }

}
