package com.sustart.shdsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;
import com.sustart.shdsystem.entity.User;

public class LoginActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout passwordTextInputLayout;

    private String phone;
    private User user;
    private SHDSystemApplication application;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneTextInputLayout = findViewById(R.id.login_user_phone);
        phoneTextInputLayout = findViewById(R.id.login_user_password);
        registerButton = findViewById(R.id.register_btn);
        loginButton = findViewById(R.id.login_btn);
        application = (SHDSystemApplication) getApplication();

        registerBind();
        loginBind();

    }

    private void loginBind() {
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 用户信息判断逻辑
                EditText phoneEditText = phoneTextInputLayout.getEditText();
                String tempPhoneNumber = phoneEditText.getText().toString();
                // 输入合法性判断……
                if (tempPhoneNumber.length() == 0) {
                    Toast.makeText(LoginActivity.this, "手机号码不能为空", Toast.LENGTH_SHORT).show();
                    return;
                }

//                进入应用内
                if (true) {


                    Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                    startActivity(intent);
                }
            }
        });
    }

    private void registerBind() {
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });
    }

    /**
     * 网络请求校验
     *
     * @param userPhone
     * @param password
     * @return
     */
    private boolean isLegalUser(String userPhone, String password) {

//        todo 网络请求返回该用户的所有信息

//        todo 根据网络返回的信息判断是否存在


//        存在则保存到Application中

        application.loginUser = new User("123", "libai", "123abc","beijing");


//        保存到SharedPreferences中
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

        return false;
    }
}