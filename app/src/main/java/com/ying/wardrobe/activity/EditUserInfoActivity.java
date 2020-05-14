package com.ying.wardrobe.activity;

import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.yanzhenjie.nohttp.NoHttp;
import com.yanzhenjie.nohttp.RequestMethod;
import com.yanzhenjie.nohttp.rest.OnResponseListener;
import com.yanzhenjie.nohttp.rest.Request;
import com.yanzhenjie.nohttp.rest.RequestQueue;
import com.yanzhenjie.nohttp.rest.Response;
import com.ying.wardrobe.BaseActivity;
import com.ying.wardrobe.R;
import com.ying.wardrobe.util.Constant;
import com.ying.wardrobe.util.HttpUtil;
import com.ying.wardrobe.view.CommonDialog;
import com.ying.wardrobe.view.CommonHead;

import org.json.JSONException;
import org.json.JSONObject;

/**
 * @ProjectName: WardrobeProject
 * @Package: com.ying.wardrobe.activity
 * @ClassName: EditUserInfoActivity
 * @Description: java类作用描述
 * @Author: liucy
 * @CreateDate: 2020/5/13 0013 下午 16:05
 * @UpdateUser: 更新者：
 * @UpdateDate: 2020/5/13 0013 下午 16:05
 * @UpdateRemark: 更新说明：
 * @Version: 1.0
 */
public class EditUserInfoActivity extends BaseActivity {
    private static final String TAG = "EditUserInfoActivity";
    private EditText ageEditText,heightEditText,weightEditText,nickNameEditText,jiankuanEditText,yaoweiEditText;
    private TextView uploadTextView;
    private CommonHead title_bar;
    CommonDialog commonDialog;
    @Override
    protected int initLayout() {
        return R.layout.activity_edit_info;
    }

    @Override
    protected void initView() {
        commonDialog=new CommonDialog(this);
        ageEditText=findViewById(R.id.ageEditText);
        heightEditText=findViewById(R.id.heightEditText);
        weightEditText=findViewById(R.id.weightEditText);
        nickNameEditText=findViewById(R.id.nickNameEditText);
        uploadTextView=findViewById(R.id.uploadTextView);
        jiankuanEditText=findViewById(R.id.jiankuanEditText);
        yaoweiEditText=findViewById(R.id.yaoweiEditText);
        title_bar=findViewById(R.id.title_bar);
        uploadTextView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (TextUtils.isEmpty(ageEditText.getText().toString().trim())||
                        TextUtils.isEmpty(heightEditText.getText().toString().trim())||
                        TextUtils.isEmpty(nickNameEditText.getText().toString().trim())||
                        TextUtils.isEmpty(jiankuanEditText.getText().toString().trim())||
                        TextUtils.isEmpty(yaoweiEditText.getText().toString().trim())||
                        TextUtils.isEmpty(weightEditText.getText().toString().trim())){
                    Toast.makeText(EditUserInfoActivity.this,"用户名/年龄/身高/体重/腰围/肩宽都要输入哦！",Toast.LENGTH_SHORT).show();
                }
                uploadInfo();
            }
        });
        title_bar.setLeftClick(new CommonHead.CommonHeadLeftClick() {
            @Override
            public void LeftClick() {
                EditUserInfoActivity.this.finish();
            }
        });
    }

    private void uploadInfo() {
        if (commonDialog.isShowing()){
            return;
        }else {
            commonDialog.show();
        }
        RequestQueue queue = NoHttp.newRequestQueue();
        //2.创建消息请求   参数1:String字符串,传网址  参数2:请求方式
        final Request<JSONObject> request = NoHttp.createJsonObjectRequest(HttpUtil.UpdateUser, RequestMethod.POST);
        //3.利用队列去添加消息请求
        //使用request对象添加上传的对象添加键与值,post方式添加上传的数据
        request.add("id", Constant.currentUserEntity.getData().getId());
        request.add("username", Constant.currentUserEntity.getData().getUsername());
        request.add("password", Constant.currentUserEntity.getData().getPassword());
        request.add("nickname", nickNameEditText.getText().toString().trim());
        request.add("phone", Constant.currentUserEntity.getData().getPhone());
        request.add("age", ageEditText.getText().toString().trim());
        request.add("weight", weightEditText.getText().toString().trim());
        request.add("tall", heightEditText.getText().toString().trim());
        request.add("jiankuan", jiankuanEditText.getText().toString().trim());
        request.add("yaowei", yaoweiEditText.getText().toString().trim());

        queue.add(1, request, new OnResponseListener<JSONObject>() {
            @Override
            public void onStart(int what) {

            }

            @Override
            public void onSucceed(int what, Response<JSONObject> response) {
                commonDialog.dismiss();
                Log.d(TAG, "onSucceed: "+response.get().toString());
                JSONObject jsonObject=response.get();
                try {
                    if (jsonObject.getInt("status")==0){
                        Toast.makeText(EditUserInfoActivity.this,"用户信息已更新！",Toast.LENGTH_SHORT).show();
                        EditUserInfoActivity.this.finish();
                    }else {
                        Toast.makeText(EditUserInfoActivity.this,"用户信息更新失败，请稍后再试！",Toast.LENGTH_SHORT).show();
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
    protected void initData() {

    }
}
