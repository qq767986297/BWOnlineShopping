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
import com.bawei.bwonlineshopping.bean.XRecyBean;
import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class MyAdapterHot  extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context mcontext;
    List<ListBean.ResultBean.RxxpBean.CommodityListBean> list;

    public MyAdapterHot(Context mcontext, List<ListBean.ResultBean.RxxpBean.CommodityListBean> list) {
        this.mcontext = mcontext;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(mcontext, R.layout.itemone, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ListBean.ResultBean.RxxpBean.CommodityListBean bean = list.get(position);
        String name = bean.getCommodityName();
        String masterPic = bean.getMasterPic();
       // int price = bean.getPrice();
      //  ((ViewHolder)holder).tv.setText(" $ "+price+".00");
        ((ViewHolder)holder).tt.setText(name);
        Glide.with(mcontext).load(masterPic).into(((ViewHolder)holder).iv);

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
