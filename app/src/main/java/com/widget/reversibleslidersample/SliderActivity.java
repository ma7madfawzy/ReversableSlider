package com.widget.reversibleslidersample;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;

import com.widget.reversibleslidersample.databinding.SliderActivityBinding;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Fwazy
 * ma7madfawzy@gmail.com
 */
public class SliderActivity extends AppCompatActivity {
    private SliderActivityBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(this, R.layout.slider_activity);
        setDummyDataSlider1();
        setDummyDataSlider2();
    }

    /**
     * Slider that doesn't move automatically
     */
    private void setDummyDataSlider2() {
        binding.slider2.setData(generateDummy());
    }

    /**
     * Slider that  moves automatically with animation
     */
    private void setDummyDataSlider1() {
        binding.slider.setData(generateDummy());
        binding.slider.withAnimation(4000);
    }

    @NotNull
    private List<String> generateDummy() {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/325/325466/man-walking-dog.jpg?w=1155&h=1541");
        imageUrls.add("https://scx2.b-cdn.net/gfx/news/hires/2019/2-nature.jpg");
        imageUrls.add("https://s3-us-west-2.amazonaws.com/uw-s3-cdn/wp-content/uploads/sites/6/2017/11/04133712/waterfall.jpg");
        imageUrls.add("https://lh3.googleusercontent.com/proxy/yBZ9E0P2iE8jfwDGGMPtFG_1pTz2mWT7BPA5-2LRk4A0P2EQS6DcaoPaZB-qXj6E7FNfX6_tB3eXO6wReuzXAxsD2qJv4aM-wmcCdl2nEzb41HXX7mlRUOsyFDg");
        imageUrls.add("https://www.scitecheuropa.eu/wp-content/uploads/2019/09/Nature-climate.jpg");
        return imageUrls;
    }

}
