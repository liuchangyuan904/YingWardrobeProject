package com.ying.wardrobe.activity;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Criteria;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.widget.TextView;

import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.view.CommonHead;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.SimpleFormatter;

import androidx.annotation.RequiresApi;

public class AddDiaryActivity extends BaseActivity {
    private CommonHead title_bar;
    private TextView dateTextView;
    private SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy年MM月dd日 HH时mm分ss秒");
    private LocationManager locationManager;
    private String provider;
    private Location currentlocation;

    @Override
    protected int initLayout() {
        return R.layout.activity_add_diary;
    }

    @Override
    protected void initView() {
        title_bar = findViewById(R.id.title_bar);
        title_bar.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                AddDiaryActivity.this.finish();
            }
        });
        dateTextView = findViewById(R.id.dateTextView);
        Date date = new Date();

        dateTextView.setText(simpleDateFormat.format(date));
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
    }
}
