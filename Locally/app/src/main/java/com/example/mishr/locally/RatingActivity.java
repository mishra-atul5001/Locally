package com.example.mishr.locally;

import android.app.PendingIntent;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.RatingBar;
import android.widget.Toast;

public class RatingActivity extends AppCompatActivity {

    RatingBar ratingBar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rating);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        ratingBar = (RatingBar)findViewById(R.id.ratingbar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float v, boolean b) {
                String sRating = String.valueOf(ratingBar.getRating());
                Toast.makeText(RatingActivity.this,"Thanks for..!! : "+ sRating, Toast.LENGTH_SHORT).show();
            }
        });




    }

    @Override
    public void onBackPressed() {

        Intent back = new Intent(this,MainActivity.class);
        startActivity(back);
        finish();
        return;
    }
}
