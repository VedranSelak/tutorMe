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
import com.example.tutorme.roomdatabase.FavouritesEntity;
import com.example.tutorme.roomdatabase.PostEntity;

import java.util.ArrayList;
import java.util.List;

public class AccountFragment extends Fragment {
    private ListView yourList;
    private ListView favouritesList;
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_account, container, false);
        this.yourList = view.findViewById(R.id.your_posts_container);
        this.favouritesList = view.findViewById(R.id.favourites_container);
        setUpListAdapter();

        this.yourList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        this.favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

        return view;
    }

    private void setUpListAdapter(){
        List<PostEntity> userPosts = AppDatabase.getAppDatabase(getContext()).postDao().getAllByUserEmail(getArguments().getString("user"));
        List<FavouritesEntity> favourites = AppDatabase.getAppDatabase(getContext()).favouritesDao().getAllByUserEmail(getArguments().getString("user"));
        List<PostEntity> favouritePosts = new ArrayList<PostEntity>();
        for(FavouritesEntity favouritesEntity : favourites){
            favouritePosts.add(AppDatabase.getAppDatabase(getContext()).postDao().getPostById(favouritesEntity.getPostId()));
        }

        ListViewAdapter postsAdapter = new ListViewAdapter(getContext(), userPosts);
        ListViewAdapter favouritesAdapter = new ListViewAdapter(getContext(), favouritePosts);
        this.yourList.setAdapter(postsAdapter);
       this.favouritesList.setAdapter(favouritesAdapter);
    }
}
