package com.cy.loopviewpageradapter_;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cy.loopviewpageradapter.R;
import com.cy.loopviewpageradapter.SimpleIndicatorView;
import com.cy.loopviewpageradapter.ViewPagerHolder;
import com.cy.loopviewpageradapter.ViewPagerLoopAdapter;

public class ViewPagerLoopAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_loop_adapter);

        ViewPager viewPager = findViewById(R.id.vp);
        SimpleIndicatorView simpleIndicatorView = findViewById(R.id.indicator);
        final ViewPagerLoopAdapter<PageBean> viewPageAdapter = new ViewPagerLoopAdapter<PageBean>(viewPager, simpleIndicatorView) {
            @Override
            public void bindDataToView(ViewPagerHolder holder, int position, PageBean bean) {
                ImageView imageView = holder.itemView.findViewById(R.id.iv);
                imageView.setImageResource(bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPagerHolder holder, int position, PageBean bean) {
                Toast.makeText(ViewPagerLoopAdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };

        viewPager.setAdapter(viewPageAdapter);

        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic1));
        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic2));
        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic3));
        viewPageAdapter.add(new PageBean(R.drawable.pic4));

    }

}
