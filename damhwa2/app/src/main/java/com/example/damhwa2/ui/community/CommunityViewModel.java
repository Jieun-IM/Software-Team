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
        initialPosts.add(new PostItem("Alice", null, "Hello, this is my first post!", 10, 5));
        initialPosts.add(new PostItem("Bob", null, "Check out this amazing view.", 7, 0));
        initialPosts.add(new PostItem("Charlie", null, "What do you think about this topic?", 2, 3));
        initialPosts.add(new PostItem("Diana", null, "Loving the weather today! 🌞", 15, 8));
        initialPosts.add(new PostItem("Evan", null, "Just finished a 5k run. Feeling great! 🏃‍♂️", 20, 12));
        initialPosts.add(new PostItem("Fiona", null, "Anyone have good book recommendations? 📚", 5, 4));
        initialPosts.add(new PostItem("George", null, "Can't believe how fast time flies. Happy New Year! 🎉", 30, 10));
        initialPosts.add(new PostItem("Hannah", null, "Trying out a new recipe tonight. Wish me luck! 🍳", 12, 3));
        initialPosts.add(new PostItem("Ivy", null, "Here's a photo of my adorable puppy! 🐶", 25, 18));
        initialPosts.add(new PostItem("Jack", null, "Who else is excited for the concert tomorrow? 🎶", 40, 25));
        initialPosts.add(new PostItem("Karen", null, "Does anyone know how to fix this? My phone won't charge. 😓", 3, 9));
        initialPosts.add(new PostItem("Leo", null, "Exploring the mountains this weekend. 🏔️", 50, 22));

        postList.setValue(initialPosts);
    }

    public LiveData<List<PostItem>> getPostList() {
        return postList;
    }

    public void addPost(String username, String content, String profileImage, int comment, int like) {
        List<PostItem> currentList = postList.getValue();
        if (currentList == null) {
            currentList = new ArrayList<>();
        }
        currentList.add(new PostItem(username, profileImage, content, comment, like));
        postList.setValue(currentList);
    }
}
