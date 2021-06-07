package com.example.tutorme.fragments;

import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.tutorme.MainActivity;
import com.example.tutorme.activities.PostActivity;
import com.example.tutorme.adapters.ListViewAdapter;
import com.example.tutorme.R;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.FavouritesEntity;
import com.example.tutorme.roomdatabase.entities.PostEntity;
import com.example.tutorme.utilities.MyOnClickListener;

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

                ImageView delete = myDialog.findViewById(R.id.pop_delete);
                ImageView edit = myDialog.findViewById(R.id.pop_edit);
                Context context = getContext();

                delete.setOnClickListener(new MyOnClickListener(post, context) {
                    @Override
                    public void onClick(View v) {
                        AppDatabase.getAppDatabase(context).favouritesDao().removeFromFavouritesByPostId(post.getId());
                        AppDatabase.getAppDatabase(context).postDao().delete(post);
                        
                        Intent intent = getActivity().getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        getActivity().finish();
                        startActivity(intent);
                    }
                });

                edit.setOnClickListener(new MyOnClickListener(post, context){
                    @Override
                    public void onClick(View v) {
                        Intent intent = new Intent(context, PostActivity.class);
                        intent.putExtra("status", "edit");
                        intent.putExtra("user", post.getEmailOfTutor());
                        intent.putExtra("postId", post.getId());
                        startActivity(intent);
                    }
                });

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
                myDialog.setContentView(R.layout.favouritespopup);

                PostEntity post = (PostEntity) parent.getItemAtPosition(position);

                TextView name = myDialog.findViewById(R.id.pop_fav_name);
                TextView field = myDialog.findViewById(R.id.pop_fav_field);
                TextView cost = myDialog.findViewById(R.id.pop_fav_cost_per_hour);

                name.setText(post.getFullNameOfTutor());
                field.setText(post.getFieldName());
                String cph = String.valueOf(post.getPerHourCost()) + " $";
                cost.setText(cph);

                ImageView remove = myDialog.findViewById(R.id.pop_remove);
                ImageView rate = myDialog.findViewById(R.id.pop_rate);
                Context context = getContext();

                remove.setOnClickListener(new MyOnClickListener(post, context) {
                    @Override
                    public void onClick(View v) {
                        AppDatabase.getAppDatabase(context).favouritesDao().removeFromFavouritesByPostId(post.getId());

                        Intent intent = getActivity().getIntent();
                        intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION);
                        getActivity().finish();
                        startActivity(intent);
                    }
                });

                rate.setOnClickListener(new MyOnClickListener(post, context){
                    @Override
                    public void onClick(View v) {

                    }
                });

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
