package com.hailang.app.myasdemo.model;

import com.hailang.app.myasdemo.bean.Product;

import java.util.List;

/**
 * Created by niantian_huang on 2016/4/25.
 */
public interface IProductModel {

    void getProductByCategory(String categoryId,int pageNum,IGetDataResult<List<Product>> onGetDataResult);
}
