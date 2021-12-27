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

//        vh.productName.setText(product.getTitle());
//        vh.tvSource.setText(product.getSource());
//        vh.ivDelete.setTag(position);
//        vh.tvPublishTime.setText(product.getPublishTime());
//
//        Glide.with(mContext).load(product.getPicUrl())
//                .into(vh.ivImage);

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
