package com.example.damhwa2.ui.home;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.damhwa2.model.ChatItem;

import java.util.ArrayList;
import java.util.List;

public class HomeViewModel extends ViewModel {

    // 채팅 목록을 LiveData로 관리
    private final MutableLiveData<List<ChatItem>> chatList;

    public HomeViewModel() {
        chatList = new MutableLiveData<>();
        loadInitialData();
    }

    // 초기 데이터를 설정하는 메서드
    private void loadInitialData() {
        // db 연결되면 수정해야함.
        List<ChatItem> initialChatList = new ArrayList<>();
        initialChatList.add(new ChatItem("Alice", "Hello!", null));
        initialChatList.add(new ChatItem("Bob", "How are you?", null));
        initialChatList.add(new ChatItem("Charlie", "See you later!", null));

        chatList.setValue(initialChatList);
    }

    // 채팅 목록을 가져오는 메서드
    public LiveData<List<ChatItem>> getChatList() {
        return chatList;
    }

    // 새로운 채팅 메시지를 추가하는 메서드 (추가 기능)
    public void addChatItem(ChatItem newChat) {
        List<ChatItem> currentList = chatList.getValue();
        if (currentList != null) {
            currentList.add(newChat);
            chatList.setValue(currentList); // LiveData 업데이트
        }
    }
}
