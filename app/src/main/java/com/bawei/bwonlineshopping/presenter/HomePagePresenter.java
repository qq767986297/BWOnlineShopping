package com.bawei.bwonlineshopping.presenter;

import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.base.IBasView;
import com.bawei.bwonlineshopping.contract.IHomeContract;
import com.bawei.bwonlineshopping.model.HomePageModel;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class HomePagePresenter extends BasePresenter implements IHomeContract.IPresenter {

    private  HomePageModel model;

    public HomePagePresenter(IBasView iBasView) {
        super(iBasView);

    }

    @Override
    public void initModel() {
        model = new HomePageModel();
    }

    @Override
    public void getListData(String url) {
        model.onGetList(url, new IHomeContract.ListCallBack() {
            @Override
            public void onSuccess(String str) {
                IBasView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                        iView.onSuccess(str);
                }
            }

            @Override
            public void onFailure(String str) {
                IBasView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onFailure(str);
                }
            }
        });
    }

    @Override
    public void getBanner(String url) {
       model.onGetBanner(url, new IHomeContract.BannerCallBack() {
           @Override
           public void onSuccess(String str) {
               IBasView view = getView();
               if(view instanceof IHomeContract.IView){
                   IHomeContract.IView iView= (IHomeContract.IView) view;
                   iView.onSuccess(str);
               }
           }

           @Override
           public void onFailure(String str) {
               IBasView view = getView();
               if(view instanceof IHomeContract.IView){
                   IHomeContract.IView iView= (IHomeContract.IView) view;
                   iView.onFailure(str);
               }
           }
       });
    }

    @Override
    public void getSerach(String json) {
        IBasView view = getView();
        if(view instanceof IHomeContract.IView){
            IHomeContract.IView iView= (IHomeContract.IView) view;
            iView.onFailure(json);
        }
    }
}
