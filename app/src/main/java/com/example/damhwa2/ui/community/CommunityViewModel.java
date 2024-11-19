package com.example.damhwa2.ui.community;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

import com.example.damhwa2.model.PostItem;

import java.util.ArrayList;
import java.util.List;

public class CommunityViewModel extends ViewModel {

    private final MutableLiveData<List<PostItem>> postList;

    public CommunityViewModel() {
        postList = new MutableLiveData<>();
        loadInitialPosts();
    }

    private void loadInitialPosts() {
        List<PostItem> initialPosts = new ArrayList<>();
        // db 연결되면 수정해야함.
        initialPosts.add(new PostItem("Alice", null, "Hello, this is my first post!"));
        initialPosts.add(new PostItem("Bob", null, "Check out this amazing view."));
        initialPosts.add(new PostItem("Charlie", null, "What do you think about this topic?"));

        postList.setValue(initialPosts);
    }

    public LiveData<List<PostItem>> getPostList() {
        return postList;
    }

    public void addPost(String username, String content, String profileImage) {
        List<PostItem> currentList = postList.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(new PostItem(username, profileImage, content));
        postList.setValue(currentList);
    }
}
