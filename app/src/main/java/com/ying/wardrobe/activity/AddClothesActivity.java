package com.ying.wardrobe.activity;

import android.content.Intent;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;

import com.bumptech.glide.Glide;
import com.google.gson.Gson;
import com.wildma.pictureselector.PictureSelector;
import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.entity.UserEntity;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;
import com.ying.wardrobe.view.CommonDialog;
import com.ying.wardrobe.view.CommonHead;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.activity
 * @ClassName: AddClothesActivity
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/14 0014 上午 8:39
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/14 0014 上午 8:39
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class AddClothesActivity extends BaseActivity implements View.OnClickListener {
    private static final String TAG = "AddClothesActivity";
    private TextView addClothesTextView;
    private TextView confirmTextView;
    private ImageView selectImage;
    private CommonHead title_bar;
    private String filePath;
    private String selectType;
    private EditText priceEditText,styleEditText;
    CommonDialog commonDialog;
    @Override
    protected int initLayout() {
        return R.layout.activity_add_clothes;
    }

    @Override
    protected void initView() {
        commonDialog=new CommonDialog(this);
        selectType = getIntent().getStringExtra("selectType");
        addClothesTextView = findViewById(R.id.addClothesTextView);
        confirmTextView = findViewById(R.id.confirmTextView);
        priceEditText = findViewById(R.id.priceEditText);
        styleEditText = findViewById(R.id.styleEditText);
        selectImage = findViewById(R.id.selectImage);
        title_bar = findViewById(R.id.title_bar);
        title_bar.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                AddClothesActivity.this.finish();
            }
        });

        addClothesTextView.setOnClickListener(this);
        confirmTextView.setOnClickListener(this);
    }

    @Override
    protected void initData() {

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.addClothesTextView:
                PictureSelector
                        .create(this, PictureSelector.SELECT_REQUEST_CODE)
                        .selectPicture(false, 200, 200, 1, 1);
                break;
            case R.id.confirmTextView:
                uploadImage(filePath);
                break;
        }
    }

    private void uploadImage(String filePath) {
        if (TextUtils.isEmpty(filePath)) {
            Toast.makeText(AddClothesActivity.this, "请先选择衣服！", Toast.LENGTH_SHORT).show();
            return;
        }
        if (commonDialog.isShowing()){
            return;
        }else {
            commonDialog.show();
        }
        final String host = HttpUtil.UpdateFile;
        System.out.println(host);
        System.out.println(filePath);
        File file = new File(filePath);
        String postUrl = host;
        //1.创建一个队列
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(postUrl, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("file", file);
        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                commonDialog.dismiss();
                Log.d(TAG, "onSucceed: " + response.get().toString());
                try {
                    JSONObject jsonObject = new JSONObject(response.get().toString());
                    if (jsonObject.getInt("status") == 0) {
                        addClothes(jsonObject.getString("data"));
                    }
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailed(int what, Response<JSONObject> response) {
                commonDialog.dismiss();
                System.out.println(response.get());
                System.out.println(response);
//                Toast.makeText(MainActivity.this, response.get().toString(), Toast.LENGTH_LONG).show();
            }

            @Override
            public void onFinish(int what) {

            }
        });
    }

    private void addClothes(String imageUrl) {
        String postUrl=HttpUtil.Add_Yifu;
        //1.创建一个队列
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(postUrl, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("userId", Constant.currentUserEntity.getData().getId());
        request.add("type", selectType);
        request.add("photo", imageUrl);
        request.add("price", priceEditText.getText().toString().trim());
        request.add("style", styleEditText.getText().toString().trim());

        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                Log.d(TAG, "onSucceed: " + response.get().toString());
                JSONObject res = response.get();
                try {
                    if (res.getInt("status") == 0) {
                        Toast.makeText(AddClothesActivity.this, "添加成功！", Toast.LENGTH_SHORT).show();
                        AddClothesActivity.this.finish();
                    } else {
                        Toast.makeText(getApplicationContext(), "添加失败，请稍后再试！", Toast.LENGTH_SHORT).show();
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

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == PictureSelector.SELECT_REQUEST_CODE) {
            if (data != null) {
                String picturePath = data.getStringExtra(PictureSelector.PICTURE_PATH);
                Glide.with(this).load(picturePath).into(selectImage);
                filePath = picturePath;
            }
        }
    }
}
