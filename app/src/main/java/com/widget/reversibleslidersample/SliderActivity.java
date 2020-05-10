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
        binding.slider2.setAnimationTime(8000); // edit the animation interval time to be 8000 milliseconds
    }

    /**
     * Slider that  moves automatically with animation
     */
    private void setDummyDataSlider1() {
        binding.slider.setData(generateDummy());
    }

    @NotNull
    private List<String> generateDummy() {
        List<String> imageUrls = new ArrayList<>();
        imageUrls.add("https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/325/325466/man-walking-dog.jpg?w=1155&h=1541");
        imageUrls.add("https://scx2.b-cdn.net/gfx/news/hires/2019/2-nature.jpg");
        imageUrls.add("https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/325/325466/man-walking-dog.jpg?w=1155&h=1541");
        imageUrls.add("https://i0.wp.com/cdn-prod.medicalnewstoday.com/content/images/articles/325/325466/man-walking-dog.jpg?w=1155&h=1541");
        imageUrls.add("https://www.scitecheuropa.eu/wp-content/uploads/2019/09/Nature-climate.jpg");
        return imageUrls;
    }

}
