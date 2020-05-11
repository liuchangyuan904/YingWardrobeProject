package com.ying.wardrobe;

import android.content.Context;
import android.os.Bundle;

import com.ying.wardrobe.util.ActivityCollector;
import com.ying.wardrobe.util.StatusBarUtil;

import androidx.annotation.Nullable;
import androidx.fragment.app.FragmentActivity;

public abstract class BaseActivity extends FragmentActivity {
    public Context context;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        context = this;
        //activity管理
        ActivityCollector.addActivity(this);
        setStatusBar();
        //设置布局
        setContentView(initLayout());
        //初始化控件
        initView();
        //设置数据
        initData();
    }

    /**
     * 沉浸式状态栏
     */
    private void setStatusBar() {
        StatusBarUtil.setTransparent(this);

    }


    /**
     * 初始化布局
     *
     * @return 布局id
     */
    protected abstract int initLayout();

    /**
     * 初始化控件
     */
    protected abstract void initView();

    /**
     * 设置数据
     */
    protected abstract void initData();

    @Override
    protected void onDestroy() {
        super.onDestroy();
        ActivityCollector.removeActivity(this);
    }
}
