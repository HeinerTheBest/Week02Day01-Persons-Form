package com.mobileapps.week02day01persons;

import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

import com.google.gson.Gson;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    List<Person> persons = new ArrayList<>();
    EditText etFirstName, etLastName, etAge;
    RadioButton rbMale, rbFemale;
    TextView tvPersonCOunt;
    String firstName, lastName, sex, age;
    ImageView btnMusic;
    boolean playing = true;
    MediaPlayer gotMusic;
    SharedPreferences preferences;


    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        etFirstName = findViewById(R.id.etFirstName);
        etLastName  = findViewById(R.id.etLasttName);
        etAge       = findViewById(R.id.etAge);
        rbMale      = findViewById(R.id.rbMale);
        rbFemale    = findViewById(R.id.rbFemale);
        tvPersonCOunt = findViewById(R.id.tvNumberPersons);
        btnMusic    = findViewById(R.id.btnMusic);
        preferences = getPreferences(MODE_PRIVATE);

        tvPersonCOunt.setText(String.valueOf(persons.size()));
        gotMusic= MediaPlayer.create(MainActivity.this,R.raw.got);
        gotMusic.start();
    }

    
    private boolean allFill()
    {
        if(etFirstName.getText().toString().isEmpty())
        {
            showToast("Enter the First Name");
            return false;
        }
        if(etLastName.getText().toString().isEmpty())
        {
            showToast("Enter the Last Name");
            return false;
        }
        if(etAge.getText().toString().isEmpty())
        {
            showToast("Enter the Age");
            return false;
        }
        return true;
    }
    private void showToast(String text)
    {
        Toast toast=Toast.makeText(getApplicationContext(),text,Toast.LENGTH_SHORT);
        toast.setMargin(50,50);
        toast.show();
    }
    private void cleanViews()
    {
        etFirstName.setText("");
        etLastName.setText("");
        etAge.setText("");
        rbMale.setChecked(true);
    }
    public void submit(View view)
    {
        if(allFill())
        {
            firstName = etFirstName.getText().toString();
            lastName  = etLastName.getText().toString();
            if(rbMale.isChecked())
            {
                sex = "Male";
            }else
            {
                sex = "Female";
            }
            age = etAge.getText().toString();
            persons.add(new Person(firstName,lastName,sex,age));
            tvPersonCOunt.setText(String.valueOf(persons.size()));
            showToast("Success");
            cleanViews();
        }
    }
    public void cancel(View view)
    {
        cleanViews();
    }

    public  void checkPersons(View view)
    {

        if(persons.size()==0)
        {
            showToast("You don't have any person yet");
        }
        else
        {
            gotMusic.pause();
            Intent intent = new Intent(this,PersonsListActivity.class);
            Bundle bundle = new Bundle();
            bundle.putParcelableArrayList("persons",(ArrayList) persons);
            intent.putExtras(bundle);
            startActivity(intent);
        }
    }

    public void checkPictures(View view)
    {
        gotMusic.pause();
        Intent intent = new Intent(this,CameraActivity.class);
        startActivity(intent);
    }

    @RequiresApi(api = Build.VERSION_CODES.LOLLIPOP)
    public void music(View view)
    {
        if(playing) //Mean is Playing
        {
            btnMusic.setImageDrawable(getDrawable(R.drawable.play));
            playing = false;
            gotMusic.pause();
        }
        else
        {
            btnMusic.setImageDrawable(getDrawable(R.drawable.stop));
            playing = true;
            gotMusic.start();
        }
    }

}
