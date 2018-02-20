package com.example.mishr.locally;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class Home_Activity extends AppCompatActivity {

    Button button,clear;
    TextView name_tv,city_tv,contact_tv,email_tv;
    public static final String nameKey = "nameKey";
    public static final String cityKey = "cityKey";
    public static final String contactKey = "contactKey";
    public static final String emailKey = "emailKey";
    public static final String mainKey = "mainKey";

    SharedPreferences sharedPreferences;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_);
         clear = findViewById(R.id.clear_button);
        button = findViewById(R.id.show_value);
        name_tv = findViewById(R.id.name_here);
        city_tv = findViewById(R.id.city_here);
        contact_tv = findViewById(R.id.contact_here);
        email_tv = findViewById(R.id.email_here);


        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences(mainKey,MODE_PRIVATE);
                if (sharedPreferences.contains(nameKey)){
                    name_tv.setText(sharedPreferences.getString(nameKey,"Default Name"));
                }

                if (sharedPreferences.contains(emailKey)){
                    email_tv.setText(sharedPreferences.getString(emailKey,"Default Email"));
                }
                if (sharedPreferences.contains(cityKey)){
                    city_tv.setText(sharedPreferences.getString(cityKey,"Default City"));
                }
                if (sharedPreferences.contains(contactKey)){
                    contact_tv.setText(sharedPreferences.getString(contactKey,"Default Contact"));
                }
                Toast.makeText(Home_Activity.this, "Recent Value Shown..!!", Toast.LENGTH_SHORT).show();
            }
        });

        clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                name_tv.setText("");
                city_tv.setText("");
                email_tv.setText("");
                contact_tv.setText("");
            }
        });

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        

    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(),MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }

        return super.onOptionsItemSelected(item);
    }



}


