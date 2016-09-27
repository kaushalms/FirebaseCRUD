package com.example.kaushalmandayam.kaushalmandayamcodingtest.Adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.example.kaushalmandayam.kaushalmandayamcodingtest.FormFragment;
import com.example.kaushalmandayam.kaushalmandayamcodingtest.ListFragment;

import java.util.ArrayList;

/**
 * Created by Kaushal.Mandayam on 9/24/2016.
 */
public class PagerAdapter extends FragmentStatePagerAdapter {
    int mNumOfTabs;
    ArrayList<Fragment> pages = new ArrayList<>();

    public PagerAdapter(FragmentManager fm, int NumOfTabs) {
        super(fm);
        this.mNumOfTabs = NumOfTabs;
    }

    @Override
    public Fragment getItem(int position) {

        switch (position) {
            case 0:
                FormFragment tab1 = new FormFragment();
                return tab1;
            case 1:
                ListFragment tab2 = new ListFragment();
                return tab2;

            default:
                return null;
        }
    }

    @Override
    public int getCount() {
        return mNumOfTabs;
    }


}
