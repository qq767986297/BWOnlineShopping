package com.bawei.bwonlineshopping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.base.BaseActivity;
import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.bean.RegBean;
import com.bawei.bwonlineshopping.contract.ILoginContract;
import com.bawei.bwonlineshopping.presenter.LoginPresenter;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class RegActivity extends BaseActivity implements ILoginContract.ILoginView {


    private Button bt;
    private TextView tv_login;
    private EditText pass;
    private EditText verify;
    private EditText name;
    private BasePresenter presenter;
    private LoginPresenter presenter1;

    @Override
    protected BasePresenter initPresenter() {
        presenter1 = new LoginPresenter(this);
        return presenter1;
    }

    @Override
    protected int getLayout() {
        return R.layout.reg;
    }

    @Override
    protected void initView() {
        bt = findViewById(R.id.bt_reg);
        name = findViewById(R.id.reg_name);
        verify = findViewById(R.id.reg_verify);
        pass = findViewById(R.id.reg_pass);
        tv_login = findViewById(R.id.tv_login);
    }
    //校验
    private static boolean machPhone(String str){
        Pattern pattern = Pattern.compile("^1\\d{10}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    @Override
    protected void initData() {
        bt.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                String phone = name.getText().toString();
                String pwd = pass.getText().toString();
                Log.i("xxx",phone);
                if(!TextUtils.isEmpty(phone)&&machPhone(phone)){
                    if(!TextUtils.isEmpty(pwd)){
                        presenter = getPresenter();
                            HashMap<String, String> map = new HashMap<>();
                            map.put("phone",phone);
                            map.put("pwd",pwd);
                            String path = "http://mobile.bwstudent.com/small/user/v1/register";
                            Log.i("xxx",path);
                           presenter1.onReg(path,map);
                    }else {
                        Toast.makeText(RegActivity.this, "密码输入有误", Toast.LENGTH_SHORT).show();
                    }
                }else {
                    Toast.makeText(RegActivity.this, "手机号输入有误", Toast.LENGTH_SHORT).show();
                }
            }
        });
        tv_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(RegActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });
    }

    @Override
    public void onLoginSuccess(String str) {
        Gson gson = new Gson();
        RegBean regBean = gson.fromJson(str, RegBean.class);
        String message = regBean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onLoginFailure(String str) {

    }
}
