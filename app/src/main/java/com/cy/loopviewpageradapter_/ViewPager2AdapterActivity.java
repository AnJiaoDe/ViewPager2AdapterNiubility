package com.cy.loopviewpageradapter_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.cy.viewpager2adapterniubility.R;
import com.cy.viewpager2adapterniubility.ViewPager2Adapter;
import com.cy.viewpager2adapterniubility.ViewPager2Holder;

public class ViewPager2AdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_adapter2);

        ViewPager2 viewPager2=findViewById(R.id.vp2);
        ViewPager2Adapter<PageBean> viewPager2Adapter =new ViewPager2Adapter<PageBean>(viewPager2) {
            @Override
            public void bindDataToView(ViewPager2Holder holder, int position, PageBean bean) {
                ImageView imageView=holder.itemView.findViewById(R.id.iv);
                imageView.setImageResource(bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPager2Holder holder, int position, PageBean bean) {
                Toast.makeText(ViewPager2AdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };
        viewPager2.setAdapter(viewPager2Adapter);

        viewPager2Adapter.add(new PageBean(R.drawable.pic1));
        viewPager2Adapter.add(new PageBean(R.drawable.pic2));

        viewPager2.setCurrentItem(1,false);

    }
}
