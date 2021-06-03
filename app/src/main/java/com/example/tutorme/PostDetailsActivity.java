package com.example.tutorme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.PostEntity;
import com.example.tutorme.roomdatabase.UserEntity;

public class PostDetailsActivity extends AppCompatActivity {

    private EditText field;
    private EditText description;
    private EditText perHourCost;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post_details);

        this.description = findViewById(R.id.post_description_input);
        this.field = findViewById(R.id.post_field_input);
        this.perHourCost = findViewById(R.id.post_per_hour_input);
    }

    public void onPost(View view){
        UserEntity user = AppDatabase.getAppDatabase(this).userDao().loginUser(getIntent().getExtras().getString("user"));
        PostEntity post = new PostEntity(user.getUserFullName(), user.getUserEmail(), this.field.getText().toString(), this.description.getText().toString(), Double.parseDouble(this.perHourCost.getText().toString()));
        AppDatabase.getAppDatabase(this).postDao().add(post);
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", getIntent().getExtras().getString("user"));
        startActivity(intent);
    }

    public void goHome(View view){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("user", getIntent().getExtras().getString("user"));
        startActivity(intent);
    }
}