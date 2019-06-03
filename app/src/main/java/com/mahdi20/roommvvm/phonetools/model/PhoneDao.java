package com.mahdi20.roommvvm.phonetools.model;

import android.arch.lifecycle.LiveData;
import android.arch.persistence.room.Dao;
import android.arch.persistence.room.Delete;
import android.arch.persistence.room.Insert;
import android.arch.persistence.room.Query;
import android.arch.persistence.room.Update;


import java.util.List;


@Dao
public interface PhoneDao {

    @Insert
    void insert(Phone phone);

    @Update
    void update(Phone phone);

    @Delete
    void delete(Phone phone);

    @Query("DELETE FROM phone_tb")
    void deleteAllPhones();

    @Query("SELECT * FROM phone_tb")
    LiveData<List<Phone>> getAllPhones();
}