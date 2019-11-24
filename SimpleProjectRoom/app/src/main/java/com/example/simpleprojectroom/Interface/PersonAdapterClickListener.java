package com.example.simpleprojectroom.Interface;

import com.example.simpleprojectroom.Models.Person;

public interface PersonAdapterClickListener {
    void onPersonClick(Person person);
    void onDeleteClick(int position);
}
