package com.bawei.bwonlineshopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ImageView;
import android.widget.TextView;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.bean.ShopCarBean;
import com.bumptech.glide.Glide;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

/**
 * Time: 2020/3/26
 * Author: 王冠华
 * Description:
 */
public class ShopCarAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {
    Context context;
    List<ShopCarBean.ResultBean.ShoppingCartListBean> list;

    public ShopCarAdapter(Context context, List<ShopCarBean.ResultBean.ShoppingCartListBean> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.shopitem, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ShopCarBean.ResultBean.ShoppingCartListBean bean = list.get(position);
        String name = bean.getCommodityName();
        String pic = bean.getPic();
        int price = bean.getPrice();
        boolean check = bean.isCheck();
        ((ViewHolder)holder).name.setText(name);
        ((ViewHolder)holder).price.setText(price+"");
        Glide.with(context).load(pic).into(((ViewHolder)holder).iv);
        ((ViewHolder)holder).cb.setChecked(bean.isCheck());
        ((ViewHolder)holder).cb.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                list.get(position).setCheck(isChecked);
                EventBus.getDefault().post(0);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
    private class ViewHolder extends RecyclerView.ViewHolder{

        private final CheckBox cb;
        private final ImageView iv;
        private final TextView name;
        private final TextView price;

        public ViewHolder(View itemView) {
            super(itemView);
            cb = itemView.findViewById(R.id.it_cb);
            iv = itemView.findViewById(R.id.shop_it_img);
            name = itemView.findViewById(R.id.shop_it_name);
            price = itemView.findViewById(R.id.shop_it_price);
        }
    }
}
