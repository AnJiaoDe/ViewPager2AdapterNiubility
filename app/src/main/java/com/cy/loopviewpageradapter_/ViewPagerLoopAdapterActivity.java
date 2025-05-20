package com.cy.loopviewpageradapter_;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.cy.viewpager2adapterniubility.R;
import com.cy.viewpager2adapterniubility.SimpleIndicatorView;
import com.cy.viewpager2adapterniubility.ViewPagerHolder;
import com.cy.viewpager2adapterniubility.ViewPagerLoopAdapter;

public class ViewPagerLoopAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_loop_adapter);

        ViewPager viewPager = findViewById(R.id.vp);
        SimpleIndicatorView simpleIndicatorView = findViewById(R.id.indicator);
        final ViewPagerLoopAdapter<PageBean> viewPageAdapter = new ViewPagerLoopAdapter<PageBean>(viewPager, simpleIndicatorView) {
            @Override
            public void onPageSelected(ViewPagerHolder viewPagerHolder, int position, @NonNull PageBean bean) {
                super.onPageSelected(viewPagerHolder, position, bean);
                LogUtils.log("onPageSelected ", position + "");
            }

            @Override
            public void onPageScrolled(int p, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(p, positionOffset, positionOffsetPixels);
                LogUtils.log("onPageScrolled ", p + ">>>"+positionOffset+">>>"+positionOffsetPixels);
            }

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
        viewPageAdapter.setLoopAuto(false);

        viewPager.setAdapter(viewPageAdapter);

        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic1));
        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic2));
//        viewPageAdapter.addNoNotify(new PageBean(R.drawable.pic3));
        viewPageAdapter.add(new PageBean(R.drawable.pic4));

    }

}
