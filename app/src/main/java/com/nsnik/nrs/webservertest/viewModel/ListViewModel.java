package com.nsnik.nrs.webservertest.viewModel;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.arch.lifecycle.MutableLiveData;
import android.support.annotation.NonNull;

import com.nsnik.nrs.webservertest.MyApplication;
import com.nsnik.nrs.webservertest.data.UserEntity;
import com.nsnik.nrs.webservertest.util.DbUtil;
import com.nsnik.nrs.webservertest.util.NetworkUtil;

import java.util.List;

/**
 * @author nikhil
 * @version 1.0
 * @since 15-02-2018
 */

public class ListViewModel extends AndroidViewModel {

    private final NetworkUtil mNetworkUtil;
    private final DbUtil mDbUtil;
    private MutableLiveData<List<UserEntity>> mDemoList;

    public ListViewModel(@NonNull Application application) {
        super(application);
        mNetworkUtil = ((MyApplication) application).getNetworkUtil();
        mDbUtil = ((MyApplication) application).getDbUtil();
        mNetworkUtil.getDemoListServer();
        mDemoList = mNetworkUtil.getUserList();
    }

    public MutableLiveData<List<UserEntity>> getDemoList() {
        return mDemoList;
    }

    public void insertValue(String name, String phoneNo) {
        mNetworkUtil.insertValue(name, phoneNo);
    }

    public LiveData<UserEntity> getUserByPhoneNo(String phoneNo) {
        return mDbUtil.getUserFromPhoneNo(phoneNo);
    }

    public LiveData<UserEntity> getUserById(int id) {
        return mDbUtil.getUserById(id);
    }

    public LiveData<List<UserEntity>> getUserByName(String name) {
        return mDbUtil.getUsersByName(name);
    }

    public void insertUser(UserEntity... userEntities) {
        mDbUtil.insertUser(userEntities);
    }

    public void updateUser(UserEntity... userEntities) {
        mDbUtil.updateUser(userEntities);
    }

    public void deleteUser(UserEntity... userEntities) {
        mDbUtil.deleteUser(userEntities);
    }

    public void deleteUserByPhoneNo(String phoneNo) {
        mDbUtil.deleteUserByPhoneNo(phoneNo);
    }

    public void deleteUserByName(String name) {
        mDbUtil.deleteUserByName(name);
    }

    public void deleteUserById(int id) {
        mDbUtil.deleteUserById(id);
    }
}