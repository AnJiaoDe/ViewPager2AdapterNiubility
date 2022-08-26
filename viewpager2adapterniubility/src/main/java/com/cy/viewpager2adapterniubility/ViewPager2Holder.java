package com.cy.viewpager2adapterniubility;

import android.graphics.Bitmap;
import android.util.SparseArray;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class ViewPager2Holder extends RecyclerView.ViewHolder implements IViewHolder {
    public ViewPagerHolder viewPagerHolder;
    public ViewPager2Holder(@NonNull View itemView) {
        super(itemView);
        viewPagerHolder=new ViewPagerHolder(itemView);
    }
}
