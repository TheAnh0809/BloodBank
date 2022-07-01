package com.example.mad_n5_t16.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.mad_n5_t16.Public.model_class.DangKyHienMau;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.employee.DangKyHienMauActivityEmployee;
import com.example.mad_n5_t16.employee.MainActivityEmployee;

public class DienThongTinDangKyActivity extends AppCompatActivity {
    int maLichHienMau;
    int maNguoiHienMau;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dotv_layout_dienthongtindangky);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        TextView toolbar = findViewById(R.id.textTitle);
        toolbar.setText("Thông tin đăng ký");

        View cau1 = findViewById(R.id.dotv_cauhoi_1_dienthongtindangky);
        TextView cau1index = cau1.findViewById(R.id.dotv_index_dienthongtindangky);
        TextView cau1cauhoi = cau1.findViewById(R.id.dotv_cauhoi_dienthongtindangky);
        cau1index.setText("1");
        cau1cauhoi.setText("Quý vị đã từng hiến máu chưa?");

        View cau2 = findViewById(R.id.dotv_cauhoi_2_dienthongtindangky);
        TextView cau2index = cau2.findViewById(R.id.dotv_index_dienthongtindangky);
        TextView cau2cauhoi = cau2.findViewById(R.id.dotv_cauhoi_dienthongtindangky);
        cau2index.setText("2");
        cau2cauhoi.setText("Từ nhỏ Quý vị có bị các bệnh mãn tính không?");

        View cau3 = findViewById(R.id.dotv_cauhoi_3_dienthongtindangky);
        TextView cau3index = cau3.findViewById(R.id.dotv_index_dienthongtindangky);
        TextView cau3cauhoi = cau3.findViewById(R.id.dotv_cauhoi_dienthongtindangky);
        cau3index.setText("3");
        cau3cauhoi.setText("Trong vòng 1 tuần gần đây, Quý vị có bị cúm, ho, nhức đầu, ...?");

        View cau4 = findViewById(R.id.dotv_cauhoi_4_dienthongtindangky);
        TextView cau4index = cau4.findViewById(R.id.dotv_index_dienthongtindangky);
        TextView cau4cauhoi = cau4.findViewById(R.id.dotv_cauhoi_dienthongtindangky);
        cau4index.setText("4");
        cau4cauhoi.setText("Quý vị đã được tiêm mũi 3 vaccin Covid chưa?");

        View cau5 = findViewById(R.id.dotv_cauhoi_5_dienthongtindangky);
        TextView cau5index = cau5.findViewById(R.id.dotv_index_dienthongtindangky);
        TextView cau5cauhoi = cau5.findViewById(R.id.dotv_cauhoi_dienthongtindangky);
        cau5index.setText("5");
        cau5cauhoi.setText("Quý vị hiện là đối tượng tàn tật hoặc hưởng trợ cấp tàn tật hoặc nạn nhân chất độc màu da cam không?");

        Intent intent = getIntent();
        maLichHienMau = intent.getIntExtra("maLichHienMau",0);
        SharedPreferences sharedPreferences = getSharedPreferences("data", MODE_PRIVATE);
        maNguoiHienMau = sharedPreferences.getInt("idNguoiHienMau",0);
    }

    public void btnDangKyHienMau(View view){
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        db.luuDangKyHienMau(maNguoiHienMau, maLichHienMau);
        Intent success = new Intent(DienThongTinDangKyActivity.this, DangKyThanhCongActivity.class);


        startActivity(success);
    }
}