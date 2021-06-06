package com.example.tutorme;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.PostEntity;

import java.util.List;

public class HomeFragment extends Fragment {
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.listView = view.findViewById(R.id.list_container);
        setUpListAdapter();

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

        return view;
    }

    private void setUpListAdapter(){
        List<PostEntity> posts = AppDatabase.getAppDatabase(getContext()).postDao().getAll();


        ListViewAdapter adapter = new ListViewAdapter(getContext(), posts);
        this.listView.setAdapter(adapter);
    }
}
