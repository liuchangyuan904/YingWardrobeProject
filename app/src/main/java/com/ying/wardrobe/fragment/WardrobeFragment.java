package com.ying.wardrobe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.wildma.pictureselector.PictureSelector;
import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class WardrobeFragment extends BaseFragment implements View.OnClickListener{
    private TextView addYifu;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_wardrobe;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        addYifu=view.findViewById(R.id.addYifu);
        addYifu.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    /**
     * 初始化View
     * @param view
     */
    private void initView(View view) {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.addYifu:
                PictureSelector
                        .create(this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false, 200, 200, 1, 1);
                break;
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
            }
        }
    }
}
