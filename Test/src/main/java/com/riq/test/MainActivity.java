package com.riq.test;

import android.graphics.Color;
import android.support.v4.app.Fragment;
import android.view.View;

import com.riq.basebottomtablib.BaseBottomTabActivity;
import com.riq.basebottomtablib.BottomTabView;
import com.riq.mylibrary.utils.Lcat;
import com.riq.mylibrary.utils.ToastUtils;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends BaseBottomTabActivity {

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new BottomTabView.TabItemView(this, "hello", 10, 10, 10, 10
                , Color.rgb(255, 255, 255), Color.RED, 0, 0));
        tabItemViews.add(new BottomTabView.TabItemView(this, "h2", 10, 10, 10, 10
                , 0, Color.argb(55,44,51,47), R.mipmap.eye_close, R.mipmap.eye_open));
        Lcat.print(String.valueOf(Color.BLUE));
        Lcat.print(String.valueOf(R.color.colorPrimary));
        Lcat.print(String.valueOf("#124"));
        Lcat.print(String.valueOf(Color.rgb(255, 255, 255)));
        Lcat.print(String.valueOf(Color.argb(55,44,51,47)));
        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentTab1());
        fragments.add(new FragmentTab2());
        return fragments;
    }

    @Override
    public View setCenterView(int iconRes, int iconWidth, int iconHeight, int leftMargin, int rightMargin, boolean isOutTab) {
        return super.setCenterView(R.mipmap.ic_launcher_round, 300, 300, 10, 10, true);
    }

    @Override
    public void onCenterViewClick() {
        ToastUtils.showToast(this, "点击");
    }

    @Override
    public void onCenterViewLongClick() {
        ToastUtils.showToast(this, "长按");
    }

    @Override
    public void onSecondClick() {
        ToastUtils.showToast(this, "再次点击");
    }


    @Override
    public boolean isVpScrollable() {
        return true;
    }
}
