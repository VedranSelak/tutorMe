package com.example.tutorme.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.tutorme.adapters.ListViewAdapter;
import com.example.tutorme.R;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.FavouritesEntity;
import com.example.tutorme.roomdatabase.entities.PostEntity;

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
                Dialog myDialog = new Dialog(getContext());
                myDialog.setContentView(R.layout.custompopup);

                PostEntity post = (PostEntity) parent.getItemAtPosition(position);

                TextView name = myDialog.findViewById(R.id.pop_full_name_text);
                TextView field = myDialog.findViewById(R.id.pop_field_text);
                TextView cost = myDialog.findViewById(R.id.pop_cost_per_hour_text);

                name.setText(post.getFullNameOfTutor());
                field.setText(post.getFieldName());
                String cph = String.valueOf(post.getPerHourCost()) + " $";
                cost.setText(cph);

                myDialog.show();
            }
        });

        this.favouritesList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Dialog myDialog = new Dialog(getContext());
                myDialog.setContentView(R.layout.custompopup);

                PostEntity post = (PostEntity) parent.getItemAtPosition(position);

                TextView name = myDialog.findViewById(R.id.pop_full_name_text);
                TextView field = myDialog.findViewById(R.id.pop_field_text);
                TextView cost = myDialog.findViewById(R.id.pop_cost_per_hour_text);

                name.setText(post.getFullNameOfTutor());
                field.setText(post.getFieldName());
                String cph = String.valueOf(post.getPerHourCost()) + " $";
                cost.setText(cph);

                myDialog.show();
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
