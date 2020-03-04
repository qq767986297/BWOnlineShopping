package com.bawei.bwonlineshopping.bean;

import com.stx.xhb.xbanner.entity.SimpleBannerInfo;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class LinBanner extends SimpleBannerInfo {
    String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public LinBanner(String url) {
        this.url = url;
    }

    @Override
    public Object getXBannerUrl() {
        return null;
    }
}
