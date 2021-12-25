package com.sustart.shdsystem;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class LoginActivity extends AppCompatActivity {

    private Button registerButton;
    private Button loginButton;
    private TextInputLayout phoneTextInputLayout;
    private TextInputLayout passwordTextInputLayout;

    private String phone;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        phoneTextInputLayout = findViewById(R.id.login_user_phone);
        phoneTextInputLayout = findViewById(R.id.login_user_password);
        registerButton = findViewById(R.id.register_btn);
        loginButton = findViewById(R.id.login_btn);


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

        return false;
    }
}