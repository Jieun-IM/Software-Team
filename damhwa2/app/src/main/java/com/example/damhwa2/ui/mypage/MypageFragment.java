package com.example.damhwa2.ui.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.damhwa2.R;
import com.example.damhwa2.databinding.FragmentMypageBinding;

public class MypageFragment extends Fragment {

    private FragmentMypageBinding binding;
    private MypageViewModel mypageViewModel;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        mypageViewModel = new ViewModelProvider(this).get(MypageViewModel.class);

        binding = FragmentMypageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText nameEditText = binding.editName;
        final EditText statusEditText = binding.editStatus;
        final ImageView profileImageView = binding.profileImage;
        final ImageView divorceBadgeImageView = binding.divorceBadge;
        Button saveButton = binding.saveButton;

        // 데이터 관찰
        mypageViewModel.getUserName().observe(getViewLifecycleOwner(), name -> nameEditText.setText(name));
        mypageViewModel.getUserStatus().observe(getViewLifecycleOwner(), status -> statusEditText.setText(status));
        mypageViewModel.isDivorced().observe(getViewLifecycleOwner(), isDivorced -> {
            if (isDivorced) {
                divorceBadgeImageView.setVisibility(View.VISIBLE); // 돌싱인 경우 인증 마크 보이기
            } else {
                divorceBadgeImageView.setVisibility(View.GONE); // 아니면 숨김
            }
        });

        // 저장 버튼 클릭 시
        saveButton.setOnClickListener(v -> {
            String newName = nameEditText.getText().toString();
            String newStatus = statusEditText.getText().toString();

            // ViewModel에 새 데이터 저장
            mypageViewModel.updateUserProfile(newName, newStatus);
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
