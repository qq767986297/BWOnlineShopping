package com.bawei.bwonlineshopping.bean;

import java.util.List;

/**
 * Time: 2020/3/11
 * Author: 王冠华
 * Description:
 */
public class XRecyBean {

    private int commodityId;
    private String commodityName;
    private String masterPic;
    private double price;
    private int saleNum;
    int type;

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }

    public void setCommodityId(int commodityId) {
        this.commodityId = commodityId;
    }
    public int getCommodityId() {
        return commodityId;
    }

    public void setCommodityName(String commodityName) {
        this.commodityName = commodityName;
    }
    public String getCommodityName() {
        return commodityName;
    }

    public void setMasterPic(String masterPic) {
        this.masterPic = masterPic;
    }
    public String getMasterPic() {
        return masterPic;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    public double getPrice() {
        return price;
    }

    public void setSaleNum(int saleNum) {
        this.saleNum = saleNum;
    }
    public int getSaleNum() {
        return saleNum;
    }
}
