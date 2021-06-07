package com.example.tutorme.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.example.tutorme.R;
import com.example.tutorme.MainActivity;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.PostEntity;
import com.example.tutorme.roomdatabase.entities.UserEntity;

public class PostActivity extends AppCompatActivity {

    protected String post_field;
    private EditText description;
    private EditText perHourCost;
    private Spinner spinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_post);

        this.description = findViewById(R.id.post_description_input);
        this.perHourCost = findViewById(R.id.post_per_hour_input);
        this.spinner = findViewById(R.id.spinner_post);
        post_field = "All";

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                 post_field = parent.getAdapter().getItem(position).toString();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        if(getIntent().hasExtra("status")){
            Button button = findViewById(R.id.post_button);
            button.setText("Update");
            PostEntity postEntity = AppDatabase.getAppDatabase(this).postDao().getPostById(getIntent().getExtras().getLong("postId"));
            this.description.setText(postEntity.getDescription());
            this.perHourCost.setText(String.valueOf(postEntity.getPerHourCost()));
        }
    }

    public void onPost(View view){
        if(getIntent().hasExtra("status")){
            AppDatabase.getAppDatabase(this).postDao().updatePost(post_field, this.description.getText().toString(), Double.parseDouble(this.perHourCost.getText().toString()), getIntent().getExtras().getLong("postId"));
        } else {
            UserEntity user = AppDatabase.getAppDatabase(this).userDao().loginUser(getIntent().getExtras().getString("user"));
            PostEntity post = new PostEntity(user.getUserFullName(), user.getUserEmail(), post_field, this.description.getText().toString(), Double.parseDouble(this.perHourCost.getText().toString()));
            AppDatabase.getAppDatabase(this).postDao().add(post);
        }
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