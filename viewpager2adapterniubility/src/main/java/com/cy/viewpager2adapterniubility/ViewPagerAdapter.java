package com.cy.viewpager2adapterniubility;

import android.util.Log;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/10/7 23:50
 * @UpdateUser:
 * @UpdateDate: 2020/10/7 23:50
 * @UpdateRemark:
 * @Version: 1.0
 */
public abstract class ViewPagerAdapter<T> extends PagerAdapter implements IPageAdapter<T, ViewPagerHolder> {
    protected List<T> list_bean;
    private ViewPager viewPager;
    protected SparseArray<ViewPagerHolder> sparseArrayViewPagerHolder;
    private View.OnAttachStateChangeListener onAttachStateChangeListener;
    private ViewPager.OnPageChangeListener onPageChangeListener;
    private int position_selected_last = -1;

    public ViewPagerAdapter(final ViewPager viewPager) {
        list_bean = new ArrayList<>();
        this.viewPager = viewPager;
        sparseArrayViewPagerHolder = new SparseArray<>();
        onPageChangeListener = new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewPagerAdapter.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(position);
                if (viewPagerHolder == null) return;
                ViewPagerAdapter.this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ViewPagerAdapter.this.onPageScrollStateChanged(state);
            }
        };
        onAttachStateChangeListener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                ViewPagerAdapter.this.onViewDetachedFromWindow(v);
                clear();
                sparseArrayViewPagerHolder.clear();
                viewPager.removeOnAttachStateChangeListener(onAttachStateChangeListener);
                viewPager.removeOnPageChangeListener(onPageChangeListener);
            }
        };
        viewPager.addOnAttachStateChangeListener(onAttachStateChangeListener);
        viewPager.addOnPageChangeListener(onPageChangeListener);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }


    public void onPageScrollStateChanged(int state) {

    }

    public void onViewDetachedFromWindow(View v) {
    }

    @Override
    public void  onPageSelected(int position) {
//        LogUtils.log("onPageSelected", position);
        ViewPagerHolder viewPagerHolder = getViewPagerHolderFromPosition(position);
        if (viewPagerHolder!=null&&position >= 0 && position < list_bean.size())
            ViewPagerAdapter.this.onPageSelected(viewPagerHolder, position, list_bean.get(position));
    }

    @Override
    public void onViewRecycled(int position, @NonNull T bean) {

    }

    @Override
    public ViewPagerHolder getViewPagerHolderFromPosition(int position) {
        return sparseArrayViewPagerHolder.get(position);
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
        bindDataToView(viewPagerHolder, position, list_bean.get(position));
//        LogUtils.log("onPageSelectedhavesparseArrayViewPagerHolder.put", position);
        sparseArrayViewPagerHolder.put(position, viewPagerHolder);
        if (position_selected_last == -1) {
            position_selected_last = position;
            onPageSelected(position);
        }
        return viewPagerHolder;
    }


    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView(((ViewPagerHolder) object).itemView);
        sparseArrayViewPagerHolder.remove(position);
        if (position < 0 || position >= list_bean.size()) return;
        onViewRecycled(position, list_bean.get(position));
    }


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return ((ViewPagerHolder) object).itemView == view;
    }

    @Override
    public <W extends IPageAdapter> W getAdapter() {
        return (W) this;
    }

    @Override
    public <W extends IPageAdapter> W setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return (W) this;
    }

    @Override
    public List<T> getList_bean() {
        return list_bean;
    }

    /**
     * 删除相应position的数据Item
     */

    @Override
    public <W extends IPageAdapter> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    @Override
    public <W extends IPageAdapter> W remove(int position) {
        removeNoNotify(position);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IPageAdapter> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IPageAdapter> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyDataSetChanged();
        return (W) this;
    }


    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IPageAdapter> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IPageAdapter> W add(T bean) {
        addNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0
     */
    @Override
    public <W extends IPageAdapter> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    @Override
    public <W extends IPageAdapter> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加List
     */
    @Override
    public <W extends IPageAdapter> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;
    }

    /**
     * 添加List,并且notify
     */
    @Override
    public <W extends IPageAdapter> W add(List<T> beans) {
        addNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 先清空后添加List
     */

    @Override
    public <W extends IPageAdapter> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return (W) this;
    }


    /**
     * 先清空后添加
     */
    @Override
    public <W extends IPageAdapter> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        return (W) this;
    }

    /**
     * 先清空后添加,并且notify
     */
    @Override
    public <W extends IPageAdapter> W clearAdd(T bean) {
        clearNoNotify();
        add(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 先清空后添加List,并且notify
     */
    @Override
    public <W extends IPageAdapter> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加List到position 0
     */
    @Override
    public <W extends IPageAdapter> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;
    }

    /**
     * 添加List到position 0,并且notify
     */
    @Override
    public <W extends IPageAdapter> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends IPageAdapter> W clearNoNotify() {
        list_bean.clear();
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends IPageAdapter> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        sparseArrayViewPagerHolder.clear();
        return (W) this;
    }

    @Override
    public <W extends IPageAdapter> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;
    }

    @Override
    public <W extends IPageAdapter> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyDataSetChanged();
        return (W) this;
    }
}
