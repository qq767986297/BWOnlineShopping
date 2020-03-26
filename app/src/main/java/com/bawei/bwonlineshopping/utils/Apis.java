package com.bawei.bwonlineshopping.utils;

import com.bawei.bwonlineshopping.bean.BannerBean;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bawei.bwonlineshopping.bean.ShopCarBean;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Time: 2020/3/25
 * Author: 王冠华
 * Description:
 */
public interface Apis {
    //轮播方法
    @GET("commodity/v1/bannerShow")
    Observable<BannerBean> getBanner();
    //列表方法
    @GET("commodity/v1/commodityList")
    Observable<ListBean> getonList();
    //购物车
    @GET("order/verify/v1/findShoppingCart")
    Observable<ShopCarBean> getShopCar();
}
