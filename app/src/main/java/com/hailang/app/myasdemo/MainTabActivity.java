package com.hailang.app.myasdemo;

import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.hailang.app.myasdemo.view.Fragment.Test1Fragment;
import com.hailang.app.myasdemo.view.activity.BaseTabFramgmentActivity;

public class MainTabActivity extends BaseTabFramgmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        addTab("test1","test1", Test1Fragment.class,null);
        addTab("test2","test2", Test1Fragment.class,null);
        addTab("test3","test3", Test1Fragment.class,null);
        addTab("test4","test4", Test1Fragment.class,null);

        TabLayout tabLayout;
        TabLayout.Tab tab;
//        tab.setCustomView()
//        tabLayout.setCu
    }

    @Override
    public int getContentLayout() {
        return R.layout.activity_tab_main;
    }

    @Override
    protected View getTabItemView(String tag,String title) {
        View view=getLayoutInflater().inflate(R.layout.main_tab_item_view,null);
        TextView tvView=(TextView)view.findViewById(R.id.textview);
        tvView.setText(title);
        return view;
    }
}
