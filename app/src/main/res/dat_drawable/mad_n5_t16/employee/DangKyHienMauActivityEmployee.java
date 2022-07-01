package com.example.mad_n5_t16.employee;

import android.os.Bundle;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.R;

public class DangKyHienMauActivityEmployee extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_hien_mau_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
    }
}