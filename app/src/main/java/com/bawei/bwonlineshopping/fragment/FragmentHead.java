package com.bawei.bwonlineshopping.fragment;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.adapter.MyAdapterFashoin;
import com.bawei.bwonlineshopping.adapter.MyAdapterHot;
import com.bawei.bwonlineshopping.base.BaseFragment;
import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.bean.BannerBean;
import com.bawei.bwonlineshopping.bean.DataBean;
import com.bawei.bwonlineshopping.bean.LinBanner;
import com.bawei.bwonlineshopping.bean.ListBean;
import com.bawei.bwonlineshopping.contract.IHomeContract;
import com.bawei.bwonlineshopping.customview.CustomViewGroup;
import com.bawei.bwonlineshopping.customview.FlowLayout;
import com.bawei.bwonlineshopping.presenter.HomePagePresenter;
import com.bawei.bwonlineshopping.sqlite.DBHelper;
import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.stx.xhb.xbanner.XBanner;

import java.util.ArrayList;
import java.util.List;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class FragmentHead extends BaseFragment implements IHomeContract.IView {

    private XBanner xb;
    private RecyclerView rv_hot;
    private RecyclerView rv_fashion;
    private ArrayList<LinBanner> beans;
    private CustomViewGroup cvg;
    private FlowLayout fl;
    private SQLiteDatabase db;
    ArrayList<DataBean.Data> list = new ArrayList<>();
    private BasePresenter presenter;

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
        cvg = view.findViewById(R.id.cvg);
        fl = view.findViewById(R.id.fl);
    }

    @Override
    protected void initData() {
        String bannerpath="http://mobile.bwstudent.com/small/commodity/v1/bannerShow";
        String listpath="http://mobile.bwstudent.com/small/commodity/v1/commodityList";
        presenter = getPresenter();
//        if(presenter!=null&&presenter instanceof BasePresenter){
//            ((HomePagePresenter)presenter).getBanner(bannerpath);
//            ((HomePagePresenter)presenter).getListData(listpath);
//        }
        HomePagePresenter presenter1 = new HomePagePresenter(this);
        presenter1.getListData(listpath);
        presenter1.getBanner(bannerpath);

        //创建数据库
        DBHelper helper = new DBHelper(getContext());
        db = helper.getWritableDatabase();
        Cursor cursor = db.query("shop", null, null, null, null, null, null);
        while(cursor.moveToNext()) {
            DataBean.Data data = new DataBean.Data();
            String name=cursor.getString(cursor.getColumnIndex("name"));
            data.setName(name);
           list.add(data);
        }
        cvg.setOnClick(new CustomViewGroup.OnSouClickListener() {
            @Override
            public void onSou(String str) {
                String url = "http://mobile.bwstudent.com/small/commodity/v1/findCommodityByKeyword";
                url = url + "?keyword=" + str +"&page=1&count=5";
                Log.i("eee",""+url);
                if(presenter !=null && presenter instanceof HomePagePresenter){ ;
                    ((HomePagePresenter) presenter).getSerach(url);
                }
                db.delete("shop",null,null);
                DataBean.Data data = new DataBean.Data();
                data.setName(str);
                list.add(data);


                for(int i=0;i<list.size();i++){

                    DataBean.Data data1 = list.get(i);
                    String name = data1.getName();
                    ContentValues values = new ContentValues();
                    values.put("name", name);
                    db.insert("shop", null, values);
                }
                list.clear();
                Cursor cursor = db.query("shop", null, null, null, null, null, null);
                while (cursor.moveToNext()){
                    DataBean.Data data1 = new DataBean.Data();
                    String name = cursor.getString(cursor.getColumnIndex("name"));
                    data1.setName(name);
                    list.add(data1);
                }
                fl.removeAllViews();
                for (int i=0;i<list.size();i++){
                    LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT);
                    layoutParams.setMargins(10,5,10,5);
                    TextView view = new TextView(getContext());
                    view.setPadding(20, 10, 20, 10);
                    view.setText(list.get(i).getName());
                    view.setLayoutParams(layoutParams);
                    fl.addView(view, layoutParams);
                }
            }
        });
    }

    @Override
    public void onSuccess(String str) {
//        Log.i("xxx",str);
//        Gson gson = new Gson();
//        BannerBean bean = gson.fromJson(str,BannerBean.class);
//        final List<BannerBean.ResultBean> result = bean.getResult();
//        BannerBean bannerBean = new BannerBean();
//        beans = new ArrayList<>();
//
//        for(int i=0;i<result.size();i++){
//            LinBanner banner = new LinBanner(result.get(i).getImageUrl());
//            beans.add(banner);
//        }
//        xb.setBannerData(beans);
//        xb.loadImage(new XBanner.XBannerAdapter() {
//            @Override
//            public void loadBanner(XBanner banner, Object model, View view, int position) {
//                LinBanner banner1 = beans.get(position);
//                String url = banner1.getUrl();
//                Glide.with(getContext()).load(url).into((ImageView) view);
//            }
//        });
    }

    @Override
    public void onFailure(String str) {

    }

    @Override
    public void onListSuccess(String str) {
//        Gson gson = new Gson();
//        ListBean bean = gson.fromJson(str, ListBean.class);
//        ListBean.ResultBean result = bean.getResult();
//        ListBean.ResultBean.RxxpBean rxxp = result.getRxxp();
//        List<ListBean.ResultBean.RxxpBean.CommodityListBean> hotlist = rxxp.getCommodityList();
//        LinearLayoutManager manager = new LinearLayoutManager(getContext(), RecyclerView.HORIZONTAL, false);
//        rv_hot.setLayoutManager(manager);
//        MyAdapterHot hot = new MyAdapterHot(getContext(), hotlist);
//        rv_hot.setAdapter(hot);
    }

    @Override
    public void onListFailure(String str) {

    }

    @Override
    public void getSerachSuccess(String str) {

    }

    @Override
    public void getSerachErr(String str) {

    }
}
