package com.bawei.bwonlineshopping.utils;

import com.android.volley.AuthFailureError;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.bawei.bwonlineshopping.base.App;

import java.util.HashMap;
import java.util.Map;

/**
 * Time: 2020/3/3
 * Author: 王冠华
 * Description:
 */
public class VolleyUtils {

    private final RequestQueue queue;

    public VolleyUtils() {
        queue = Volley.newRequestQueue(App.getAppContext());
    }
    private static class SingleInstance{
        private static final VolleyUtils INSTANCE=new VolleyUtils();
    }
    public static VolleyUtils getInstance(){
        return SingleInstance.INSTANCE;
    }
    //get请求
    public void doGet(String path, final ICallBack iCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.GET, path, new Response.Listener<String>() {
            @Override
            public void onResponse(String response) {
                iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.onFailure(error.getMessage());
            }
        });
        queue.add(stringRequest);
    }
    //post
    public void doPost(String url, final HashMap<String,String>map, final ICallBack iCallBack){
        StringRequest stringRequest = new StringRequest(Request.Method.POST, url, new Response.Listener<String>() {
            @Override
            public void    onResponse(String response) {
                iCallBack.onSuccess(response);
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                iCallBack.onFailure(error.getMessage());
            }
        }) {
            @Override
            protected Map<String, String> getParams() {
                return map;
            }

        };
        queue.add(stringRequest);
    }
    //创建接口
    public interface ICallBack{
        void onSuccess(String json);
        void onFailure(String msg);
    }
}
