package com.example.damhwa2.ui.matching;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.damhwa2.R;

public class ProfileFragment extends Fragment {

    private ImageView ivProfileImage;
    private TextView tvUserName, tvUserAge, tvUserGender, tvUserTags;
    private Button btnMatch;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_profile, container, false);

        // 뷰 초기화
        ivProfileImage = view.findViewById(R.id.ivProfileImage);
        tvUserName = view.findViewById(R.id.tvUserName);
        tvUserAge = view.findViewById(R.id.tvUserAge);
        tvUserGender = view.findViewById(R.id.tvUserGender);
        tvUserTags = view.findViewById(R.id.tvUserTags);
        btnMatch = view.findViewById(R.id.btnMatch);

        // 전달된 데이터 처리
        Bundle args = getArguments();
        if (args != null) {
            tvUserName.setText(args.getString("userName"));
            tvUserAge.setText(String.valueOf(args.getInt("userAge")));
            tvUserGender.setText(args.getString("userGender"));
            tvUserTags.setText(String.join(", ", args.getStringArrayList("userTags")));
        }

        // 매칭 버튼 클릭 시 채팅 화면으로 이동
        btnMatch.setOnClickListener(v -> {
            if (args != null) {
                Bundle chatArgs = new Bundle();
                chatArgs.putString("chatWith", args.getString("userName")); // 사용자 이름 전달
                // 필요하다면 추가 데이터를 전달 가능
                chatArgs.putInt("userAge", args.getInt("userAge"));
                chatArgs.putString("userGender", args.getString("userGender"));
                chatArgs.putStringArrayList("userTags", args.getStringArrayList("userTags"));

                // ChatFragment로 이동
                NavController navController = Navigation.findNavController(requireActivity(), R.id.nav_host_fragment_home);
                navController.navigate(R.id.action_profileFragment_to_chatFragment, chatArgs);
            }
        });

        return view;
    }
}

