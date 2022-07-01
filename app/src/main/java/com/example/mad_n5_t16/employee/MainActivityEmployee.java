package com.example.mad_n5_t16.employee;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_n5_t16.MainActivity;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.TheAnh.Activity.Employee.DiaDiemHienMauActivity;
import com.example.mad_n5_t16.user.MainActivityUser;

public class MainActivityEmployee extends AppCompatActivity {

    TextView txtHeader, txtSoLuongDangKyHienmau, txtNhomMauA,txtNhomMauB,txtNhomMauO, txtNhomMauAB ;
    ImageView markerEmployee, hospitalEmployee, order_historyEmployee;
    int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_employee);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        Intent intent=getIntent();
        id = intent.getIntExtra("id", 0);
        txtHeader = findViewById(R.id.textTitle);
        txtHeader.setText("Chào mừng đến với ngân hàng máu");

        txtSoLuongDangKyHienmau = findViewById(R.id.txtSoDonDangKy);
        txtNhomMauA = findViewById(R.id.txtNhomMauA);
        txtNhomMauB = findViewById(R.id.txtNhomMauB);
        txtNhomMauO = findViewById(R.id.txtNhomMauO);
        txtNhomMauAB = findViewById(R.id.txtNhomMauAB);

//        if(id == 0 ){
//            dangNhapHetHan();
//        } else {
//            txtSoLuongDangKyHienmau.setText(dat_laysoLuongDangKyHienMau()+"");
//            txtNhomMauA.setText(dat_laySoLuongMauConLaiTheoNhomMau("A")+"");
//            txtNhomMauB.setText(dat_laySoLuongMauConLaiTheoNhomMau("B")+"");
//            txtNhomMauO.setText(dat_laySoLuongMauConLaiTheoNhomMau("O")+"");
//            txtNhomMauAB.setText(dat_laySoLuongMauConLaiTheoNhomMau("AB")+"");
//        }
        txtSoLuongDangKyHienmau.setText(dat_laysoLuongDangKyHienMau()+"");
        txtNhomMauA.setText(dat_laySoLuongMauConLaiTheoNhomMau("A")+"");
        txtNhomMauB.setText(dat_laySoLuongMauConLaiTheoNhomMau("B")+"");
        txtNhomMauO.setText(dat_laySoLuongMauConLaiTheoNhomMau("O")+"");
        txtNhomMauAB.setText(dat_laySoLuongMauConLaiTheoNhomMau("AB")+"");

        markerEmployee = findViewById(R.id.markerEmployee);
        hospitalEmployee = findViewById(R.id.hospitalEmployee);
        order_historyEmployee = findViewById(R.id.order_historyEmployee);

        hospitalEmployee.setImageResource(R.drawable.hospital_2);

        markerEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMarker = new Intent(MainActivityEmployee.this, DiaDiemHienMauActivity.class);
                startActivity(goMarker);
                finish();
            }
        });
        order_historyEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHistory = new Intent(MainActivityEmployee.this, LichSuSuDungMauActivity.class);
                startActivity(goHistory);
                finish();
            }
        });
    }

    public void clickTest(View view){
        Intent goDetail = new Intent(MainActivityEmployee.this, DangKyHienMauActivityEmployee.class);
        startActivity(goDetail);
        finish();
    }

    public int dat_laysoLuongDangKyHienMau(){
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        return db.dat_laySoLuongDangKyHienMau();
    }
    public int dat_laySoLuongMauConLaiTheoNhomMau(String nhomMau){
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        return db.dat_laySoLuongMauConLaiTheoNhomMau(nhomMau);
    }
    public void dangNhapHetHan(){
        Toast.makeText(this, "Đăng nhập hết hạn", Toast.LENGTH_SHORT).show();
        finish();
    }
}