/*
 *    Copyright 2018 nsnikhil
 *
 *    Licensed under the Apache License, Version 2.0 (the "License");
 *    you may not use this file except in compliance with the License.
 *    You may obtain a copy of the License at
 *
 *        http://www.apache.org/licenses/LICENSE-2.0
 *
 *    Unless required by applicable law or agreed to in writing, software
 *    distributed under the License is distributed on an "AS IS" BASIS,
 *    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 *    See the License for the specific language governing permissions and
 *    limitations under the License.
 */

package com.nsnik.nrs.webservertest.util;


import android.arch.lifecycle.MutableLiveData;

import com.nsnik.nrs.webservertest.dagger.scopes.ApplicationScope;
import com.nsnik.nrs.webservertest.data.UserEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * @author nikhil
 * @version 1.0
 * @since 25-02-2018
 */


@ApplicationScope
public class NetworkUtil {

    private final Retrofit mRetrofit;
    private MutableLiveData<List<UserEntity>> mUserList;

    @Inject
    NetworkUtil(final Retrofit retrofitClient) {
        mRetrofit = retrofitClient;
        mUserList = new MutableLiveData<>();
    }

    public MutableLiveData<List<UserEntity>> getUserList() {
        return mUserList;
    }

    public void getDemoListServer() {
        mRetrofit.create(DemoRetrofitService.class).getDemoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<UserEntity>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<UserEntity> userEntity) {
                        mUserList.setValue(userEntity);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e.getMessage());
                    }
                });
    }

    public void insertValue(final String name, final String phoneNo) {
        mRetrofit.create(DemoRetrofitService.class)
                .addItem(name, phoneNo)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(String s) {
                        Timber.d(s);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e.getMessage());
                    }
                });
    }

    public void updateUser() {
        //TODO
    }

    public void deleteUser() {
        //TODO
    }
}
