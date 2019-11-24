package com.example.simpleprojectroom.View;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.room.Room;

import com.example.simpleprojectroom.Models.Person;
import com.example.simpleprojectroom.PersonDatabase;
import com.example.simpleprojectroom.R;
import com.example.simpleprojectroom.adapter.PersonAdapter;

import java.util.List;

public class CreateNewPerson extends AppCompatActivity {

    EditText edtName,edtSurname,edtPT;
    PersonAdapter myAdapter;
    Button btnAdd;
    List<Person> people;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.new_person);

        edtName = findViewById(R.id.edtName);
        edtSurname = findViewById(R.id.edtSurName);
        edtPT = findViewById(R.id.edtPt);
        btnAdd = findViewById(R.id.btnAdd);


        final PersonDatabase db = Room.databaseBuilder(getApplicationContext(),PersonDatabase.class,"person")
                .allowMainThreadQueries()
                .build();

        btnAdd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Person person = new Person(edtName.getText().toString().trim(),edtSurname.getText().toString().trim(),edtPT.getText().toString().trim());
                db.personDao().insertAll(person);
                myAdapter.notifyItemInserted(0);
                startActivity(new Intent(CreateNewPerson.this, MainActivity.class));
            }
        });

    }

}
