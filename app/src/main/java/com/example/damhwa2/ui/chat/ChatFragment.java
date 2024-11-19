package com.example.damhwa2.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.damhwa2.R;
import com.example.damhwa2.adapter.ChatMessageAdapter;
import com.example.damhwa2.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {

    private ChatMessageAdapter adapter;
    private ChatViewModel chatViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View root = inflater.inflate(R.layout.fragment_chat, container, false);

        // ViewModel 연결
        chatViewModel = new ViewModelProvider(this).get(ChatViewModel.class);

        RecyclerView recyclerView = root.findViewById(R.id.chat_recycler_view);
        EditText messageInput = root.findViewById(R.id.message_input);
        root.findViewById(R.id.send_button).setOnClickListener(v -> {
            String message = messageInput.getText().toString().trim();
            if (!message.isEmpty()) {
                chatViewModel.sendMessage(message);  // ViewModel을 통해 메시지 전송
                messageInput.setText("");
            }
        });

        // RecyclerView 설정
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        adapter = new ChatMessageAdapter(new ArrayList<>());
        recyclerView.setAdapter(adapter);

        // ViewModel의 메시지 리스트를 관찰하여 UI 업데이트
        chatViewModel.getChatMessages().observe(getViewLifecycleOwner(), chatMessages -> {
            adapter.updateData(chatMessages);  // RecyclerView 어댑터에 새로운 데이터 반영
        });

        return root;
    }
}
