package com.example.cityparcel;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

public class PagerAdapter extends FragmentPagerAdapter {
    public PagerAdapter(@NonNull FragmentManager fm) {
        super(fm);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return new fragment1();

            case 1:
                return new fragment2();

            case 2:
                return new fragment3();

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return 3;
    }
}

