package com.example.notepad_client.activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.notepad_client.MainActivity;
import com.example.notepad_client.R;
import com.example.notepad_client.bean.RegisterBean;
import com.example.notepad_client.net.Urls;
import com.example.notepad_client.utils.SpUtils;
import com.example.notepad_client.utils.ToastUtil;
import com.example.notepad_client.view.ClearEditText;
import com.lzy.okgo.OkGo;
import com.lzy.okgo.callback.StringCallback;
import com.lzy.okgo.model.Response;

import org.json.JSONException;
import org.json.JSONObject;

import androidx.annotation.Nullable;
import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import okhttp3.MediaType;
import okhttp3.RequestBody;

public class LoginActivity extends BaseActivity {
    @BindView(R.id.edt_name)
    ClearEditText edtName;
    @BindView(R.id.edt_pwd)
    ClearEditText edtPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.tv_register)
    TextView tvRegister;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        initData();
    }

    private void initData() {
       String objid = SpUtils.getString(this,"name");
       if (!objid.isEmpty()){
           startActivity(new Intent(LoginActivity.this, MainActivity.class));
           finish();
       }
    }
    @OnClick({R.id.btn_login, R.id.tv_register})
    public void onViewClicked(View view) {
        switch (view.getId()){
            case R.id.btn_login:
                String name = edtName.getText().toString();
                String pwd = edtPwd.getText().toString();
                if (name.isEmpty()){
                    ToastUtil.showToast("请输入用户名");
                    return;
                }
                if (pwd.isEmpty()){
                    ToastUtil.showToast("请输入密码");
                    return;
                }
                JSONObject json = new JSONObject();
                try {
                    json.put("username", name);
                    json.put("password",pwd);
                    MediaType JSON = MediaType.parse("application/json; charset=utf-8");
                    RequestBody body = RequestBody.create(JSON, String.valueOf(json));
                    OkGo.<String>post(Urls.LOGIN)
                            .upRequestBody(body)
                            .execute(new StringCallback() {
                                @Override
                                public void onSuccess(Response<String> response) {
                                    Log.e("TAG","登录---"+response.body());
                                    try {
                                        RegisterBean bean = getGson().fromJson(response.body(), RegisterBean.class);
                                        if (bean.getStatus()==0){
                                            SpUtils.saveString(LoginActivity.this,"updatetime",bean.getData().getUpdateTime());
                                            SpUtils.saveString(LoginActivity.this,"name",bean.getData().getUsername());
                                            startActivity(new Intent(LoginActivity.this, MainActivity.class));
                                            finish();
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
            case R.id.tv_register:
                startActivity(new Intent(LoginActivity.this, RegisterActivity.class));
                break;
        }
    }
}
