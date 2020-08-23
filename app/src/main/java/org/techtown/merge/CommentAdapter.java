package org.techtown.merge;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class CommentAdapter extends RecyclerView.Adapter<CommentAdapter.ViewHolder>{
    ArrayList<Comment> items = new ArrayList<Comment>();

    public void addItem(Comment item){
        items.add(item);
    }

    public void setItems(ArrayList<Comment> items){
        this.items=items;
    }

    public Comment getITem(int position){
        return items.get(position);
    }

    public void setITem(int position, Comment item){
        items.set(position, item);
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(parent.getContext());
        View itemView = inflater.inflate(R.layout.comment_item, parent, false);

        return new ViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewholder, int position) {
        Comment item = items.get(position);
        viewholder.setItem(item);
    }

    @Override
    public int getItemCount() {
        return items.size();
    }


    static class ViewHolder extends RecyclerView.ViewHolder{
        TextView textView8;
        TextView textView9;

        public ViewHolder(View itemView){
            super(itemView);

            textView8 = itemView.findViewById(R.id.textView8);
            textView9 = itemView.findViewById(R.id.textView9);
        }

        public void setItem(Comment item){
            textView8.setText(item.getNickname());
            textView9.setText(item.getContent());
        }
    }
}
