package com.example.tutorme;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.UserEntity;

public class LoginActivity extends AppCompatActivity {

    EditText userEmail, userPassword;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        this.userEmail = findViewById(R.id.login_email_input);
        this.userPassword = findViewById(R.id.login_password_input);
    }

    public void goToRegister(View view){
        Intent intent = new Intent(this, RegisterActivity.class);
        startActivity(intent);
    }

    public void login(View view){
        try {
            UserEntity userEntity = AppDatabase.getAppDatabase(this).userDao().loginUser(this.userEmail.getText().toString());
            if(userEntity.getUserPassword().equals(this.userPassword.getText().toString())){
                Intent intent = new Intent(this, MainActivity.class);
                intent.putExtra("user", this.userEmail.getText().toString());
                startActivity(intent);
            } else {
                Toast.makeText(this, "Invalid password!", Toast.LENGTH_SHORT).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "This user doesn't exist!", Toast.LENGTH_SHORT).show();
        }
    }

}