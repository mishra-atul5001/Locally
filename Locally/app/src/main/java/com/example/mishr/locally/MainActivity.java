package com.example.mishr.locally;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Vibrator;
import android.support.v4.app.ActivityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    EditText name,city,email,contact;
    Button insert,view,home_activity,clear;
    String make_a_call;

    public static final String nameKey = "nameKey";
    public static final String cityKey = "cityKey";
    public static final String contactKey = "contactKey";
    public static final String emailKey = "emailKey";
    public static final String mainKey = "mainKey";
    private static long back_pressed;
    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        name = findViewById(R.id.name);
        city = findViewById(R.id.city);
        email =findViewById(R.id.email);
        contact = findViewById(R.id.Contact);

        insert = findViewById(R.id.insert_button);
        view = findViewById(R.id.view_button);
        home_activity = findViewById(R.id.home_button);
        clear=findViewById(R.id.clear);
        insert.setOnClickListener(this);
        view.setOnClickListener(this);
        home_activity.setOnClickListener(this);

      sharedPreferences = getSharedPreferences(mainKey,MODE_PRIVATE);
    //  call again();   // Method used to provide some data when activity is opened 2nd Time..!!
      clear.setOnClickListener(new View.OnClickListener() {
          @Override
          public void onClick(View view) {
              name.setText("");
              email.setText("");
              city.setText("");
              contact.setText("");


          }
      });
      

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menuus,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();

         if (id==R.id.rate_it) {
           // Toast.makeText(this, "Rate us On Playstore..!!", Toast.LENGTH_SHORT).show();
            Intent rating = new Intent(this,RatingActivity.class);
            startActivity(rating);
        }
            else if (id==R.id.settings) {
           // Toast.makeText(this, "Settings will be out soon..!!", Toast.LENGTH_SHORT).show();
            Intent settings = new Intent(this,SettingsActivity.class);
            startActivity(settings);
         }
        else if (id==R.id.make_a_call){
            Intent calling = new Intent(Intent.ACTION_CALL);
             make_a_call = contact.getText().toString();
            if (make_a_call.trim().isEmpty()){
                calling.setData(Uri.parse("tel:7905993107"));
            }
            else {
                calling.setData(Uri.parse("tel:" + make_a_call));
            }
            if (ActivityCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)!= PackageManager.PERMISSION_GRANTED){
                Toast.makeText(this, "Please Grant the Permission to make a call..!!!", Toast.LENGTH_SHORT).show();
                requestPermission();
            }
            else {
                startActivity(calling);
            }
            Vibrator v = (Vibrator)getSystemService(Context.VIBRATOR_SERVICE);
            long[] pattern = {0,100,1000,200,3000};
            v.vibrate(pattern,-1);
            Toast.makeText(this, "Calling..Please wait..!!", Toast.LENGTH_SHORT).show();
        }


        return true;
    }

    private void requestPermission() {
        ActivityCompat.requestPermissions(this,new String[]{Manifest.permission.CALL_PHONE},1);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.insert_button:
                String NAME= name.getText().toString();
                String CITY= city.getText().toString();
                String EMAIL= email.getText().toString();
                String CONTACT= contact.getText().toString();

                SharedPreferences.Editor editor = sharedPreferences.edit();
                editor.putString(nameKey,NAME);
                editor.putString(emailKey,EMAIL);
                editor.putString(cityKey,CITY);
                editor.putString(contactKey,CONTACT);
                editor.commit();
                Toast.makeText(this, "Data Inserted Successfully", Toast.LENGTH_SHORT).show();
                Vibrator vibrator = (Vibrator) getSystemService(Context.VIBRATOR_SERVICE);
                vibrator.vibrate(500);
                name.setText("");
                email.setText("");
                city.setText("");
                contact.setText("");


                break;


            case R.id.view_button:

                sharedPreferences = getSharedPreferences(mainKey,MODE_PRIVATE);
                if (sharedPreferences.contains(nameKey)){
                    name.setText(sharedPreferences.getString(nameKey,"Default Name"));
                }

                if (sharedPreferences.contains(emailKey)){
                    email.setText(sharedPreferences.getString(emailKey,"Default Email"));
                }
                if (sharedPreferences.contains(cityKey)){
                    city.setText(sharedPreferences.getString(cityKey,"Default City"));
                }
                if (sharedPreferences.contains(contactKey)){
                    contact.setText(sharedPreferences.getString(contactKey,"Default Contact"));
                }

                break;

            case R.id.home_button:

                Intent intent = new Intent(getApplicationContext(),Home_Activity.class);
                startActivity(intent);

                break;


        }
        
    }
  /*  public void callagain(){
        if (sharedPreferences.contains(nameKey)){
            name.setText(sharedPreferences.getString(nameKey,"Default Name"));
        }

        if (sharedPreferences.contains(emailKey)){
            email.setText(sharedPreferences.getString(emailKey,"Default Email"));
        }
        if (sharedPreferences.contains(cityKey)){
            city.setText(sharedPreferences.getString(cityKey,"Default City"));
        }
        if (sharedPreferences.contains(contactKey)){
            contact.setText(sharedPreferences.getString(contactKey,"Default Contact"));
        }
    } */
    @Override
    public void onBackPressed(){
        if (back_pressed + 2000 > System.currentTimeMillis()){
            super.onBackPressed();
        }
        else{
            Toast.makeText(getBaseContext(), "Press once again to exit", Toast.LENGTH_SHORT).show();
            back_pressed = System.currentTimeMillis();
        }
    }

   
}
