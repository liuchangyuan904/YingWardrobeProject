package com.ying.wardrobe.activity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private TextView registerTextView, loginTextView;
    private EditText userEditText, passwordEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Override
    protected int initLayout() {
        return R.layout.activity_login;
    }

    @Override
    protected void initView() {
        registerTextView = findViewById(R.id.registerTextView);
        loginTextView = findViewById(R.id.loginTextView);
        userEditText = findViewById(R.id.userEditText);
        passwordEditText = findViewById(R.id.passwordEditText);
        registerTextView.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * 注册/登录
     * @param username
     * @param password
     */
    private void tryToRegister(final String username, final String password, final boolean isLogin) {
        String postUrl;
        if (isLogin){
            postUrl= HttpUtil.HOST + "api/user/login";
        }else {
            postUrl= HttpUtil.HOST + "api/user/register";
        }

        //1.创建一个队列
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(postUrl, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("username", username);
        request.add("password", password);

        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: "+response.toString());
                JSONObject res = response.get();
                try {
                    if (res.getInt("status") == 0) {
                        JSONObject data = res.getJSONObject("data");
                        if (isLogin){
                            Toast.makeText(getApplicationContext(), "欢迎登录：" + username, Toast.LENGTH_SHORT).show();
                        }else {
                            Toast.makeText(getApplicationContext(), "成功注册用户：" + username, Toast.LENGTH_SHORT).show();
                        }
                        //跳转到商品购买页面
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                        finish();
                    } else {
                        Toast.makeText(getApplicationContext(), res.getString("msg"), Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
            }

            @Override
            public void onFinish(int what) {

            }
        });

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.registerTextView:
                if (TextUtils.isEmpty(userEditText.getText().toString().trim()) || TextUtils.isEmpty(passwordEditText.getText().toString().trim())) {
                    Toast.makeText(this, "账号密码不要忘记输入哦！", Toast.LENGTH_LONG).show();
                }
                tryToRegister(userEditText.getText().toString().trim(), passwordEditText.getText().toString().trim(),false);
                break;
            case R.id.loginTextView:
                tryToRegister(userEditText.getText().toString().trim(), passwordEditText.getText().toString().trim(),true);
                break;
        }
    }
}
