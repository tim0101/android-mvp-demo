package com.hailang.app.myasdemo.model;

import com.hailang.app.myasdemo.bean.Product;
import com.hailang.app.myasdemo.bean.ResultEntity;
import com.hailang.app.myasdemo.businessService.ProductService;
import com.hailang.app.myasdemo.network.RetrofitFactory;

import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * Created by niantian_huang on 2016/4/25.
 */
public class IProductModelImpl implements IProductModel {
    @Override
    public void getProductByCategory(String categoryId,int pageNum,final IGetDataResult<List<Product>> onGetDataResult) {
        ProductService service= RetrofitFactory.getService(ProductService.class);
        service.getProductByCategory(categoryId,String.valueOf(pageNum))
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ResultEntity<List<Product>>>(){
                            @Override
                            public void onCompleted() {
                            }

                            @Override
                            public void onError(Throwable e) {
                                e.printStackTrace();
                                onGetDataResult.onDataaFaiture("网络不可用");
                            }

                            @Override
                            public void onNext(ResultEntity<List<Product>> resultEntity) {
                                if (resultEntity.getCode()==0){
                                    onGetDataResult.onDataSuccess(resultEntity.getObject());
                                }else{
                                    onGetDataResult.onDataaFaiture(resultEntity.getMsg());
                                }

                            }
                        }
                );
    }
}
