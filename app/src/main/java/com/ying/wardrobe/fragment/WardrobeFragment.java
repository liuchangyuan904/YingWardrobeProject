package com.ying.wardrobe.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WardrobeFragment extends BaseFragment {
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_wardrobe, container, false);
        initView(view);
        return view;
    }

    /**
     * 初始化View
     * @param view
     */
    private void initView(View view) {

    }
}
