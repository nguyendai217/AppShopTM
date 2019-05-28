package com.example.shoptm.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.view.PagerAdapter;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.example.shoptm.R;
import com.example.shoptm.model.Slider;

import java.util.List;

public class SliderAdapter extends PagerAdapter {
    private List<Slider> sliderList;
    Context context;

    public SliderAdapter(List<Slider> sliderList, Context context) {
        this.sliderList = sliderList;
        this.context = context;
    }

    @Override
    public int getCount() {
        return sliderList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view = LayoutInflater.from(context).inflate(R.layout.slider_layout, container, false);

        ImageView banerSlider = view.findViewById(R.id.banner_slider);
        banerSlider.setImageResource(sliderList.get(position).getBanner());
        container.addView(view, 0);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
