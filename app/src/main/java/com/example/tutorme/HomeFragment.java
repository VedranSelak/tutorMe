package com.example.tutorme;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class HomeFragment extends Fragment {
    private ListView listView;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        this.listView = view.findViewById(R.id.list_container);
        setUpListAdapter();

        return view;
    }

    private void setUpListAdapter(){
        List<Post> posts = new ArrayList<Post>();
        posts.add(new Post("Eldar Jahic", "eldarjahic@gmail.com", "Math"));
        posts.add(new Post("Vedran Selak", "selakvedran@gmail.com", "Sport"));

        ListViewAdapter adapter = new ListViewAdapter(getContext(), posts);
        this.listView.setAdapter(adapter);
    }
}
