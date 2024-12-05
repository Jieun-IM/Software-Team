package com.example.damhwa2.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.damhwa2.R;
import com.example.damhwa2.model.ChatMessage;

import java.util.List;

public class ChatMessageAdapter extends RecyclerView.Adapter<ChatMessageAdapter.ChatViewHolder> {

    private List<ChatMessage> chatMessages;

    // 생성자에서 데이터를 받도록 설정
    public ChatMessageAdapter(List<ChatMessage> chatMessages) {
        this.chatMessages = chatMessages;
    }

    @NonNull
    @Override
    public ChatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.item_chat_message_me, parent, false);
        return new ChatViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ChatViewHolder holder, int position) {
        ChatMessage chatMessage = chatMessages.get(position);
        holder.message.setText(chatMessage.getMessage());

        // 메시지의 발신 여부에 따라 스타일 조정
        if (chatMessage.isSentByCurrentUser()) { // 발신자일 경우
            holder.message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_END);  // 오른쪽 정렬
            holder.message.setBackgroundResource(R.drawable.chat_bubble_me);  // 발신자 스타일
        } else { // 수신자일 경우
            holder.message.setTextAlignment(View.TEXT_ALIGNMENT_TEXT_START);  // 왼쪽 정렬
            holder.message.setBackgroundResource(R.drawable.chat_bubble_other);  // 수신자 스타일
        }
    }

    @Override
    public int getItemCount() {
        return chatMessages.size();
    }

    // 데이터를 업데이트하는 메서드 추가
    public void updateData(List<ChatMessage> newChatMessages) {
        // 새로 들어온 데이터를 기존 리스트에 업데이트
        this.chatMessages = newChatMessages;
        notifyDataSetChanged();  // 데이터 갱신 후 어댑터에 변경사항 알림
    }

    // ViewHolder 클래스
    static class ChatViewHolder extends RecyclerView.ViewHolder {
        TextView message;

        ChatViewHolder(View itemView) {
            super(itemView);
            message = itemView.findViewById(R.id.message_text_me);
        }
    }
}
