package com.example.simpleprojectroom;

import androidx.room.Database;
import androidx.room.RoomDatabase;

import com.example.simpleprojectroom.Interface.PersonDao;
import com.example.simpleprojectroom.Models.Person;

@Database(entities = {Person.class},version = 1)
public abstract class PersonDatabase extends RoomDatabase {

        public abstract PersonDao personDao();
}
