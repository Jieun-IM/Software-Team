package com.example.damhwa2.ui.mypage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;

import com.example.damhwa2.R;
import com.example.damhwa2.databinding.FragmentMypageBinding;

public class MypageFragment extends Fragment {

    private FragmentMypageBinding binding;
    private MypageViewModel mypageViewModel;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mypageViewModel = new ViewModelProvider(this).get(MypageViewModel.class);

        binding = FragmentMypageBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        final EditText nameEditText = binding.editName;
        final EditText statusEditText = binding.editStatus;
        final EditText profileImageEditText = binding.editProfileImage;
        final EditText heightEditText = binding.editHeight;
        final EditText weightEditText = binding.editWeight;
        final Spinner spinnerLiving = binding.spinnerLiving; // Living location spinner
        final EditText tagsEditText = binding.editTag;
        final ImageView divorceBadgeImageView = binding.divorceBadge;
        final CheckBox divorceCheckbox = binding.divorceCheckbox;
        Button saveButton = binding.saveButton;

        // Set Spinner adapter for Living options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(getActivity(),
                R.array.living_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLiving.setAdapter(adapter);

        // Set initial values to compare later
        final String[] initialName = {""};
        final String[] initialStatus = {""};
        final String[] initialProfileImage = {""};
        final String[] initialHeight = {""};
        final String[] initialWeight = {""};
        final String[] initialLiving = {""};
        final String[] initialTags = {""};
        final boolean[] initialDivorced = {false};

        // 데이터 관찰
        mypageViewModel.getUserName().observe(getViewLifecycleOwner(), name -> {
            nameEditText.setText(name);
            initialName[0] = name;
        });
        mypageViewModel.getUserStatus().observe(getViewLifecycleOwner(), status -> {
            statusEditText.setText(status);
            initialStatus[0] = status;
        });
        mypageViewModel.isDivorced().observe(getViewLifecycleOwner(), isDivorced -> {
            if (isDivorced) {
                divorceBadgeImageView.setVisibility(View.VISIBLE);
            } else {
                divorceBadgeImageView.setVisibility(View.GONE);
            }
            divorceCheckbox.setChecked(isDivorced);
            initialDivorced[0] = isDivorced;
        });
        mypageViewModel.getUserProfileImage().observe(getViewLifecycleOwner(), profileImage -> {
            profileImageEditText.setText(profileImage);
            initialProfileImage[0] = profileImage;
        });
        mypageViewModel.getUserHeight().observe(getViewLifecycleOwner(), height -> {
            heightEditText.setText(height);
            initialHeight[0] = height;
        });
        mypageViewModel.getUserWeight().observe(getViewLifecycleOwner(), weight -> {
            weightEditText.setText(weight);
            initialWeight[0] = weight;
        });
        mypageViewModel.getUserLiving().observe(getViewLifecycleOwner(), living -> {
            int position = adapter.getPosition(living);
            if (position >= 0) {
                spinnerLiving.setSelection(position);
            }
            initialLiving[0] = living;
        });
        mypageViewModel.getUserTags().observe(getViewLifecycleOwner(), tags -> {
            tagsEditText.setText(tags);
            initialTags[0] = tags;
        });

        // 저장 버튼 클릭 시
        saveButton.setOnClickListener(v -> {
            String newName = nameEditText.getText().toString().trim();
            String newStatus = statusEditText.getText().toString().trim();
            String newProfileImage = profileImageEditText.getText().toString().trim();
            String newHeight = heightEditText.getText().toString().trim();
            String newWeight = weightEditText.getText().toString().trim();
            String newLiving = spinnerLiving.getSelectedItem().toString().trim();
            String newTags = tagsEditText.getText().toString().trim();
            boolean newDivorced = divorceCheckbox.isChecked();

            // Check if any of the values have changed
            boolean isModified = !newName.equals(initialName[0]) || !newStatus.equals(initialStatus[0]) ||
                    !newProfileImage.equals(initialProfileImage[0]) || !newHeight.equals(initialHeight[0]) ||
                    !newWeight.equals(initialWeight[0]) || !newLiving.equals(initialLiving[0]) ||
                    !newTags.equals(initialTags[0]) || newDivorced != initialDivorced[0];

            if (isModified) {
                Toast.makeText(getActivity(), "Profile saved!", Toast.LENGTH_SHORT).show();

                // 돌싱 인증 체크 여부에 따라 배지 표시/숨기기
                if (newDivorced) {
                    divorceBadgeImageView.setVisibility(View.VISIBLE);
                } else {
                    divorceBadgeImageView.setVisibility(View.GONE);
                }

                // ViewModel에 새 데이터 저장
                mypageViewModel.updateUserProfile(newName, newStatus, newDivorced,
                        newProfileImage, newHeight, newWeight, newLiving, newTags);
            } else {
                Toast.makeText(getActivity(), "No changes detected.", Toast.LENGTH_SHORT).show();
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
