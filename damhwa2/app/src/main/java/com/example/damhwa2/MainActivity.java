package com.example.damhwa2;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MainActivity extends AppCompatActivity {

    private Button buttonSignup, buttonLogin, buttonLogout;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // UI 요소 초기화는 setContentView 이후에 해야 함
        buttonSignup = findViewById(R.id.button_signup);
        //buttonEmailVerification = findViewById(R.id.button_email_verification);
        buttonLogin = findViewById(R.id.button_login);
        buttonLogout = findViewById(R.id.button_logout);

        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, SignupActivity.class));
            }
        });
        buttonLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, LoginActivity.class));
            }
        });

        buttonLogout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 로그아웃 로직 구현 후 로그인 화면으로 돌아가기
                if (!isFinishing()) {
                    startActivity(new Intent(MainActivity.this, LogoutActivity.class));
                }
            }
        });
    }
    
    private void requestRandomMatching() {
        String url = "http://your-server.com/api/random-matching"; // 서버 주소를 여기에 입력하세요.

        // Volley 라이브러리를 사용해 서버 요청 처리
        RequestQueue requestQueue = Volley.newRequestQueue(this);

        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(
                Request.Method.GET,
                url,
                null,
                new Response.Listener<JSONObject>() {
                    @Override
                    public void onResponse(JSONObject response) {
                        try {
                            String matchedUser = response.getString("matched_user");
                            tvMatchedUser.setText("매칭된 사용자: " + matchedUser);
                        } catch (JSONException e) {
                            e.printStackTrace();
                            Toast.makeText(MatchingActivity.this, "응답 처리 중 오류 발생", Toast.LENGTH_SHORT).show();
                        }
                    }
                },
                new Response.ErrorListener() {
                    @Override
                    public void onErrorResponse(VolleyError error) {
                        error.printStackTrace();
                        Toast.makeText(MatchingActivity.this, "서버 요청 실패", Toast.LENGTH_SHORT).show();
                    }
                }
        );

        requestQueue.add(jsonObjectRequest);
    }
}
