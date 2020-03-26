package com.bawei.bwonlineshopping.activity;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.bawei.bwonlineshopping.R;
import com.bawei.bwonlineshopping.base.BaseActivity;
import com.bawei.bwonlineshopping.base.BasePresenter;
import com.bawei.bwonlineshopping.bean.LoginBean;
import com.bawei.bwonlineshopping.contract.ILoginContract;
import com.bawei.bwonlineshopping.presenter.LoginPresenter;
import com.bawei.bwonlineshopping.utils.SPUtils;
import com.google.gson.Gson;

import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class LoginActivity extends BaseActivity implements ILoginContract.ILoginView, View.OnClickListener {


    private CheckBox cb;
    private EditText mphone;
    private EditText mpwd;
    private TextView tv_reg;
    private Button bt_login;
    private LoginPresenter presenter;

    @Override
    protected BasePresenter initPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    protected int getLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        mphone = findViewById(R.id.login_phone);
        mpwd = findViewById(R.id.login_pass);
        cb = findViewById(R.id.cb);
        tv_reg = findViewById(R.id.tv_reg);
        bt_login = findViewById(R.id.bt_login);
    }

    @Override
    protected void initData() {
        bt_login.setOnClickListener(this);
        tv_reg.setOnClickListener(this);

    }
    //正则校验
    private static Boolean matchPhone(String str){
        Pattern pattern = Pattern.compile("^1\\d{10}$");
        Matcher matcher = pattern.matcher(str);
        return matcher.matches();
    }
    private void doLogin(){
        String phone = mphone.getText().toString();
        String pwd = mpwd.getText().toString();
        if(!TextUtils.isEmpty(phone)&&matchPhone(phone)){
            if(!TextUtils.isEmpty(pwd)){
                presenter = new LoginPresenter(this);
                HashMap<String, String> map = new HashMap<>();
                map.put("phone",phone);
                map.put("pwd",pwd);
                String path="http://mobile.bwstudent.com/small/user/v1/login";
                presenter.onLogin(path,map,new HashMap<String, String>());
            }else {
                Toast.makeText(this, "密码输入有误", Toast.LENGTH_SHORT).show();
            }
        }else {
            Toast.makeText(this, "手机号输入有误", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onLoginSuccess(String str) {
        Gson gson = new Gson();
        LoginBean bean = gson.fromJson(str, LoginBean.class);
        String message = bean.getMessage();
        Toast.makeText(this, ""+message, Toast.LENGTH_SHORT).show();
        LoginBean.LGBean result=bean.getResult();
        String id = result.getSessionId();
        int userId = result.getUserId();
        SPUtils.putInt(this,"login","userId",userId);
        SPUtils.putString(this,"login","sessionId",id);
        SPUtils.puttBoolean(this,"login","boo",cb.isChecked());
        if(cb.isChecked()){
            SPUtils.putString(this,"login","phone",mphone.getText().toString());
            SPUtils.putString(this,"login","pwd",mpwd.getText().toString());
        }
        if(message.equals("登录成功")){
            Intent intent = new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);

        }
    }

    @Override
    public void onLoginFailure(String str) {

    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        switch (id){
            case R.id.tv_reg:
                Intent intent = new Intent(LoginActivity.this, RegActivity.class);
                startActivity(intent);
                break;
            case R.id.bt_login:
                doLogin();
                break;
        }
    }
}
