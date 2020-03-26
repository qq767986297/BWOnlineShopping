package com.bawei.bwonlineshopping.presenter;

import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.base.IBasView;
import com.bawei.bwonlineshopping.bean.BannerBean;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bawei.bwonlineshopping.bean.ShopCarBean;
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
    public void onBanner() {
        model.onGetBanner(new IHomeContract.BannerCallBack() {
            @Override
            public void onSuccess(BannerBean bannerBean) {
                IBasView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onGetBanner(bannerBean);
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
    public void onList() {
        model.onGetList(new IHomeContract.ListCallBack() {
            @Override
            public void onSuccess(ListBean listBean) {
                IBasView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onGetList(listBean);
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
    public void onShopCar() {
        model.onGetShopCar(new IHomeContract.CallBackShopCar() {
            @Override
            public void getShopCarSuccess(ShopCarBean shopCarBean) {
                IBasView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onShopCarSuccess(shopCarBean);
                }
            }

            @Override
            public void getShopCarErr(String str) {
                IBasView view = getView();
                if(view instanceof IHomeContract.IView){
                    IHomeContract.IView iView= (IHomeContract.IView) view;
                    iView.onShopCarFailure(str);
                }
            }
        });
    }


}
