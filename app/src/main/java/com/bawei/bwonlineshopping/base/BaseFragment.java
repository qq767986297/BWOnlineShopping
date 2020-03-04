package com.bawei.bwonlineshopping.base;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public abstract class BaseFragment<P extends BasePresenter> extends Fragment {

    private P presenter;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = View.inflate(getContext(), getLayout(), null);
        presenter = initPresenter();
        initView(view);
        initData();
        return view;
    }

    public P getPresenter() {
        return presenter;
    }

    protected abstract P initPresenter();
    protected abstract int getLayout();
    protected abstract void initView(View view);
    protected abstract void initData();


}
