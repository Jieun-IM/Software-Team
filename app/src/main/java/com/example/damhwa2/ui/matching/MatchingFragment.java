package com.example.damhwa2.ui.matching;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.damhwa2.databinding.FragmentMatchingBinding;
import com.example.damhwa2.model.TestUser;
import com.example.damhwa2.R;

import java.util.Arrays;
import java.util.List;

public class MatchingFragment extends Fragment {

    private FragmentMatchingBinding binding;
    private MatchingViewModel matchingViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        // ViewModel 연결
        matchingViewModel = new ViewModelProvider(this).get(MatchingViewModel.class);

        // ViewBinding 설정
        binding = FragmentMatchingBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // 랜덤 매칭 버튼 동작
        binding.buttonRandomMatching.setOnClickListener(v -> {
            TestUser randomMatch = matchingViewModel.getRandomMatch("male"); // 현재 사용자 성별 예시: 남성
            if (randomMatch != null) {
                Toast.makeText(getContext(), "랜덤 매칭: " + randomMatch, Toast.LENGTH_SHORT).show();

                // 매칭된 상대와 채팅하기 위한 Bundle 전달
                Bundle args = new Bundle();
                args.putString("chatWith", randomMatch.getName());  // 여기에서 상대방 이름을 설정
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_home);
                navController.navigate(R.id.navigation_chat, args);  // navigation_chat은 모바일 내비게이션 xml에서 설정한 ID
            } else {
                Toast.makeText(getContext(), "매칭 가능한 상대가 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        // 태그 기반 매칭 버튼 동작
        binding.buttonTagMatching.setOnClickListener(v -> {
            List<String> preferredTags = Arrays.asList("sports", "movies");
            TestUser tagBasedMatch = matchingViewModel.getTagBasedMatch("male", preferredTags);
            if (tagBasedMatch != null) {
                Toast.makeText(getContext(), "태그 기반 매칭: " + tagBasedMatch, Toast.LENGTH_SHORT).show();

                // 매칭된 상대와 채팅하기 위한 Bundle 전달
                Bundle args = new Bundle();
                args.putString("chatWith", tagBasedMatch.getName());  // 여기에서 상대방 이름을 설정
                NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_home);
                navController.navigate(R.id.navigation_chat, args);  // navigation_chat은 모바일 내비게이션 xml에서 설정한 ID
            } else {
                Toast.makeText(getContext(), "매칭 가능한 상대가 없습니다.", Toast.LENGTH_SHORT).show();
            }
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}