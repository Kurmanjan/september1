package com.example.viewpager.adepters;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;

import com.example.viewpager.R;
import com.example.viewpager.data.models.PostModel;

import java.util.ArrayList;

public class PostAdepter extends RecyclerView.Adapter<PostAdepter.ViewHolder> {

    com.example.viewpager.adepters.OnClick onClick;

    public ArrayList<PostModel> postModels = new ArrayList<>();

    public void setOnClick(com.example.viewpager.adepters.OnClick onClick) {
        this.onClick = onClick;
    }

    public void setPostModels(ArrayList<PostModel> postModels) {
        this.postModels = postModels;
        notifyDataSetChanged();
    }

    public void delete(int position){
        postModels.remove(position);
        notifyItemRemoved(position); //udalyaet c recycler
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.post_list, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        holder.title.setText(postModels.get(position).getTitle());
        holder.content.setText(postModels.get(position).getContent());


    }

    @Override
    public int getItemCount() {
        return postModels.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView title, content;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                 onClick.onClick(getAdapterPosition());
                }
            });
            title = itemView.findViewById(R.id.title_view);
            content = itemView.findViewById(R.id.content_view);


            itemView.setOnLongClickListener(new View.OnLongClickListener() {
                @Override
                public boolean onLongClick(View view) {



                    onClick.onLongClick(getAdapterPosition());

                    return true;
                }
            });
        }


    }

    public interface OnClick {
        void onClick(int position);
    }
}
