package com.hailang.app.myasdemo.presenter;

import com.hailang.app.myasdemo.bean.Product;
import com.hailang.app.myasdemo.model.IGetDataResult;
import com.hailang.app.myasdemo.model.IProductModel;
import com.hailang.app.myasdemo.model.IProductModelImpl;
import com.hailang.app.myasdemo.utils.Tools;
import com.hailang.app.myasdemo.view.IProductView;

import java.util.List;

/**
 * Created by niantian_huang on 2016/4/25.
 */
public class IProdcutPresenterImpl implements IProductPresenter{

    IProductModel iProductModel;
    IProductView iProductView;

    public IProdcutPresenterImpl(IProductView iProductView1){
        this.iProductView=iProductView1;
        this.iProductModel=new IProductModelImpl();
    }
    IGetDataResult onGetProductData=new IGetDataResult<List<Product>>() {
        @Override
        public  void onDataSuccess(List<Product> t) {
            iProductView.dismissLoading();
            iProductView.showProductList(t);
        }

        @Override
        public void onDataaFaiture(String msg) {
            iProductView.dismissLoading();
            iProductView.showLoadFailMsg(msg);
        }
    };
    @Override
    public void getPoductList(String categoryId,int pageNum) {
        iProductModel.getProductByCategory(categoryId,pageNum,onGetProductData);
    }
}
