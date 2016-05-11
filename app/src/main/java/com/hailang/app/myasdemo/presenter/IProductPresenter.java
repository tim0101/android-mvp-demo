package com.hailang.app.myasdemo.presenter;

import com.hailang.app.myasdemo.model.IProductModel;
import com.hailang.app.myasdemo.view.IProductView;

/**
 * Created by niantian_huang on 2016/4/25.
 */
public interface IProductPresenter {
    public void getPoductList(String categoryId,int pageNum);
}
