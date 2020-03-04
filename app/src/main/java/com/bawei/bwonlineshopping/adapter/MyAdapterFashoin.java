package com.bawei.bwonlineshopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bumptech.glide.Glide;

import java.util.List;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class MyAdapterFashoin extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> list;

    public MyAdapterFashoin(Context context, List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.itemtwo, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListBean.ResultBean.MlssBean.CommodityListBeanXX bean = list.get(position);
        String name = bean.getCommodityName();
        String masterPic = bean.getMasterPic();
        int price = bean.getPrice();
        ((ViewHolder)holder).tv.setText(" $ "+price+".00");
        ((ViewHolder)holder).tt.setText(name);
        Glide.with(context).load(masterPic).into(((ViewHolder)holder).iv);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{

        private final ImageView iv;
        private final TextView tt;
        private final TextView tv;

        public ViewHolder(View itemView) {
            super(itemView);
            iv = itemView.findViewById(R.id.iv);
            tt = itemView.findViewById(R.id.tt);
            tv = itemView.findViewById(R.id.tv);
        }
    }
}
