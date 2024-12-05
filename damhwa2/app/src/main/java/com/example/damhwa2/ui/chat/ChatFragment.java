package com.example.damhwa2.ui.chat;

import com.example.damhwa2.R;
import android.os.Bundle;
import android.util.Log;
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
import com.example.damhwa2.adapter.ChatAdapter;
import com.example.damhwa2.model.ChatItem;
import org.java_websocket.client.WebSocketClient;
import org.java_websocket.handshake.ServerHandshake;
import java.net.URI;
import java.util.ArrayList;
import java.util.List;
import org.json.JSONObject;

public class ChatFragment extends Fragment {
    private WebSocketClient webSocketClient;
    private List<ChatItem> chatList;
    private ChatAdapter chatAdapter;
    private EditText messageEditText;
    private String chatWith;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_chat, container, false);

        // 전달받은 상대 이름 설정
        Bundle args = getArguments();

        if (args != null) {
            chatWith = args.getString("chatWith", "MatchedUser");  // chatWith 값 초기화
        }

        Log.d("ChatFragment", "Chatting with: " + chatWith);

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
                String senderName = "Me"; // 이 부분은 실제 사용자 이름으로 바꿀 수 있습니다.
                // JSON 형식으로 메시지와 상대 정보를 전송
                String jsonMessage = String.format("{\"from\":\"%s\", \"to\":\"%s\", \"text\":\"%s\"}", senderName, chatWith, message);
                webSocketClient.send(jsonMessage);
                // 로컬 메시지 추가 (발신자 이름을 "Me"로 설정)
                updateChatList(senderName, message);
                messageEditText.setText("");
            }
        });

        return view;
    }


    private void connectWebSocket() {
        URI uri;
        try {
            uri = new URI("ws://10.0.2.2:8080"); // WebSocket 서버 주소 입력
        } catch (Exception e) {
            e.printStackTrace();
            return;
        }

        webSocketClient = new WebSocketClient(uri) {
            @Override
            public void onOpen(ServerHandshake handshake) {
//                updateChatList("System", "Connected to the server");
            }

            @Override
            public void onMessage(String message) {
                try {
                    // 메시지 로그 확인
                    Log.d("WebSocket", "Received message: " + message);

                    // JSON 문자열을 JSONObject로 변환
                    JSONObject jsonMessage = new JSONObject(message);

                    // "message" 필드의 값을 추출
                    String receivedMessage = jsonMessage.getString("text");

                    // 채팅 리스트에 추가
                    updateChatList(chatWith, receivedMessage); // "Server"는 발신자 이름
                } catch (Exception e) {
                    e.printStackTrace(); // 예외 처리
                }
            }

            @Override
            public void onClose(int code, String reason, boolean remote) {
//                updateChatList("System", "Disconnected from the server");
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