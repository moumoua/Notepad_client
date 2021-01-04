package com.example.notepad_client.activity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.example.notepad_client.R;
import com.example.notepad_client.bean.RegisterBean;
import com.example.notepad_client.net.Urls;
import com.example.notepad_client.utils.ToastUtil;
import com.example.notepad_client.view.ClearEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;
import com.lzy.okgo.request.base.Request;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class RegisterActivity extends BaseActivity {

    @BindView(R.id.img_back)
    ImageView imgBack;
    @BindView(R.id.img_tx)
    ImageView imgTx;
    @BindView(R.id.edt_name)
    ClearEditText edtName;
    @BindView(R.id.edt_pwd)
    ClearEditText edtPwd;
    @BindView(R.id.edt_repwd)
    ClearEditText edtRepwd;
    @BindView(R.id.btn_register)
    Button btnRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.btn_register, R.id.img_back,R.id.img_tx})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_register:
                String name = edtName.getText().toString();
                String pwd = edtPwd.getText().toString();
                String repwd = edtRepwd.getText().toString();
                if (name.isEmpty()){
                    ToastUtil.showToast("请输入用户名");
                    return;
                }
                if (pwd.isEmpty()){
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                if (repwd.isEmpty()){
                    ToastUtil.showToast("请输入确认密码");
                    return;
                }
                if (!pwd.equals(repwd)){
                    ToastUtil.showToast("两次密码输入不一致");
                    return;
                }
                JSONObject json = new JSONObject();
                try {
                    json.put("username", name);
                    json.put("password",pwd);
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, String.valueOf(json));
                    OkGo.<String>post(Urls.REGISTER)
                            .upRequestBody(body)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Log.e("TAG","注册---"+response.body());
                                    try {
                                        RegisterBean bean = getGson().fromJson(response.body(), RegisterBean.class);
                                        if (bean.getStatus()==0){
                                            startActivity(new Intent(RegisterActivity.this, LoginActivity.class));
                                            finish();
                                            ToastUtil.showToast("注册成功");
                                        }else {
                                            ToastUtil.showToast(bean.getMsg());
                                        }

                                    }catch (Exception e){

                                    }
                                }
                            });
                } catch (JSONException e) {
                    e.printStackTrace();
                }




                break;
            case R.id.img_back:
                finish();
                break;
        }
    }

}
