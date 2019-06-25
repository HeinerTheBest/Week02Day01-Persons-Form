package com.mobileapps.week02day01persons;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

public class PersonsListActivity extends AppCompatActivity {

    TextView tvPersonsList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_persons_list);
        tvPersonsList = findViewById(R.id.tvPersonsList);

        List<Person> persons;
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        persons = bundle.getParcelableArrayList("persons");

        if (persons != null) {
            for (int i=0; i<persons.size();i++)
            {
                tvPersonsList.append(i+1+"\n");
                tvPersonsList.append("First Name: "+persons.get(i).getFirstName()+"\n");
                tvPersonsList.append("Last Name: "+persons.get(i).getLastName()+"\n");
                tvPersonsList.append("Sex: "+persons.get(i).getSex()+"\n");
                tvPersonsList.append("Age: "+persons.get(i).getAge()+"\n\n");
            }
        }
    }
}
