package com.ying.wardrobe;

import android.os.Bundle;

import com.ying.wardrobe.util.StatusBarUtil;

import androidx.fragment.app.FragmentActivity;

public class BaseActivity extends FragmentActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setStatusBar();
    }

    /**
     * 沉浸式状态栏
     */
    private void setStatusBar() {
        StatusBarUtil.setTransparent(this);

    }
}
