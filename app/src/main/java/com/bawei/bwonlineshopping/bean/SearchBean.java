package com.bawei.bwonlineshopping.bean;

import java.util.List;

/**
 * Time: 2020/3/11
 * Author: 王冠华
 * Description:
 */
public class SearchBean {
    String message;
    String status;
    List<XRecyBean> result;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<XRecyBean> getResult() {
        return result;
    }

    public void setResult(List<XRecyBean> result) {
        this.result = result;
    }
}
