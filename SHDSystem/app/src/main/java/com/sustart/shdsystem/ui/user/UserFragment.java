package com.sustart.shdsystem.ui.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;

import com.sustart.shdsystem.R;
import com.sustart.shdsystem.SHDSystemApplication;
import com.sustart.shdsystem.databinding.FragmentUserBinding;

public class UserFragment extends Fragment {


    private FragmentUserBinding binding;
    private SHDSystemApplication application;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentUserBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        application = (SHDSystemApplication) getContext().getApplicationContext();

        TextView userName = view.findViewById(R.id.my_user_name);
        TextView userAddress = view.findViewById(R.id.my_user_address);
        TextView purchaseRecord = view.findViewById(R.id.purchase_record);

        userName.setText(application.loginUser.getName());
        userAddress.setText(application.loginUser.getAddress());

        userName.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToUserEdit = new Intent(getActivity(), UserEditActivity.class);
                startActivity(intentToUserEdit);
            }
        });

        purchaseRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intentToUserEdit = new Intent(getActivity(), PurchaseRecordActivity.class);
                startActivity(intentToUserEdit);
            }
        });

        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }
}