package com.nsnik.nrs.webservertest.util;

import com.nsnik.nrs.webservertest.data.UserEntity;

import java.util.List;

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

    @GET("/php/readAll.php")
    Single<List<UserEntity>> getDemoList();

    @FormUrlEncoded
    @POST("/php/insert.php")
    Single<String> addItem(@Field("name") String name, @Field("phone") String phone);
}
