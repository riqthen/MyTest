package com.riq.mytest.bottomtab;

import android.support.v4.app.Fragment;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;

import com.riq.mytest.R;

import java.util.ArrayList;
import java.util.List;

public class Main9Activity extends BottomTabBaseActivity {

    @Override
    protected List<BottomTabView.TabItemView> getTabViews() {
        List<BottomTabView.TabItemView> tabItemViews = new ArrayList<>();
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题1", R.color.colorAccent,
                R.color.colorPrimary, R.mipmap.icon_warning, R.mipmap.eye_close));
        tabItemViews.add(new BottomTabView.TabItemView(this, "标题2", R.color.colorAccent,
                R.color.colorPrimary, R.mipmap.icon_warning, R.mipmap.eye_close));
//        tabItemViews.add(new BottomTabView.TabItemView(this, "标题2", R.color.colorAccent,
//                R.color.colorPrimary, R.mipmap.icon_warning, R.mipmap.eye_close));

        return tabItemViews;
    }

    @Override
    protected List<Fragment> getFragments() {
        List<Fragment> list = new ArrayList<>();
        list.add(new FragmentTab1());
        list.add(new FragmentTab2());
        return list;
    }

    @Override
    protected View getCenterView() {
        ImageView centerView = new ImageView(this);
        centerView.setImageResource(R.mipmap.ic_launcher_round);
        RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(200, 200);
        params.leftMargin = 60;
        params.rightMargin = 60;
        centerView.setLayoutParams(params);
        return centerView;
    }
}
