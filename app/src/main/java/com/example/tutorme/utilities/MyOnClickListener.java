package com.example.tutorme.utilities;

import android.content.Context;
import android.content.Intent;
import android.view.View;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;

import com.example.tutorme.MainActivity;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.PostEntity;
import com.example.tutorme.roomdatabase.entities.UserEntity;

public class MyOnClickListener implements View.OnClickListener {

    private Context context;
    private PostEntity post;
    public MyOnClickListener(PostEntity post, Context context) {
        this.post = post;
        this.context = context;
    }

    @Override
    public void onClick(View v) {

    }
}
