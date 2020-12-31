package com.cy.loopviewpageradapter;

import android.content.Context;
import android.os.SystemClock;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.viewpager2.widget.ViewPager2;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/12/31 15:45
 * @UpdateUser:
 * @UpdateDate: 2020/12/31 15:45
 * @UpdateRemark:
 * @Version:
 */
public class ViewPager2NoConflict extends FrameLayout {
    private float downX, downY;
    private float moveX;
    private float moveY;
    private ViewPager2 viewPager2;

    public ViewPager2NoConflict(@NonNull Context context) {
        this(context, null);
    }

    public ViewPager2NoConflict(@NonNull Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        this.viewPager2 = new ViewPager2(context);
        addView(viewPager2, new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
    }

    public ViewPager2 getViewPager2() {
        return viewPager2;
    }

    @Override
    public boolean dispatchTouchEvent(MotionEvent ev) {
        switch (ev.getAction()) {
            case MotionEvent.ACTION_DOWN:
                downX = ev.getX();
                downY = ev.getY();
                break;
            case MotionEvent.ACTION_MOVE:
                moveX = ev.getX();
                moveY = ev.getY();
                float dx = moveX - downX;
                float dy = moveY - downY;

                downX = moveX;
                downY = moveY;

                if (Math.abs(dx) < Math.abs(dy)) return true;
        }
        return super.dispatchTouchEvent(ev);
    }

}
