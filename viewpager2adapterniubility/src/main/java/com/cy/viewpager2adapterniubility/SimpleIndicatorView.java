package com.cy.viewpager2adapterniubility;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/12/9 16:26
 * @UpdateUser:
 * @UpdateDate: 2020/12/9 16:26
 * @UpdateRemark:
 * @Version:
 */
public class SimpleIndicatorView extends View implements IIndicatorView {
    private int position_selected = 0;
    private int position_scroll = 0;
    private int width = 0;
    private int height = 0;
    private int space = 0;
    private int radius_normal = 0;
    private int radius_selected = 0;
    private Paint paint_normal;
    private Paint paint_selected;
    private float positionOffset;
    private int count = 0;

    public SimpleIndicatorView(Context context) {
        this(context, null);
    }

    public SimpleIndicatorView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);

        TypedArray typedArray = context.obtainStyledAttributes(attrs,R.styleable.SimpleIndicatorView);

        if (typedArray != null) {
            setSpace(typedArray.getDimensionPixelSize(R.styleable.SimpleIndicatorView_cy_indicator_space, ScreenUtils.dpAdapt(context, 8)));
            setRadius_normal(typedArray.getDimensionPixelSize(R.styleable.SimpleIndicatorView_cy_indicator_radius_normal, ScreenUtils.dpAdapt(context, 2)));
            setRadius_selected(typedArray.getDimensionPixelSize(R.styleable.SimpleIndicatorView_cy_indicator_radius_selected, ScreenUtils.dpAdapt(context, 3)));
            typedArray.recycle();
        } else {
            setSpace(ScreenUtils.dpAdapt(context, 8));
            setRadius_normal(ScreenUtils.dpAdapt(context, 2));
            setRadius_selected(ScreenUtils.dpAdapt(context, 3));
        }

        paint_normal = new Paint();
        setColor_normal(0x668c8c8c);
        paint_normal.setAntiAlias(true);
        paint_normal.setStyle(Paint.Style.FILL);

        paint_selected = new Paint();
        setColor_selected(0xff00a2fd);
        paint_selected.setAntiAlias(true);
        paint_selected.setStyle(Paint.Style.FILL);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(MeasureSpec.getMode(widthMeasureSpec) == MeasureSpec.AT_MOST ?
                        MeasureSpec.makeMeasureSpec(ScreenUtils.getScreenWidth(getContext()), MeasureSpec.EXACTLY) : widthMeasureSpec,
                MeasureSpec.getMode(heightMeasureSpec) == MeasureSpec.AT_MOST ?
                        MeasureSpec.makeMeasureSpec(ScreenUtils.dpAdapt(getContext(), 20), MeasureSpec.EXACTLY) : heightMeasureSpec);
    }

    public int getCount() {
        return count;
    }


    @Override
    public <R extends IIndicatorView> R setCount(int count) {
        this.count = count;
        return (R) this;
    }


    public int getPosition_selected() {
        return position_selected;
    }

    public int getSpace() {
        return space;
    }

    public SimpleIndicatorView setSpace(int space) {
        this.space = space;
        return this;
    }

    @Override
    public <R extends View> R getView() {
        return (R) this;
    }

    public int getRadius_normal() {
        return radius_normal;
    }

    public SimpleIndicatorView setRadius_normal(int radius_normal) {
        this.radius_normal = radius_normal;
        return this;
    }

    public int getRadius_selected() {
        return radius_selected;
    }

    public SimpleIndicatorView setRadius_selected(int radius_selected) {
        this.radius_selected = radius_selected;
        return this;
    }

    public Paint getPaint_normal() {
        return paint_normal;
    }


    public SimpleIndicatorView setColor_normal(int color_normal) {
        paint_normal.setColor(color_normal);
        return this;
    }

    public SimpleIndicatorView setColor_selected(int color_selected) {
        paint_selected.setColor(color_selected);
        return this;
    }

    public Paint getPaint_selected() {
        return paint_selected;
    }

    @Override
    public <R extends IIndicatorView> R scroll(int position_scroll, float positionOffset) {
        this.position_scroll = position_scroll;
        this.positionOffset = positionOffset;
        return (R) this;
    }

    @Override
    protected void onLayout(boolean changed, int l, int t, int r, int b) {
        super.onLayout(changed, l, t, r, b);
        width = getWidth();
        height = getHeight();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if(count<=1)return;
        float cx = width * 1f / 2;
        float cy = height * 1f / 2;
        float x_left_0 = cx - (2 * radius_normal * count + space * (count - 1)) * 1f / 2;
        float left_min=0;
        float left_max=0;
        for (int i = 0; i < count; i++) {
            float c_x_ = x_left_0 + (2 * radius_normal + space) * i + radius_normal;
            canvas.drawCircle(c_x_, cy, radius_normal, paint_normal);
            if(i==0)left_min=c_x_-radius_selected;
            if(i==count-1)left_max=c_x_-radius_selected;
        }

        float c_x_ = x_left_0+ (2 * radius_normal + space) * position_scroll + radius_normal;
        float left = c_x_ - radius_normal + (2 * radius_normal + space) * positionOffset-(radius_selected-radius_normal);

        left=Math.max(left_min,Math.min(left_max,left));
        canvas.drawRoundRect(left, cy - radius_selected, left + 2 * radius_selected, cy + radius_selected,
                radius_selected, radius_selected, paint_selected);
    }
}
