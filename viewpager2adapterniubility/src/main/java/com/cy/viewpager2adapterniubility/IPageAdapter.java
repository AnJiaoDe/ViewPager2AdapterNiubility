package com.cy.viewpager2adapterniubility;

import android.util.SparseArray;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;

import java.util.List;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/8/9 12:00
 * @UpdateUser:
 * @UpdateDate: 2020/8/9 12:00
 * @UpdateRemark:
 * @Version: 1.0
 */
public interface IPageAdapter<T, V extends IViewHolder> {
//    public void onPageSelected(int position);

    public void onPageSelected(V holder, int position, @NonNull T bean);

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels);

    public void onPageScrollStateChanged(int state);

//    public void onViewRecycled(int position, @NonNull T bean);

    public void onViewPagerDetachedFromWindow(View v);

//    public V getViewPagerHolderFromPosition(int position);

    public void bindDataToView(V holder, int position, T bean);

    public int getItemLayoutID(int position, T bean);

    public void onItemClick(V holder, int position, T bean);

    public <W extends IPageAdapter<T,V>> W getAdapter();

    public List<T> getList_bean();

    public int getDataCount();

    /**
     * @param list_bean
     */
    public <W extends IPageAdapter<T,V>> W setList_bean(List<T> list_bean);

    /**
     * 删除相应position的数据Item
     */
    public <W extends IPageAdapter<T,V>> W removeNoNotify(int position);

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public <W extends IPageAdapter<T,V>> W remove(int position);

    /**
     * 添加一条数据item
     */
    public <W extends IPageAdapter<T,V>> W addNoNotify(int position, T bean);

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IPageAdapter<T,V>> W add(int position, T bean);


    /**
     * 添加一条数据item
     */
    public <W extends IPageAdapter<T,V>> W addNoNotify(T bean);

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IPageAdapter<T,V>> W add(T bean);

    /**
     * 添加一条数据item到position 0
     */

    public <W extends IPageAdapter<T,V>> W addToTopNoNotify(T bean);

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public <W extends IPageAdapter<T,V>> W addToTop(T bean);

    /**
     * 添加List
     */
    public <W extends IPageAdapter<T,V>> W addNoNotify(List<T> beans);

    /**
     * 添加List,并且notify
     */
    public <W extends IPageAdapter<T,V>> W add(List<T> beans);


    /**
     * 先清空后添加List
     */

    public <W extends IPageAdapter<T,V>> W clearAddNoNotify(List<T> beans);


    /**
     * 先清空后添加
     */

    public <W extends IPageAdapter<T,V>> W clearAddNoNotify(T bean);

    /**
     * 先清空后添加,并且notify
     */

    public <W extends IPageAdapter<T,V>> W clearAdd(T bean);

    /**
     * 先清空后添加List,并且notify
     */

    public <W extends IPageAdapter<T,V>> W clearAdd(List<T> beans);

    /**
     * 添加List到position 0
     */

    public <W extends IPageAdapter<T,V>> W addToTopNoNotify(List<T> beans);

    /**
     * 添加List到position 0,并且notify
     */

    public <W extends IPageAdapter<T,V>> W addToTop(List<T> beans);

    /**
     * 清空list
     */
    public <W extends IPageAdapter<T,V>> W clearNoNotify();

    /**
     * 清空list
     */
    public <W extends IPageAdapter<T,V>> W clear();


    public <W extends IPageAdapter<T,V>> W setNoNotify(int index, T bean);

    public <W extends IPageAdapter<T,V>> W set(int index, T bean);
}
