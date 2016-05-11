package com.hailang.app.myasdemo.bean;

import android.text.TextUtils;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.annotations.Expose;
import com.google.gson.reflect.TypeToken;

import java.util.List;

/**
 * Created by niantian_huang on 2016/5/4.
 */
public class ResultEntity<T>{
    int code;
    String msg;
    T object;
    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getObject() {
        return object;
    }
    public void setObject(T object) {
        this.object = object;
    }
//
//    public T getContent(){
//        if (isSuccess()) return null;
//        Gson gson=new Gson();//GsonBuilder().excludeFieldsWithoutExposeAnnotation().create();
//        T t=gson.fromJson("[]", new TypeToken<T>(){}.getType());
//        return t;
//    }
//    public boolean isSuccess(){
//        if (code!=0||TextUtils.isEmpty(String.valueOf(object))){
//            return false;
//        }else{
//            return  true;
//        }
//    }
}
