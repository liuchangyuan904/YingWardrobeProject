package com.ying.wardrobe.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;
import com.ying.wardrobe.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StatisticsFragment extends BaseFragment {
    private static final String TAG = "StatisticsFragment";
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {

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
    private void getStatistics() {

        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.UpdateUser, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据

        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: " + response.get().toString());
                JSONObject jsonObject = response.get();
                try {
                    if (jsonObject.getInt("status") == 0) {
                    } else {
                        Toast.makeText(getActivity(), "用户信息更新失败，请稍后再试！", Toast.LENGTH_SHORT).show();
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
}
