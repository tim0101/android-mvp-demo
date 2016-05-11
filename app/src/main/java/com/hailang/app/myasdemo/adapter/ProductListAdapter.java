package com.hailang.app.myasdemo.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hailang.app.myasdemo.R;
import com.hailang.app.myasdemo.bean.Product;
import com.hailang.app.myasdemo.utils.ImageLoader;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by niantian_huang on 2016/4/26.
 */
public class ProductListAdapter extends RecyclerView.Adapter {

    private List<Product> products;
    private Context context;
    public ProductListAdapter(Context context,List<Product> products){
        this.context=context;
        this.products=products;
    }
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View root= LayoutInflater.from(parent.getContext()).inflate(R.layout.product_list_item,parent,false);
        return new ProductViewHolder(root);
    }

    @Override
    public void onBindViewHolder(RecyclerView.ViewHolder holder, int position) {
        ProductViewHolder myViewHolder=(ProductViewHolder)holder;
        Product p=products.get(position);
        myViewHolder.tvName.setText(p.getTitle());
        myViewHolder.tvScore.setText(p.getExchangeValue());
        ImageLoader.loadImage(myViewHolder.igwImg,p.getProductImgThumb());
    }

    @Override
    public int getItemCount() {
        return products.size();
    }

    class ProductViewHolder extends RecyclerView.ViewHolder{
        @BindView(R.id.ll_product_1)
        View vProduct1;
        @BindView(R.id.tv_product_name)
        TextView tvName;
        @BindView(R.id.igw_product_img)
        SimpleDraweeView igwImg;
        @BindView(R.id.igw_add_car)
        ImageView igwAddToCar;
        @BindView(R.id.tv_score)
        TextView tvScore;
        public ProductViewHolder(View view){
            super(view);
            ButterKnife.bind(this, view);
        }
    }

}
