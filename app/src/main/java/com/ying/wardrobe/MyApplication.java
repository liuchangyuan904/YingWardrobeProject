package com.ying.wardrobe;

import android.app.Application;

import com.yanzhenjie.nohttp.InitializationConfig;
import com.yanzhenjie.nohttp.NoHttp;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe
 * @ClassName: MyApplication
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/13 0013 下午 14:01
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/13 0013 下午 14:01
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class MyApplication extends Application {
    @Override
    public void onCreate() {
        super.onCreate();
        InitializationConfig config = InitializationConfig.newBuilder(this)
                .connectionTimeout(30 * 1000)
                .readTimeout(30 * 1000)
                .retry(10)
                .build();
        NoHttp.initialize(config);
    }
}
