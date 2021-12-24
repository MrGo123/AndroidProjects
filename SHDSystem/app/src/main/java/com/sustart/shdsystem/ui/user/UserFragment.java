package com.sustart.shdsystem.ui.user;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sustart.shdsystem.R;
import com.sustart.shdsystem.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {


    private FragmentUserBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

//  todo 通过接口获取的用户名
        TextView userName = view.findViewById(R.id.my_user_name);
        userName.setText("用户名");


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}