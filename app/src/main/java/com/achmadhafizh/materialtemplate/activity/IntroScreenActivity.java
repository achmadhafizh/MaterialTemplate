package com.achmadhafizh.materialtemplate.activity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.achmadhafizh.materialtemplate.R;
import com.achmadhafizh.materialtemplate.utilities.CustomProgressDialog;
import com.daimajia.slider.library.Animations.DescriptionAnimation;
import com.daimajia.slider.library.Indicators.PagerIndicator;
import com.daimajia.slider.library.SliderLayout;
import com.daimajia.slider.library.SliderTypes.BaseSliderView;
import com.daimajia.slider.library.SliderTypes.DefaultSliderView;
import com.daimajia.slider.library.Tricks.ViewPagerEx;

import java.util.HashMap;

import butterknife.BindView;
import butterknife.ButterKnife;

public class IntroScreenActivity extends AppCompatActivity implements BaseSliderView.OnSliderClickListener, ViewPagerEx.OnPageChangeListener {
    private String TAG = IntroScreenActivity.class.getSimpleName();
    private CustomProgressDialog customProgressDialog;
    private HashMap<String, String> url_image;
    private HashMap<String, Integer> file_image;
    private DefaultSliderView defaultSliderView;
    private Intent intent;

    @BindView(R.id.slider)
    SliderLayout sliderShow;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_intro_screen);

        ButterKnife.bind(this);

        customProgressDialog = new CustomProgressDialog(IntroScreenActivity.this, "Loading...",false);

        localSlider();
    }

    @Override
    protected void onStop() {
        // make sure to call stopAutoCycle() on the slider before activity or fragment is destroyed
        sliderShow.stopAutoCycle();
        super.onStop();
    }

    @Override
    public void onSliderClick(BaseSliderView slider) {
        Log.d("Slider Intro", "Page Clicked: " + slider.getBundle().get("extra"));

        intentTaskTo(MainActivity.class);
    }

    @Override
    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
        Log.d("Slider Intro", "Page Changed: " + position);
    }

    @Override
    public void onPageScrollStateChanged(int state) {

    }

    private void localSlider() {
        file_image = new HashMap<String, Integer>();
        file_image.put("Intro 1", R.drawable.intro1);
        file_image.put("Intro 2", R.drawable.intro2);
        file_image.put("Intro 3", R.drawable.intro3);
        file_image.put("Intro 4", R.drawable.intro4);

        setupSlider(null, file_image);
    }

    private void setupSlider(HashMap<String, String> url_image, HashMap<String, Integer> file_image) {
        if(url_image != null) {
            for(String name : url_image.keySet()){
                defaultSliderView = new DefaultSliderView(this);
                // initialize a SliderLayout
                defaultSliderView
                        .description(name)
                        .image(url_image.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                defaultSliderView.bundle(new Bundle());
                defaultSliderView.getBundle().putString("extra", name);

                sliderShow.addSlider(defaultSliderView);
            }
        }

        if(file_image != null) {
            for(String name : file_image.keySet()){
                defaultSliderView = new DefaultSliderView(this);
                // initialize a SliderLayout
                defaultSliderView
                        .description(name)
                        .image(file_image.get(name))
                        .setScaleType(BaseSliderView.ScaleType.Fit)
                        .setOnSliderClickListener(this);

                //add your extra information
                defaultSliderView.bundle(new Bundle());
                defaultSliderView.getBundle().putString("extra", name);

                sliderShow.addSlider(defaultSliderView);
            }
        }

        sliderShow.setPresetTransformer(SliderLayout.Transformer.Accordion);
        //sliderShow.setPresetIndicator(SliderLayout.PresetIndicators.Center_Bottom);
        sliderShow.setCustomIndicator((PagerIndicator) findViewById(R.id.custom_indicator));
        sliderShow.setCustomAnimation(new DescriptionAnimation());
        sliderShow.setDuration(5000);
        sliderShow.addOnPageChangeListener(this);
    }

    private void intentTaskTo(Class activity) {
        intent = new Intent(this, activity);
        startActivity(intent);
        finish();
    }
}
