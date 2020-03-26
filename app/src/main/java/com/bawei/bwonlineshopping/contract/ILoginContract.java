package com.bawei.bwonlineshopping.contract;

import com.bawei.bwonlineshopping.base.IBasView;

import java.util.HashMap;

/**
 * Time: 2020/3/6
 * Author: 王冠华
 * Description:
 */
public interface ILoginContract {
    interface ILoginView extends IBasView{
        void onLoginSuccess(String str);
        void onLoginFailure(String str);

    }
    interface ILoginPresenter{
        void onLogin(String url, HashMap<String,String>map,HashMap<String,String>head);
        void onReg(String url, HashMap<String,String>map);

    }
    interface ILoginModel{
        void onLogin(String url, HashMap<String,String>map,HashMap<String,String>head,ILoginCallBack iLoginCallBack);
        void onReg(String url, HashMap<String,String>map,IRegCallBack iRegCallBack);
    }
    interface ILoginCallBack{
        void onLoginSuccess(String str);
        void onLoginFailure(String str);
    }
    interface IRegCallBack{
        void onLoginSuccess(String str);
        void onLoginFailure(String str);
    }
}
