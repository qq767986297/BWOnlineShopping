package com.bawei.bwonlineshopping.base;

import java.lang.ref.WeakReference;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public abstract class BasePresenter <V extends IBasView>{

    private  WeakReference<V> vWeakReference;

    public BasePresenter(V v) {
        vWeakReference = new WeakReference<>(v);
        initModel();
    }
    public abstract void initModel();
    public V getView(){
        if(vWeakReference!=null){
           return vWeakReference.get();
        }
        return null;
    }
    public void datachView(){
        if(vWeakReference!=null){
            vWeakReference.clear();
            vWeakReference=null;
        }
    }
}
