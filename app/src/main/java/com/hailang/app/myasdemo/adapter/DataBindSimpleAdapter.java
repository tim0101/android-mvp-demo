package com.hailang.app.myasdemo.adapter;

import android.content.Context;
import android.databinding.BindingAdapter;
import android.databinding.DataBindingUtil;
import android.databinding.ViewDataBinding;
import android.databinding.adapters.AdapterViewBindingAdapter;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hailang.app.myasdemo.utils.ImageLoader;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * Created by niantian_huang on 2016/8/18.
 */
public class DataBindSimpleAdapter<T> extends RecyclerView.Adapter<DataBindSimpleAdapter.BindingHolder> {
    private int holderLayout, variableId;
    private List<T> items;
    private OnItemClickListener<T> onItemClickListener;
    private int[] clickViewIds;
    //
    Map<Integer,Object> eventHandler=new HashMap<>();

    public DataBindSimpleAdapter(int holderLayout, int variableId, List<T> items) {
        this.holderLayout = holderLayout;
        this.variableId = variableId;
        this.items = items;
    }

    @Override
    public DataBindSimpleAdapter.BindingHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext())
                .inflate(holderLayout, parent, false);
        return new BindingHolder(v);
    }

    @Override
    public void onBindViewHolder(DataBindSimpleAdapter.BindingHolder holder, final int position) {
        final T item = items.get(position);
//        .setOnClickListener(v -> {
//            if (onItemClickListener != null)
//                onItemClickListener.onItemClick(position, item);
//        });
//        if(onItemClickListener != null){
//            View root=holder.getBinding().getRoot();
//            for(int i:clickViewIds){
//                View v=root.findViewById(i);
//                if(v!=null){
//                   v.setOnClickListener(new OnItemViewOnClick(item,position));
//                }
//            }
//        }
//        if (eventHandler.size()>0){
//            Set<Map.Entry<Integer,Object>> keyValueSet=eventHandler.entrySet();
//            Iterator<Map.Entry<Integer,Object>> it= keyValueSet.iterator();
//            while (it.hasNext()){
//                Map.Entry<Integer,Object> keyValue= it.next();
//                holder.getBinding().setVariable(keyValue.getKey(), keyValue.getValue());
//            }
//        }
        for (Map.Entry<Integer,Object> entry : eventHandler.entrySet()) {
            holder.getBinding().setVariable(entry.getKey(), entry.getValue());
        }
        holder.getBinding().setVariable(variableId, item);
        holder.getBinding().executePendingBindings();
    }

      class OnItemViewOnClick implements View.OnClickListener{
          private T data;
          private int position;
          public OnItemViewOnClick(T data,int position){
              this.data=data;
              this.position=position;
          }
          @Override
          public void onClick(View view) {
              onItemClickListener.onItemClick(position, data,view.getId());
          }
      };
    @Override
    public int getItemCount() {
        return items.size();
    }

    public void setOnItemClickListener(OnItemClickListener<T> onItemClickListener,int[] viewids) {
        this.onItemClickListener = onItemClickListener;
        clickViewIds=viewids;
    }

    public interface OnItemClickListener<T> {
        void onItemClick(int position, T item,int viewId);
    }

    public static class BindingHolder extends RecyclerView.ViewHolder {
        private ViewDataBinding binding;

        public BindingHolder(View v) {
            super(v);
            binding = DataBindingUtil.bind(v);
        }

        public ViewDataBinding getBinding() {
            return binding;
        }
    }

    public void addEventHandler(int objectV,Object handler){
        eventHandler.put(objectV,handler);
    }
}