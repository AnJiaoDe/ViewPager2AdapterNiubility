package com.cy.viewpager2adapterniubility;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/7 23:50
 * @UpdateUser:
 * @UpdateDate: 2020/10/7 23:50
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class ViewPagerAdapter<T> extends AbsViewPagerAdapter<T> {
    public ViewPagerAdapter(final ViewPager viewPager) {
        super(viewPager);
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewPagerAdapter.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(position);
                if (viewPagerHolder != null && position >= 0 && position < list_bean.size())
                    ViewPagerAdapter.this.onPageSelected(viewPagerHolder, position, list_bean.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ViewPagerAdapter.this.onPageScrollStateChanged(state);
            }
        });
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onViewDetachedFromWindow(View v) {

    }


    public void onPageSelected(ViewPagerHolder viewPagerHolder, int position, @NonNull T bean) {
//        LogUtils.log("onPageSelected000", position);
    }

    @Override
    public int getCount() {
        return list_bean.size();
    }


    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, final int position) {
        View view = LayoutInflater.from(container.getContext()).inflate(getItemLayoutID(position, list_bean.get(position)), container, false);
        container.addView(view, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        final ViewPagerHolder viewPagerHolder = new ViewPagerHolder(view);
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onItemClick(viewPagerHolder, position, list_bean.get(position));
            }
        });
        sparseArrayViewPagerHolder.put(position, viewPagerHolder);
        bindDataToView(viewPagerHolder, position, list_bean.get(position));
//        LogUtils.log("onPageSelectedhavesparseArrayViewPagerHolder.put", position);
        if (position_selected_last == -1) {
            position_selected_last = position;
            onPageSelected(viewPagerHolder, position, list_bean.get(position));
        }
        return view;
    }

    /**
     * 有且只有3个item同时存在
     *
     * @param container
     * @param position
     * @param object
     */
    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
        sparseArrayViewPagerHolder.remove(position);
        if (position < 0 || position >= list_bean.size()) return;
        onViewRecycled(position, list_bean.get(position));
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        // 是否有缓存
        // view 显示的View
        // object : instantiateItem 返回的标记
        return view == object;
    }
}
