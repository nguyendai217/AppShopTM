package com.example.shoptm.fragment;


import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.shoptm.R;
import com.example.shoptm.adapter.CategoryAdapter;
import com.example.shoptm.adapter.HorizontalProductAdapter;
import com.example.shoptm.adapter.SliderAdapter;
import com.example.shoptm.model.Category;
import com.example.shoptm.model.HorizontalProduct;
import com.example.shoptm.model.Slider;

import java.util.ArrayList;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;
    private List<Category> categoryList = new ArrayList<>();
    private CategoryAdapter categoryAdapter;
    // banner slider
    private ViewPager bannerSliderPager;
    private List<Slider> listSlider = new ArrayList<>();
    private SliderAdapter sliderAdapter;
    private int currentPage = 2;
    private Timer timer;
    final private long DELAY_TIME = 3000;
    final private long PERIOD_TIME = 3000;

    // horizontal product
    private RecyclerView horizontalProductRecyclerview;
    private List<HorizontalProduct> horizontalProductList= new ArrayList<>();
    private HorizontalProductAdapter horizontalProductAdapter;
    private Button btnViewAll;

    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView = view.findViewById(R.id.category_recyclerview);
        bannerSliderPager = view.findViewById(R.id.banner_slider_viewpager);
        horizontalProductRecyclerview= view.findViewById(R.id.recyclerview_horizontal);
        btnViewAll= view.findViewById(R.id.btnViewAll);

        setRecyclerViewCategory(); // thiet lap recycler view Category
        setBanerSliderpager();  // thieet lap banner slider
        setHorizontalRecyclerView();
        return view;
    }


    private void setRecyclerViewCategory() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryList.add(new Category("Home", "Home"));
        categoryList.add(new Category("Mobile", "Mobile"));
        categoryList.add(new Category("Laptop", "Laptop"));
        categoryList.add(new Category("Phụ kiện", "Phụ kiện"));
        categoryList.add(new Category("Đồng hồ", "Đồng hồ"));
        categoryList.add(new Category("Sách", "Sách"));
        categoryList.add(new Category("Quần áo", "Quần áo"));
        categoryList.add(new Category("Phụ kiện", "Phụ kiện"));

        categoryAdapter = new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();
    }

    private void setBanerSliderpager() {
        listSlider.add(new Slider(R.mipmap.banner));
        listSlider.add(new Slider(R.mipmap.ic_launcher));
        listSlider.add(new Slider(R.mipmap.banner));
        listSlider.add(new Slider(R.mipmap.ic_launcher));
        listSlider.add(new Slider(R.mipmap.banner));
        listSlider.add(new Slider(R.mipmap.ic_launcher));
        listSlider.add(new Slider(R.mipmap.banner));
        listSlider.add(new Slider(R.mipmap.ic_launcher));

        sliderAdapter = new SliderAdapter(listSlider, getActivity());
        bannerSliderPager.setClipToPadding(false);
        bannerSliderPager.setAdapter(sliderAdapter);

        ViewPager.OnPageChangeListener onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int i, float v, int i1) {

            }

            @Override
            public void onPageSelected(int i) {
                currentPage = i;
            }

            @Override
            public void onPageScrollStateChanged(int i) {
                if (i == ViewPager.SCROLL_STATE_IDLE) {
                    pageLooper();
                }
            }
        };
        bannerSliderPager.addOnPageChangeListener(onPageChangeListener);
        startBannerShow();

        bannerSliderPager.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                pageLooper();
                stopBannerShow();
                if (event.getAction()== MotionEvent.ACTION_UP){
                    startBannerShow();
                }
                return false;
            }
        });

    }

    private void pageLooper() {    // vongf lap cua banner
        if (currentPage == listSlider.size() - 2) {
            currentPage = 2;
            bannerSliderPager.setCurrentItem(currentPage, false);
        }
        if (currentPage == 1) {
            currentPage = listSlider.size() - 3;
            bannerSliderPager.setCurrentItem(currentPage, false);
        }
    }

    private void startBannerShow() {
        final Handler handler = new Handler();
        final Runnable runnable = new Runnable() {
            @Override
            public void run() {
                if (currentPage >= listSlider.size()) {
                    currentPage = 1;
                }
                bannerSliderPager.setCurrentItem(currentPage++, true);
            }
        };
        timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                handler.post(runnable);
            }
        }, DELAY_TIME, PERIOD_TIME);
    }
    private void stopBannerShow(){
        timer.cancel();
    }

    private void setHorizontalRecyclerView() {
        LinearLayoutManager layoutManager1 = new LinearLayoutManager(getActivity());
        layoutManager1.setOrientation(LinearLayoutManager.HORIZONTAL);
        horizontalProductRecyclerview.setLayoutManager(layoutManager1);

        horizontalProductList.add(new HorizontalProduct(R.mipmap.logo,"Redmi 5","SND 450 process","$220"));
        horizontalProductList.add(new HorizontalProduct(R.mipmap.logo,"Redmi 5","SND 450 process","$220"));
        horizontalProductList.add(new HorizontalProduct(R.mipmap.logo,"Redmi 5","SND 450 process","$220"));
        horizontalProductList.add(new HorizontalProduct(R.mipmap.logo,"Redmi 5","SND 450 process","$220"));
        horizontalProductList.add(new HorizontalProduct(R.mipmap.logo,"Redmi 5","SND 450 process","$220"));
        horizontalProductList.add(new HorizontalProduct(R.mipmap.logo,"Redmi 5","SND 450 process","$220"));
        horizontalProductList.add(new HorizontalProduct(R.mipmap.logo,"Redmi 5","SND 450 process","$220"));

        horizontalProductAdapter= new HorizontalProductAdapter(horizontalProductList,getActivity());
        horizontalProductRecyclerview.setAdapter(horizontalProductAdapter);
        horizontalProductAdapter.notifyDataSetChanged();

        btnViewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), "View All", Toast.LENGTH_SHORT).show();
            }
        });

    }


}
