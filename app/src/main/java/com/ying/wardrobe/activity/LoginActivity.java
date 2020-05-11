package com.ying.wardrobe.activity;

import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class LoginActivity extends BaseActivity implements View.OnClickListener {
    private TextView registerTextView,loginTextView;
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
                Intent intent=new Intent();
                intent.setClass(this,MainActivity.class);
                startActivity(intent);
                this.finish();
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
