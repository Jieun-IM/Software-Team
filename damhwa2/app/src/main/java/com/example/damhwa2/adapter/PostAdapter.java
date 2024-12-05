package com.example.damhwa2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.damhwa2.R;
import com.example.damhwa2.model.PostItem;

import java.util.List;

public class PostAdapter extends RecyclerView.Adapter<PostAdapter.PostViewHolder> {

    private List<PostItem> postList;

    public PostAdapter(List<PostItem> postList) {
        this.postList = postList;
    }

    @NonNull
    @Override
    public PostViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_post, parent, false);
        return new PostViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull PostViewHolder holder, int position) {
        PostItem post = postList.get(position);

        holder.username.setText(post.getUsername());
        holder.content.setText(post.getContent());
        holder.comment.setText(String.valueOf(post.getComment()));
        holder.like.setText(String.valueOf(post.getLike()));


        // Glide로 프로필 이미지 로드
        if (post.getProfileImage() != null) {
            Glide.with(holder.profileImage.getContext())
                    .load(post.getProfileImage())
                    .placeholder(R.drawable.ic_launcher_foreground)
                    .into(holder.profileImage);
        } else {
            holder.profileImage.setImageResource(R.drawable.ic_launcher_foreground);
        }
    }

    @Override
    public int getItemCount() {
        return postList.size();
    }

    public void updateData(List<PostItem> newPostList) {
        this.postList = newPostList;
        notifyDataSetChanged();
    }

    static class PostViewHolder extends RecyclerView.ViewHolder {
        ImageView profileImage;
        TextView username;
        TextView content;
        TextView comment;
        TextView like;

        PostViewHolder(View itemView) {
            super(itemView);
            profileImage = itemView.findViewById(R.id.profile_image);
            username = itemView.findViewById(R.id.username);
            content = itemView.findViewById(R.id.post_content);
            comment = itemView.findViewById(R.id.comment_count);
            like = itemView.findViewById(R.id.like_count);
        }
    }
}

