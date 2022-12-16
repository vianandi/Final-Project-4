package com.mbkm.project4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputLayout;
import com.mbkm.project4.Model.Users;
import com.mbkm.project4.Pre.Prevalent;
import com.mbkm.project4.adapter.AlertDialogManager;
import com.mbkm.project4.database.DatabaseHelper;
import com.mbkm.project4.session.SessionManager;

import java.util.Objects;

public class Login extends AppCompatActivity {
    TextView textRegister;
    ImageView image;
    TextView text,desc;
    TextInputLayout input_username,input_password;
    Button btnLogin;
    ProgressDialog loadbar;
    AlertDialogManager alert = new AlertDialogManager();
    SessionManager session;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    private String DBname = "Users";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        textRegister = findViewById(R.id.register);
        image = findViewById(R.id.logo);
        text = findViewById(R.id.text);
        desc = findViewById(R.id.desc);
        input_username = findViewById(R.id.username);
        input_password = findViewById(R.id.password);
        btnLogin = findViewById(R.id.btn_login);
        loadbar = new ProgressDialog(this);

        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        session = new SessionManager(getApplicationContext());

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login();
            }
        });

        textRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Login.this,Register.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image,"logo");
                pairs[1] = new Pair<View, String>(text,"text");
                pairs[2] = new Pair<View, String>(desc,"desc");
                pairs[3] = new Pair<View, String>(input_username,"username_trans");
                pairs[4] = new Pair<View, String>(input_password,"password_trans");
                pairs[5] = new Pair<View, String>(btnLogin,"btn_trans");
                pairs[6] = new Pair<View, String>(textRegister,"text_login_register_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Login.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        });
    }

    private void login() {
        String username = input_username.getEditText().getText().toString();
        String password = input_password.getEditText().getText().toString();

        // Check if username, password is filled
        try {
            // Check if username, password is filled
            if (username.trim().length() > 0 && password.trim().length() > 0) {
                dbHelper.open();

                if (dbHelper.Login(username, password)) {
                    session.createLoginSession(username);

                    finish();
                    Intent i = new Intent(getApplicationContext(), landing_page.class);
                    startActivity(i);

                } else {
                    alert.showAlertDialog(Login.this, "Login gagal..", "Email atau Password salah!", false);

                }
            } else {
                alert.showAlertDialog(Login.this, "Login gagal..", "Form tidak boleh kosong!", false);
            }
        } catch (Exception e) {
            Toast.makeText(Login.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }
    }
}