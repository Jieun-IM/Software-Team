package com.example.damhwa2.ui.mypage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MypageViewModel extends ViewModel {

    private final MutableLiveData<String> userName;
    private final MutableLiveData<String> userStatus;
    private final MutableLiveData<Boolean> isDivorced;
    private final MutableLiveData<String> userProfileImage;
    private final MutableLiveData<String> userHeight;
    private final MutableLiveData<String> userWeight;
    private final MutableLiveData<String> userLiving;
    private final MutableLiveData<String> userTags;

    public MypageViewModel() {
        userName = new MutableLiveData<>();
        userStatus = new MutableLiveData<>();
        isDivorced = new MutableLiveData<>();
        userProfileImage = new MutableLiveData<>();
        userHeight = new MutableLiveData<>();
        userWeight = new MutableLiveData<>();
        userLiving = new MutableLiveData<>();
        userTags = new MutableLiveData<>();

        // 초기 데이터 설정 (필요에 따라 설정)
        // userName.setValue("John Doe");
        // userStatus.setValue("Living life to the fullest!");
        // isDivorced.setValue(true); // 예시로 돌싱 상태를 true로 설정
        // userProfileImage.setValue("URL or File Path");
        // userHeight.setValue("180");
        // userWeight.setValue("75");
        // userLiving.setValue("Seoul");
        // userTags.setValue("#tag1 #tag2");
    }

    public LiveData<String> getUserName() {
        return userName;
    }

    public LiveData<String> getUserStatus() {
        return userStatus;
    }

    public LiveData<Boolean> isDivorced() {
        return isDivorced;
    }

    public LiveData<String> getUserProfileImage() {
        return userProfileImage;
    }

    public LiveData<String> getUserHeight() {
        return userHeight;
    }

    public LiveData<String> getUserWeight() {
        return userWeight;
    }

    public LiveData<String> getUserLiving() {
        return userLiving;
    }

    public LiveData<String> getUserTags() {
        return userTags;
    }

    public void updateUserProfile(String newName, String newStatus, boolean isDivorced,
                                  String newProfileImage, String newHeight, String newWeight,
                                  String newLiving, String newTags) {
        userName.setValue(newName);
        userStatus.setValue(newStatus);
        this.isDivorced.setValue(isDivorced);
        userProfileImage.setValue(newProfileImage);
        userHeight.setValue(newHeight);
        userWeight.setValue(newWeight);
        userLiving.setValue(newLiving);
        userTags.setValue(newTags);
    }
}
