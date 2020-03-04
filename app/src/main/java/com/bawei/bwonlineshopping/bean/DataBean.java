package com.bawei.bwonlineshopping.bean;

import java.util.List;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class DataBean {
    public List<Data> data;
    public static class Data{
        public String name;

        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }
    }
}
