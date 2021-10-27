package com.android.travelapp;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;

public class SignUp extends AppCompatActivity {
    TextView txtSignIn;

    ImageView img;
    TextView txtTitle, txtSub;
    TextInputLayout inpFullname, inpEmail, inpPhone, inpUser, inpPass, inpRePass;
    Button btnSignUp, btnReset;

    SharedPreferences preferences;

    private static final String KEY_NAME = "name";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_PHONE = "phone";
    private static final String KEY_USER = "user";
    private static final String KEY_PASS = "pass";
    private static final String KEY_REPASS = "repass";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sign_up);

        img = findViewById(R.id.img_logo);
        txtTitle = findViewById(R.id.tv_title_regis);
        inpFullname = findViewById(R.id.name);
        inpEmail= findViewById(R.id.email);
        inpPhone= findViewById(R.id.phone);
        inpUser = findViewById(R.id.username_regis);
        inpPass = findViewById(R.id.password_regis);
        inpRePass = findViewById(R.id.retype_password);
        btnSignUp = findViewById(R.id.btn_signUp);
        txtSignIn = findViewById(R.id.btn_signIn);
        btnReset = findViewById(R.id.btn_reset);

        preferences = getSharedPreferences("userInfo", 0);

        btnSignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nameValue = inpFullname.getEditText().getText().toString();
                String emailValue = inpEmail.getEditText().getText().toString();
                String phoneValue = inpPhone.getEditText().getText().toString();
                String userValue = inpUser.getEditText().getText().toString();
                String passValue = inpPass.getEditText().getText().toString();
                String repassValue = inpRePass.getEditText().getText().toString();

                if (passValue.equals(repassValue)){
                    SharedPreferences.Editor editor = preferences.edit();
                    editor.putString(KEY_NAME, nameValue);
                    editor.putString(KEY_EMAIL, emailValue);
                    editor.putString(KEY_PHONE, phoneValue);
                    editor.putString(KEY_USER, userValue);
                    editor.putString(KEY_PASS, passValue);
                    editor.putString(KEY_REPASS, repassValue);
                    editor.apply();

                    try{
                        if (nameValue.equals("") ||
                        emailValue.equals("") ||
                        phoneValue.equals("") ||
                        userValue.equals("") ||
                        passValue.equals("") ||
                        repassValue.equals("")){
                            Toast.makeText(SignUp.this, "Data Cannot be Empty. \nData can be Exhausted.", Toast.LENGTH_LONG).show();
                        }else{
                            String name = preferences.getString(KEY_NAME, null);
                            if (name != null){
                                Toast.makeText(SignUp.this, "Successful Registration", Toast.LENGTH_LONG).show();
                                Intent intent = new Intent(SignUp.this, LoginPage.class);
                                startActivity(intent);
                            }
                        }
                    }catch (Exception e){
                        Toast.makeText(SignUp.this, "Username has been used", Toast.LENGTH_LONG).show();
                    }
                }else{
                    Toast.makeText(SignUp.this, "Password doesn't match", Toast.LENGTH_LONG).show();
                }
            }
        });

        btnReset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                reset();
            }
        });

        txtSignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(SignUp.this, LoginPage.class);
                startActivity(intent);
            }
        });
    }

    public void reset(){
        inpFullname.getEditText().setText(null);
        inpEmail.getEditText().setText(null);
        inpPhone.getEditText().setText(null);
        inpUser.getEditText().setText(null);
        inpPass.getEditText().setText(null);
        inpRePass.getEditText().setText(null);
    }
}