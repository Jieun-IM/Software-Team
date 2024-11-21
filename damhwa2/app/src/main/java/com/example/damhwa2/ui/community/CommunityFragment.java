package com.example.damhwa2.ui.community;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.damhwa2.R;
import com.example.damhwa2.adapter.PostAdapter;
import com.example.damhwa2.databinding.FragmentCommunityBinding;

public class CommunityFragment extends Fragment {

    private FragmentCommunityBinding binding;
    private CommunityViewModel communityViewModel;
    private PostAdapter postAdapter;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentCommunityBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        communityViewModel = new ViewModelProvider(this).get(CommunityViewModel.class);

        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        postAdapter = new PostAdapter(communityViewModel.getPostList().getValue());
        recyclerView.setAdapter(postAdapter);

        communityViewModel.getPostList().observe(getViewLifecycleOwner(), postAdapter::updateData);

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
