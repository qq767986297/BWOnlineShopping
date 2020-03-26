package com.bawei.bwonlineshopping.model;

import com.bawei.bwonlineshopping.bean.BannerBean;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bawei.bwonlineshopping.bean.ShopCarBean;
import com.bawei.bwonlineshopping.contract.IHomeContract;
import com.bawei.bwonlineshopping.utils.RetroiftManger;
import com.bawei.bwonlineshopping.utils.VolleyUtils;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class HomePageModel implements IHomeContract.IModel {

    @Override
    public void onGetBanner(final IHomeContract.BannerCallBack bannerCallBack) {
        RetroiftManger.getInstance().getApis().getBanner()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<BannerBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(BannerBean bannerBean) {
                        if(bannerCallBack!=null){
                            bannerCallBack.onSuccess(bannerBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(bannerCallBack!=null){
                            bannerCallBack.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });

    }

    @Override
    public void onGetList(final IHomeContract.ListCallBack listCallBack) {
        RetroiftManger.getInstance().getApis().getonList()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ListBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ListBean listBean) {
                        if(listCallBack!=null){
                            listCallBack.onSuccess(listBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(listCallBack!=null){
                            listCallBack.onFailure(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onGetShopCar(final IHomeContract.CallBackShopCar callBackShopCar) {
        RetroiftManger.getInstance().getApis().getShopCar()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<ShopCarBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(ShopCarBean shopCarBean) {
                        if(callBackShopCar!=null){
                            callBackShopCar.getShopCarSuccess(shopCarBean);
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        if(callBackShopCar!=null){
                            callBackShopCar.getShopCarErr(e.getMessage());
                        }
                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }


}
