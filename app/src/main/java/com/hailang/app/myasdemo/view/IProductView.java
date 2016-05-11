package com.hailang.app.myasdemo.view;

import com.hailang.app.myasdemo.bean.Product;

import java.util.List;

/**
 * Created by niantian_huang on 2016/4/25.
 */
public interface IProductView {
    public void showLoading();
    public void dismissLoading();
    public void showProductList(List<Product> productList);
    public void showLoadFailMsg(String msg);
}
