package com.example.tutorme;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

public class PostDetailsActivity extends AppCompatActivity {

    private TextView tutorName, tutorEmail, tutorField, tutorDescription, perHourCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        this.tutorName = findViewById(R.id.full_name_details);
        this.tutorEmail = findViewById(R.id.email_details);
        this.tutorField = findViewById(R.id.field_details);
        this.tutorDescription = findViewById(R.id.description_details);
        this.perHourCost = findViewById(R.id.cost_details);

    }
}