package com.riq.mytest.bottomtab2;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.Gravity;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.riq.mytest.R;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public abstract class BaseBottomTabActivity extends AppCompatActivity {
    @BindView(R.id.mvp)
    MyViewPager mvp;
    @BindView(R.id.btv_tab)
    BottomTabView btvTab;
    @BindView(R.id.ly_main)
    LinearLayout lyMain;

    private boolean vpScrollable;   //viewPager是否可滑动

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_bottom_tab);
        ButterKnife.bind(this);
        //是否有CenterView的情况
        if (setCenterView(iconRes, iconWidth, iconHeight, leftMargin, rightMargin) == null) {
            btvTab.setTabItemViews(getTabViews());
        } else {
            btvTab.setTabItemViews(getTabViews(), setCenterView(iconRes, iconWidth, iconHeight, leftMargin, rightMargin));
        }
        mvp.setScrollable(vpScrollable);
        mvp.setAdapter(new MyPagerAdapter(getSupportFragmentManager()));
        btvTab.setOnTabItemSelectListener(new BottomTabView.OnTabItemSelectListener() {
            @Override
            public void onTabItemSelect(int position) {
                mvp.setCurrentItem(position);
            }
        });
        mvp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }

            @Override
            public void onPageSelected(int position) {
                btvTab.updatePosition(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
        //CenterView是否溢出Tab的情况
        if (isCenterViewOut()) {
            btvTab.setGravity(Gravity.BOTTOM);
            lyMain.setClipChildren(false);
        } else {
            btvTab.setGravity(Gravity.CENTER);
            lyMain.setClipChildren(true);
        }
        setBottomTabViewPadding(left, top, right, bottom);
        btvTab.setOnSecondSelectListener(new BottomTabView.OnSecondSelectListener() {
            @Override
            public void onSecondSelect(int position) {
                onSecondClick();
            }
        });
    }

    /**
     * 设置CenterView是否溢出
     */
    public boolean isCenterViewOut() {
        return false;
    }


    /**
     * 按钮选择之后，再次点击的效果
     */
    public void onSecondClick() {

    }


    /**
     * 设置底部栏的Padding
     *
     * @param left
     * @param top
     * @param right
     * @param bottom
     */
    private int left;
    private int top;
    private int right;
    private int bottom;

    public void setBottomTabViewPadding(int left, int top, int right, int bottom) {
        this.left = left;
        this.top = top;
        this.right = right;
        this.bottom = bottom;
        btvTab.setPadding(left, top, right, bottom);
    }


    /**
     * 设置ViewPager是否可以滑动
     *
     * @param vpScrollable
     */
    public void setVpScrollable(boolean vpScrollable) {
        this.vpScrollable = vpScrollable;
    }


    /**
     * 设置底部tab按钮
     *
     * @return
     */
    protected abstract List<BottomTabView.TabItemView> getTabViews();


    protected abstract List<Fragment> getFragments();

    private int iconRes;
    private int iconWidth;
    private int iconHeight;
    private int leftMargin;
    private int rightMargin;

    /**
     * 设置CenterView按钮
     *
     * @param iconRes     按钮图片
     * @param iconWidth   图片宽 ViewGroup.LayoutParams.WRAP_CONTENT
     * @param iconHeight  图片高
     * @param leftMargin  图片左Margin
     * @param rightMargin 图片右Margin
     * @return
     */
    public View setCenterView(int iconRes, int iconWidth, int iconHeight, int leftMargin, int rightMargin) {
        this.iconRes = iconRes;
        this.iconWidth = iconWidth;
        this.iconHeight = iconHeight;
        this.leftMargin = leftMargin;
        this.rightMargin = rightMargin;
        ImageView centerView = new ImageView(this);
        centerView.setImageResource(iconRes);
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(iconWidth, iconHeight);
        params.leftMargin = leftMargin;
        params.rightMargin = rightMargin;
        centerView.setLayoutParams(params);
        centerView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onCenterViewClick();
            }
        });
        centerView.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                onCenterViewLongClick();
                return true;
            }
        });
        return centerView;
    }

    /**
     * CenterView按钮点击事件
     */
    public void onCenterViewClick() {
    }

    /**
     * CenterView按钮长按事件
     */
    public void onCenterViewLongClick() {
    }


    //适配器
    private class MyPagerAdapter extends FragmentPagerAdapter {
        MyPagerAdapter(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            return getFragments().get(position);
        }

        @Override
        public int getCount() {
            return getFragments().size();
        }
    }
}
