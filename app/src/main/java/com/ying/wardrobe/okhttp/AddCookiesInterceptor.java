//package com.ying.wardrobe.okhttp;
//
//import android.util.Log;
//
//
//import java.io.IOException;
//
//import okhttp3.Interceptor;
//import okhttp3.Request;
//import okhttp3.Response;
//
///**
// * Created by dl on 2017/8/15.
// */
//
//public class AddCookiesInterceptor implements Interceptor {
//
//    @Override
//    public Response intercept(Chain chain) throws IOException {
//        Log.e("okhttp", "拦截器");
//        Request.Builder builder = chain.request().newBuilder();
//        //HashSet<String> preferences = (HashSet) SharePreUtil.getInstance().getStringSet(Preferences.PREF_COOKIES, new HashSet<>());
//        String newSession = SharePreUtil.getInstance().getCookie();
//
//        builder.addHeader("Cookie", "token=" + newSession); //"token="
//        Log.e("okhttp", "Adding Header: " + newSession);
//
//        return chain.proceed(builder.build());
//
////        Request request = chain.request();
////
////        Response response = chain.proceed(request);
////
////        Log.e("okhttp","response.code=" + response.code());
////
////        if (isTokenExpired(response)) {//根据和服务端的约定判断token过期
////            Log.e("okhttp","静默自动刷新Token,然后重新请求数据");
////            //同步请求方式，获取最新的Token
////            String newSession = SharePreUtil.getInstance().getUserToken();
////            Log.e("okhttp", "Adding Header: " + newSession);
////            //使用新的Token，创建新的请求
////            Request newRequest = chain.request()
////                    .newBuilder()
////                    .header("Cookie", "token=" + newSession)
////                    .build();
////            //重新请求
////            return chain.proceed(newRequest);
////        }
////
////        return response;
//
//    }
//
//    /**
//     * 根据Response，判断Token是否失效
//     * @param response
//     * @return
//     */
//    private boolean isTokenExpired(Response response) {
//        if (response.code() == 404) {
//            return true;
//        }
//        return false;
//    }
//
//}
