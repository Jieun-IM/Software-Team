package com.example.damhwa2.model; // 프로젝트 패키지 경로에 맞게 작성

public class ChatItem {
    private String name;          // 채팅 상대 이름
    private String lastMessage;   // 마지막 메시지
    private String profileImage;  // 프로필 이미지 URL (옵션)

    // 생성자
    public ChatItem(String name, String lastMessage, String profileImage) {
        this.name = name;
        this.lastMessage = lastMessage;
        this.profileImage = profileImage;
    }

    // Getter와 Setter
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastMessage() {
        return lastMessage;
    }

    public void setLastMessage(String lastMessage) {
        this.lastMessage = lastMessage;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }
}
