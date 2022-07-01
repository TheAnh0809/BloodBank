package com.example.mad_n5_t16.TheAnh.Activity.Employee;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.TimePicker;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;
import com.example.mad_n5_t16.Public.model_class.LichHienMau;
import com.example.mad_n5_t16.Public.model_class.ThoiGian;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.employee.MainActivityEmployee;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class ThemLichHienMau_Activity extends AppCompatActivity {
    ImageView btnThem;
    TextView txtNgay, txtGioBatDau, txtGioKetThuc, txtNameTitle;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh_employee_themlichienmau);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        btnThem = findViewById(R.id.imagethemlich);
        txtGioBatDau = findViewById(R.id.textChonGioBatDau);
        txtGioKetThuc = findViewById(R.id.textChonGioKetThuc);
        txtNgay = findViewById(R.id.textChonNgay);
        txtNameTitle = findViewById(R.id.textTitle);
        txtNameTitle.setText("Chọn thời gian cho lịch hiến máu");
        databaseHelper = new DatabaseHelper(this);
        String currentDate = new SimpleDateFormat("yyyy/MM/dd", Locale.getDefault()).format(new Date());
        txtNgay.setText(currentDate);
        txtGioBatDau.setText("00:00");
        txtGioKetThuc.setText("00:00");
        txtGioBatDau.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTimePicker(txtGioBatDau);
            }
        });
        txtGioKetThuc.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popTimePicker(txtGioKetThuc);
            }
        });
        txtNgay.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                popDatePicker(txtNgay);
            }
        });
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                themLichHienMau();
            }
        });
    }
    public void themLichHienMau(){
        ThoiGian tg = new ThoiGian(txtNgay.getText().toString(), txtGioBatDau.getText().toString(), txtGioKetThuc.getText().toString());
        DiaDiem dd = (DiaDiem) getIntent().getSerializableExtra("diadiem");
        LichHienMau lichHienMau = new LichHienMau(tg, "", dd);
        databaseHelper.addLichHienMau(lichHienMau);
        Toast.makeText(this, "Thêm lịch hiến máu thành công", Toast.LENGTH_SHORT).show();
        Intent goMarker = new Intent(ThemLichHienMau_Activity.this, DiaDiemHienMauActivity.class);
        startActivity(goMarker);
        finish();
    }
    public void popDatePicker(TextView textView){
        final int[] year = new int[1];
        final int[] month = new int[1];
        final int[] day = new int[1];
        DatePickerDialog.OnDateSetListener onDateSetListener = new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int getyear, int getmonth, int dayOfMonth) {
                year[0] = getyear;
                month[0] = getmonth + 1;
                day[0] = dayOfMonth;
                textView.setText(String.format(Locale.getDefault(), "%02d/%02d/%04d", day[0], month[0], year[0]));
            }
        };
        DatePickerDialog datePickerDialog = new DatePickerDialog(this, onDateSetListener, day[0], month[0], year[0]);
        datePickerDialog.setTitle("Chọn thời gian");
        datePickerDialog.show();
    }
    public void popTimePicker(TextView textView){
        final int[] house = new int[1];
        final int[] minute = new int[1];
        TimePickerDialog.OnTimeSetListener onTimeSetListener =  new TimePickerDialog.OnTimeSetListener() {
            @Override
            public void onTimeSet(TimePicker view, int selectedHouse, int selectedMinute) {
                house[0] = selectedHouse;
                minute[0] = selectedMinute;
                textView.setText(String.format(Locale.getDefault(), "%02d:%02d", house[0], minute[0]));
            }
        };
        TimePickerDialog timePickerDialog = new TimePickerDialog(this, onTimeSetListener, house[0], minute[0], true);
        timePickerDialog.setTitle("Chọn thời gian");
        timePickerDialog.show();
    }
}