package com.bawei.bwonlineshopping.presenter;

import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.base.IBasView;
import com.bawei.bwonlineshopping.contract.ILoginContract;
import com.bawei.bwonlineshopping.model.LoginModel;

import java.util.HashMap;

/**
 * Time: 2020/3/6
 * Author: 王冠华
 * Description:
 */
public class LoginPresenter extends BasePresenter implements ILoginContract.ILoginPresenter {

    private LoginModel model;

    public LoginPresenter(IBasView iBasView) {
        super(iBasView);
    }

    @Override
    public void initModel() {
        model = new LoginModel();
    }

    @Override
    public void onLogin(String url, HashMap<String, String> map, HashMap<String, String> head) {
        model.onLogin(url, map, head, new ILoginContract.ILoginCallBack() {
            @Override
            public void onLoginSuccess(String str) {
                IBasView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onLoginSuccess(str);
                }
            }

            @Override
            public void onLoginFailure(String str) {
                IBasView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onLoginFailure(str);
                }
            }
        });
    }

    @Override
    public void onReg(String url, HashMap<String, String> map) {
        model.onReg(url, map, new ILoginContract.IRegCallBack() {
            @Override
            public void onLoginSuccess(String str) {
                IBasView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onLoginSuccess(str);
                }
            }

            @Override
            public void onLoginFailure(String str) {
                IBasView view = getView();
                if(view instanceof ILoginContract.ILoginView){
                    ILoginContract.ILoginView iView= (ILoginContract.ILoginView) view;
                    iView.onLoginFailure(str);
                }
            }
        });
    }
}
