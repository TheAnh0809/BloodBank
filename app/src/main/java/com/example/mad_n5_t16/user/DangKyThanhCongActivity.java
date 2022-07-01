package com.example.mad_n5_t16.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.example.mad_n5_t16.R;

public class DangKyThanhCongActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dotv_layout_dangkythanhcong);
        ActionBar actionBar = getSupportActionBar();
        assert actionBar != null;
        actionBar.hide();
        Intent intent = getIntent();
        int id = intent.getIntExtra("idNguoiDung",0);
        Handler handler = new Handler();
        handler.postDelayed(() -> {
            Intent goHome = new Intent(DangKyThanhCongActivity.this, MainActivityUser.class);
            goHome.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
            startActivity(goHome);
            goHome.putExtra("id",id);
        }, 1000);
    }
}