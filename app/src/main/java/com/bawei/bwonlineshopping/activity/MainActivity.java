package com.bawei.bwonlineshopping.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.base.BaseActivity;
import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.fragment.FragmentClub;
import com.bawei.bwonlineshopping.fragment.FragmentHead;
import com.bawei.bwonlineshopping.fragment.FragmentMine;
import com.bawei.bwonlineshopping.fragment.FragmentShop;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {

    ArrayList<Fragment> list = new ArrayList<>();
    private ViewPager vp;

    @Override
    protected BasePresenter initPresenter() {
        return null;
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_main;
    }

    @Override
    protected void initView() {
        vp = findViewById(R.id.vp);
    }

    @Override
    protected void initData() {

        FragmentClub club = new FragmentClub();
        FragmentHead head = new FragmentHead();
        FragmentMine mine = new FragmentMine();
        FragmentShop shop = new FragmentShop();
        list.add(head);
        list.add(club);
        list.add(shop);
        list.add(mine);

        MyViewPager pager = new MyViewPager(getSupportFragmentManager());
        vp.setAdapter(pager);
    }
    private class MyViewPager extends FragmentPagerAdapter {

        public MyViewPager(FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int i) {
            return list.get(i);
        }

        @Override
        public int getCount() {
            return list.size();
        }
    }
}
