package com.cy.viewpager2adapterniubility;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.util.SparseArray;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.DecelerateInterpolator;
import android.widget.FrameLayout;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;
import java.util.List;

/**
 * @param <T>
 */

public abstract class ViewPager2Adapter<T> extends RecyclerView.Adapter<ViewPager2Holder> implements IPageAdapter<T, ViewPager2Holder> {

    protected List<T> list_bean;//数据源
    private ViewPager2 viewPager2;
    private View.OnAttachStateChangeListener onAttachStateChangeListener;
    private ViewPager2.OnPageChangeCallback onPageChangeCallback;
    protected SparseArray<ViewPager2Holder> sparseArrayViewPager2Holder;
    private int position_selected_last = -1;

    public ViewPager2Adapter(final ViewPager2 viewPager2) {
        list_bean = new ArrayList<>();//数据源
        this.viewPager2 = viewPager2;
        sparseArrayViewPager2Holder = new SparseArray<>();
        onPageChangeCallback = new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                ViewPager2Adapter.this.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                ViewPager2Holder viewPager2Holder = getViewPagerHolderFromPosition(position);
                if (viewPager2Holder == null) return;
                ViewPager2Adapter.this.onPageSelected(position);
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                ViewPager2Adapter.this.onPageScrollStateChanged(state);
            }
        };
        onAttachStateChangeListener = new View.OnAttachStateChangeListener() {
            @Override
            public void onViewAttachedToWindow(View v) {

            }

            @Override
            public void onViewDetachedFromWindow(View v) {
                ViewPager2Adapter.this.onViewDetachedFromWindow(v);
                clear();
                sparseArrayViewPager2Holder.clear();
                viewPager2.removeOnAttachStateChangeListener(onAttachStateChangeListener);
                viewPager2.unregisterOnPageChangeCallback(onPageChangeCallback);
            }
        };
        viewPager2.addOnAttachStateChangeListener(onAttachStateChangeListener);
        viewPager2.registerOnPageChangeCallback(onPageChangeCallback);
    }

    public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {

    }

    @Override
    public void onPageSelected(int position) {
//        LogUtils.log("onPageSelected", position);
        ViewPager2Holder viewPager2Holder = getViewPagerHolderFromPosition(position);
        if (viewPager2Holder != null && position >= 0 && position < list_bean.size())
            ViewPager2Adapter.this.onPageSelected(viewPager2Holder, position, list_bean.get(position));
    }

    @Override
    public void onPageSelected(ViewPager2Holder holder, int position, @NonNull T bean) {
//        LogUtils.log("onPageSelected000", position);
    }

    public void onPageScrollStateChanged(int state) {

    }

    @Override
    public void onViewRecycled(int position, @NonNull T bean) {

    }

    public void onViewDetachedFromWindow(View v) {
    }

    @Override
    public ViewPager2Holder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ViewPager2Holder(LayoutInflater.from(parent.getContext()).inflate(viewType, parent, false));
    }

    @Override
    public void onViewRecycled(@NonNull ViewPager2Holder holder) {
        super.onViewRecycled(holder);
        int position = holder.getAdapterPosition();
        sparseArrayViewPager2Holder.remove(position);
        if (position < 0 || position >= list_bean.size()) return;
        onViewRecycled(position, list_bean.get(position));
    }

    @Override
    public ViewPager2Holder getViewPagerHolderFromPosition(int position) {
        return sparseArrayViewPager2Holder.get(position);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewPager2Holder holder, int position) {
        sparseArrayViewPager2Holder.put(position, holder);
//        LogUtils.log("onPageSelectedhavesparseArrayViewPagerHolder.put", position);
        handleClick(holder);
        bindDataToView(holder, position, list_bean.get(position));
        if (position_selected_last==-1) {
            position_selected_last = position;
            onPageSelected(position);
        }
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
        sparseArrayViewPager2Holder.clear();
        position_selected_last=-1;
        return (W) this;
    }

    /**
     * 清空list
     */
    public <W extends IPageAdapter> W clear() {
        clearNoNotify();
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
