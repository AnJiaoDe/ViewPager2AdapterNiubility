package com.cy.viewpager2adapterniubility;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

public class ViewPagerHolder implements IViewHolder {
    protected SparseArray<View> array_view;
    public View itemView;

    public ViewPagerHolder(View itemView) {
        this.itemView = itemView;
        array_view = new SparseArray<View>();
    }

    //获取View
    public <T extends View> T getView(int viewId) {
        View view = array_view.get(viewId);
        if (view == null) {
            view = itemView.findViewById(viewId);
            array_view.put(viewId, view);
        }
        return (T) view;
    }

    public <T extends ViewPagerHolder> T putView(int viewId, View view) {
        array_view.put(viewId, view);
        return (T) this;
    }


//???????????????????????????????????????????????????????????????


    //设置View显示
    public <T extends ViewPagerHolder> T setVisible(int res_id) {
        getView(res_id).setVisibility(View.VISIBLE);
        return (T) this;
    }
//设置View隐藏

    public <T extends ViewPagerHolder> T setInVisible(int res_id) {
        getView(res_id).setVisibility(View.INVISIBLE);
        return (T) this;
    }
//设置View Gone

    public <T extends ViewPagerHolder> T setGone(int res_id) {
        getView(res_id).setVisibility(View.GONE);
        return (T) this;
    }


//???????????????????????????????????????????????????????????????

    //null转空String
    public String nullToString(Object object) {
        return object == null ? "" : object.toString();
    }

//设置TextView 的Text

    public <T extends ViewPagerHolder> T setText(int tv_id, Object text) {
        TextView tv = getView(tv_id);
        tv.setText(nullToString(text));
        return (T) this;
    }


    //设置TextView 前面+¥
    public <T extends ViewPagerHolder> T setPriceText(int tv_id, Object text) {
        TextView tv = getView(tv_id);
        tv.setText("¥" + String.valueOf(text));
        return (T) this;
    }


    //设置TextView或者EditText的TextColor
    public <T extends ViewPagerHolder> T setTextColor(int tv_id, int color) {
        TextView tv = getView(tv_id);
        tv.setTextColor(color);
        return (T) this;
    }

//获取TextView的文本值(去空格)

    public String getTVText(int tv_id) {
        TextView tv = getView(tv_id);
        return tv.getText().toString().trim();
    }

    //获取EditText的文本值(去空格)
    public String getETText(int tv_id) {
        EditText tv = getView(tv_id);
        return tv.getText().toString().trim();
    }

//???????????????????????????????????????????????????????????????

//设置View的BackgroundResource

    public <T extends ViewPagerHolder> T setBackgroundResource(int v_id, int resid) {
        View view = getView(v_id);
        view.setBackgroundResource(resid);
        return (T) this;
    }

    //设置ImageView的ImageBitmap
    public <T extends ViewPagerHolder> T setImageBitmap(int iv_id, Bitmap bitmap) {
        ImageView view = getView(iv_id);
        view.setImageBitmap(bitmap);
        return (T) this;
    }

//设置ImageView的ImageResource

    public <T extends ViewPagerHolder> T setImageResource(int iv_id, int resID) {
        ImageView view = getView(iv_id);
        view.setImageResource(resID);
        return (T) this;
    }


    //???????????????????????????????????????????????????????????????
//设置进度条进度
    public <T extends ViewPagerHolder> T setProgress(int progress_id, int progress) {
        ProgressBar progressBar = getView(progress_id);
        progressBar.setProgress(progress);
        return (T) this;
    }

    //???????????????????????????????????????????????????????????????
//设置点击监听
    public <T extends ViewPagerHolder> T setOnClickListener(int res_id, View.OnClickListener onClickListener) {
        getView(res_id).setOnClickListener(onClickListener);
        return (T) this;
    }

    //设置长按监听
    public <T extends ViewPagerHolder> T setOnLongClickListener(int res_id, View.OnLongClickListener onLongClickListener) {
        getView(res_id).setOnLongClickListener(onLongClickListener);
        return (T) this;
    }
}
