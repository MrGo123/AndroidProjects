package com.sustart.shdsystem.ui.home;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.sustart.shdsystem.R;
import com.sustart.shdsystem.databinding.FragmentHomeBinding;
import com.sustart.shdsystem.entity.Product;
import com.sustart.shdsystem.ui.home.detail.DetailActivity;

public class HomeFragment extends Fragment implements View.OnClickListener {
    private FragmentHomeBinding binding;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();


        //  创建可循环视图 RecyclerView
        RecyclerView recyclerView = view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new GridLayoutManager(getContext(), 2, GridLayoutManager.VERTICAL, false));
        ProductCardRecyclerViewAdapter adapter = new ProductCardRecyclerViewAdapter(
                Product.initProductList(getResources()), getContext());

        adapter.setOnItemClickLitener(new ProductCardRecyclerViewAdapter.OnItemClickLitener() {
            @SuppressLint("ResourceType")
            @Override
            public void onItemClick(Product product, View view, int position) {
//                点击之后触发详情页，这个详情页可能用增加类型的fragment来做更好。不用跳出这个MainActivity。
                Intent intent = new Intent(getActivity(), DetailActivity.class);
                intent.putExtra("detailProduct", product);
                startActivity(intent);
                System.out.println("打开详情页" + position);
            }
        });
        recyclerView.setAdapter(adapter);

        //  卡片间距
        int largePadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing);
        int smallPadding = getResources().getDimensionPixelSize(R.dimen.shr_product_grid_spacing_small);
        recyclerView.addItemDecoration(new ProductGridItemDecoration(largePadding, smallPadding));


        return view;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        System.out.println("点击了控件");
    }
}