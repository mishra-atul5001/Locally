package com.example.mishr.locally;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class Splash_Screen extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
      //  setContentView(R.layout.activity_splash__screen);
        Toast.makeText(this, "Welcome..!!", Toast.LENGTH_SHORT).show();
        for(int i = 0 ; i<4000;i++){
            //Delaying the Splash Screen
        }
        Thread timer=new Thread()
        {
            public void run() {
                try {
                    sleep(4000);

                } catch (InterruptedException e) {
                    //Just for making splash screen stay longer
                    e.printStackTrace();
                }
                finally
                {
                    Intent i=new Intent(getApplicationContext(),MainActivity.class);
                    finish();
                    startActivity(i);
                }

            }
        };
        timer.start();
        // Intent intent = new Intent(SplashScreenActivity.this,MainActivity.class);
        //  startActivity(intent);


    }

}
