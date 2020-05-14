package com.ying.wardrobe.view;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;

import com.wang.avi.AVLoadingIndicatorView;
import com.ying.wardrobe.R;

/**
 * description:自定义dialog
 */

public class CommonDialog extends Dialog {

    public CommonDialog(Context context) {
        super(context, R.style.CustomDialog);
    }
    private AVLoadingIndicatorView loadingView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.common_dialog_layout);
        //按空白处不能取消动画
        setCanceledOnTouchOutside(false);
        loadingView=findViewById(R.id.loadingView);
    }

    @Override
    public void show() {
        super.show();
        loadingView.show();
    }

    @Override
    public void dismiss() {
        super.dismiss();
        loadingView.hide();
    }
}
