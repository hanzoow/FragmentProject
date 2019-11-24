package com.example.simpleprojectroom.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.room.Room;

import com.example.simpleprojectroom.Interface.PersonAdapterClickListener;
//import com.example.simpleprojectroom.Interface.PersonAdapterLongClickToDelete;
import com.example.simpleprojectroom.Models.Person;
import com.example.simpleprojectroom.PersonDatabase;
import com.example.simpleprojectroom.R;
import com.example.simpleprojectroom.adapter.PersonAdapter;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.List;


public class MainActivity extends AppCompatActivity implements PersonAdapter.onLoadMoreListener, PersonAdapterClickListener {
    RecyclerView.LayoutManager layoutManager;
    RecyclerView recyclerView;
    PersonAdapter myAdapter;
    //  RecyclerView.Adapter adapter;

    FloatingActionButton fab;
    ImageView imDelete;
    List<Person> people;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        imDelete = findViewById(R.id.imgDelete);
        recyclerView = findViewById(R.id.recycler_view);
        fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this, CreateNewPerson.class));
            }
        });
//        SwipeController swipeController = new SwipeController();
//
//        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(swipeController);
//        itemTouchHelper.attachToRecyclerView(recyclerView);

        PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "person")
                .allowMainThreadQueries()
                .build();

        List<Person> persons = db.personDao().getAllPersons();
        // recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new PersonAdapter(this, persons, this);
        //  adapter = new PersonAdapter(this,persons);
        recyclerView.setAdapter(myAdapter);
        layoutManager = new LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false);
        recyclerView.setLayoutManager(layoutManager);


    }
//
//    public void removeItem(int position) {
//        PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "person")
//                .allowMainThreadQueries()
//                .build();
//        db.personDao().deletePerson(position);
//        myAdapter.notifyItemRemoved(position);
//    }

    @Override
    public void onLoadMore() {
        PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "person")
                .allowMainThreadQueries()
                .build();

        final List<Person> persons = db.personDao().getAllPersons();

        if (persons != null) {
            recyclerView.postDelayed(new Runnable() {
                @Override
                public void run() {
                    myAdapter.updatePeopleData(persons);
                }
            }, 2000);
        }
    }

//
//    @Override
//    public void onDeleteClick(final int position) {
//        imDelete.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                removeItem(position);
//            }
//        });
//    }


    @Override
    public void onPersonClick(Person person) {
        PersonDatabase db = Room.databaseBuilder(getApplicationContext(), PersonDatabase.class, "person")
                .allowMainThreadQueries()
                .build();
        List<Person> persons = db.personDao().getAllPersons();
//        int size = persons1.size();
//        for (int i = 0; i < size ; i++) {
//            String[] nameArray = db.personDao().getAllName();
//            if(nameArray[i].equals(db.personDao().getAllName())){
        Toast.makeText(this, "Name: " + person.getName(), Toast.LENGTH_LONG).show();
//                break;
//            }
//        }
//
    }

    @Override
    public void onDeleteClick(int position) {

    }


}
