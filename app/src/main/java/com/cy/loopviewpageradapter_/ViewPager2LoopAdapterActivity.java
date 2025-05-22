package com.cy.loopviewpageradapter_;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.Toast;

import com.cy.viewpager2adapterniubility.SimpleIndicatorView;
import com.cy.viewpager2adapterniubility.ViewPager2Holder;
import com.cy.viewpager2adapterniubility.ViewPager2LoopAdapter;

public class ViewPager2LoopAdapterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_pager2_loop_adapter);

        ViewPager2 viewPager2=findViewById(R.id.vp2);
        SimpleIndicatorView simpleIndicatorView=findViewById(R.id.indicator);
        final ViewPager2LoopAdapter<PageBean> viewPage2Adapter=new ViewPager2LoopAdapter<PageBean>(viewPager2,simpleIndicatorView) {
            @Override
            public void bindDataToView(ViewPager2Holder holder, int position, PageBean bean) {
                holder.viewPagerHolder.setImageResource(R.id.iv,bean.getResID());
            }

            @Override
            public int getItemLayoutID(int position, PageBean bean) {
                return R.layout.item_page_pic;
            }

            @Override
            public void onItemClick(ViewPager2Holder holder, int position, PageBean bean) {
                Toast.makeText(ViewPager2LoopAdapterActivity.this, "点击" + position, Toast.LENGTH_SHORT).show();
            }
        };

        viewPager2.setAdapter(viewPage2Adapter);

        viewPage2Adapter.addNoNotify(new PageBean(R.drawable.pic1));
        viewPage2Adapter.addNoNotify(new PageBean(R.drawable.pic2));
        viewPage2Adapter.addNoNotify(new PageBean(R.drawable.pic3));
        viewPage2Adapter.add(new PageBean(R.drawable.pic4));

        viewPage2Adapter.setStartItem();

    }
}
