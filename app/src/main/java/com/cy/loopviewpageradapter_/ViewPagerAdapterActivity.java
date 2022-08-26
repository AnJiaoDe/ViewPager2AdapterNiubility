package com.cy.loopviewpageradapter_;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.util.Log;
import android.widget.ImageView;
import android.widget.Toast;

import com.cy.viewpager2adapterniubility.R;
import com.cy.viewpager2adapterniubility.ViewPagerAdapter;
import com.cy.viewpager2adapterniubility.ViewPagerHolder;

public class ViewPagerAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager_adapter);

        ViewPager viewPager=findViewById(R.id.vp);
        ViewPagerAdapter<PageBean> viewPagerAdapter =new ViewPagerAdapter<PageBean>(viewPager) {

            @Override
            public void onPageSelected(ViewPagerHolder viewPagerHolder, int position, @NonNull PageBean bean) {
                super.onPageSelected(viewPagerHolder, position, bean);
                Log.e("onPageSelected",position+"");
            }

            @Override
            public void bindDataToView(ViewPagerHolder holder, int position, PageBean bean) {
                ImageView imageView=holder.itemView.findViewById(R.id.iv);
                imageView.setImageResource(bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPagerHolder holder, int position, PageBean bean) {
                Toast.makeText(ViewPagerAdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };
        viewPager.setAdapter(viewPagerAdapter);

        viewPagerAdapter.addNoNotify(new PageBean(R.drawable.pic1));
        viewPagerAdapter.addNoNotify(new PageBean(R.drawable.pic2));
        viewPagerAdapter.addNoNotify(new PageBean(R.drawable.pic3));
        viewPagerAdapter.add(new PageBean(R.drawable.pic4));

//        viewPager.setCurrentItem(1,false);
    }
}
