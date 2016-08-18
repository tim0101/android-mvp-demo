package com.hailang.app.myasdemo.view.activity;

import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.hailang.app.myasdemo.R;

import java.util.List;

/**
 * Created by niantian_huang on 2016/8/8.
 */
public class TestActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener {

    SwipeRefreshLayout myRefresh;
    ListView listView;
    @Override
    public void onCreate(Bundle savedInstanceState, PersistableBundle persistentState) {
        super.onCreate(savedInstanceState, persistentState);
        setContentView(R.layout.layout_test);

//        myRefresh=(SwipeRefreshLayout)findViewById(R.id.my_refresh);
//        myRefresh.setColorScheme(getResources().getColor(android.R.color.holo_red_light),
//                getResources().getColor(android.R.color.holo_green_light),
//                getResources().getColor(android.R.color.holo_blue_bright),
//                getResources().getColor(android.R.color.holo_orange_light));
////        myRefresh.setRefreshing(true);
//        myRefresh.setOnRefreshListener(this);
//        String[] datas={"1","2","3","4","5"};
//        listView=(ListView)findViewById(R.id.mlist);
//        listView.setAdapter(new ArrayAdapter<String>(this,android.R.layout.simple_list_item_1,datas));
    }

    @Override
    public void onRefresh() {

    }
}
