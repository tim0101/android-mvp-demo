package com.hailang.app.myasdemo.utils;

import android.databinding.BindingAdapter;
import android.widget.ImageView;

/**
 * Created by niantian_huang on 2016/8/19.
 */
public class ImageLoaderUtil {
    @BindingAdapter({"imageUrl"})
    public static void loadNetImage(ImageView imageView, String url) {
        ImageLoader.loadImage(imageView, url);
    }
}
