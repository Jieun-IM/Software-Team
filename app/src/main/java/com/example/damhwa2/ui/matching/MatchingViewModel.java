package com.example.damhwa2.ui.matching;

import androidx.lifecycle.ViewModel;

import com.example.damhwa2.model.TestUser;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

public class MatchingViewModel extends ViewModel {

    private final List<TestUser> userList;

    public MatchingViewModel() {
        // 임시 사용자 리스트 초기화
        userList = Arrays.asList(
                new TestUser("Alice", "female", 25, Arrays.asList("movies", "sports")),
                new TestUser("Bob", "male", 30, Arrays.asList("reading", "music")),
                new TestUser("Charlie", "male", 28, Arrays.asList("travel", "movies")),
                new TestUser("Diana", "female", 24, Arrays.asList("cooking", "sports"))
        );
    }

    // 랜덤 매칭 로직
    public TestUser getRandomMatch(String currentGender) {
        Random random = new Random();
        List<TestUser> filteredList = new ArrayList<>();

        for (TestUser user : userList) {
            if (!user.getGender().equals(currentGender)) {
                filteredList.add(user);
            }
        }

        if (!filteredList.isEmpty()) {
            return filteredList.get(random.nextInt(filteredList.size()));
        } else {
            return null; // 매칭 가능한 대상 없음
        }
    }

    // 태그 기반 매칭 로직
    public TestUser getTagBasedMatch(String currentGender, List<String> preferredTags) {
        TestUser bestMatch = null;
        int maxScore = 0;

        for (TestUser user : userList) {
            if (!user.getGender().equals(currentGender)) {
                // 태그 점수 계산
                int score = 0;
                for (String tag : user.getTags()) {
                    if (preferredTags.contains(tag)) {
                        score++;
                    }
                }

                if (score > maxScore) {
                    maxScore = score;
                    bestMatch = user;
                }
            }
        }

        return bestMatch;
    }
}
