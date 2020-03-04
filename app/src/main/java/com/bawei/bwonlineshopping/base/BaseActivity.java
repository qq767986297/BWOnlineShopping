package com.bawei.bwonlineshopping.base;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.bwonlineshopping.contract.IHomeContract;

public abstract class BaseActivity<P extends BasePresenter> extends AppCompatActivity {

    private P presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayout());
        initView();
        initData();
        presenter = initPresenter();
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract P initPresenter();
    protected abstract int getLayout();
    protected abstract void initView();
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(presenter!=null){
            presenter.datachView();
            presenter=null;
        }
    }
}
