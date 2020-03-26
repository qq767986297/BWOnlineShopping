package com.bawei.bwonlineshopping.contract;

import com.bawei.bwonlineshopping.base.IBasView;
import com.bawei.bwonlineshopping.bean.BannerBean;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bawei.bwonlineshopping.bean.ShopCarBean;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public interface IHomeContract {
    interface IView extends IBasView {
        //成功和失败两个方法
        void onSuccess(String str);
        void onFailure(String str);
        void onGetBanner(BannerBean bannerBean);
        void onGetList(ListBean listBean);
        void onShopCarSuccess(ShopCarBean shopCarBean);
        void onShopCarFailure(String str);
    }
    //P层接口
    interface IPresenter{
        void onBanner();
        void onList();
        void onShopCar();
    }
    //M层接口
    interface IModel{
        void onGetBanner(BannerCallBack bannerCallBack);
        void onGetList(ListCallBack listCallBack);
        void onGetShopCar(CallBackShopCar callBackShopCar);
    }
    //用于M层接口回调
    interface ListCallBack{
        void onSuccess(ListBean listBean);
        void onFailure(String str);
    }
    interface BannerCallBack{
        void onSuccess(BannerBean bannerBean);
        void onFailure(String str);
    }
    interface CallBackSerach{
        void getSerachSuccess(String str);
        void getSerachErr(String str);
    }
    interface CallBackShopCar{
        void getShopCarSuccess(ShopCarBean shopCarBean);
        void getShopCarErr(String str);
    }
}
