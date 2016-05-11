package com.hailang.app.myasdemo.businessService;

import com.hailang.app.myasdemo.bean.Product;
import com.hailang.app.myasdemo.bean.ResultEntity;

import java.util.List;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * Created by niantian_huang on 2016/4/25.
 */
public interface ProductService {
    @GET("/product/listByCategoryId.do")
    Observable<ResultEntity<List<Product>>> getProductByCategory(@Query("category_id") String categoryId, @Query("page_num") String pageNum);//
}
