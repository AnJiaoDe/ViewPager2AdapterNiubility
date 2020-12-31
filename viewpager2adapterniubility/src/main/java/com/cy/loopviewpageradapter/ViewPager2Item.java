//package com.cy.loopviewpageradapter;
//
//import android.content.Context;
//import android.os.SystemClock;
//import android.util.AttributeSet;
//import android.view.MotionEvent;
//import android.view.ViewParent;
//import android.widget.FrameLayout;
//
//import androidx.annotation.NonNull;
//import androidx.annotation.Nullable;
//
///**
// * @Description:
// * @Author: cy
// * @CreateDate: 2020/12/31 14:52
// * @UpdateUser:
// * @UpdateDate: 2020/12/31 14:52
// * @UpdateRemark:
// * @Version:
// */
//public class ViewPager2Item extends FrameLayout {
//    private float downX, downY;
//    private float moveX;
//    private float moveY;
//
//    public ViewPager2Item(@NonNull Context context) {
//        this(context, null);
//    }
//
//    public ViewPager2Item(@NonNull Context context, @Nullable AttributeSet attrs) {
//        super(context, attrs);
//    }
//
//    @Override
//    public boolean dispatchTouchEvent(MotionEvent ev) {
//
//        switch (ev.getAction()) {
//            case MotionEvent.ACTION_DOWN:
//                downX = ev.getX();
//                downY = ev.getY();
//                disallowInterceptTouchEvent(true);
//                break;
//
//            case MotionEvent.ACTION_MOVE:
//                moveX = ev.getX();
//                moveY = ev.getY();
//
//                float dx = moveX - downX;
//                float dy = moveY - downY;
//
//                downX = moveX;
//                downY = moveY;
//
//                if (Math.abs(dx) > Math.abs(dy)) {
//                    disallowInterceptTouchEvent(false);
//                    //左右滑动,不消费
//                    /**
//                     * Create a new MotionEvent, filling in a subset of the basic motion
//                     * values.  Those not specified here are: device id (always 0), pressure
//                     * and size (always 1), x and y precision (always 1), and edgeFlags (always 0).
//                     *
//                     * @param downTime The time (in ms) when the user originally pressed down to start
//                     * a stream of position events.  This must be obtained from {@link SystemClock#uptimeMillis()}.
//                     * @param eventTime  The the time (in ms) when this specific event was generated.  This
//                     * must be obtained from {@link SystemClock#uptimeMillis()}.
//                     * @param action The kind of action being performed, such as {@link #ACTION_DOWN}.
//                     * @param x The X coordinate of this event.
//                     * @param y The Y coordinate of this event.
//                     * @param metaState The state of any meta / modifier keys that were in effect when
//                     * the event was generated.
//                     */
////                    static public MotionEvent obtain(long downTime, long eventTime, int action,
////                    float x, float y, int metaState)
//                    final MotionEvent last = ev;
//                    MotionEvent motionEvent = MotionEvent.obtain(SystemClock.uptimeMillis(), last.getEventTime(),
//                            MotionEvent.ACTION_DOWN, last.getX(), last.getY(), last.getMetaState());
//                    super.dispatchTouchEvent(motionEvent);
//                    motionEvent.recycle();
//                    return false;
//                }
//                break;
//            case MotionEvent.ACTION_UP:
//            case MotionEvent.ACTION_CANCEL:
//                disallowInterceptTouchEvent(true);
//                break;
//        }
//        return super.dispatchTouchEvent(ev);
//    }
//
//    private void disallowInterceptTouchEvent(boolean disallow) {
//        final ViewParent parent = getParent();
//        if (parent != null)
//            parent.requestDisallowInterceptTouchEvent(disallow);
//    }
//}
