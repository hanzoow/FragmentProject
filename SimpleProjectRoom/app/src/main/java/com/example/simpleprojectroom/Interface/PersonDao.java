package com.example.simpleprojectroom.Interface;


import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.OnConflictStrategy;
import androidx.room.Query;

import com.example.simpleprojectroom.Models.Person;

import java.util.List;

@Dao
public interface PersonDao {

    @Query("SELECT * FROM person_table")
    List<Person> getAllPersons();

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    void insertAll(Person... person);

    @Query("SELECT id FROM person_table")
    int[] getId();

   @Query("SELECT name FROM person_table")
   String[] getAllName();

   @Query("SELECT * FROM person_table WHERE id = 10")
   int returnWhereReloadDoes();

//   @Delete
//   void deletePerson(int index);

    @Query("SELECT name FROM person_table WHERE name LIKE :name  LIMIT 1")
    String findName(String name);

//    @Query("SELECT name FROM person_table WHERE name LIKE :findName")
//    Person getName(String findName);
}
