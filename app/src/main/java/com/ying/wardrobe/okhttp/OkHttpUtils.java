package com.ying.wardrobe.okhttp;

import android.util.Log;

import com.ying.wardrobe.okhttp.builder.GetBuilder;
import com.ying.wardrobe.okhttp.builder.HeadBuilder;
import com.ying.wardrobe.okhttp.builder.OtherRequestBuilder;
import com.ying.wardrobe.okhttp.builder.PostFileBuilder;
import com.ying.wardrobe.okhttp.builder.PostFormBuilder;
import com.ying.wardrobe.okhttp.builder.PostStringBuilder;
import com.ying.wardrobe.okhttp.callback.Callback;
import com.ying.wardrobe.okhttp.request.RequestCall;
import com.ying.wardrobe.okhttp.utils.Platform;

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.concurrent.Executor;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.SSLSocketFactory;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import okhttp3.Call;
import okhttp3.OkHttpClient;
import okhttp3.Response;

/**
 * Created by zhy on 15/8/17.
 */
public class OkHttpUtils {
    public static final long DEFAULT_MILLISECONDS = 10_000L;
    private volatile static OkHttpUtils mInstance;
    private OkHttpClient mOkHttpClient;
    private Platform mPlatform;


    public OkHttpUtils(OkHttpClient okHttpClient) {
        if (okHttpClient == null) {
            Log.e("okhttp", "OkHttpUtils");
            // HttpsUtils.SSLParams sslParams  = SzApplication.getApplication().getSslParams();
            mOkHttpClient = new OkHttpClient.Builder()
//                    .cookieJar(new CookieJar() {
//                        private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//                        @Override
//                        public void saveFromResponse(HttpUrl httpUrl, List<Cookie> list) {
//                            Log.e("okhttp", "设置cookie:" + httpUrl.host() + "   " + httpUrl);
//                            cookieStore.put(httpUrl, list);
//                        }
//
//                        @Override
//                        public List<Cookie> loadForRequest(HttpUrl httpUrl) {
//                            Log.e("okhttp", "发送cookie:" + httpUrl.host() + "   " + httpUrl);
//                            List<Cookie> cookies = cookieStore.get(httpUrl);
//                            return cookies != null ? cookies : new ArrayList<Cookie>();
//                        }
//                    })

//                    .addInterceptor(new AddCookiesInterceptor())
                    .hostnameVerifier(new HostnameVerifier() {
                        @Override
                        public boolean verify(String hostname, SSLSession session) {
                            return true;
                        }
                    })
                    .sslSocketFactory(createSSLSocketFactory())
                    .build();
            //.sslSocketFactory(sslParams.sSLSocketFactory,sslParams.trustManager)
            // mOkHttpClient.newBuilder().sslSocketFactory(createSSLSocketFactory());
            //mOkHttpClient.interceptors().add(new AddCookiesInterceptor());
            //mOkHttpClient.interceptors().add(new LoggerInterceptor("okhttp", true));
//                    .cookieJar(new CookieJar() {
//            private final HashMap<HttpUrl, List<Cookie>> cookieStore = new HashMap<>();
//
//            @Override
//            public void saveFromResponse(HttpUrl url, List<Cookie> cookies) {
//                cookieStore.put(url.host(), cookies);
//            }
//
//            @Override
//            public List<Cookie> loadForRequest(HttpUrl url) {
//                List<Cookie> cookies = cookieStore.get(url.host());
//                return cookies != null ? cookies : new ArrayList<Cookie>();
//            }
//        })
//        .build();


        } else {
            mOkHttpClient = okHttpClient;
        }

        mPlatform = Platform.get();
    }


    public static OkHttpUtils initClient(OkHttpClient okHttpClient) {
        if (mInstance == null) {
            synchronized (OkHttpUtils.class) {
                if (mInstance == null) {
                    Log.e("okhttp", "initClient");
                    mInstance = new OkHttpUtils(okHttpClient);
                }
            }
        }
        return mInstance;
    }

    public static class TrustAllCerts implements X509TrustManager {
        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) {
        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return new X509Certificate[0];
        }
    }

    private static SSLSocketFactory createSSLSocketFactory() {
        SSLSocketFactory ssfFactory = null;

        try {
            SSLContext sc = SSLContext.getInstance("TLS");
            sc.init(null, new TrustManager[]{new TrustAllCerts()}, new SecureRandom());
            //sc.init(prepareKeyManager(bksFile, password), new TrustManager[]{new TrustAllCerts()},null);

            ssfFactory = sc.getSocketFactory();
        } catch (Exception e) {
        }

        return ssfFactory;
    }


    public static OkHttpUtils getInstance() {
        return initClient(null);
    }


    public Executor getDelivery() {
        return mPlatform.defaultCallbackExecutor();
    }

    public OkHttpClient getOkHttpClient() {
        return mOkHttpClient;
    }

    public static GetBuilder get() {
        return new GetBuilder();
    }

    public static PostStringBuilder postString() {
        return new PostStringBuilder();
    }

    public static PostFileBuilder postFile() {
        return new PostFileBuilder();
    }

    public static PostFormBuilder post() {
        return new PostFormBuilder();
    }

    public static OtherRequestBuilder put() {
        return new OtherRequestBuilder(METHOD.PUT);
    }

    public static HeadBuilder head() {
        return new HeadBuilder();
    }

    public static OtherRequestBuilder delete() {
        return new OtherRequestBuilder(METHOD.DELETE);
    }

    public static OtherRequestBuilder patch() {
        return new OtherRequestBuilder(METHOD.PATCH);
    }

    public void execute(final RequestCall requestCall, Callback callback) {
        if (callback == null)
            callback = Callback.CALLBACK_DEFAULT;
        final Callback finalCallback = callback;
        final int id = requestCall.getOkHttpRequest().getId();

        requestCall.getCall().enqueue(new okhttp3.Callback() {
            @Override
            public void onFailure(Call call, final IOException e) {
                sendFailResultCallback(call, e, finalCallback, id);
            }

            @Override
            public void onResponse(final Call call, final Response response) {
                try {
                    if (call.isCanceled()) {
                        sendFailResultCallback(call, new IOException("Canceled!"), finalCallback, id);
                        return;
                    }

                    if (!finalCallback.validateReponse(response, id)) {
                        sendFailResultCallback(call, new IOException("request failed , reponse's code is : " + response.code()), finalCallback, id);
                        return;
                    }
//                    Log.e("返回的res", "返回:" + response + "id： " +id);
                    Object o = finalCallback.parseNetworkResponse(response, id);
                    //Log.e("返回的obj", "返回:" + o);

                    sendSuccessResultCallback(o, finalCallback, id);
                } catch (Exception e) {
                    //Log.e("抛出异常", "e:" + e.getMessage());
                    sendFailResultCallback(call, e, finalCallback, id);
                } finally {
                    if (response.body() != null)
                        response.body().close();
                }

            }
        });
    }


    public void sendFailResultCallback(final Call call, final Exception e, final Callback callback, final int id) {
        if (callback == null) return;

        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onError(call, e, id);
                callback.onAfter(id);
            }
        });
    }

    public void sendSuccessResultCallback(final Object object, final Callback callback, final int id) {
        if (callback == null) return;
        mPlatform.execute(new Runnable() {
            @Override
            public void run() {
                callback.onResponse(object, id);
                callback.onAfter(id);
            }
        });
    }

    public void cancelTag(Object tag) {
        for (Call call : mOkHttpClient.dispatcher().queuedCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
        for (Call call : mOkHttpClient.dispatcher().runningCalls()) {
            if (tag.equals(call.request().tag())) {
                call.cancel();
            }
        }
    }

    public static class METHOD {
        public static final String HEAD = "HEAD";
        public static final String DELETE = "DELETE";
        public static final String PUT = "PUT";
        public static final String PATCH = "PATCH";
    }
}

