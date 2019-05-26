package com.example.shoptm.fragment;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.shoptm.R;
import com.example.shoptm.adapter.CategoryAdapter;
import com.example.shoptm.model.Category;

import java.util.ArrayList;
import java.util.List;

/**
 * A simple {@link Fragment} subclass.
 */
public class HomeFragment extends Fragment {
    private RecyclerView categoryRecyclerView;
    private List<Category>categoryList= new ArrayList<>();
    private CategoryAdapter categoryAdapter;



    public HomeFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view= inflater.inflate(R.layout.fragment_home, container, false);
        categoryRecyclerView= view.findViewById(R.id.category_recyclerview);

        LinearLayoutManager layoutManager= new LinearLayoutManager(getActivity());
        layoutManager.setOrientation(LinearLayoutManager.HORIZONTAL);
        categoryRecyclerView.setLayoutManager(layoutManager);

        categoryList.add(new Category("Home","Home"));
        categoryList.add(new Category("Mobile","Mobile"));
        categoryList.add(new Category("Laptop","Laptop"));
        categoryList.add(new Category("Phụ kiện","Phụ kiện"));
        categoryList.add(new Category("Đồng hồ","Đồng hồ"));
        categoryList.add(new Category("Sách","Sách"));
        categoryList.add(new Category("Quần áo","Quần áo"));
        categoryList.add(new Category("Phụ kiện","Phụ kiện"));

        categoryAdapter= new CategoryAdapter(categoryList);
        categoryRecyclerView.setAdapter(categoryAdapter);
        categoryAdapter.notifyDataSetChanged();

        return view;
    }

}
