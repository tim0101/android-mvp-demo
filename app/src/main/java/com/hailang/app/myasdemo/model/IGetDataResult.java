package com.hailang.app.myasdemo.model;

import com.hailang.app.myasdemo.utils.Tools;

/**
 * Created by niantian_huang on 2016/5/11.
 */
public interface IGetDataResult<T> {
    public void onDataSuccess(T t);
    public void onDataaFaiture(String msg);
}
