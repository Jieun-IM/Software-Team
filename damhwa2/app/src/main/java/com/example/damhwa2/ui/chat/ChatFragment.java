package com.example.damhwa2.ui.chat;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.Button;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.damhwa2.R;
import com.example.damhwa2.adapter.ChatAdapter;
import com.example.damhwa2.model.ChatItem;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;

public class ChatFragment extends Fragment {
    private WebSocketClient webSocketClient;
    private List<ChatItem> chatList;
    private ChatAdapter chatAdapter;
    private EditText messageEditText;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        RecyclerView chatRecyclerView = view.findViewById(R.id.chatRecyclerView);
        messageEditText = view.findViewById(R.id.messageEditText);
        Button sendButton = view.findViewById(R.id.sendButton);

        chatList = new ArrayList<>();
        chatAdapter = new ChatAdapter(chatList);
        chatRecyclerView.setLayoutManager(new LinearLayoutManager(getContext()));
        chatRecyclerView.setAdapter(chatAdapter);

        // WebSocket 연결
        connectWebSocket();

        // 메시지 전송 버튼
        sendButton.setOnClickListener(v -> {
            String message = messageEditText.getText().toString();
            if (!message.isEmpty() && webSocketClient != null) {
                webSocketClient.send(message); // 메시지를 서버로 전송
                updateChatList("Me", message); // 로컬 메시지 추가
                messageEditText.setText("");
            }
        });

        return view;
    }

    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://yourserveraddress:8080"); // WebSocket 서버 주소 입력
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshake) {
                updateChatList("System", "Connected to the server");
            }

            @Override
            public void onMessage(String message) {
                // 서버에서 메시지를 수신했을 때
                updateChatList("Server", message);
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
                updateChatList("System", "Disconnected from the server");
            }

            @Override
            public void onError(Exception ex) {
                ex.printStackTrace();
            }
        };
        webSocketClient.connect();
    }

    private void updateChatList(String name, String lastMessage) {
        getActivity().runOnUiThread(() -> {
            chatList.add(new ChatItem(name, lastMessage));
            chatAdapter.notifyItemInserted(chatList.size() - 1);
        });
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        if (webSocketClient != null) {
            webSocketClient.close();
        }
    }
}
