package com.cy.cyrollpagerview;

import android.os.Bundle;
import android.view.View;

import com.cy.rollpagerview.CYColorPointHintView;
import com.cy.rollpagerview.CYLoopPagerAdapter;
import com.cy.rollpagerview.CYRollPagerView;

import java.util.ArrayList;
import java.util.List;

public class VPLayoutMultiActivity extends BaseActivity {

    private CYLoopPagerAdapter<VPBean> cyLoopPagerAdapter;
    private CYRollPagerView<VPBean> cyRollPagerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vplayout_multi);
        List<VPBean> list=new ArrayList<>();

        list.add(new VPBean("https://timgsa.baidu.com/timg?image&quality=80&size=b9999_10000&sec=1523860722694&di=6671f0dd55b6b164a096fc52145b75a6&imgtype=0&src=http%3A%2F%2Fc.hiphotos.baidu.com%2Fbaike%2Fpic%2Fitem%2Fd009b3de9c82d158b62f49ef890a19d8bc3e423a.jpg",
                "添加hi偶就能够"));
        list.add(new VPBean("https://ss3.bdstatic.com/70cFv8Sh_Q1YnxGkpoWK1HF6hhy/it/u=2777350900,2402717967&fm=27&gp=0.jpg",
                "德国人和"));
        list.add(new VPBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2666040239,1399277915&fm=27&gp=0.jpg",
                "如果和认同和"));
        list.add(new VPBean("https://ss1.bdstatic.com/70cFuXSh_Q1YnxGkpoWK1HF6hhy/it/u=2341746217,231983218&fm=11&gp=0.jpg",
                "人皇太后"));
        list.add(new VPBean("https://ss0.bdstatic.com/70cFuHSh_Q1YnxGkpoWK1HF6hhy/it/u=2469049613,2268261369&fm=27&gp=0.jpg",
                "而天花板"));

        cyRollPagerView= (CYRollPagerView<VPBean>) findViewById(R.id.vp);

        cyLoopPagerAdapter=new CYLoopPagerAdapter<VPBean>(cyRollPagerView,list) {


            @Override
            public void bindDataToView(ViewHolder holder, int position, VPBean bean) {


                holder.setImage(VPLayoutMultiActivity.this,R.id.iv,bean.getUrl(),1080,500);
                holder.setText(R.id.tv,bean.getText());
            }


            @Override
            public int getItemLayoutID(int position, VPBean bean) {

                if (position==0||position==3){
                    return R.layout.item_layout2;
                }
                return R.layout.item_layout;
            }
            @Override
            public void onItemClick(int position, VPBean bean) {

            }
        };

        cyRollPagerView.setAdapter(cyLoopPagerAdapter,new CYColorPointHintView(
                this,0xffff0000,0xffffffff,4,4,2));
    }

    @Override
    public void onClick(View v) {

    }
}
