package com.example.tutorme.adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import com.example.tutorme.R;
import com.example.tutorme.roomdatabase.AppDatabase;
import com.example.tutorme.roomdatabase.entities.PostEntity;
import com.example.tutorme.roomdatabase.entities.RatingEntity;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<PostEntity> posts;

    public ListViewAdapter(Context context, List<PostEntity> posts) {
        this.context = context;
        this.posts = posts;
    }

    @Override
    public int getCount() {
        return this.posts.size();
    }

    @Override
    public Object getItem(int position) {
        return this.posts.get(position);
    }

    @Override
    public long getItemId(int position) {
        return posts.get(position).getId();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        LayoutInflater inflater = LayoutInflater.from(this.context);
        convertView = inflater.inflate(R.layout.list_view_item, parent, false);

        PostEntity post = this.posts.get(position);

        TextView fullName = convertView.findViewById(R.id.full_name_text);
        TextView field = convertView.findViewById(R.id.field_text);
        TextView cph = convertView.findViewById(R.id.cost_per_hour_text);
        RatingBar ratingBar = convertView.findViewById(R.id.rating_display);

        List<RatingEntity> ratings = AppDatabase.getAppDatabase(this.context).ratingDao().getRatingsByUserEmail(post.getEmailOfTutor());
        float sum = 0;
        for(RatingEntity re : ratings) {
            sum += re.getRating();
        }

        float avrg = sum / ratings.size();

        fullName.setText(post.getFullNameOfTutor());
        field.setText(post.getFieldName());
        String cost = String.valueOf(post.getPerHourCost()) + " $";
        cph.setText(cost);
        ratingBar.setRating(avrg);
        return convertView;
    }
}
