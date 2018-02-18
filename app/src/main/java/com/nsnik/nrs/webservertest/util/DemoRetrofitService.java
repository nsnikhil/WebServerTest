package com.nsnik.nrs.webservertest.util;

import com.nsnik.nrs.webservertest.model.UserModel;

import java.util.List;

import io.reactivex.Completable;
import io.reactivex.Single;
import retrofit2.http.Field;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

public interface DemoRetrofitService {

    @GET("/phpScripts/readAll.php")
    Single<List<UserModel>> getDemoList();

    @FormUrlEncoded
    @POST("/phpScripts/insert.php")
    Single<String> addItem(@Field("fname") String name, @Field("phone") int phone);
}
