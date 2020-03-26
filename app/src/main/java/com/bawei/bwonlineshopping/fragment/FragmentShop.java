package com.bawei.bwonlineshopping.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.CheckBox;
import android.widget.TextView;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.adapter.ShopCarAdapter;
import com.bawei.bwonlineshopping.base.BaseFragment;
import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.bean.BannerBean;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bawei.bwonlineshopping.bean.ShopCarBean;
import com.bawei.bwonlineshopping.contract.IHomeContract;
import com.bawei.bwonlineshopping.presenter.HomePagePresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.List;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class FragmentShop extends BaseFragment implements IHomeContract.IView {

    private RecyclerView rv;
    private CheckBox cb;
    private TextView price;
    private ShopCarAdapter adapter;

    @Override
    protected BasePresenter initPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.shop;
    }

    @Override
    protected void initView(View view) {
        rv = view.findViewById(R.id.shop_rv);
        cb = view.findViewById(R.id.shop_cb);
        price = view.findViewById(R.id.shop_price);
        cb.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                adapter.notifyDataSetChanged();

            }
        });
    }

    @Override
    protected void initData() {
        BasePresenter presenter = getPresenter();
        if(presenter instanceof IHomeContract.IPresenter){
            ((IHomeContract.IPresenter)presenter).onShopCar();
        }
    }
    @Override
    public void onShopCarSuccess(ShopCarBean shopCarBean) {
        List<ShopCarBean.ResultBean> result = shopCarBean.getResult();
        ShopCarBean.ResultBean bean = result.get(0);
        List<ShopCarBean.ResultBean.ShoppingCartListBean> list = bean.getShoppingCartList();
        LinearLayoutManager manager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv.setLayoutManager(manager);
        adapter = new ShopCarAdapter(getActivity(), list);
        rv.setAdapter(adapter);

    }

    @Override
    public void onShopCarFailure(String str) {

    }
    @Override
    public void onSuccess(String str) {

    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    public void onGetBanner(BannerBean bannerBean) {

    }

    @Override
    public void onGetList(ListBean listBean) {

    }


}
