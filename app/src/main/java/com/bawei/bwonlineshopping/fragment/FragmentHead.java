package com.bawei.bwonlineshopping.fragment;

import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.support.v4.view.ViewPager;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.PopupWindow;
import android.widget.Toast;


import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.adapter.FirstAdapter;
import com.bawei.bwonlineshopping.adapter.MyAdapterFashoin;
import com.bawei.bwonlineshopping.adapter.MyAdapterHot;
import com.bawei.bwonlineshopping.base.BaseFragment;
import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.bean.BannerBean;
import com.bawei.bwonlineshopping.bean.DataBean;
import com.bawei.bwonlineshopping.bean.LinBanner;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bawei.bwonlineshopping.bean.SearchBean;
import com.bawei.bwonlineshopping.bean.ShopCarBean;
import com.bawei.bwonlineshopping.bean.XRecyBean;
import com.bawei.bwonlineshopping.contract.IHomeContract;
import com.bawei.bwonlineshopping.presenter.HomePagePresenter;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.jcodecraeer.xrecyclerview.XRecyclerView;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class FragmentHead extends BaseFragment implements IHomeContract.IView, View.OnClickListener {

    private XBanner xb;
    private RecyclerView rv_hot;
    private RecyclerView rv_fashion;
    private RecyclerView rv_life;
    private ArrayList<LinBanner> beans;
    private SQLiteDatabase db;
    ArrayList<DataBean.Data> list = new ArrayList<>();
    private BasePresenter presenter;
    private ImageView iv;
    private HomePagePresenter presenter1;
    private RecyclerView rv;
    private int mpage=1;
    ArrayList<XRecyBean> data = new ArrayList<>();
    private MyAdapterHot hot;


    @Override
    protected BasePresenter initPresenter() {
        return new HomePagePresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.head;

    }

    @Override
    protected void initView(View view) {
        xb = view.findViewById(R.id.xb);
        rv_hot = view.findViewById(R.id.rv_hot);
        rv_fashion = view.findViewById(R.id.rv_fashion);
        rv_life = view.findViewById(R.id.rv_life);
        iv = view.findViewById(R.id.second);



    }


    @Override
    protected void initData() {
//        String bannerpath="http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
//        String listpath="http://mobile.bwstudent.com/small/commodity/v1/commodityList";
//        String url="http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword";
//        url=url+"?keyword="+"1"+"&page="+mpage+"&count=10";
        presenter = getPresenter();
        if(presenter!=null&& presenter instanceof IHomeContract.IPresenter){
            ((IHomeContract.IPresenter)presenter).onBanner();
            ((IHomeContract.IPresenter)presenter).onList();

        }
//        iv.setOnClickListener(this);
    }

    @Override
    public void onSuccess(String str) {
//        Gson gson = new Gson();
//        BannerBean bannerBean = gson.fromJson(str, BannerBean.class);
//        final List<BannerBean.ResultBean> result = bannerBean.getResult();
//        xb.setBannerData(result);
//        Log.i("mmmm",result.size()+"");
//        xb.loadImage(new XBanner.XBannerAdapter() {
//            @Override
//            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                BannerBean.ResultBean bean = result.get(position);
//                String imageUrl = bean.getImageUrl();
//                Log.i("aaaa",""+imageUrl);
//                Glide.with(getContext()).load(result.get(position).getImageUrl()).into((ImageView) view);
//            }
//        });
    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    public void onGetBanner(final BannerBean bannerBean) {
       xb.setBannerData(bannerBean.getResult());
       xb.loadImage(new XBanner.XBannerAdapter() {
           @Override
           public void loadBanner(XBanner banner, Object model, View view, int position) {
               List<BannerBean.ResultBean> result = bannerBean.getResult();
               BannerBean.ResultBean bean = result.get(position);
               String imageUrl = bean.getImageUrl();
               Log.i("xxx",imageUrl);
               Glide.with(getActivity()).load(imageUrl).into((ImageView) view);
           }
       });

    }

    @Override
    public void onGetList(ListBean listBean) {
        ListBean.ResultBean result = listBean.getResult();
        ListBean.ResultBean.RxxpBean rxxp = result.getRxxp();
        List<ListBean.ResultBean.RxxpBean.CommodityListBean> rxlist = rxxp.getCommodityList();
        LinearLayoutManager manager1 = new LinearLayoutManager(getActivity(), RecyclerView.HORIZONTAL, false);
        rv_hot.setLayoutManager(manager1);
        MyAdapterHot hot = new MyAdapterHot(getActivity(), rxlist);
        rv_hot.setAdapter(hot);
        ListBean.ResultBean.MlssBean mlss = result.getMlss();
        List<ListBean.ResultBean.MlssBean.CommodityListBeanXX> mllist = mlss.getCommodityList();
        LinearLayoutManager manager2 = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        rv_fashion.setLayoutManager(manager2);
        MyAdapterFashoin fashoin = new MyAdapterFashoin(getActivity(), mllist);
        rv_fashion.setAdapter(fashoin);
        GridLayoutManager manager3 = new GridLayoutManager(getActivity(), 2);
        rv_life.setLayoutManager(manager3);
        MyAdapterHot hott = new MyAdapterHot(getActivity(), rxlist);
        rv_life.setAdapter(hott);
    }

    @Override
    public void onShopCarSuccess(ShopCarBean shopCarBean) {

    }

    @Override
    public void onShopCarFailure(String str) {

    }


    private void initPopWindow(){
        View view = LayoutInflater.from(getContext()).inflate(R.layout.popitem, null, false);
        rv = view.findViewById(R.id.second_rv);
        LinearLayoutManager manager = new LinearLayoutManager(getContext(), LinearLayoutManager.HORIZONTAL, false);
        rv.setLayoutManager(manager);
        ArrayList<String> list = new ArrayList<>();
        list.add("男装");
        list.add("女装");
        list.add("女鞋");
        list.add("T恤");
        list.add("美妆");
        list.add("手机");
        FirstAdapter adapter = new FirstAdapter(getContext(), list);
        rv.setAdapter(adapter);
        view.setAlpha(0.5f);
        PopupWindow popupWindow = new PopupWindow(view, ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
        popupWindow.setAnimationStyle(R.anim.anim_pop);
        popupWindow.setTouchable(true);
        popupWindow.setTouchInterceptor(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                return false;
            }
        });
        popupWindow.setBackgroundDrawable(new ColorDrawable(Color.WHITE));
        popupWindow.showAsDropDown(view,0,150);
        adapter.setOnItemClickLinster(new FirstAdapter.setOnItemClickLinster() {
            @Override
            public void onClick(int posion) {
                Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
                View view1 = LayoutInflater.from(getContext()).inflate(R.layout.manpop, null, false);
                view1.setAlpha(0.8f);
                PopupWindow popupWindow1 = new PopupWindow(view1, ViewPager.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT, true);
                popupWindow1.setAnimationStyle(R.anim.anim_pop);

                popupWindow1.setTouchable(true);
                popupWindow1.setTouchInterceptor(new View.OnTouchListener() {
                    @Override
                    public boolean onTouch(View v, MotionEvent event) {
                        return false;
                    }
                });
                popupWindow1.setBackgroundDrawable(new ColorDrawable(Color.GRAY));
                popupWindow1.showAsDropDown(view1,0,350);
            }
        });
    }
    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.second:
                Toast.makeText(getContext(), "点击了", Toast.LENGTH_SHORT).show();
                    initPopWindow();
                break;
        }
    }
}
