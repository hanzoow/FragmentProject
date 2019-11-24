package com.example.simpleprojectroom.Models;


import androidx.room.ColumnInfo;
import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "person_table")
public class Person {

    @PrimaryKey(autoGenerate = true)
    private int id;

    @ColumnInfo(name = "name")
    private String name;

    @ColumnInfo(name = "surname")
    private String surName;

    @ColumnInfo(name = "pt")
    private String PT;


    public Person(String name, String surName, String PT) {
        this.name = name;
        this.surName = surName;
        this.PT = PT;
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

    public String getSurName() {
        return surName;
    }

    public void setSurName(String surName) {
        this.surName = surName;
    }

    public String getPT() {
        return PT;
    }

    public void setPT(String PT) {
        this.PT = PT;
    }
}
