package com.example.damhwa2.ui.chat;

import androidx.lifecycle.ViewModel;
import androidx.lifecycle.MutableLiveData;

import com.example.damhwa2.model.ChatMessage;

import java.util.ArrayList;
import java.util.List;

public class ChatViewModel extends ViewModel {

    private final MutableLiveData<List<ChatMessage>> chatMessages = new MutableLiveData<>(new ArrayList<>());

    // 메시지 리스트 가져오기
    public MutableLiveData<List<ChatMessage>> getChatMessages() {
        return chatMessages;
    }

    // 메시지 보내기
    public void sendMessage(String message) {
        List<ChatMessage> updatedMessages = chatMessages.getValue();
        if (updatedMessages != null) {
            updatedMessages.add(new ChatMessage("You", message, true));
            chatMessages.setValue(updatedMessages);  // 변경된 값으로 LiveData 업데이트
        }
    }
}
