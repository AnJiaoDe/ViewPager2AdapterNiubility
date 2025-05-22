package com.cy.viewpager2adapterniubility;

import android.util.SparseArray;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

public abstract class AbsViewPagerAdapter<T> extends PagerAdapter implements IPageAdapter<T, ViewPagerHolder> {
    protected List<T> list_bean;
    protected ViewPager viewPager;
    protected int position_selected_last = -1;
    protected SparseArray<ViewPagerHolder> sparseArrayViewPagerHolder;
    public AbsViewPagerAdapter(final ViewPager viewPager) {
        list_bean = new ArrayList<>();
        this.viewPager = viewPager;
        sparseArrayViewPagerHolder=new SparseArray<>();
        viewPager.addOnAttachStateChangeListener(new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                AbsViewPagerAdapter.this.onViewPagerDetachedFromWindow(v);
//                clear();
//                sparseArrayViewPagerHolder.clear();
//                viewPager.removeOnAttachStateChangeListener(onAttachStateChangeListener);
//                viewPager.removeOnPageChangeListener(onPageChangeListener);
            }
        });
    }

    @Override
    public void onViewPagerDetachedFromWindow(View v) {

    }

    @Override
    public int getDataCount() {
        return list_bean.size();
    }
    public void onViewRecycled(int position, @NonNull T bean){

    }

    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W getAdapter() {
        return (W) this;
    }

//    public SparseArray<ViewPagerHolder> getSparseArrayIViewHolder() {
//        return sparseArrayViewPagerHolder;
//    }

    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W setList_bean(List<T> list_bean) {
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
    public <W extends IPageAdapter<T, ViewPagerHolder>> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W remove(int position) {
        removeNoNotify(position);
        viewPager.setAdapter(this);
        viewPager.setCurrentItem(position);
        return (W) this;
    }

    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加一条数据item
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W add(T bean) {
        addNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加List
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;
    }

    /**
     * 添加List,并且notify
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W add(List<T> beans) {
        addNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W clearNoNotify() {
        list_bean.clear();
        position_selected_last = -1;
        return (W) this;
    }

    /**
     * 清空list
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W clear() {
        clearNoNotify();
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 先清空后添加List
     */

    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W clearAddNoNotify(List<T> beans) {
        clearNoNotify();
        list_bean.addAll(beans);
        return (W) this;
    }

    /**
     * 先清空后添加
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W clearAddNoNotify(T bean) {
        clearNoNotify();
        list_bean.add(bean);
        return (W) this;
    }

    /**
     * 先清空后添加,并且notify
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W clearAdd(T bean) {
        clearAddNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 先清空后添加List,并且notify
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }


    /**
     * 添加List到position 0
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;
    }

    /**
     * 添加List到position 0,并且notify
     */
    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }


    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;
    }

    @Override
    public <W extends IPageAdapter<T, ViewPagerHolder>> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyDataSetChanged();
        return (W) this;
    }
}
