package com.nsnik.nrs.webservertest.data;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.OnConflictStrategy;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;

import java.util.List;

@Dao
public interface UserDao {

    @Query("SELECT * FROM UserEntity")
    LiveData<List<UserEntity>> getUserList();

    @Query("SELECT * FROM UserEntity WHERE mId = :id")
    LiveData<UserEntity> getUserById(int id);

    @Query("SELECT * FROM UserEntity WHERE mPhoneNo = :phoneNo")
    LiveData<UserEntity> getUserFromPhoneNo(String phoneNo);

    @Query("SELECT * FROM UserEntity WHERE mUserName = :userName")
    LiveData<List<UserEntity>> getUserByName(String userName);

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    long[] insertUsers(UserEntity... userEntities);

    @Delete
    void deleteUsers(UserEntity... userEntities);

    @Query("DELETE FROM UserEntity WHERE mId = :id")
    void deleteUserById(int id);

    @Query("DELETE FROM UserEntity WHERE mPhoneNo = :phoneNo")
    void deleteUserByPhoneNo(String phoneNo);

    @Query("DELETE FROM UserEntity WHERE mUserName = :userName")
    void deleteUsersByName(String userName);

    @Update(onConflict = OnConflictStrategy.REPLACE)
    int updateUsers(UserEntity... userEntities);

}
