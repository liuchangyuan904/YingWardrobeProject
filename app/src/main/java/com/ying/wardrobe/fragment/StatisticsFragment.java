package com.ying.wardrobe.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseFragment;
import com.ying.wardrobe.R;
import com.ying.wardrobe.entity.StatisticsEntity;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

public class StatisticsFragment extends BaseFragment {
    private static final String TAG = "StatisticsFragment";
    private TextView duanxiuNumber;
    private TextView changkuNumber;
    private TextView qunziNumber;
    private TextView waitaoNumber;
    private TextView maoziNumber;
    private TextView pinlv1TextView;
    private TextView pinlv2TextView;
    private TextView pinlv3TextView;
    private ImageView imageView1,imageView2,imageView3;
    @Override
    protected int getLayoutId() {
        return R.layout.fragment_statistics;
    }

    @Override
    protected void initView(View view, Bundle savedInstanceState) {
        duanxiuNumber=view.findViewById(R.id.duanxiuNumber);
        changkuNumber=view.findViewById(R.id.changkuNumber);
        qunziNumber=view.findViewById(R.id.qunziNumber);
        waitaoNumber=view.findViewById(R.id.waitaoNumber);
        maoziNumber=view.findViewById(R.id.maoziNumber);
        pinlv1TextView=view.findViewById(R.id.pinlv1TextView);
        pinlv2TextView=view.findViewById(R.id.pinlv2TextView);
        pinlv3TextView=view.findViewById(R.id.pinlv3TextView);
        imageView1=view.findViewById(R.id.imageView1);
        imageView2=view.findViewById(R.id.imageView2);
        imageView3=view.findViewById(R.id.imageView3);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onResume() {
        super.onResume();
        getStatistics();
    }

    private void getStatistics() {

        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.Get_Yifu_Stat, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("userId", Constant.currentUserEntity.getData().getId());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: 统计" + response.get().toString());
                JSONObject jsonObject = response.get();
                try {
                    if (jsonObject.getInt("status") == 0) {
                        Gson gson=new Gson();
                        StatisticsEntity entity=gson.fromJson(jsonObject.toString(),StatisticsEntity.class);
                        duanxiuNumber.setText(entity.getData().getPinlv().getDuanxiu()+"次");
                        changkuNumber.setText(entity.getData().getPinlv().getChangku()+"次");
                        waitaoNumber.setText(entity.getData().getPinlv().getWaitao()+"次");
                        qunziNumber.setText(entity.getData().getPinlv().getQunzi()+"次");
                        maoziNumber.setText(entity.getData().getPinlv().getMaozi()+"次");
                        if (entity.getData().getList().size()==0){
                            pinlv1TextView.setText("0次");
                            pinlv2TextView.setText("0次");
                            pinlv3TextView.setText("0次");
                        }else if (entity.getData().getList().size()==1){
                            pinlv1TextView.setText(entity.getData().getList().get(0).getCount()+"次");
                            Glide.with(getActivity()).load(entity.getData().getList().get(0).getUrl()).into(imageView1);
                            pinlv2TextView.setText("0次");
                            pinlv3TextView.setText("0次");
                        }else if (entity.getData().getList().size()==2){
                            pinlv1TextView.setText(entity.getData().getList().get(0).getCount()+"次");
                            pinlv2TextView.setText(entity.getData().getList().get(1).getCount()+"次");
                            Glide.with(getActivity()).load(entity.getData().getList().get(0).getUrl()).into(imageView1);
                            Glide.with(getActivity()).load(entity.getData().getList().get(1).getUrl()).into(imageView2);
                            pinlv3TextView.setText("0次");
                        }else if (entity.getData().getList().size()==3){
                            pinlv1TextView.setText(entity.getData().getList().get(0).getCount()+"次");
                            pinlv2TextView.setText(entity.getData().getList().get(1).getCount()+"次");
                            pinlv3TextView.setText(entity.getData().getList().get(2).getCount()+"次");
                            Glide.with(getActivity()).load(entity.getData().getList().get(0).getUrl()).into(imageView1);
                            Glide.with(getActivity()).load(entity.getData().getList().get(1).getUrl()).into(imageView2);
                            Glide.with(getActivity()).load(entity.getData().getList().get(2).getUrl()).into(imageView3);
                        }

                    } else {
                        Toast.makeText(getActivity(), "统计信息获取失败，请稍后再试！", Toast.LENGTH_SHORT).show();
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
