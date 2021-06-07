package com.example.tutorme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tutorme.R;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.UserEntity;

public class ReviewActivity extends AppCompatActivity {

    private RatingBar ratingBar;
    private float rateValue;
    private TextView userName, userEmail;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_review);

        this.ratingBar = findViewById(R.id.rating_bar);
        ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {
            @Override
            public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                rateValue = ratingBar.getRating();
            }
        });

        UserEntity userEntity = AppDatabase.getAppDatabase(this).userDao().loginUser(getIntent().getExtras().getString("user"));
        this.userName = findViewById(R.id.rate_user_name);
        this.userEmail = findViewById(R.id.rate_email);
        this.userEmail.setText(userEntity.getUserEmail());
        this.userName.setText(userEntity.getUserFullName());
    }

    public void submitRating(View view){

    }
}