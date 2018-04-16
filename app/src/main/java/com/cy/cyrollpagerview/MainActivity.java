package com.cy.cyrollpagerview;

import android.os.Bundle;
import android.view.View;

public class MainActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        findViewById(R.id.btn_1).setOnClickListener(this);
        findViewById(R.id.btn_2).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {

        switch (v.getId()) {
            case R.id.btn_1:

                startAppcompatActivity(VPLayoutActivity.class);
                break;
            case R.id.btn_2:
                startAppcompatActivity(VPLayoutMultiActivity.class);

                break;
        }
    }
}
