package com.mbkm.project4;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.app.ActivityOptions;
import android.app.ProgressDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Color;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Pair;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputLayout;
import com.mbkm.project4.database.DatabaseHelper;

import java.util.HashMap;
import java.util.Objects;

public class Register extends AppCompatActivity {
    TextView textLogin,text,desc;
    ImageView image;
    TextInputLayout input_fullname,input_username,input_phone,input_email,input_password;
    Button register;
    DatabaseHelper dbHelper;
    SQLiteDatabase db;
    private ProgressDialog loadbar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);

        getWindow().getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_STABLE | View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);

        if (Build.VERSION.SDK_INT >= 21) {
            setWindowFlag(this, WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS, false);
            getWindow().setStatusBarColor(Color.TRANSPARENT);
        }


        image = findViewById(R.id.logo);
        text = findViewById(R.id.text);
        desc = findViewById(R.id.desc);
        input_fullname = findViewById(R.id.fullname);
        input_username = findViewById(R.id.username);
        input_phone = findViewById(R.id.phone);
        input_email = findViewById(R.id.email);
        input_password = findViewById(R.id.password);
        register = findViewById(R.id.register);
        textLogin = findViewById(R.id.login);
        loadbar = new ProgressDialog(this);


        dbHelper = new DatabaseHelper(this);
        db = dbHelper.getWritableDatabase();

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                register();
            }
        });

        textLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(Register.this,Login.class);

                Pair[] pairs = new Pair[7];
                pairs[0] = new Pair<View, String>(image,"logo");
                pairs[1] = new Pair<View, String>(text,"text");
                pairs[2] = new Pair<View, String>(desc,"desc");
                pairs[3] = new Pair<View, String>(input_username,"username_trans");
                pairs[4] = new Pair<View, String>(input_password,"password_trans");
                pairs[5] = new Pair<View, String>(register,"btn_trans");
                pairs[6] = new Pair<View, String>(textLogin,"text_login_register_trans");

                ActivityOptions options = ActivityOptions.makeSceneTransitionAnimation(Register.this,pairs);
                startActivity(intent,options.toBundle());
                finish();
            }
        });
    }

    private void register() {
        String fullname = Objects.requireNonNull(input_fullname.getEditText()).getText().toString();
        String username = Objects.requireNonNull(input_username.getEditText()).getText().toString();
        String phone = Objects.requireNonNull(input_phone.getEditText()).getText().toString();
        String email = Objects.requireNonNull(input_email.getEditText()).getText().toString();
        String password = Objects.requireNonNull(input_password.getEditText()).getText().toString();
        try {
            if (fullname.trim().length() > 0 && username.trim().length() > 0 && phone.trim().length() > 0 && email.trim().length() > 0 && password.trim().length() > 0) {
                dbHelper.open();
                dbHelper.Register(fullname, username, phone, email, password);
                Toast.makeText(Register.this, "Daftar berhasil", Toast.LENGTH_LONG).show();
                finish();
                Intent intent = new Intent(Register.this,Login.class);
                startActivity(intent);
            } else {
                Toast.makeText(Register.this, "Daftar gagal, lengkapi form!", Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(Register.this, e.getMessage(), Toast.LENGTH_LONG).show();
        }

    }

    @Override
    public void finish() {
        super.finish();
    }

    public static void setWindowFlag(Activity activity, final int bits, boolean on) {

        Window win = activity.getWindow();
        WindowManager.LayoutParams winParams = win.getAttributes();
        if (on) {
            winParams.flags |= bits;
        } else {
            winParams.flags &= ~bits;
        }
        win.setAttributes(winParams);
    }

}