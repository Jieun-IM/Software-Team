package com.example.damhwa2;

import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

public class MatchingActivity extends AppCompatActivity {

    private TextView tvMatchedUser;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matching);

        // View 초기화
        tvMatchedUser = findViewById(R.id.tvMatchedUser);

        // 매칭 요청 실행
        requestRandomMatching();
    }

    private void requestRandomMatching() {
        String url = "http://10.0.2.2:8080/api/random-matching"; // 서버 주소를 여기에 입력하세요.

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
