package com.bawei.bwonlineshopping.contract;

import com.bawei.bwonlineshopping.base.IBasView;

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

        void onListSuccess(String str);
        void onListFailure(String str);
        void getSerachSuccess(String str);
        void getSerachErr(String str);

    }
    //P层接口
    interface IPresenter{
        void getListData(String url);
        void getBanner(String url);
        void getSerach(String json);
    }
    //M层接口
    interface IModel{
        void onGetList(String url,ListCallBack listCallBack);
        void onGetBanner(String url,BannerCallBack bannerCallBack);
        void getSerach(String json,CallBackSerach callBackSerach);

    }
    //用于M层接口回调
    interface ListCallBack{
        void onSuccess(String str);
        void onFailure(String str);
    }
    interface BannerCallBack{
        void onSuccess(String str);
        void onFailure(String str);
    }
    interface CallBackSerach{
        void getSerachSuccess(String str);
        void getSerachErr(String str);
    }
}
