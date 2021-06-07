package com.example.tutorme.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tutorme.adapters.ListViewAdapter;
import com.example.tutorme.activities.PostDetailsActivity;
import com.example.tutorme.R;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.PostEntity;

import java.util.List;

public class HomeFragment extends Fragment {
    private ListView listView;
    private Spinner spinner;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.listView = view.findViewById(R.id.list_container);
        this.spinner = view.findViewById(R.id.spinner);

        setUpListAdapter(AppDatabase.getAppDatabase(getContext()).postDao().getAll());

        ArrayAdapter<String> spinnerAdapter = new ArrayAdapter<>(getContext(), android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.names));
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinnerAdapter);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if(parent.getAdapter().getItem(position).toString().equals("All")){
                    setUpListAdapter(AppDatabase.getAppDatabase(getContext()).postDao().getAll());
                } else {
                    String field = parent.getAdapter().getItem(position).toString();
                    setUpListAdapter(AppDatabase.getAppDatabase(getContext()).postDao().getPostByField(field));
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        this.listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getContext(), PostDetailsActivity.class);
                PostEntity post = (PostEntity) parent.getItemAtPosition(position);
                intent.putExtra("post", post.getId());
                intent.putExtra("user", getArguments().getString("user"));
                startActivity(intent);
            }
        });
        setUpListAdapter(AppDatabase.getAppDatabase(getContext()).postDao().getAll());

        return view;
    }

    private void setUpListAdapter(List<PostEntity> posts){
        ListViewAdapter adapter = new ListViewAdapter(getContext(), posts);
        this.listView.setAdapter(adapter);
    }
}
