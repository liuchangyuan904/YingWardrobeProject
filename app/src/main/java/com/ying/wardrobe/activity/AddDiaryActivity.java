package com.ying.wardrobe.activity;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Criteria;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.annotation.RequiresApi;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.entity.ClothesEntity;
import com.ying.wardrobe.entity.WeatherEntity;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;
import com.ying.wardrobe.view.CommonDialog;
import com.ying.wardrobe.view.CommonHead;

import org.json.JSONException;
import org.json.JSONObject;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Locale;

public class AddDiaryActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "AddDiaryActivity";
    private CommonHead title_bar;
    private TextView dateTextView;
    private ImageView selectImage;
    private TextView weatherTextView;
    private TextView wenduTextView;
    private TextView confirmTextView;
    private TextView chooseTextView;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private LocationManager locationManager;
    private String provider;
    private Location currentlocation;
    private String yifuId;
    CommonDialog commonDialog;
    @Override
    protected int initLayout() {
        return R.layout.activity_add_diary;
    }

    @Override
    protected void initView() {
        commonDialog=new CommonDialog(this);
        title_bar = findViewById(R.id.title_bar);
        title_bar.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                AddDiaryActivity.this.finish();
            }
        });
        dateTextView = findViewById(R.id.dateTextView);
        weatherTextView = findViewById(R.id.weatherTextView);
        wenduTextView = findViewById(R.id.wenduTextView);
        chooseTextView = findViewById(R.id.chooseTextView);
        confirmTextView = findViewById(R.id.confirmTextView);
        selectImage = findViewById(R.id.selectImage);
        Date date = new Date();
        dateTextView.setText(simpleDateFormat.format(date));
        chooseTextView.setOnClickListener(this);
        confirmTextView.setOnClickListener(this);
    }

    @RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    protected void initData() {
        // 获取位置管理服务
        String serviceName = Context.LOCATION_SERVICE;
        locationManager = (LocationManager) this.getSystemService(serviceName);
// 查找到服务信息
        Criteria criteria = new Criteria();
        criteria.setAccuracy(Criteria.ACCURACY_FINE); // 高精度
        criteria.setAltitudeRequired(false);
        criteria.setBearingRequired(false);
        criteria.setCostAllowed(true);
        criteria.setPowerRequirement(Criteria.POWER_LOW); // 低功耗

        provider = locationManager.getBestProvider(criteria, true); // 获取GPS信息
        if (checkSelfPermission(Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && checkSelfPermission(Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    Activity#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for Activity#requestPermissions for more details.
            return;
        }
        currentlocation = locationManager.getLastKnownLocation(provider); // 通过GPS获取位置
        getWearDiary();
    }

    private void getWearDiary() {
        if (commonDialog.isShowing()){
            return;
        }else {
            commonDialog.show();
        }
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.Get_Yifu_Weather, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("location", currentlocation.getLatitude() + "," + currentlocation.getLongitude());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                commonDialog.dismiss();
                Log.d(TAG, "onSucceed: " + response.get().toString());
                JSONObject jsonObject = response.get();
                try {
                    if (jsonObject.getInt("status") == 0) {
                        Gson gson = new Gson();
                        WeatherEntity entity = gson.fromJson(jsonObject.toString(), WeatherEntity.class);
                        weatherTextView.setText("今天天气：" + entity.getData().getWeather());
                        wenduTextView.setText("温度：" + entity.getData().getTemp() + "°");
                    } else {
                        Toast.makeText(AddDiaryActivity.this, "获取天气更新失败，请稍后再试！", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                commonDialog.dismiss();
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.chooseTextView:
                Intent intent = new Intent();
                intent.setClass(this, WardrobeActivity.class);
                startActivityForResult(intent, 10010);
                break;
            case R.id.confirmTextView:
                if (TextUtils.isEmpty(yifuId)){
                    Toast.makeText(AddDiaryActivity.this, "请先选择一件衣服哦！", Toast.LENGTH_SHORT).show();
                    return;
                }
                uploadDiary();
                break;
        }
    }

    private void uploadDiary() {
        if (commonDialog.isShowing()){
            return;
        }else {
            commonDialog.show();
        }
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.Get_Yifu_AddDaily , RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("yifuId", yifuId);
        request.add("userId", Constant.currentUserEntity.getData().getId());
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                commonDialog.dismiss();
                Log.d(TAG, "onSucceed: 获取衣服列表：  " + response.get().toString());
                JSONObject jsonObject = response.get();
                try {
                    if (jsonObject.getInt("status") == 0) {
                        Toast.makeText(AddDiaryActivity.this, "日记添加成功！", Toast.LENGTH_SHORT).show();
                        AddDiaryActivity.this.finish();
                    } else {
                        Toast.makeText(AddDiaryActivity.this, "日记添加失败，请稍后再试！", Toast.LENGTH_SHORT).show();
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onFailed: ");
                commonDialog.dismiss();
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (resultCode == Activity.RESULT_OK) {
            if (requestCode == 10010) {
                yifuId = data.getStringExtra("yifuId");
                String yifuUrl = data.getStringExtra("yifuUrl");
                Glide.with(AddDiaryActivity.this).load(yifuUrl).into(selectImage);
                confirmTextView.setVisibility(View.VISIBLE);
            }
        }
    }
}
