package com.widget.reversible_slider.utils;

import android.content.Context;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.widget.reversibleslider.R;

/**
 * Created by fawzy on 10/01/2018.
 */

public class ImageLoader {
    public static void loadImage(Context activity, ImageView imageView, Object imageUrl) {
        if (imageUrl == null)
            return;
        if (imageUrl instanceof String)
            loadImageFromUrl(activity, (String) imageUrl, imageView);
        else if (imageUrl instanceof Integer)
            loadImageFromRes(activity, (Integer) imageUrl, imageView);
    }

    private static void loadImageFromUrl(Context activity, String imageUrl, ImageView imageView) {
        Glide.with(activity).load(imageUrl).apply(new RequestOptions().centerCrop()
                .placeholder(R.drawable.ic_image)).into(imageView);
    }

    private static void loadImageFromRes(Context activity, Integer url, ImageView imageView) {
        Glide.with(activity).load(url).apply(new RequestOptions()
                .placeholder(R.drawable.ic_image)).into(imageView);

    }

}
