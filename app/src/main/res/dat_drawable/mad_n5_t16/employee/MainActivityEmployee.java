package com.example.mad_n5_t16.employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.MainActivity;
import com.example.mad_n5_t16.R;

public class MainActivityEmployee extends AppCompatActivity {

    TextView txtHeader;
    ImageView markerEmployee, hospitalEmployee, order_historyEmployee;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        txtHeader = findViewById(R.id.textTitle);
        txtHeader.setText("Chào mừng đến với ngân hàng máu");

        markerEmployee = findViewById(R.id.markerEmployee);
        hospitalEmployee = findViewById(R.id.hospitalEmployee);
        order_historyEmployee = findViewById(R.id.order_historyEmployee);

        hospitalEmployee.setImageResource(R.drawable.hospital_2);

        markerEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMarker = new Intent(MainActivityEmployee.this, MainActivity.class);
                startActivity(goMarker);
            }
        });
        order_historyEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHistory = new Intent(MainActivityEmployee.this, MainActivity.class);
                startActivity(goHistory);
            }
        });
    }

    public void clickTest(View view){
        Intent goDetail = new Intent(MainActivityEmployee.this, DangKyHienMauActivityEmployee.class);
        startActivity(goDetail);
    }
}