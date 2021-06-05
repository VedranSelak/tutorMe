package com.example.tutorme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.PostEntity;

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

        PostEntity post = AppDatabase.getAppDatabase(this).postDao().getPostById(getIntent().getExtras().getLong("post"));

        this.tutorName.setText(post.getFullNameOfTutor());
        this.tutorEmail.setText(post.getEmailOfTutor());
        this.tutorField.setText(post.getFieldName());
        this.tutorDescription.setText(post.getDescription());
        String cost = String.valueOf(post.getPerHourCost()) + " $";
        this.perHourCost.setText(cost);

    }

    public void onAddToFavourites(View view){

    }

    public void goToHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", getIntent().getExtras().getString("user"));
        startActivity(intent);
    }
}