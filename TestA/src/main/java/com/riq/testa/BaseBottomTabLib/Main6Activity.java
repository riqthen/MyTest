package com.riq.testa.BaseBottomTabLib;

import android.graphics.Color;
import android.support.v4.app.Fragment;

import com.riq.basebottomtablib.BaseBottomTabActivity;
import com.riq.basebottomtablib.BottomTabView;
import com.riq.mylibrary.utils.Lcat;
import com.riq.testa.R;

import java.util.ArrayList;
import java.util.List;

public class Main6Activity extends BaseBottomTabActivity {
    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new BottomTabView.TabItemView(this, "主页", Color.WHITE, Color.WHITE, R.mipmap.tab_home, R.mipmap.tab_home, Color.BLACK, Color.TRANSPARENT));
        tabItemViews.add(new BottomTabView.TabItemView(this, "育儿百科", 0, 5, 0, 2, Color.WHITE, Color.WHITE, R.mipmap.tab_baike, R.mipmap.tab_baike, Color.BLACK, Color.argb(0x151,0x159,0x74,0x66)));
        Lcat.print(Color.BLACK);
        Lcat.print(Color.argb(0x151,0x159,0x74,0x66));
        Lcat.print(R.color.transparent);
        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new Fragment());
        fragments.add(new Fragment());
        return fragments;
    }
}
