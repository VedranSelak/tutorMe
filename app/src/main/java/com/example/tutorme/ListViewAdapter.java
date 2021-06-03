package com.example.tutorme;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.List;

public class ListViewAdapter extends BaseAdapter {

    private Context context;
    private List<Post> posts;

    public ListViewAdapter(Context context, List<Post> posts) {
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

        Post post = this.posts.get(position);

        TextView fullName = convertView.findViewById(R.id.full_name_text);
        TextView email = convertView.findViewById(R.id.email_text);
        TextView field = convertView.findViewById(R.id.field_text);

        fullName.setText(post.getFullNameOfTutor());
        email.setText(post.getEmailOfTutor());
        field.setText(post.getFieldName());
        return convertView;
    }
}
