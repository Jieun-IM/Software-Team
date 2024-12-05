package com.example.damhwa2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

public class LoginActivity extends AppCompatActivity {

    private EditText editTextId, editTextPassword;
    private Button buttonLogin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        editTextId = findViewById(R.id.editTextId);
        editTextPassword = findViewById(R.id.editTextPassword);
        buttonLogin = findViewById(R.id.buttonLogin);

        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = editTextId.getText().toString().trim();
                String password = editTextPassword.getText().toString().trim();

                // 아이디와 비밀번호가 모두 입력되었는지 확인
                if (email.isEmpty() || password.isEmpty()) {
                    Toast.makeText(LoginActivity.this, "Please enter both ID and password.", Toast.LENGTH_SHORT).show();
                } else {
                    // 서버로 로그인 데이터 전송하는 코드 추가 필요
                    Toast.makeText(LoginActivity.this, "Login successful.", Toast.LENGTH_SHORT).show();

                    // 로그인 성공 시 메인 화면으로 이동
                    startActivity(new Intent(LoginActivity.this, HomeActivity.class));
                }
            }
        });
    }
}
