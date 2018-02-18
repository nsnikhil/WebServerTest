package com.nsnik.nrs.webservertest.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.nsnik.nrs.webservertest.MyApplication;
import com.nsnik.nrs.webservertest.model.UserModel;
import com.nsnik.nrs.webservertest.util.DemoRetrofitService;

import java.util.List;

import io.reactivex.CompletableObserver;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import retrofit2.Retrofit;
import timber.log.Timber;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

public class ListViewModel extends AndroidViewModel {

    private final Retrofit mRetrofit;
    private MutableLiveData<List<UserModel>> mDemoList;

    public ListViewModel(@NonNull Application application) {
        super(application);
        mRetrofit = ((MyApplication) application).getRetrofitClient();
        mDemoList = new MutableLiveData<>();
        getDemoListServer();
    }

    public LiveData<List<UserModel>> getDemoList() {
        return mDemoList;
    }

    private void getDemoListServer() {
        mRetrofit.create(DemoRetrofitService.class).getDemoList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new SingleObserver<List<UserModel>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onSuccess(List<UserModel> userModels) {
                        mDemoList.setValue(userModels);
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e.getMessage());
                    }
                });
    }

    public void insertValue(final String name, final int phoneNo) {
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
                        getDemoListServer();
                    }

                    @Override
                    public void onError(Throwable e) {
                        Timber.d(e.getMessage());
                    }
                });
    }
}