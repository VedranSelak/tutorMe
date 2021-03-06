package com.example.tutorme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.tutorme.R;
import com.example.tutorme.MainActivity;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.FavouritesEntity;
import com.example.tutorme.roomdatabase.entities.PostEntity;
import com.example.tutorme.roomdatabase.entities.UserEntity;

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
        UserEntity userEntity = AppDatabase.getAppDatabase(this).userDao().loginUser(getIntent().getExtras().getString("user"));
        FavouritesEntity favouritesEntity = new FavouritesEntity(getIntent().getExtras().getLong("post"), userEntity.getId());
        AppDatabase.getAppDatabase(this).favouritesDao().add(favouritesEntity);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", getIntent().getExtras().getString("user"));
        startActivity(intent);
    }

    public void goToHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", getIntent().getExtras().getString("user"));
        startActivity(intent);
    }
}