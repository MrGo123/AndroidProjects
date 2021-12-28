package com.sustart.shdsystem;

import android.content.Intent;
import android.os.Bundle;
import android.os.NetworkOnMainThreadException;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;
import com.sustart.shdsystem.common.BaseResponse;
import com.sustart.shdsystem.common.Constant;
import com.sustart.shdsystem.entity.User;

import java.io.IOException;
import java.lang.reflect.Type;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity.class";

    private Button registerButton;
    private Button loginButton;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout passwordTextInputLayout;

    private User legalUser;
    private SHDSystemApplication application;
    private boolean requestStatus = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneTextInputLayout = findViewById(R.id.login_user_phone);
        passwordTextInputLayout = findViewById(R.id.login_user_password);
        registerButton = findViewById(R.id.register_btn);
        loginButton = findViewById(R.id.login_btn);

        application = (SHDSystemApplication) getApplication();

        registerBind();
        loginBind();
    }

    /**
     * 登录按钮及其相关逻辑、网络请求
     */
    private void loginBind() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 用户填写信息获取
                EditText phoneEditText = phoneTextInputLayout.getEditText();
                String tempPhoneNumber = phoneEditText.getText().toString();

                EditText passwordEditText = passwordTextInputLayout.getEditText();
                String tempPassword = passwordEditText.getText().toString();

                // 输入合法性判断，暂时只判断是否填写
                if (tempPhoneNumber.length() == 0) {
//                    显示错误提示
                    Toast.makeText(LoginActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (tempPassword.length() == 0) {
                    Toast.makeText(LoginActivity.this, "密码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (isLegalUser(tempPhoneNumber, tempPassword)) {
                    Toast.makeText(LoginActivity.this, "欢迎，" + legalUser.getName(), Toast.LENGTH_SHORT).show();
//                 进入主页
                    Intent intentToMainActivity = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intentToMainActivity);
                }
            }
        });
    }

    /**
     * 注册按钮事件绑定
     */
    private void registerBind() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }
// todo 这里okhttp为异步执行：存在线程冲突的问题，因为使用了共享变量 legalUser 和 requestStatus
    private okhttp3.Callback callback = new okhttp3.Callback() {

        @Override
        public void onResponse(@NonNull okhttp3.Call call, @NonNull Response response) throws IOException {
//            响应成功
            if (response.isSuccessful()) {
//                获取响应数据体
                String body = response.body().string();
                Log.e(TAG, "登录服务器返回数据：" + body);

                if (body != null) {
                    Gson gson = new Gson();
                    Type jsonType = new TypeToken<BaseResponse<User>>() {
                    }.getType();
                    BaseResponse<User> userResponse = gson.fromJson(body, jsonType);
                    User user = userResponse.getData();
                    if (user != null) {
                        legalUser = user;
                    }
                }
                requestStatus = true;
            }
        }

        @Override
        public void onFailure(@NonNull okhttp3.Call call, @NonNull IOException e) {
            Log.e(TAG, "连接服务器失败! ");
            e.printStackTrace();
            requestStatus = true;
        }
    };

    /**
     * 通过后台获取该用户对象
     *
     * @param userPhone
     */
    private void getUserInfoByHttp(String userPhone) {
        String requestUrl = Constant.HOST_URL + "user/queryByPhone/" + userPhone;
        Request request = new Request.Builder().url(requestUrl).get().build();
        OkHttpClient client = new OkHttpClient();
        try {
            client.newCall(request).enqueue(callback);
        } catch (NetworkOnMainThreadException ex) {
            ex.printStackTrace();
        }
    }

    /**
     * 网络请求校验
     *
     * @param userPhone
     * @param password
     * @return
     */
    private boolean isLegalUser(String userPhone, String password) {

//        网络请求返回该用户的所有信息
        getUserInfoByHttp(userPhone);
//        todo 存在线程问题：如果当前用户没请求回来，则该线程空转
        while (!requestStatus) {
        }
//        没有返回User说明没有该用户
        if (this.legalUser == null) {
            Log.e(TAG, "不存在该用户或手机号码错误");
            Toast.makeText(LoginActivity.this, "不存在该用户或手机号码错误", Toast.LENGTH_SHORT).show();
            return false;
        }

//        根据网络返回的信息判断是否合法用户
        if (!userPhone.equals(this.legalUser.getPhone()) || !password.equals(this.legalUser.getPassword())) {
            Log.e(TAG, "用户手机号或密码错误");
            Toast.makeText(LoginActivity.this, "密码错误", Toast.LENGTH_SHORT).show();
            return false;
        }

//        合法
//        保存到Application中
        application.loginUser = this.legalUser;
        return true;

//        todo 记住密码功能：保存到SharedPreferences中
//        User passedUser = new User();
//        String spFileName = getResources().getString(R.string.shared_preference_file_name);
//        String loginUserName = getResources().getString(R.string.login_user_name);
//        String loginUserPhone = getResources().getString(R.string.login_user_phone);
//        String loginUserPassword = getResources().getString(R.string.login_user_password);
//        String loginUserAddress = getResources().getString(R.string.login_user_address);
//        String rememberPasswordKey = getResources().getString(R.string.login_remember_password);
//
//        SharedPreferences spFile = getSharedPreferences(spFileName, Context.MODE_PRIVATE);
//        SharedPreferences.Editor editor = spFile.edit();
//
//        editor.putString(loginUserPhone, passedUser.getPhone());
//        editor.putString(loginUserName, passedUser.getName());
//        editor.putString(loginUserPassword, passedUser.getPassword());
//        editor.putString(loginUserAddress, passedUser.getAddress());
//        editor.putBoolean(rememberPasswordKey, true);
//        editor.apply();
    }
}