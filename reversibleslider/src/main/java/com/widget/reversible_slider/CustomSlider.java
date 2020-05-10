package com.widget.reversible_slider;

import android.content.Context;
import android.os.Handler;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.LinearLayout;

import androidx.databinding.DataBindingUtil;
import androidx.databinding.ViewDataBinding;
import androidx.viewpager.widget.ViewPager;

import com.widget.reversible_slider.transformations.ZoomOutPageTransformer;
import com.widget.reversibleslider.R;
import com.widget.reversibleslider.databinding.CustomSliderLayoutBinding;

import java.lang.reflect.Field;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by Muhammad Noamany
 * muhammadnoamany@gmail.com
 */
public class CustomSlider extends LinearLayout {
    public static final String TAG = CustomSlider.class.getSimpleName();
    private final short DEFAULT_ANIMATION_DURATION = 4000;
    private CustomSliderLayoutBinding binding;
    private SliderAdapter adapter;
    private boolean reverse, scrollingState;
    private Runnable runnable;
    private Handler handler = new Handler();
    private SliderAdapter.SliderClickHandler sliderClickHandler;

    public CustomSlider(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        initView();
    }

    public CustomSlider(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    public CustomSlider(Context context) {
        super(context);
        initView();
    }

    /**
     * inflate the xml layout of the custom slider
     **/
    private void initView() {
        binding = (CustomSliderLayoutBinding) inflateView(getContext(), R.layout.custom_slider_layout, this, true);
        initViewPager();
        setViewPagerBehaviour();
        setTouchBehaviour();
    }

    private void initViewPager() {
        adapter = new SliderAdapter(getContext(), new ArrayList<>(), sliderClickHandler);
        binding.viewPager.setAdapter(adapter);
        binding.viewPager.setPageTransformer(new ZoomOutPageTransformer());
    }

    private void setTouchBehaviour() {
        binding.viewPager.setOnTouchListener((view, motionEvent) -> {
            int event = motionEvent.getAction();
            switch (event) {
                case MotionEvent.ACTION_DOWN:
                case MotionEvent.ACTION_MOVE:
                    Log.d(TAG, "slider event: ACTION_DOWN or MOVE");
                    scrollingState = true;
                    break;
                default:
                    Log.d(TAG, "slider event: default");
                    scrollingState = false;
                    break;
            }
            return false;
        });
    }

    /**
     * called to set the data of the slider
     */
    public void setData(List<String> imageUrls) {
        adapter.setDataList(imageUrls);
        binding.dotsIndicator.setViewPager(binding.viewPager);
        withAnimation();
    }

    /**
     * set the animation of the view pager sliding behaviour and the time of that animation using @{{@link FixedSpeedScroller}}.
     */
    private void setViewPagerBehaviour() {
        binding.viewPager.setPageTransformer((page, position) -> {
            final float normalizedPosition = Math.abs(Math.abs(position) - 1);
            page.setScaleX(normalizedPosition / 2 + 0.5f);
            page.setScaleY(normalizedPosition / 2 + 0.5f);
        });
    }

    /*
    only supports ViewPager not ViewPager2
     */
    private void setSmoothScrollingDuration() {
        try {
            Field mScroller;
            mScroller = ViewPager.class.getDeclaredField("mScroller");
            mScroller.setAccessible(true);
            FixedSpeedScroller scroller = new FixedSpeedScroller(binding.viewPager.getContext(), null);
//             scroller.setFixedDuration(5000);
            mScroller.set(binding.viewPager, scroller);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private ViewDataBinding inflateView(Context context, int viewID, ViewGroup root, boolean attachToRoot) {
        ViewDataBinding binding = DataBindingUtil.inflate(LayoutInflater.from(context),
                viewID, root, attachToRoot);
        return binding;
    }

    /**
     * optional to animate the viewPager sliding behaviour
     **/
    protected void withAnimation(int... milliseconds) {
        runnable = new Runnable() {
            public void run() {
                int currentPage = binding.viewPager.getCurrentItem();
                if (adapter.getItemCount() == currentPage + 1) {//case reached the last item then start decreasing the currentPage
                    reverse = true;
                    currentPage--;
                } else if (currentPage < adapter.getItemCount() && currentPage > 0) {//case in middle
                    if (reverse) currentPage--;//case reverse decrease
                    else currentPage++;//else increase

                } else if (currentPage == 0) {//case first item then start increasing the currentPage to move forward
                    currentPage++;
                    reverse = false;
                }
                slidePager(currentPage);
                handler.postDelayed(this, milliseconds.length > 0 ? milliseconds[0] : DEFAULT_ANIMATION_DURATION);
            }
        };
        runnable.run();
    }

    /**
     * edit animation interval
     *
     * @param milliseconds
     */
    public void setAnimationTime(int milliseconds) {
        if (handler != null && runnable != null) handler.removeCallbacks(runnable);
        withAnimation(milliseconds);
    }

    private void slidePager(int currentPage) {
        if (!scrollingState) {
            binding.viewPager.setCurrentItem(currentPage, true);
        }
    }

    /**
     * being called to remove the handler callbacks that is responsile of the auto slide of the viewpager
     *
     * @param {handler} callbacks being removed to avoid memory leaks
     */
    @Override
    protected void onDetachedFromWindow() {
        super.onDetachedFromWindow();
        if (handler != null) handler.removeCallbacks(runnable);
    }

    public void addOnSliderClickListener(SliderAdapter.SliderClickHandler sliderClickHandler) {
        this.sliderClickHandler = sliderClickHandler;
        adapter.setSliderClickListener(sliderClickHandler);
    }
}
