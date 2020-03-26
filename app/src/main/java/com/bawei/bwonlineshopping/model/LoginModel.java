package com.bawei.bwonlineshopping.model;

import com.bawei.bwonlineshopping.contract.ILoginContract;
import com.bawei.bwonlineshopping.utils.VolleyUtils;

import java.util.HashMap;

/**
 * Time: 2020/3/6
 * Author: 王冠华
 * Description:
 */
public class LoginModel implements ILoginContract.ILoginModel {
    @Override
    public void onLogin(String url, HashMap<String, String> map, HashMap<String, String> head, final ILoginContract.ILoginCallBack iLoginCallBack) {
        VolleyUtils.getInstance().doPost(url, map, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
                iLoginCallBack.onLoginSuccess(json);
            }

            @Override
            public void onFailure(String msg) {
                iLoginCallBack.onLoginFailure(msg);
            }
        });
    }

    @Override
    public void onReg(String url, HashMap<String, String> map, final ILoginContract.IRegCallBack iRegCallBack) {
        VolleyUtils.getInstance().doPost(url, map, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
               iRegCallBack.onLoginSuccess(json);
            }

            @Override
            public void onFailure(String msg) {
                iRegCallBack.onLoginFailure(msg);
            }
        });
    }
}
