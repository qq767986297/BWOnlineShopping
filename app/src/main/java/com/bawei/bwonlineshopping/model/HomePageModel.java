package com.bawei.bwonlineshopping.model;

import com.bawei.bwonlineshopping.contract.IHomeContract;
import com.bawei.bwonlineshopping.utils.VolleyUtils;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class HomePageModel implements IHomeContract.IModel {
    @Override
    public void onGetList(String url, final IHomeContract.ListCallBack listCallBack) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
                listCallBack.onSuccess(json);
            }

            @Override
            public void onFailure(String msg) {
                listCallBack.onFailure(msg);
            }
        });
    }

    @Override
    public void onGetBanner(String url, final IHomeContract.BannerCallBack bannerCallBack) {
        VolleyUtils.getInstance().doGet(url, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
                bannerCallBack.onSuccess(json);
            }

            @Override
            public void onFailure(String msg) {
                bannerCallBack.onFailure(msg);
            }
        });
    }

    @Override
    public void getSerach(String json, final IHomeContract.CallBackSerach callBackSerach) {
        VolleyUtils.getInstance().doGet(json, new VolleyUtils.ICallBack() {
            @Override
            public void onSuccess(String json) {
                callBackSerach.getSerachSuccess(json);
            }

            @Override
            public void onFailure(String msg) {
                callBackSerach.getSerachErr(msg);
            }
        });
    }
}
