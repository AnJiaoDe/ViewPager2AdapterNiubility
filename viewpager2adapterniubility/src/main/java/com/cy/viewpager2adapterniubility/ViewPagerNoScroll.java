package com.cy.viewpager2adapterniubility;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;

import androidx.viewpager.widget.ViewPager;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2021/1/9 18:42
 * @UpdateUser:
 * @UpdateDate: 2021/1/9 18:42
 * @UpdateRemark:
 * @Version:
 */
public class ViewPagerNoScroll extends ViewPager {

    private boolean canScroll = true;

    public ViewPagerNoScroll(Context context) {
        super(context);
    }

    public ViewPagerNoScroll(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public boolean isCanScroll() {
        return canScroll;
    }

    public void setCanScroll(boolean canScroll) {
        this.canScroll = canScroll;
    }

    @Override
    public boolean onInterceptTouchEvent(MotionEvent ev) {
        return canScroll && super.onInterceptTouchEvent(ev);
    }

    @Override
    public boolean onTouchEvent(MotionEvent ev) {
        return canScroll && super.onTouchEvent(ev);
    }
}