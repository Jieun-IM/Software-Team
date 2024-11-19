package com.example.damhwa2.ui.home;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.navigation.NavController;
import androidx.navigation.Navigation;

import com.example.damhwa2.adapter.ChatAdapter;
import com.example.damhwa2.databinding.FragmentHomeBinding;
import com.example.damhwa2.model.ChatItem; // ChatItem을 사용
import com.example.damhwa2.R;

import java.util.ArrayList;

public class HomeFragment extends Fragment {

    private FragmentHomeBinding binding;
    private ChatAdapter chatAdapter;
    private HomeViewModel homeViewModel;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View root = binding.getRoot();

        // ViewModel 초기화
        homeViewModel = new ViewModelProvider(this).get(HomeViewModel.class);

        // RecyclerView 설정
        RecyclerView recyclerView = binding.recyclerView;
        recyclerView.setLayoutManager(new LinearLayoutManager(getContext()));

        // 어댑터 초기화
        chatAdapter = new ChatAdapter(new ArrayList<>());
        recyclerView.setAdapter(chatAdapter);

        // ViewModel의 데이터 관찰
        homeViewModel.getChatList().observe(getViewLifecycleOwner(), chatList -> {
            // 데이터 변경 시 어댑터 업데이트
            chatAdapter.updateData(chatList);
        });

        // 대화 목록 클릭 시 해당 상대와의 채팅 페이지로 이동
        chatAdapter.setOnItemClickListener(position -> {
            ChatItem chatItem = homeViewModel.getChatList().getValue().get(position); // ChatItem 사용
            String chatWith = chatItem.getName();  // 대화 상대 이름 (ChatItem의 필드)

            // Bundle을 사용하여 채팅 상대 이름 전달
            Bundle args = new Bundle();
            args.putString("chatWith", chatWith);

            // NavController를 사용하여 ChatFragment로 이동
            NavController navController = Navigation.findNavController(getActivity(), R.id.nav_host_fragment_home);
            navController.navigate(R.id.navigation_chat, args);  // navigation_chat은 모바일 내비게이션 xml에서 설정한 ID
        });

        return root;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}
