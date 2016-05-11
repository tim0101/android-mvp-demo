package com.hailang.app.myasdemo.network;

import android.util.Log;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonDeserializationContext;
import com.google.gson.JsonDeserializer;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParseException;
import com.google.gson.reflect.TypeToken;
import com.hailang.app.myasdemo.Constants;
import com.hailang.app.myasdemo.bean.Product;
import com.hailang.app.myasdemo.bean.ResultEntity;

import org.json.JSONObject;

import java.io.IOException;
import java.lang.reflect.Type;
import java.util.List;

import okhttp3.HttpUrl;
import okhttp3.Interceptor;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by niantian_huang on 2016/4/26.
 */
public class RetrofitFactory {
    private static Retrofit retrofit;
    private static synchronized Retrofit getRetrofit(){
        if (retrofit==null){
            OkHttpClient client = new OkHttpClient.Builder()
                    .addInterceptor(new MyInterceptor())
                    .build();

            Gson customGsonInstance = new GsonBuilder()
                    .registerTypeAdapter(new TypeToken<ResultEntity>(){}.getType(),
                            new RetrofitFactory.MylResultsDeserializer())
                    .create();

            retrofit = new Retrofit.Builder()
                    .baseUrl(Constants.SERVER_URL)
                    .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                    .addConverterFactory(GsonConverterFactory.create(customGsonInstance))
                    .client(client)
                    .build();
        }
        return retrofit;
    }
    public static <T> T getService(Class<T> tClass){
        return getRetrofit().create(tClass);
    }

    /**
     * 自定义拦截类
     */
    private static class MyInterceptor implements Interceptor {

        @Override
        public Response intercept(Chain chain) throws IOException {
            Request oldRequest = chain.request();
            // 添加新的参数
            HttpUrl.Builder authorizedUrlBuilder = oldRequest.url()
                    .newBuilder()
                    .scheme(oldRequest.url().scheme())
                    .host(oldRequest.url().host())
                    .addQueryParameter("app_id",Constants.APP_ID);
            // 新的请求
            Request newRequest = oldRequest.newBuilder()
                    .method(oldRequest.method(), oldRequest.body())
                    .url(authorizedUrlBuilder.build())
                    .build();
            Response response=chain.proceed(newRequest);
//            String body=response.body().source().toString();
//            Log.w("Response",body);
            return response;
        }
    }

    /**
     * 自定义JSON序列化
     *
     */
    public static class MylResultsDeserializer implements JsonDeserializer<ResultEntity> {
        @Override
        public ResultEntity deserialize(JsonElement je, Type typeOfT,
                                                 JsonDeserializationContext context) throws JsonParseException {
            // 转换Json的数据, 获取内部有用的信息
            JsonObject resultJson=je.getAsJsonObject();
            if(resultJson.get("code").getAsInt()==0){
                return new Gson().fromJson(je, typeOfT);
            }else{
                ResultEntity<Object> b=new ResultEntity<>();
                b.setCode(resultJson.get("code").getAsInt());
                b.setMsg(resultJson.get("msg").getAsString());
                b.setObject(null);
                return b;
            }

        }
    }
}
