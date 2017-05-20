package com.riq.mytest.bottomtab;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.riq.mytest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BottomTabBaseActivity extends AppCompatActivity {

    @BindView(R.id.vp)
    ViewPager vp;
    @BindView(R.id.btv)
    BottomTabView btv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bottom_tab_base);
        ButterKnife.bind(this);
        if (getCenterView() == null) {
            btv.setTabItemViews(getTabViews());
        } else {
            btv.setTabItemViews(getTabViews(), getCenterView());
        }
        vp.setAdapter(new MyPagerAdapter(getSupportFragmentManager(), getFragments()));
        btv.setOnTabItemSelectListener(new BottomTabView.OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                vp.setCurrentItem(position, true);
            }
        });
        vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

            }

            @Override
            public void onPageSelected(int position) {
                btv.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {

            }
        });
    }

    protected abstract List<BottomTabView.TabItemView> getTabViews();

    protected abstract List<Fragment> getFragments();

    protected View getCenterView() {
        return null;
    }

}
