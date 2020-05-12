package com.ying.wardrobe.activity;

import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.Constants;
import com.ying.wardrobe.R;
import com.ying.wardrobe.okhttp.OkHttpUtils;
import com.ying.wardrobe.okhttp.callback.Callback;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import okhttp3.Call;
import okhttp3.Response;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "LoginActivity";
    private TextView registerTextView,loginTextView;
    private EditText userEditText,passwordEditText;
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
        registerTextView=findViewById(R.id.registerTextView);
        loginTextView=findViewById(R.id.loginTextView);
        userEditText=findViewById(R.id.userEditText);
        passwordEditText=findViewById(R.id.passwordEditText);
        registerTextView.setOnClickListener(this);
        loginTextView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View view) {
        switch (view.getId()){
            case R.id.registerTextView:
                if (TextUtils.isEmpty(userEditText.getText().toString().trim())||TextUtils.isEmpty(passwordEditText.getText().toString().trim())){
                    Toast.makeText(this,"账号密码不要忘记输入哦！",Toast.LENGTH_LONG).show();
                }
                OkHttpUtils.post()
//                        .url("https://www.baidu.com")
                        .url(Constants.BaseUrl+"api/user/register")
                        .addParams("username",userEditText.getText().toString().trim())
                        .addParams("password",passwordEditText.getText().toString().trim())
                        .build().execute(new Callback() {
                    @Override
                    public Object parseNetworkResponse(Response response, int id) throws Exception {
                        return null;
                    }

                    @Override
                    public void onError(Call call, Exception e, int id) {
                        Log.d(TAG, "onError: "+e.toString());
                    }

                    @Override
                    public void onResponse(Object response, int id) {
                        Log.d(TAG, "onResponse: "+response.toString());
                        Intent intent=new Intent();
                        intent.setClass(LoginActivity.this,MainActivity.class);
                        startActivity(intent);
                        LoginActivity.this.finish();
                    }
                });

                break;
            case R.id.loginTextView:
                Intent loginIntent=new Intent();
                loginIntent.setClass(this,MainActivity.class);
                startActivity(loginIntent);
                this.finish();
                break;
        }
    }
}
