package com.example.tutorme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.UserDao;
import com.example.tutorme.roomdatabase.UserEntity;

public class RegisterActivity extends AppCompatActivity {

    EditText userFullName, userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        this.userFullName = findViewById(R.id.full_name_input);
        this.userEmail = findViewById(R.id.email_input);
        this.userPassword = findViewById(R.id.password_input);
    }

    public void register(View view){
        UserEntity userEntity = new UserEntity();
        userEntity.setUserFullName(this.userFullName.getText().toString());
        userEntity.setUserEmail(this.userEmail.getText().toString());
        userEntity.setUserPassword(this.userPassword.getText().toString());

        try {
            if(validate(userEntity)){
                AppDatabase appDatabase = AppDatabase.getAppDatabase(this);
                appDatabase.userDao().registerUser(userEntity);
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", this.userEmail.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid input!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "User with that email already exists!", Toast.LENGTH_LONG).show();
        }

    }

    private boolean validate(UserEntity userEntity){
        if(userEntity.getUserFullName().isEmpty() || userEntity.getUserEmail().isEmpty() || userEntity.getUserPassword().isEmpty()){
            return false;
        } else {
            return true;
        }
    }

    public void goToLogin(View view){
        Intent intent = new Intent(this, LoginActivity.class);
        startActivity(intent);
    }

}