package com.cy.viewpager2adapterniubility;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 */

public abstract class ViewPager2Adapter<T> extends RecyclerView.Adapter<ViewPager2Holder> implements IPageAdapter<T, ViewPager2Holder> {

    protected List<T> list_bean;//数据源

    public ViewPager2Adapter() {
        list_bean = new ArrayList<>();//数据源
    }

    @NonNull
    @Override
    public ViewPager2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPager2Holder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPager2Holder holder, int position) {
        handleClick(holder);
        bindDataToView(holder, position, list_bean.get(position));
    }

    @Override
    public int getItemViewType(int position) {
        return getItemLayoutID(position, list_bean.get(position));
    }


    @Override
    public int getItemCount() {
        return list_bean.size();
    }

    public List<T> getList_bean() {
        return list_bean;
    }

    protected void handleClick(final ViewPager2Holder holder) {
        /**
         *
         */
        //添加Item的点击事件
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int position = holder.getAdapterPosition();
                onItemClick(holder, position, list_bean.get(position));
            }
        });
    }

    /**
     * ----------------------------------------------------------------------------------
     */

    public void startDefaultAttachedAnim(ViewPager2Holder holder) {

        final ObjectAnimator objectAnimator_scaleX = ObjectAnimator.ofFloat(holder.itemView, "scaleX", 0.5f, 1);

        final ObjectAnimator objectAnimator_scaleY = ObjectAnimator.ofFloat(holder.itemView, "scaleY", 0.5f, 1);

        final ObjectAnimator objectAnimator_alpha = ObjectAnimator.ofFloat(holder.itemView, "alpha", 0.5f, 1);

//        final ObjectAnimator objectAnimator_transX = ObjectAnimator.ofFloat(holder.itemView, "translationX", -1000,0);

        AnimatorSet animatorSet = new AnimatorSet();
        animatorSet.setDuration(1000);
        animatorSet.playTogether(objectAnimator_scaleX, objectAnimator_scaleY, objectAnimator_alpha);
        animatorSet.setInterpolator(new DecelerateInterpolator());
        animatorSet.start();
    }


    /**
     * ------------------------------------------------------------------------------
     */
    @Override
    public <W extends IPageAdapter> W getAdapter() {
        return (W) this;
    }

    /**
     * @param list_bean
     */
    public <W extends IPageAdapter> W setList_bean(List<T> list_bean) {
        this.list_bean = list_bean;
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 删除相应position的数据Item
     */
    public <W extends IPageAdapter> W removeNoNotify(int position) {
        list_bean.remove(position);
        return (W) this;
    }

    /**
     * 删除相应position的数据Item ,并且notify,
     */
    public <W extends IPageAdapter> W remove(int position) {
        removeNoNotify(position);
        /**
         onBindViewHolder回调的position永远是最后一个可见的item的position,
         比如一次最多只能看到5个item,只要执行了notifyItemRemoved(position)，
         onBindViewHolder回调的position永远是4
         */
        notifyItemRemoved(position);
        return (W) this;
    }

    /**
     * 添加一条数据item
     */
    public <W extends IPageAdapter> W addNoNotify(int position, T bean) {
        list_bean.add(position, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IPageAdapter> W add(int position, T bean) {
        addNoNotify(position, bean);
        notifyItemInserted(position);
        return (W) this;
    }


    /**
     * 添加一条数据item
     */
    public <W extends IPageAdapter> W addNoNotify(T bean) {
        list_bean.add(bean);
        return (W) this;
    }

    /**
     * 添加一条数据item,并且notify
     */
    public <W extends IPageAdapter> W add(T bean) {
        addNoNotify(bean);
        notifyItemInserted(list_bean.size() - 1);
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0
     */

    public <W extends IPageAdapter> W addToTopNoNotify(T bean) {
        list_bean.add(0, bean);
        return (W) this;
    }

    /**
     * 添加一条数据item到position 0,并且notify
     */
    public <W extends IPageAdapter> W addToTop(T bean) {
        addToTopNoNotify(bean);
        notifyItemInserted(0);
        return (W) this;
    }

    /**
     * 添加List
     */
    public <W extends IPageAdapter> W addNoNotify(List<T> beans) {
        list_bean.addAll(beans);
        return (W) this;
    }

    /**
     * 添加List,并且notify
     */
    public <W extends IPageAdapter> W add(List<T> beans) {
        addNoNotify(beans);
        notifyItemRangeInserted(list_bean.size() - beans.size(), beans.size());
        return (W) this;
    }

    /**
     * 先清空后添加List
     */

    public <W extends IPageAdapter> W clearAddNoNotify(List<T> beans) {
        list_bean.clear();
        list_bean.addAll(beans);
        return (W) this;
    }


    /**
     * 先清空后添加
     */

    public <W extends IPageAdapter> W clearAddNoNotify(T bean) {
        clearAdd(bean);
        return (W) this;
    }

    /**
     * 先清空后添加,并且notify
     */

    public <W extends IPageAdapter> W clearAdd(T bean) {
        clearNoNotify();
        addNoNotify(bean);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 先清空后添加List,并且notify
     */

    public <W extends IPageAdapter> W clearAdd(List<T> beans) {
        clearAddNoNotify(beans);
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 添加List到position 0
     */

    public <W extends IPageAdapter> W addToTopNoNotify(List<T> beans) {
        list_bean.addAll(0, beans);
        return (W) this;
    }

    /**
     * 添加List到position 0,并且notify
     */

    public <W extends IPageAdapter> W addToTop(List<T> beans) {
        addToTopNoNotify(beans);
        //没有刷新的作用
//        notifyItemRangeInserted(0, beans.size());
        notifyDataSetChanged();
        return (W) this;
    }

    /**
     * 清空list
     */
    public <W extends IPageAdapter> W clearNoNotify() {
        list_bean.clear();
        return (W) this;
    }

    /**
     * 清空list
     */
    public <W extends IPageAdapter> W clear() {
        list_bean.clear();
        notifyDataSetChanged();
        return (W) this;
    }

    public <W extends IPageAdapter> W setNoNotify(int index, T bean) {
        list_bean.set(index, bean);
        return (W) this;
    }

    public <W extends IPageAdapter> W set(int index, T bean) {
        setNoNotify(index, bean);
        notifyItemChanged(index);
        return (W) this;
    }
}
