package com.example.damhwa2;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.Spinner;
import android.widget.ArrayAdapter;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;

public class SignupActivity extends AppCompatActivity {

    private EditText editTextId, editTextName, editTextPassword, editTextConfirmPassword,
            editTextNickname, editTextProfileImage, editTextBirth,
            editTextHeight, editTextWeight, editTextTag, editTextPhoneNumber,
            editTextVerificationCode;
    private Button buttonSignup, buttonPhoneNumberVerification, buttonVerifyCode, buttonCheckId;
    private RadioGroup radioGroupSex;
    private RadioButton radioMale, radioFemale;
    private Spinner spinnerLiving;

    private String verificationCode;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        // Initialize UI elements
        editTextId = findViewById(R.id.editTextId);
        editTextName = findViewById(R.id.editTextName);
        editTextPassword = findViewById(R.id.editTextPassword);
        editTextConfirmPassword = findViewById(R.id.editTextConfirmPassword);
        editTextNickname = findViewById(R.id.editTextNickname);
        editTextProfileImage = findViewById(R.id.editTextProfileImage);
        radioGroupSex = findViewById(R.id.radioGroupSex);
        radioMale = findViewById(R.id.radioMale);
        radioFemale = findViewById(R.id.radioFemale);
        editTextBirth = findViewById(R.id.editTextBirth);
        editTextHeight = findViewById(R.id.editTextHeight);
        editTextWeight = findViewById(R.id.editTextWeight);
        spinnerLiving = findViewById(R.id.spinnerLiving);
        editTextTag = findViewById(R.id.editTextTag);
        editTextPhoneNumber = findViewById(R.id.editTextPhoneNumber);
        editTextVerificationCode = findViewById(R.id.editTextVerificationCode);

        buttonSignup = findViewById(R.id.buttonSignup);
        buttonPhoneNumberVerification = findViewById(R.id.buttonPhoneNumberVerification);
        buttonVerifyCode = findViewById(R.id.buttonVerifyCode);
        buttonCheckId = findViewById(R.id.buttonCheckId);

        // Set Spinner adapter for Living options
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.living_options, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerLiving.setAdapter(adapter);

        // Set ID Check button listener
        buttonCheckId.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String id = editTextId.getText().toString().trim();
                if (!id.isEmpty()) {
                    checkIdAvailability(id);
                } else {
                    Toast.makeText(SignupActivity.this, "Please enter an ID to check.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set Phone Number Verification button listener
        buttonPhoneNumberVerification.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String phoneNumber = editTextPhoneNumber.getText().toString().trim();
                if (!phoneNumber.isEmpty()) {
                    sendVerificationCode(phoneNumber);
                } else {
                    Toast.makeText(SignupActivity.this, "Please enter a valid phone number.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set Verify Code button listener
        buttonVerifyCode.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String code = editTextVerificationCode.getText().toString().trim();
                if (!code.isEmpty()) {
                    verifyCode(code);
                } else {
                    Toast.makeText(SignupActivity.this, "Please enter the verification code.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        // Set Signup button listener
        buttonSignup.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // Proceed with the signup logic
                handleSignup();
            }
        });
    }

    private void sendVerificationCode(final String phoneNumber) {
        new AsyncTask<Void, Void, String>() {
            @Override
            protected String doInBackground(Void... voids) {
                try {
                    URL url = new URL("http://10.0.2.2:3000/send_code");
                    HttpURLConnection conn = (HttpURLConnection) url.openConnection();
                    conn.setRequestMethod("POST");
                    conn.setRequestProperty("Content-Type", "application/x-www-form-urlencoded");
                    conn.setDoOutput(true);

                    String params = "phoneNumber=" + phoneNumber;
                    OutputStream os = conn.getOutputStream();
                    os.write(params.getBytes());
                    os.flush();
                    os.close();

                    int responseCode = conn.getResponseCode();
                    if (responseCode == HttpURLConnection.HTTP_OK) {
                        BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()));
                        String inputLine;
                        StringBuilder response = new StringBuilder();
                        while ((inputLine = in.readLine()) != null) {
                            response.append(inputLine);
                        }
                        in.close();
                        return response.toString();
                    } else {
                        return "Server returned error: " + responseCode;
                    }
                } catch (Exception e) {
                    return "Exception: " + e.getMessage();
                }
            }

            @Override
            protected void onPostExecute(String result) {
                if (result.startsWith("Exception") || result.startsWith("Server returned error")) {
                    Toast.makeText(SignupActivity.this, "Error: " + result, Toast.LENGTH_LONG).show();
                } else {
                    verificationCode = result;  // Save the verification code for verification
                    Toast.makeText(SignupActivity.this, "Verification code sent.", Toast.LENGTH_SHORT).show();
                }
            }
        }.execute();
    }

    private void verifyCode(final String code) {
        if (verificationCode != null && verificationCode.equals(code)) {
            // Verification successful
            Toast.makeText(SignupActivity.this, "Verification successful.", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(SignupActivity.this, "Invalid verification code.", Toast.LENGTH_SHORT).show();
        }
    }

    private void handleSignup() {
        // Handle signup logic here
        String id = editTextId.getText().toString().trim();
        String name = editTextName.getText().toString().trim();
        String password = editTextPassword.getText().toString().trim();
        String confirmPassword = editTextConfirmPassword.getText().toString().trim();
        String nickname = editTextNickname.getText().toString().trim();
        String profileImage = editTextProfileImage.getText().toString().trim();
        String birth = editTextBirth.getText().toString().trim();
        String height = editTextHeight.getText().toString().trim();
        String weight = editTextWeight.getText().toString().trim();
        String tag = editTextTag.getText().toString().trim();
        String sex = ((RadioButton) findViewById(radioGroupSex.getCheckedRadioButtonId())).getText().toString();
        String living = spinnerLiving.getSelectedItem().toString();

        if (id.isEmpty() || name.isEmpty() || password.isEmpty() || confirmPassword.isEmpty() ||
                nickname.isEmpty() || profileImage.isEmpty() || birth.isEmpty() ||
                height.isEmpty() || weight.isEmpty() || tag.isEmpty() ||
                radioGroupSex.getCheckedRadioButtonId() == -1 || living.isEmpty()) {
            Toast.makeText(SignupActivity.this, "Please fill in all fields.", Toast.LENGTH_SHORT).show();
        } else if (!password.matches("^(?=.*[A-Za-z])(?=.*\\d)[A-Za-z\\d]{8,}$")) {
            Toast.makeText(SignupActivity.this, "Password must be at least 8 characters long and include letters and numbers.", Toast.LENGTH_SHORT).show();
        } else if (!password.equals(confirmPassword)) {
            Toast.makeText(SignupActivity.this, "Passwords do not match.", Toast.LENGTH_SHORT).show();
        } else {
            // Proceed with registration logic (e.g., server API call)
            Toast.makeText(SignupActivity.this, "Signup successful.", Toast.LENGTH_SHORT).show();
            Intent intent = new Intent(SignupActivity.this, LoginActivity.class);
            startActivity(intent);
        }
    }

    private void checkIdAvailability(final String id) {
        // Logic to check ID availability, e.g., send request to server
        // Placeholder logic
        Toast.makeText(SignupActivity.this, "ID is available.", Toast.LENGTH_SHORT).show();
    }
}
