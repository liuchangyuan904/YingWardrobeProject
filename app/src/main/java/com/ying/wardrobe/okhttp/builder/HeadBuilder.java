package com.ying.wardrobe.okhttp.builder;


import com.ying.wardrobe.okhttp.OkHttpUtils;
import com.ying.wardrobe.okhttp.request.OtherRequest;
import com.ying.wardrobe.okhttp.request.RequestCall;

/**
 * Created by zhy on 16/3/2.
 */
public class HeadBuilder extends GetBuilder
{
    @Override
    public RequestCall build()
    {
        return new OtherRequest(null, null, OkHttpUtils.METHOD.HEAD, url, tag, params, headers,id).build();
    }
}
