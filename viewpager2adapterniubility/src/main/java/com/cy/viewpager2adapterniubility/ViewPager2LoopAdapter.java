package com.cy.viewpager2adapterniubility;

import android.os.Looper;
import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.Timer;
import java.util.TimerTask;

import javax.security.auth.callback.Callback;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/12/28 16:30
 * @UpdateUser:
 * @UpdateDate: 2020/12/28 16:30
 * @UpdateRemark:
 * @Version:
 */
public abstract class ViewPager2LoopAdapter<T> extends ViewPager2Adapter<T> {
    private boolean loopAuto = true;
    private ViewPager2 viewPager2;
    private IIndicatorView indicatorView;
    private Timer timer;
    private TimerTask timerTask;
    private android.os.Handler handler;
    private long periodLoop = 3000;
    private boolean isLoopStarted = false;

    public ViewPager2LoopAdapter(final ViewPager2 viewPager2, final IIndicatorView indicatorView) {
        super(viewPager2);
        this.viewPager2 = viewPager2;
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
                .scroll(position - 1, positionOffset)
                .getView()
                .invalidate();
    }

    @Override
    public final void onPageScrollStateChanged(int state) {
        super.onPageScrollStateChanged(state);
        //验证当前的滑动是否结束
        if (state == ViewPager2.SCROLL_STATE_IDLE) {
            if (viewPager2.getCurrentItem() == 0) {
                viewPager2.setCurrentItem(getItemCount() - 2, false);
                return;
            }
            if (viewPager2.getCurrentItem() == getItemCount() - 1) {
                viewPager2.setCurrentItem(1, false);
                return;
            }
        }
    }

    /**
     * ViewPager2,因为在从最后一页切换到第一页的时候，onPageSelected会回调3次，所以，废弃
     * @param v
     */
//    @Override
//    public final void onPageSelected(int position) {
//        com.cy.loopviewpageradapter.LogUtils.log("onPageSelected", position);
//        final int p = position - 1;
//        ViewPager2Holder viewPager2Holder = getViewPagerHolderFromPosition(p);
//        if (viewPager2Holder == null) {
//            sparseArrayCallbackViewPager2Holder.put(p, new CallbackViewPager2Holder() {
//                @Override
//                public void onBindViewHolder(int position, ViewPager2Holder viewPager2Holder) {
//                    if (p >= 0 && p < list_bean.size())
//                        onPageSelected(viewPager2Holder, p, list_bean.get(p));
//                    sparseArrayCallbackViewPager2Holder.remove(p);
//                }
//            });
//            return;
//        }
//        com.cy.loopviewpageradapter.LogUtils.log("onPageSelectedviewPager2Holder==null", viewPager2Holder == null);
//        if (p >= 0 && p < list_bean.size())
//            onPageSelected(viewPager2Holder, p, list_bean.get(p));
//    }

    @Override
    public final void onViewDetachedFromWindow(View v) {
        super.onViewDetachedFromWindow(v);
        stopLoop();
    }

    @Override
    public final void onPageSelected(ViewPager2Holder viewPager2Holder, int position, @NonNull T bean) {
    }

    private int getPosition(int position) {
        return position == 0 ? list_bean.size() - 1 : position == getItemCount() - 1 ? 0 : position - 1;
    }

    @Override
    public final void onBindViewHolder(@NonNull ViewPager2Holder holder, int position) {
        super.onBindViewHolder(holder, getPosition(position));
    }

    @Override
    public final void onViewRecycled(@NonNull ViewPager2Holder holder) {
        int position = holder.getAdapterPosition();
        int p=getPosition(position);
        sparseArrayViewPager2Holder.remove(p);
        if (p < 0 || p >= list_bean.size()) return;
        onViewRecycled(p, list_bean.get(p));
    }

    @Override
    public int getItemViewType(int position) {
        int p = getPosition(position);
        return getItemLayoutID(p, list_bean.get(p));
    }

    @Override
    protected void handleClick(final ViewPager2Holder holder) {
        //添加Item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition() - 1;
                onItemClick(holder, position, list_bean.get(position));
            }
        });
    }

    @Override
    public final int getItemCount() {
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
        if (!loopAuto) return;
        stopLoop();
        timer = new Timer();
        timerTask = new TimerTask() {
            @Override
            public void run() {
                handler.post(new Runnable() {
                    @Override
                    public void run() {
                        viewPager2.setCurrentItem(viewPager2.getCurrentItem() + 1);
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
        viewPager2.setCurrentItem(1, false);
    }

}
