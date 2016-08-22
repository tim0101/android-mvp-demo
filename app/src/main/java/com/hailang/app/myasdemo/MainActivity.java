package com.hailang.app.myasdemo;

import android.os.Handler;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.hailang.app.myasdemo.adapter.DataBindSimpleAdapter;
import com.hailang.app.myasdemo.adapter.ProductListAdapter;
import com.hailang.app.myasdemo.bean.Product;
import com.hailang.app.myasdemo.presenter.IProdcutPresenterImpl;
import com.hailang.app.myasdemo.presenter.IProductPresenter;
import com.hailang.app.myasdemo.utils.Tools;
import com.hailang.app.myasdemo.view.IProductView;
import com.hailang.app.myasdemo.view.widget.SRecyclerview;
import com.hailang.app.myasdemo.view.widget.SpacesItemDecoration;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
//import com.malinskiy.superrecyclerview.OnMoreListener;
//import com.malinskiy.superrecyclerview.SuperRecyclerView;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements IProductView{
    protected IProductPresenter iProductPresenter;

    SRecyclerview sRecyclerview;
//    @BindView(R.id.list)
    protected XRecyclerView superRecyclerView;

    protected List<Product> productList=new ArrayList<>();
    protected DataBindSimpleAdapter productListAdapter;
    protected int pageNum=1;
//    private RecyclerView recyclerView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        sRecyclerview=(SRecyclerview)getLayoutInflater().inflate(R.layout.activity_main,null);
        setContentView(sRecyclerview);
        superRecyclerView=sRecyclerview.getXRecyclerView();
//        ButterKnife.bind(this);

        GridLayoutManager layoutManager = new GridLayoutManager(this,2);
        superRecyclerView.setLayoutManager(layoutManager);
        int spacingInPixels = getResources().getDimensionPixelSize(R.dimen.grid_product_margin);
        superRecyclerView.addItemDecoration(new SpacesItemDecoration(spacingInPixels));

        productListAdapter=new DataBindSimpleAdapter(R.layout.product_list_item, com.hailang.app.myasdemo.BR.product,productList);
        productListAdapter.addEventHandler(BR.enventHandler,this);
        superRecyclerView.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                pageNum=1;
                iProductPresenter.getPoductList("387",pageNum);
            }

            @Override
            public void onLoadMore() {
                pageNum++;
                iProductPresenter.getPoductList("387", pageNum);
            }
        });


        if(iProductPresenter==null){
            iProductPresenter=new IProdcutPresenterImpl(this);
        }
        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                superRecyclerView.setRefreshing(true);
            }
        },1000);
    }

    @Override
    public void showLoading() {
        superRecyclerView.setRefreshing(true);
    }

    /**
     * 显示更多
     */
    @Override
    public void dismissLoading() {
       superRecyclerView.setRefreshing(false);
//        superRecyclerView.hideProgress();
    }

    @Override
    public void showProductList(List<Product> pds) {
        //第一次或者刷新
        if(pageNum==1){
            sRecyclerview.hidenStartLoading();
            superRecyclerView.reset();
            productList.clear();

            if(superRecyclerView.getAdapter()==null) {
                superRecyclerView.setAdapter(productListAdapter);
            }
        }else if(pds.size()==0){//没有更多了
            superRecyclerView.noMoreLoading();
        }else{
            superRecyclerView.loadMoreComplete();
        }
        productList.addAll(pds);
        productListAdapter.notifyDataSetChanged();
    }

    /**
     * 加载失败
     * @param msg
     */
    @Override
    public void showLoadFailMsg(String msg) {
        Tools.showInfo(this,msg);
        if(pageNum==1) {
            sRecyclerview.hidenStartLoading();
            superRecyclerView.reset();
        }else{
            superRecyclerView.loadMoreComplete();
        }
    }

    public void addProduct(Product product){
        Tools.showInfo(this,product.getTitle());
    }

}
