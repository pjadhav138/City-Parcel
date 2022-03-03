package com.example.cityparcel;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.View;

public class PagerActivity extends AppCompatActivity {

    NonSwipableViewPager pager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pager);
        pager = findViewById(R.id.pager);

        PagerAdapter pagerAdapter = new com.example.cityparcel.PagerAdapter(getSupportFragmentManager());
        pager.setAdapter(pagerAdapter);

    }
}