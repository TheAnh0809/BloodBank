package com.example.mad_n5_t16;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.mad_n5_t16.user.DangKyThanhCongActivity;
import com.example.mad_n5_t16.user.LoginActivity;
import com.example.mad_n5_t16.user.MainActivityUser;

public class Starter extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dotv_layout_laucher);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        Handler handler = new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent goHome = new Intent(Starter.this, LoginActivity.class);
                startActivity(goHome);
                finish();
            }
        }, 1000);
    }
}