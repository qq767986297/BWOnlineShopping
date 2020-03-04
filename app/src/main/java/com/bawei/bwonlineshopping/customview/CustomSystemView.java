package com.bawei.bwonlineshopping.customview;

import android.annotation.SuppressLint;
import android.content.Context;
import android.os.Handler;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
@SuppressLint("AppCompatCustomView")
public class CustomSystemView extends EditText {
    Handler handler=new Handler();
    private String s1;
    public CustomSystemView(Context context) {
        super(context);
        init();
    }

    public CustomSystemView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    private void init() {
        addTextChangedListener(new TextWatcher() {



            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                s1 = s.toString();

            }
        });
    }

    Runnable runnable = new Runnable() {

        @Override
        public void run() {
            if(mOnTextChanged!=null){
                mOnTextChanged.onChanged(s1);
            }
        }
    };
    OnTextChanged mOnTextChanged;

    public void setOnTextChanged(OnTextChanged onTextChanged) {
        mOnTextChanged = onTextChanged;
    }

    public interface OnTextChanged {
        void onChanged(String str);
    }
}