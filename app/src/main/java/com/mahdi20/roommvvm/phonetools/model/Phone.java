package com.mahdi20.roommvvm.phonetools.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "phone_tb")
public class Phone {

    @PrimaryKey(autoGenerate = true)
    private int id;
    private String name;
    private String phone;


    public Phone(String name, String phone) {
        this.name = name;
        this.phone = phone;
    }


    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}