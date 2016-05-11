package com.hailang.app.myasdemo.utils;

import android.net.Uri;
import android.text.TextUtils;

import com.facebook.drawee.view.SimpleDraweeView;

/**
 * Created by niantian_huang on 2016/5/9.
 */
public class ImageLoader {
    public static void  loadImage(SimpleDraweeView draweeView, String url){
        if(!TextUtils.isEmpty(url)){
            draweeView.setImageURI(Uri.parse(url));
        }
    }
}
