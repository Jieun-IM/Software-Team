package com.example.damhwa2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.damhwa2.R;
import com.example.damhwa2.model.ChatItem;

import java.util.List;

public class ChatAdapter extends RecyclerView.Adapter<ChatAdapter.ChatViewHolder> {

    private List<ChatItem> chatList;
    private OnItemClickListener onItemClickListener;

    public ChatAdapter(List<ChatItem> chatList) {
        this.chatList = chatList;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatItem chatItem = chatList.get(position);

        // 이름과 마지막 메시지 설정
        holder.chatName.setText(chatItem.getName());
        holder.lastMessage.setText(chatItem.getLastMessage());

        // 프로필 이미지 설정 (기본 이미지 대체 가능)
        if (chatItem.getProfileImage() != null) {
            // Glide 또는 Picasso 같은 라이브러리를 사용해서 이미지 로드 가능
            // Glide.with(holder.profileImage.getContext()).load(chatItem.getProfileImage()).into(holder.profileImage);
//            holder.profileImage.setImageResource(R.drawable.ic_launcher_foreground); // 예시: 기본 이미지
        } else {
//            holder.profileImage.setImageResource(R.drawable.ic_launcher_foreground); // 기본 이미지
        }

        // 아이템 클릭 리스너 설정
        holder.itemView.setOnClickListener(v -> {
            if (onItemClickListener != null) {
                onItemClickListener.onItemClick(position); // 클릭한 위치 전달
            }
        });
    }

    @Override
    public int getItemCount() {
        return chatList.size();
    }

    // 데이터 갱신 메서드
    public void updateData(List<ChatItem> newChatList) {
        this.chatList = newChatList;
        notifyDataSetChanged(); // RecyclerView 갱신
    }

    // 클릭 이벤트를 처리할 인터페이스
    public interface OnItemClickListener {
        void onItemClick(int position);
    }

    // 아이템 클릭 리스너 설정 메서드
    public void setOnItemClickListener(OnItemClickListener listener) {
        this.onItemClickListener = listener;
    }

    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView chatName;
        TextView lastMessage;
        ImageView profileImage;

        ChatViewHolder(View itemView) {
            super(itemView);
            chatName = itemView.findViewById(R.id.chat_name);
            lastMessage = itemView.findViewById(R.id.last_message);
            profileImage = itemView.findViewById(R.id.profile_image);
        }
    }
}
