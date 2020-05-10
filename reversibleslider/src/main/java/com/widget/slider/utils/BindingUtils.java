package com.widget.slider.utils;

import android.widget.ImageView;

import androidx.databinding.BindingAdapter;

/**
 * @author muhammad.noamany on 5/3/2020
 * @project BarChart-master
 */
public class BindingUtils {
    @BindingAdapter({"imageUrl"})
    public static void loadImage(ImageView view, Object imageUrl) {
        ImageLoader.loadImage(view.getContext(), view, imageUrl);
    }

}
