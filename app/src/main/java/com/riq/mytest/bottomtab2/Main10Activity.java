package com.riq.mytest.bottomtab2;

import android.support.v4.app.Fragment;
import android.view.View;

import com.riq.mylibrary.utils.ToastUtils;
import com.riq.mytest.R;
import com.riq.mytest.bottomtab.FragmentTab1;
import com.riq.mytest.bottomtab.FragmentTab2;

import java.util.ArrayList;
import java.util.List;

public class Main10Activity extends BaseBottomTabActivity {

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题1", 0, 0, 0, 0, R.color.colorPrimary, R.color.colorAccent, R.mipmap.eye_open, R.mipmap.eye_close));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题2", 0, 0, 0, 0, R.color.colorPrimary, R.color.colorAccent, R.mipmap.eye_open, R.mipmap.eye_close));
        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> fragments = new ArrayList<>();
        fragments.add(new FragmentTab1());
        fragments.add(new FragmentTab2());
        return fragments;
    }

//    @Override
//    public void isCenterViewOut(boolean centerViewOut) {
//        super.isCenterViewOut(false);
//    }

    @Override
    public View setCenterView(int iconRes, int iconWidth, int iconHeight, int leftMargin, int rightMargin) {
        return super.setCenterView(R.mipmap.ic_launcher_round, 300, 300, 0, 0);
    }

    @Override
    public void onCenterViewClick() {
        super.onCenterViewClick();
        ToastUtils.showToast(this, "单击");
    }

    @Override
    public void onCenterViewLongClick() {
        ToastUtils.showToast(this, "长按");
    }
    //    @Override
//    public void setBottomTabViewPadding(int left, int top, int right, int bottom) {
//        super.setBottomTabViewPadding(10, 10, 10, 10);
//    }


    @Override
    public boolean isCenterViewOut() {
        return true;
    }

    @Override
    public void onSecondClick() {
        ToastUtils.showToast(this,"再次点击了");
    }
}
