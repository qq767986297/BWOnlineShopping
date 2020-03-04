package com.bawei.bwonlineshopping.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.util.AttributeSet;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.bwonlineshopping.R;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class CustomViewGroup extends LinearLayout {

    private CustomSystemView con;
    private TextView sou;
    public CustomViewGroup(Context context) {
        super(context);
        init(context);
    }

    public CustomViewGroup(Context context,  AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    private void init(Context context){
        View view = View.inflate(context, R.layout.viewgroup_serach, null);
        con = view.findViewById(R.id.con);
        sou = view.findViewById(R.id.sou);
        con.setOnTextChanged(new CustomSystemView.OnTextChanged() {
            @Override
            public void onChanged(String str) {
                if(monSouClickListener!=null){
                    monSouClickListener.onSou(str);
                }
            }
        });
        sou.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
                if(monSouClickListener!=null){
                    monSouClickListener.onSou(con.getText().toString());
                }
            }
        });
        addView(view);
    }
    OnSouClickListener monSouClickListener;
    public void setOnClick(OnSouClickListener onSouClickListener ){
        monSouClickListener=onSouClickListener;
    }
    public interface OnSouClickListener{
        void onSou(String str);
    }
}
