package com.cy.rollpagerview;

import android.content.Context;
import android.support.v4.view.PagerAdapter;
import android.util.AttributeSet;

import com.jude.rollviewpager.RollPagerView;


/**
 * Created by lenovo on 2017/8/26.
 */

public class CYRollPagerView<T> extends RollPagerView {
    private Context context;

    public CYRollPagerView(Context context) {
        this(context, null);
    }

    public CYRollPagerView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }

    @Override
    public  void setAdapter(PagerAdapter adapter) {
        super.setAdapter(adapter);
    }

    public void setAdapter(CYLoopPagerAdapter cyLoopPagerAdapter,CYColorPointHintView cyColorPointHintView) {

        if (cyLoopPagerAdapter.getRealCount() <= 1) {
            setHintView(null);
        } else {

            setHintView(cyColorPointHintView);

        }
        setAdapter(cyLoopPagerAdapter);
    }
    public void setAdapter(CYLoopPagerAdapter cyLoopPagerAdapter,int delay,CYColorPointHintView cyColorPointHintView) {

        if (cyLoopPagerAdapter.getRealCount() <= 1) {
            setHintView(null);
        } else {

            setHintView(cyColorPointHintView);

        }
        setPlayDelay(delay);
        setAdapter(cyLoopPagerAdapter);
    }
}
