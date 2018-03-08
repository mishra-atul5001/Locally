package com.example.mishr.locally;

import android.Manifest;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.NavUtils;
import android.support.v7.app.ActionBar;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class Home_Activity extends AppCompatActivity {

    Button button, clear, call_button;
    TextView name_tv, city_tv, contact_tv, email_tv;
    // EditText call_et;
    public static final String nameKey = "nameKey";
    public static final String cityKey = "cityKey";
    public static final String contactKey = "contactKey";
    public static final String emailKey = "emailKey";
    public static final String mainKey = "mainKey";
    String make_a_call;
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
        //  call_et = findViewById(R.id.number_here);
        // call_button = findViewById(R.id.calling_button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                sharedPreferences = getSharedPreferences(mainKey, MODE_PRIVATE);
                if (sharedPreferences.contains(nameKey)) {
                    name_tv.setText(sharedPreferences.getString(nameKey, "Default Name"));
                }

                if (sharedPreferences.contains(emailKey)) {
                    email_tv.setText(sharedPreferences.getString(emailKey, "Default Email"));
                }
                if (sharedPreferences.contains(cityKey)) {
                    city_tv.setText(sharedPreferences.getString(cityKey, "Default City"));
                }
                if (sharedPreferences.contains(contactKey)) {
                    contact_tv.setText(sharedPreferences.getString(contactKey, "Default Contact"));
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
       /* call_button.setOnClickListener(new View.OnClickListener() {
                                           @Override
                                           public void onClick(View view) {
                                               Intent calling = new Intent(Intent.ACTION_CALL);
                                               make_a_call = call_et.getText().toString();
                                               if (make_a_call.trim().isEmpty()) {
                                                   calling.setData(Uri.parse("tel:7905993107"));
                                               } else {
                                                   calling.setData(Uri.parse("tel:" + make_a_call));
                                               }
                                               if (ActivityCompat.checkSelfPermission(getApplicationContext(), Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
                                                  // Toast.makeText(this, "Please Grant the Permission to make a call..!!!", Toast.LENGTH_SHORT).show();
                                                   requestPermission();
                                               } else {
                                                   startActivity(calling);
                                               }
                                              // Toast.makeText(this, "Calling..Please wait..!!", Toast.LENGTH_SHORT).show();
                                           }
                                       });  */


        getSupportActionBar().setDisplayHomeAsUpEnabled(true);


    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menus, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                Intent i = new Intent(getApplicationContext(), MainActivity.class);
                startActivity(i);
                finish();
                return true;
        }
        int id = item.getItemId();
        if (id == R.id.call) {
            Intent calling = new Intent(Intent.ACTION_DIAL);
            make_a_call = contact_tv.getText().toString();
            if (make_a_call.trim().isEmpty()) {
                calling.setData(Uri.parse("tel:7905993107"));
            } else {
                calling.setData(Uri.parse("tel:" + make_a_call));
            }
            startActivity(calling);
            Toast.makeText(this, "Transferred you to the Dial Pad..!!", Toast.LENGTH_SHORT).show();
        }


        return true;
    }
}
   /* private void requestPermission() {
        ActivityCompat.requestPermissions(this, new String[]{Manifest.permission.CALL_PHONE}, 1);

    }}; */





