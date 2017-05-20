package com.riq.mytest;

import android.support.v4.app.Fragment;

import com.riq.basebottomtablib.BaseBottomTabActivity;
import com.riq.basebottomtablib.BottomTabView;
import com.riq.mytest.bottomtab.FragmentTab1;
import com.riq.mytest.bottomtab.FragmentTab2;

import java.util.ArrayList;
import java.util.List;

public class Main11Activity extends BaseBottomTabActivity {

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new BottomTabView.TabItemView(this, "hello", 10, 10, 10, 10, R.color.colorPrimary, R.color.colorAccent, R.mipmap.eye_open, R.mipmap.eye_close));
        tabItemViews.add(new BottomTabView.TabItemView(this, "hello2", 10, 10, 10, 10, R.color.colorPrimary, R.color.colorAccent, R.mipmap.eye_open, R.mipmap.eye_close));
        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentTab1());
        fragments.add(new FragmentTab2());
        return fragments;
    }
}
