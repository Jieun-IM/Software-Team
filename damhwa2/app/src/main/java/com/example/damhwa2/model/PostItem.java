package com.example.damhwa2.model;

public class PostItem {
    private String username;
    private String profileImage;
    private String content;

    // 생성자
    public PostItem(String username, String profileImage, String content) {
        this.username = username;
        this.profileImage = profileImage;
        this.content = content;
    }

    // Getter와 Setter
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getProfileImage() {
        return profileImage;
    }

    public void setProfileImage(String profileImage) {
        this.profileImage = profileImage;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}

