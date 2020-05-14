package com.ying.wardrobe.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;
import com.ying.wardrobe.activity.EditUserInfoActivity;
import com.ying.wardrobe.entity.UserInfoEntity;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;

import org.json.JSONObject;

public class MineFragment extends BaseFragment implements View.OnClickListener{
    private static final String TAG = "MineFragment";
    private TextView userNameTextView,ageTextView,heightTextView,weightTextView,editTextView,jiankuanTextView,yaoweiTextView;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_mine;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        userNameTextView=view.findViewById(R.id.userNameTextView);
        ageTextView=view.findViewById(R.id.ageTextView);
        heightTextView=view.findViewById(R.id.heightTextView);
        weightTextView=view.findViewById(R.id.weightTextView);
        editTextView=view.findViewById(R.id.editTextView);
        yaoweiTextView=view.findViewById(R.id.yaoweiTextView);
        jiankuanTextView=view.findViewById(R.id.jiankuanTextView);
        ageTextView.setOnClickListener(this);
        heightTextView.setOnClickListener(this);
        weightTextView.setOnClickListener(this);
        editTextView.setOnClickListener(this);
        userNameTextView.setText("用户名："+Constant.currentUserEntity.getData().getNickname());
    }

    @Override
    public void onResume() {
        super.onResume();
        initData();
    }

    @Override
    protected void initData() {
        //1.创建一个队列
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.User_Info, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
//        request.add("username", username);
        request.add("userId", Constant.currentUserEntity.getData().getId());

        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: "+response.toString());
                Gson gson=new Gson();
                UserInfoEntity userInfoEntity=gson.fromJson(response.get().toString(), UserInfoEntity.class);
                if (userInfoEntity.getData().getAge()==null){
                    ageTextView.setText("");
                }else {
                    ageTextView.setText("年龄："+userInfoEntity.getData().getAge().toString());
                }
                if (userInfoEntity.getData().getTall()==null){
                    heightTextView.setText("");
                }else {
                    heightTextView.setText("身高："+userInfoEntity.getData().getTall().toString());
                }
                if (userInfoEntity.getData().getWeight()==null){
                    weightTextView.setText("");
                }else {
                    weightTextView.setText("体重："+userInfoEntity.getData().getWeight().toString());
                }
                if (userInfoEntity.getData().getJiankuan()==null){
                    jiankuanTextView.setText("");
                }else {
                    jiankuanTextView.setText("肩宽："+userInfoEntity.getData().getJiankuan().toString());
                }
                if (userInfoEntity.getData().getYaowei()==null){
                    yaoweiTextView.setText("");
                }else {
                    yaoweiTextView.setText("腰围："+userInfoEntity.getData().getYaowei().toString());
                }
                userNameTextView.setText("用户名："+userInfoEntity.getData().getNickname());
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
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.editTextView:
                Intent intent=new Intent();
                intent.setClass(getActivity(), EditUserInfoActivity.class);
                startActivity(intent);
                break;
        }
    }
}
