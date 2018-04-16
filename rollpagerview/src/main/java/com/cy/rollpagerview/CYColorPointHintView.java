package com.cy.rollpagerview;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.GradientDrawable;
import android.util.AttributeSet;

import com.jude.rollviewpager.Util;
import com.jude.rollviewpager.hintview.ShapeHintView;


/**
 * Created by lenovo on 2017/7/11.
 */

public class CYColorPointHintView extends ShapeHintView {
    private int focusColor=0xffffff00;
    private int normalColor=0x55999999;
    private int width=6;//dp
    private int height=6;//dp
    private float radius=3;

    public CYColorPointHintView(Context context) {
        this(context,null);
    }



    public CYColorPointHintView(Context context, int focusColor,int normalColor) {
        this(context,null);
        this.focusColor = focusColor;
        this.normalColor = normalColor;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }
    public CYColorPointHintView(Context context, int focusColor, int normalColor, int width, int height, float radius) {
        this(context,null);
        this.focusColor = focusColor;
        this.normalColor = normalColor;
        this.width = width;
        this.height = height;
        this.radius = radius;
    }
    public CYColorPointHintView(Context context, int width, int height, float radius) {
        this(context,null);
        this.width = width;
        this.height = height;
        this.radius = radius;
    }

    public CYColorPointHintView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    public Drawable makeFocusDrawable() {
        GradientDrawable dot_focus = new GradientDrawable();
        dot_focus.setColor(focusColor);
        dot_focus.setCornerRadius(Util.dip2px(getContext(),radius));

        dot_focus.setSize(Util.dip2px(getContext(),width),Util.dip2px(getContext(),height));
        return dot_focus;
    }

    @Override
    public Drawable makeNormalDrawable() {
        GradientDrawable dot_normal = new GradientDrawable();
        dot_normal.setColor(normalColor);
        dot_normal.setCornerRadius(Util.dip2px(getContext(),radius));
        dot_normal.setSize(Util.dip2px(getContext(),width),Util.dip2px(getContext(),height));


        return dot_normal;
    }
}
