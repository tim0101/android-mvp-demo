package com.hailang.app.myasdemo.utils;

import android.net.Uri;
import android.text.TextUtils;
import android.widget.ImageView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hailang.app.myasdemo.R;
import com.squareup.picasso.Picasso;

/**
 * Created by niantian_huang on 2016/5/9.
 */
public class ImageLoader {
    public static void  loadImage(SimpleDraweeView draweeView, String url){
        if(!TextUtils.isEmpty(url)){
            draweeView.setImageURI(Uri.parse(url));
        }
    }
    public static void  loadImage(ImageView imageView, String url){
        loadImage(imageView, R.drawable.progressloading,url);
    }
    public static void  loadImage(ImageView imageView,int defultimage, String url){
        if(!TextUtils.isEmpty(url)){
            Picasso.with(imageView.getContext()).load(url).placeholder(defultimage).into(imageView);
        }
    }
}
