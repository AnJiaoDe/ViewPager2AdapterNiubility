package com.cy.viewpager2adapterniubility;

import android.os.Looper;
import android.util.Log;
import android.view.LayoutInflater;
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
public abstract class ViewPagerLoopAdapter<T> extends AbsViewPagerAdapter<T> {
    private boolean loopAuto = true;
    private IIndicatorView indicatorView;
    private Timer timer;
    private TimerTask timerTask;
    private android.os.Handler handler;
    private long periodLoop = 3000;
    private boolean isLoopStarted = false;
//    private int positonSelectedNoCallbck = -1;

    public ViewPagerLoopAdapter(final ViewPager viewPager, final IIndicatorView indicatorView) {
        super(viewPager);
        this.indicatorView = indicatorView;
        handler = new android.os.Handler(Looper.getMainLooper());
        viewPager.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewPagerLoopAdapter.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            /**3个 data
             /onPageSelected: ----------------------------------->>>>1
             /onPageSelected: ----------------------------------->>>>2
             /onPageSelected: ----------------------------------->>>>3

             /onPageSelected: ----------------------------------->>>>4
             /onPageSelected: ----------------------------------->>>>1

             /onPageSelected: ----------------------------------->>>>2
             /onPageSelected: ----------------------------------->>>>3
             /onPageSelected: ----------------------------------->>>>2
             /onPageSelected: ----------------------------------->>>>1

             /onPageSelected: ----------------------------------->>>>0
             /onPageSelected: ----------------------------------->>>>3
             */
            @Override
            public void onPageSelected(int p) {
//                if (p == 0 || p == getCount() - 1) return;
                int position = getPosition(p);

                if(position<0||position>=list_bean.size())return;
                ViewPagerHolder viewPagerHolder = sparseArrayViewPagerHolder.get(position);
                if (viewPagerHolder == null) {
//                    positonSelectedNoCallbck = position;
                    return;
                }
                ViewPagerLoopAdapter.this.onPageSelected(viewPagerHolder, position, list_bean.get(position));
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ViewPagerLoopAdapter.this.onPageScrollStateChanged(state);
            }
        });
    }

    @Override
    public  void onPageScrolled(int p, float positionOffset, int positionOffsetPixels) {
        if (isLoopAuto() && !isLoopStarted && list_bean.size() > 1) {
            startLoop();
            isLoopStarted = true;
        }
        indicatorView.setCount(list_bean.size())
                .scroll(getPosition(p), positionOffset)
                .getView()
                .invalidate();
    }

    @Override
    public void onPageScrollStateChanged(int state) {
//        switch (state) {
//            //左右各加一个item，实现轮播
//            case ViewPager.SCROLL_STATE_IDLE:
//                if (viewPager.getCurrentItem() == 0) {
//                    viewPager.setCurrentItem(getCount() - 2, false);
//                    return;
//                }
//                if (viewPager.getCurrentItem() == getCount() - 1) {
//                    viewPager.setCurrentItem(1, false);
//                    return;
//                }
//                break;
//        }
    }
//    @Override
//    public  void onPageSelected(int position) {
////        int p = position - 1;
////        ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(p);
////        if (viewPagerHolder != null && p >= 0 && p < list_bean.size())
////            onPageSelected(viewPagerHolder, p, list_bean.get(p));
////        super.onPageSelected(position - 1);
//    }

    @Override
    public void onPageSelected(ViewPagerHolder viewPagerHolder, int position, @NonNull T bean) {
    }

    @Override
    public void onViewPagerDetachedFromWindow(View v) {
        super.onViewPagerDetachedFromWindow(v);
        stopLoop();
    }

    private int getPosition(int position) {
//        return position == 0 || position == getCount() - 1 ? 0 : position - 1;
//        return position == 0 ? 0 : position == getCount() - 1 ? getCount() - 3 : position - 1;
//        return position == 0 ? list_bean.size() - 1 : position == getCount() - 1 ? 0 : position - 1;
        return position % list_bean.size();
    }

    @Override
    public final Object instantiateItem(@NonNull ViewGroup container, final int p) {
//        LogUtils.log("instantiateItem", p);
        final int position = getPosition(p);
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
//            positonSelectedNoCallbck = -1;
            onPageSelected(viewPagerHolder, position, list_bean.get(position));
        }
//        else if (positonSelectedNoCallbck == position) {
//            positonSelectedNoCallbck = -1;
//            onPageSelected(viewPagerHolder, position, list_bean.get(position));
//        }
        return view;
    }

    @Override
    public final void destroyItem(@NonNull ViewGroup container, int p, @NonNull Object object) {
//        LogUtils.log("destroyItem", p);
        final int position = getPosition(p);
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

    @Override
    public final int getCount() {
        if (list_bean.size() <= 1) return list_bean.size();
        //设置轮播最大值，等于无限循环
        return Integer.MAX_VALUE;
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
        if (!loopAuto) return;
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

    @Override
    public void notifyDataSetChanged() {
        super.notifyDataSetChanged();
        // 设置选中中间的页面
        int middle = Integer.MAX_VALUE / 2;
        int extra = middle % list_bean.size();
        int item = middle - extra;// 设置选中的页面
        viewPager.setCurrentItem(item);
        indicatorView.setCount(list_bean.size())
                .getView()
                .invalidate();
    }
}
