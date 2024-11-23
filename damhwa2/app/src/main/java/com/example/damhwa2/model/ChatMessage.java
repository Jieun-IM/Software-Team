package com.example.damhwa2.model;

public class ChatMessage {
    private String sender;                // 송신자 이름
    private String message;               // 메시지 내용
    private boolean isSentByCurrentUser;  // 현재 사용자가 보낸 메시지인지 여부

    public ChatMessage(String sender, String message, boolean isSentByCurrentUser) {
        this.sender = sender;
        this.message = message;
        this.isSentByCurrentUser = isSentByCurrentUser;
    }

    // Getter와 Setter
    public String getSender() {
        return sender;
    }

    public void setSender(String sender) {
        this.sender = sender;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSentByCurrentUser() {
        return isSentByCurrentUser;
    }

    public void setSentByCurrentUser(boolean sentByCurrentUser) {
        isSentByCurrentUser = sentByCurrentUser;
    }
}
