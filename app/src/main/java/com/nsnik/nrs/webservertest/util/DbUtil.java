package com.nsnik.nrs.webservertest.util;

import android.arch.lifecycle.LiveData;

import com.nsnik.nrs.webservertest.dagger.scopes.ApplicationScope;
import com.nsnik.nrs.webservertest.data.AppDatabase;
import com.nsnik.nrs.webservertest.data.UserEntity;

import java.util.List;

import javax.inject.Inject;

import io.reactivex.Completable;
import io.reactivex.CompletableObserver;
import io.reactivex.Single;
import io.reactivex.SingleObserver;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import timber.log.Timber;

@ApplicationScope
public class DbUtil {

    private final AppDatabase mAppDatabase;

    @Inject
    DbUtil(AppDatabase appDatabase) {
        this.mAppDatabase = appDatabase;
    }


    public void insertUser(UserEntity... userEntities) {
        Single<long[]> single = Single.fromCallable(() -> mAppDatabase.getUserDao().insertUsers(userEntities)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        single.subscribe(new SingleObserver<long[]>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(long[] longs) {
                for (long aLong : longs) {
                    Timber.d(String.valueOf(aLong));
                }
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.getMessage());
            }
        });
    }

    public void deleteUser(UserEntity... userEntities) {
        Completable completable = Completable.fromCallable(() -> {
            mAppDatabase.getUserDao().deleteUsers(userEntities);
            return null;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        completable.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Deleted Successfully");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.getMessage());
            }
        });
    }

    public void updateUser(UserEntity... userEntities) {
        Single<Integer> single = Single.fromCallable(() -> mAppDatabase.getUserDao().updateUsers(userEntities)).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        single.subscribe(new SingleObserver<Integer>() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onSuccess(Integer integer) {
                Timber.d(String.valueOf(integer));
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.getMessage());
            }
        });
    }

    public void deleteUserByPhoneNo(String phoneNo) {
        Completable completable = Completable.fromCallable(() -> {
            mAppDatabase.getUserDao().deleteUserByPhoneNo(phoneNo);
            return null;
        }).subscribeOn(Schedulers.io()).subscribeOn(AndroidSchedulers.mainThread());
        completable.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Deleted Successfully");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.getMessage());
            }
        });
    }

    public void deleteUserByName(String name) {
        Completable completable = Completable.fromCallable(() -> {
            mAppDatabase.getUserDao().deleteUsersByName(name);
            return null;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        completable.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Deleted Successfully");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.getMessage());
            }
        });
    }

    public void deleteUserById(int id) {
        Completable completable = Completable.fromCallable(() -> {
            mAppDatabase.getUserDao().deleteUserById(id);
            return null;
        }).subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread());
        completable.subscribe(new CompletableObserver() {
            @Override
            public void onSubscribe(Disposable d) {

            }

            @Override
            public void onComplete() {
                Timber.d("Deleted Successfully");
            }

            @Override
            public void onError(Throwable e) {
                Timber.d(e.getMessage());
            }
        });
    }

    public LiveData<List<UserEntity>> getUsersList() {
        return mAppDatabase.getUserDao().getUserList();
    }

    public LiveData<UserEntity> getUserFromPhoneNo(String phoneNo) {
        return mAppDatabase.getUserDao().getUserFromPhoneNo(phoneNo);
    }

    public LiveData<UserEntity> getUserById(int id) {
        return mAppDatabase.getUserDao().getUserById(id);
    }

    public LiveData<List<UserEntity>> getUsersByName(String name) {
        return mAppDatabase.getUserDao().getUserByName(name);
    }

}
