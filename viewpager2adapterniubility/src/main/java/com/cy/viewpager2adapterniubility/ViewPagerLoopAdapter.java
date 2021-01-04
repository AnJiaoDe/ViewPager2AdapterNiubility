package com.cy.viewpager2adapterniubility;

import android.os.Looper;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.ViewPager;

import java.util.Timer;
import java.util.TimerTask;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/12/28 16:30
 * @UpdateUser:
 * @UpdateDate: 2020/12/28 16:30
 * @UpdateRemark:
 * @Version:
 */
public abstract class ViewPagerLoopAdapter<T> extends ViewPagerAdapter<T> {
    private boolean loopAuto = true;
    private ViewPager viewPager;
    private IIndicatorView indicatorView;
    private Timer timer;
    private TimerTask timerTask;
    private android.os.Handler handler;
    private long periodLoop = 3000;
    private boolean isLoopStarted = false;
    private int position_selected_last = -1;

    public ViewPagerLoopAdapter(final ViewPager viewPager, final IIndicatorView indicatorView) {
        super(viewPager);
        this.viewPager = viewPager;
        this.indicatorView = indicatorView;
        handler = new android.os.Handler(Looper.getMainLooper());
    }

    @Override
    public final void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
        super.onPageScrolled(position, positionOffset, positionOffsetPixels);
        if (isLoopAuto() && !isLoopStarted && list_bean.size() > 1) {
            startLoop();
            isLoopStarted = true;
        }
        indicatorView.setCount(list_bean.size())
                .scroll(position-1 , positionOffset)
                .getView()
                .invalidate();
    }
    @Override
    public final void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
        switch (state) {
            case ViewPager.SCROLL_STATE_IDLE:
                //验证当前的滑动是否结束
                if (state == ViewPager.SCROLL_STATE_IDLE) {
                    if (viewPager.getCurrentItem() == 0) {
                        viewPager.setCurrentItem(getCount()-2, false);
                        return;
                    }
                    if (viewPager.getCurrentItem() == getCount()-1) {
                        viewPager.setCurrentItem(1, false);
                        return;
                    }
                }
        }
    }
    @Override
    public final void onPageSelected(int position) {
        int p=position-1;
        ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(p);
        if (viewPagerHolder != null && p >=0 && p < list_bean.size())
            onPageSelected(viewPagerHolder, p, list_bean.get(p));
    }

    @Override
    public void onPageSelected(ViewPagerHolder viewPagerHolder, int position, @NonNull T bean) {
    }

    @Override
    public final void onViewDetachedFromWindow(View v) {
        super.onViewDetachedFromWindow(v);
        stopLoop();
    }

    private int getPosition(int position) {
        return position == 0 ? list_bean.size() - 1 : position == getCount() - 1 ? 0 : position - 1;
    }
    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, int position) {
        Object object= super.instantiateItem(container, getPosition(position));
        if (position == 1 && position_selected_last == -1) {
            position_selected_last = position;
            onPageSelected(position);
        }
        return object;
    }

    @Override
    public final void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        super.destroyItem(container, getPosition(position), object);
    }

    @Override
    public final int getCount() {
        if (list_bean.size() <= 1) return list_bean.size();
        return list_bean.size() + 2;
    }

    public long getPeriodLoop() {
        return periodLoop;
    }

    public void setPeriodLoop(long periodLoop) {
        this.periodLoop = periodLoop;
    }

    public boolean isLoopAuto() {
        return loopAuto;
    }

    public void setLoopAuto(boolean loopAuto) {
        this.loopAuto = loopAuto;
    }

    public void startLoop() {
        if(!loopAuto)return;
        stopLoop();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager.setCurrentItem(viewPager.getCurrentItem() + 1);
                    }
                });
            }
        };
        try {
            timer.scheduleAtFixedRate(timerTask, periodLoop, periodLoop);
        } catch (Exception e) {

        }
    }

    public void stopLoop() {
        if (timer != null) timer.cancel();
        if (timerTask != null) timerTask.cancel();
        timer = null;
        timerTask = null;
    }

    public void setStartItem() {
        viewPager.setCurrentItem(1, false);
    }

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        setStartItem();
        indicatorView.setCount(list_bean.size())
                .getView()
                .invalidate();
    }
}
