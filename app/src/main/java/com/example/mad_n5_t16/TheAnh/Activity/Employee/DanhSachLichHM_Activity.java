package com.example.mad_n5_t16.TheAnh.Activity.Employee;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;
import com.example.mad_n5_t16.Public.model_class.LichHienMau;
import com.example.mad_n5_t16.Public.model_class.ThoiGian;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.TheAnh.ModelAdapter.ThoiGianCuaDiaDiemAdapter;

import java.io.Serializable;
import java.util.ArrayList;

public class DanhSachLichHM_Activity extends AppCompatActivity {
    ThoiGianCuaDiaDiemAdapter thoiGianCuaDiaDiemAdapter;
    ArrayList<LichHienMau> list;
    ListView listView;
    ImageButton btnThem;
    TextView txtNameTitle, txtDD;
    DatabaseHelper databaseHelper;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh_employee_danhsachlichhienmau);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        listView = findViewById(R.id.listLichHien);
        btnThem = findViewById(R.id.imagethemlich);
        txtNameTitle = findViewById(R.id.textTitle);
        txtDD = findViewById(R.id.textDiaDiem);
        txtNameTitle.setText("Danh sách lịch hiến máu");
        list = new ArrayList<>();
        databaseHelper = new DatabaseHelper(this);
        fillData();
        btnThem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(DanhSachLichHM_Activity.this, ThemLichHienMau_Activity.class);
                intent.putExtra("diadiem", (Serializable) getIntent().getSerializableExtra("diadiem"));
                startActivity(intent);
                finish();
            }
        });
    }
    public void fillData(){
        DiaDiem diaDiem = (DiaDiem) getIntent().getSerializableExtra("diadiem");
        txtDD.setText(diaDiem.getTenDiaDiem());
        list = databaseHelper.getLichHienMau(diaDiem);
        thoiGianCuaDiaDiemAdapter = new ThoiGianCuaDiaDiemAdapter(list, this, R.layout.item_thoigiancuadiadiem, databaseHelper, this);
        listView.setAdapter(thoiGianCuaDiaDiemAdapter);
    }
}