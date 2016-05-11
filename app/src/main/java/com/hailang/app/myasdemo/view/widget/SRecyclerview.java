package com.hailang.app.myasdemo.view.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;

import com.hailang.app.myasdemo.R;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

/**
 * Created by niantian_huang on 2016/5/9.
 */
public class SRecyclerview extends FrameLayout {
    private View emptyView;
    private XRecyclerView xRecyclerView;
    //第一次加载时显示的loading
    private View startLoadingView;
    public SRecyclerview(Context context, AttributeSet attrs) {
        super(context, attrs);
        initView();
    }

    private void initView(){
        View view = View.inflate(getContext(), R.layout.secyclerview_layout_view,this);
        emptyView=view.findViewById(R.id.tv_empty);
        xRecyclerView=(XRecyclerView)view.findViewById(R.id.list);
        startLoadingView=view.findViewById(android.R.id.progress);
        setEmptyView(emptyView);
    }
    public XRecyclerView getXRecyclerView(){
        return xRecyclerView;
    }
    public void setEmptyView(View emptyView){
        this.emptyView=emptyView;
        xRecyclerView.setEmptyView(emptyView);
    }
    public void showStartLoading(){
        if(startLoadingView!=null){
            startLoadingView.setVisibility(View.VISIBLE);
        }
    }
    public void hidenStartLoading(){
        if(startLoadingView!=null){
            startLoadingView.setVisibility(View.GONE);
        }
    }
}
