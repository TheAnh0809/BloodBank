package com.example.mad_n5_t16;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.employee.MainActivityEmployee;
<<<<<<< HEAD
import com.example.mad_n5_t16.user.BloodDonationHistoryActivity;
=======
>>>>>>> e3f9c4c7200ceefd2095c1298bc36b61c0aef2a5
import com.example.mad_n5_t16.user.LoginActivity;
import com.example.mad_n5_t16.user.MainActivityUser;
import com.example.mad_n5_t16.user.RegisterActivity;

public class MainActivity extends AppCompatActivity {
    private Button btnCallActivity1;
    private Button btnCallActivity2;
    private Button btnCallActivity3;
    private Button btnCallActivity4;
    private Button btnCallActivity5;
    private Button btnCallActivity6;
    private Button btnHuongDangKy;
    private Button btnHuongDangNhap;
    private Button btnHuongLichSuHienMau;
    private Button btnMHChinhUser;
    private Button btnMHChinhEmployee;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnCallActivity1 = (Button) findViewById(R.id.btnCallActivity1);
        btnHuongDangNhap = (Button) findViewById(R.id.btnHuongDangNhap);
        btnHuongDangKy = (Button) findViewById(R.id.btnHuongDangKy);
        btnHuongLichSuHienMau = (Button) findViewById(R.id.btnHuongLichSuHienMau);
        btnMHChinhUser = (Button) findViewById(R.id.btnMHChinhUser);
        btnMHChinhEmployee = (Button) findViewById(R.id.btnMHChinhEmployee);

        btnCallActivity1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, ThongBaoActivity.class);
                startActivity(intent);
            }
        });


        btnMHChinhUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivityUser.class);
                startActivity(intent);
            }
        });
        btnMHChinhEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, MainActivityEmployee.class);
                startActivity(intent);
            }
        });
        btnHuongDangNhap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, LoginActivity.class);
                startActivity(intent);
            }
        });

        btnHuongDangKy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivity(intent);
            }
        });

        btnHuongLichSuHienMau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(MainActivity.this, BloodDonationHistoryActivity.class);
                startActivity(intent);
            }
        });
    }
}