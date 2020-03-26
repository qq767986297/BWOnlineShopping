package com.bawei.bwonlineshopping.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.bwonlineshopping.R;

import java.util.ArrayList;

/**
 * Time: 2020/3/10
 * Author: 王冠华
 * Description:
 */
public class FirstAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder>  {
    Context context;
    ArrayList<String> list;

    public FirstAdapter(Context context, ArrayList<String> list) {
        this.context = context;
        this.list = list;
    }


    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = View.inflate(context, R.layout.popit, null);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, final int position) {
        ((ViewHolder)holder).tv.setText(list.get(position));
        ((ViewHolder)holder).ll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                msetOnItemClickLinster.onClick(position);
            }
        });
    }
    setOnItemClickLinster msetOnItemClickLinster;
    public void setOnItemClickLinster(setOnItemClickLinster setOnItemClickLinster){
        msetOnItemClickLinster = setOnItemClickLinster;
    }
    public interface setOnItemClickLinster{
        void onClick(int posion);
    }
    @Override
    public int getItemCount() {
        return list.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView tv;
        private final LinearLayout ll;
        public ViewHolder(View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.man);
            ll = itemView.findViewById(R.id.ll);
        }
    }
}
