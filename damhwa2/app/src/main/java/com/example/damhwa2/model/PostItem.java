package com.example.damhwa2.model;

public class PostItem {
    private String username;
    private String profileImage;
    private String content;
    private int comment;
    private int like;


    // 생성자
    public PostItem(String username, String profileImage, String content, int comment, int like) {
        this.username = username;
        this.profileImage = profileImage;
        this.content = content;
        this.comment = comment;
        this.like = like;
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

    public int getComment() {
        return comment;
    }

    public void setComment(int comment) {
        this.comment = comment;
    }

    public int getLike() {
        return like;
    }

    public void setLike(int like) {
        this.like = like;
    }
}

