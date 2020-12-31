package com.cy.loopviewpageradapter;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.view.View;

/**
 * @Description:
 * @Author: cy
 * @CreateDate: 2020/12/9 16:25
 * @UpdateUser:
 * @UpdateDate: 2020/12/9 16:25
 * @UpdateRemark:
 * @Version:
 */
public interface IIndicatorView {
    public <R extends View > R getView();
    public <R extends IIndicatorView > R setCount(int count);
    public <R extends IIndicatorView> R scroll(int position_scroll, float positionOffset);
}
