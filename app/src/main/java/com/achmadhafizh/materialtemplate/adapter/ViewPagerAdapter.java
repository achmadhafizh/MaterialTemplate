package com.achmadhafizh.materialtemplate.adapter;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentStatePagerAdapter;

import com.achmadhafizh.materialtemplate.fragment.OneFragment;
import com.achmadhafizh.materialtemplate.fragment.ThreeFragment;
import com.achmadhafizh.materialtemplate.fragment.TwoFragment;

/**
 * Created by achmad.hafizh on 11/7/2017.
 */

public class ViewPagerAdapter extends FragmentStatePagerAdapter {
    private int TAB_COUNT = 3;

    public ViewPagerAdapter(FragmentManager fragmentManager) {
        super(fragmentManager);
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                return OneFragment.newInstance();
            case 1:
                return TwoFragment.newInstance();
            case 2:
                return ThreeFragment.newInstance();
        }
        return null;
    }

    @Override
    public int getCount() {
        return TAB_COUNT;
    }

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return OneFragment.TITLE;

            case 1:
                return TwoFragment.TITLE;

            case 2:
                return ThreeFragment.TITLE;
        }
        return super.getPageTitle(position);
    }
}
