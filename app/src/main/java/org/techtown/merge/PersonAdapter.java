package org.techtown.merge;

import android.content.Context;
import android.text.Layout;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.squareup.picasso.Picasso;

import org.w3c.dom.Text;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;

public class PersonAdapter extends RecyclerView.Adapter<PersonAdapter.ViewHolder> {

    LayoutInflater inflater;
    List<Person> posts;

    public PersonAdapter(Context ctx, List<Person> posts){
        this.inflater = LayoutInflater.from(ctx);
        this.posts = posts;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.person_item,parent,false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(posts.get(position).getTitle());
        holder.address.setText(posts.get(position).getAddress());
        Picasso.get().load(posts.get(position).getCoverImage()).into(holder.postImage);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{

        TextView title, address;
        ImageView postImage;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            title = itemView.findViewById(R.id.textView1);
            address = itemView.findViewById(R.id.textView2);
            postImage = itemView.findViewById(R.id.imageView);
        }
    }

}