package com.sustart.shdsystem.ui.goodsManager;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.sustart.shdsystem.R;
import com.sustart.shdsystem.entity.Product;

import java.util.List;

public class ProductManageAdapter extends ArrayAdapter<Product> {

    private List<Product> productData;
    private Context mContext;
    private int resourceId;

    public ProductManageAdapter(Context context, int resourceId, List<Product> data) {
        super(context, resourceId, data);
        this.mContext = context;
        this.productData = data;
        this.resourceId = resourceId;
    }

    /**
     * 展示视图
     *
     * @param position
     * @param convertView
     * @param parent
     * @return
     */
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        Product product = getItem(position);
        View view;
        final ViewHolder vh;

        if (convertView == null) {
            view = LayoutInflater.from(getContext()).inflate(resourceId, parent, false);
            vh = new ViewHolder();
            vh.productName = view.findViewById(R.id.manage_product_name);
            vh.productPrice = view.findViewById(R.id.manage_product_price);
            vh.productImage = view.findViewById(R.id.manage_product_image);
            vh.productStatus = view.findViewById(R.id.manage_product_status);
            vh.productPublishTime = view.findViewById(R.id.manage_product_publish_time);
            view.setTag(vh);
        } else {
            view = convertView;
            vh = (ViewHolder) view.getTag();
        }

        vh.productName.setText(product.getName());
        vh.productPrice.setText(String.valueOf(product.getPrice()));
        if (product.getDealTime() == null) {
            vh.productStatus.setText("未卖");
        } else {
            vh.productStatus.setText("已买");
        }
        vh.productPublishTime.setText(product.getName());
        String imgUri = "https://gimg2.baidu.com/image_search/src=http%3A%2F%2Fimgx.xiawu.com%2Fxzimg%2Fi4%2Fi4%2F2473866184%2FTB2VESTXNjxQeBjy1zbXXbqApXa_%21%212473866184.jpg&refer=http%3A%2F%2Fimgx.xiawu.com&app=2002&size=f9999,10000&q=a80&n=0&g=0n&fmt=jpeg?sec=1642908591&t=38f7523c41df70658a5c2b8b0d8ea6a7";
        Glide.with(mContext).load(imgUri).into(vh.productImage);

        return view;
    }


    class ViewHolder {
        TextView productName;
        TextView productPrice;
        TextView productStatus;
        ImageView productImage;
        TextView productPublishTime;
    }

}
