package com.example.mad_n5_t16;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

public class ThongBaoActivity extends AppCompatActivity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dotv_layout_thongbao);
        TextView titleBar = findViewById(R.id.textTitle);
        titleBar.setText("Thông báo");
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ImageView markerNoti = findViewById(R.id.marker);
        markerNoti.setImageResource(R.drawable.marker_2);
        ImageView homeGo = findViewById(R.id.home);
        homeGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHome = new Intent(com.example.mad_n5_t16.user.ThongBaoActivity.this, MainActivity.class);
                startActivity(goHome);
            }
        });
    }
}