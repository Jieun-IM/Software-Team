package com.example.damhwa2.ui.mypage;

import androidx.lifecycle.LiveData;
import androidx.lifecycle.MutableLiveData;
import androidx.lifecycle.ViewModel;

public class MypageViewModel extends ViewModel {

    private final MutableLiveData<String> userName;
    private final MutableLiveData<String> userStatus;
    private final MutableLiveData<Boolean> isDivorced;

    public MypageViewModel() {
        userName = new MutableLiveData<>();
        userStatus = new MutableLiveData<>();
        isDivorced = new MutableLiveData<>();

        // 초기 데이터 설정
        userName.setValue("John Doe");
        userStatus.setValue("Living life to the fullest!");
        isDivorced.setValue(true); // 예시로 돌싱 상태를 true로 설정
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

    public void updateUserProfile(String newName, String newStatus) {
        userName.setValue(newName);
        userStatus.setValue(newStatus);
    }
}
