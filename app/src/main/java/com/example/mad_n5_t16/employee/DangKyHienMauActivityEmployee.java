package com.example.mad_n5_t16.employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.Public.model_class.DangKyHienMau;
import com.example.mad_n5_t16.Public.model_class.DangKyHienMauAdapter;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;
import com.example.mad_n5_t16.Public.model_class.LichHienMau;
import com.example.mad_n5_t16.Public.model_class.NguoiHienMau;
import com.example.mad_n5_t16.Public.model_class.ThoiGian;

import java.util.ArrayList;

public class DangKyHienMauActivityEmployee extends AppCompatActivity {

    RecyclerView listDSDangKyHienMau;
    RecyclerView.Adapter adapter;
    RecyclerView.LayoutManager layoutManager;
    TextView txtHoVaTen;
    ImageView imgTroVe;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dang_ky_hien_mau_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        imgTroVe = findViewById(R.id.imgTroVe);

        imgTroVe.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(DangKyHienMauActivityEmployee.this, MainActivityEmployee.class);
                startActivity(intent);
                finish();
            }
        });
        txtHoVaTen = findViewById(R.id.textTitle);
        txtHoVaTen.setText("Đơn đăng ký hôm nay");

       ArrayList<DangKyHienMau> dsDangKyHienMau = dat_laydsDangKyHienMau();
//       ArrayList<DangKyHienMau> dsDangKyHienMau = new ArrayList<DangKyHienMau>();
//
//
//        NguoiHienMau tempNHM = new NguoiHienMau("taiKhoan1", "1", "Trịnh Tiến Đạt", "1",
//                "23122000", "abc@gmail.com", "123456789", " ", "0368257596");
//        ThoiGian tempTG = new ThoiGian("25042022", "07:00", "11h:00");
//        DiaDiem tempDD = new DiaDiem("Viện Huyết học và truyền máu trung ương");
//        LichHienMau tempLHM = new LichHienMau(tempTG, " ", tempDD);
//        DangKyHienMau temp = new DangKyHienMau(tempLHM, tempNHM, 350);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);
//        dsDangKyHienMau.add(temp);


        listDSDangKyHienMau = findViewById(R.id.listDSDangKyHienMau);
        listDSDangKyHienMau.setHasFixedSize(true);

        layoutManager = new LinearLayoutManager(this);
        listDSDangKyHienMau.setLayoutManager(layoutManager);

        int count = dsDangKyHienMau.size();
        DangKyHienMauAdapter dangKyHienMauAdapter = new DangKyHienMauAdapter(this, dsDangKyHienMau);
        listDSDangKyHienMau.setAdapter(dangKyHienMauAdapter);


    }

    public ArrayList<DangKyHienMau> dat_laydsDangKyHienMau() {
        ArrayList<DangKyHienMau> result = new ArrayList<DangKyHienMau>();
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        result = db.dat_layDSDangKyHienMau();
        return result;
    }
}