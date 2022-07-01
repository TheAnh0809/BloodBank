package com.example.mad_n5_t16.TheAnh.Activity.User;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.user.BloodDonationHistoryActivity;
import com.example.mad_n5_t16.MainActivity;
import com.example.mad_n5_t16.Public.model_class.NguoiHienMau;
import com.example.mad_n5_t16.Public.model_class.TaiKhoan;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.ThongBaoActivity;
import com.example.mad_n5_t16.user.DangKyHienMauActivity;
import com.example.mad_n5_t16.user.DanhSachLichHienMauActivity;
import com.example.mad_n5_t16.user.LoginActivity;
import com.example.mad_n5_t16.user.MainActivityUser;

import java.io.Serializable;


public class ThongTinCaNhan_Activity extends AppCompatActivity {
    private TextView txtNameTitle, txtFullName, txtMainTitle;
    private FrameLayout btnThongtin, btnThongBao, btnDangXuat;
    private NguoiHienMau nguoiHienMau;
    DatabaseHelper databaseHelper;
    ImageView marker, home, heart, history, infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh_thongtincanhan);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        // Anh xa
        txtFullName = findViewById(R.id.textFullName);
        txtNameTitle = findViewById(R.id.textTitle);
        txtNameTitle.setText("Thông tin cá nhân");
        txtMainTitle = findViewById(R.id.textImageName);
        btnThongtin = findViewById(R.id.frameThongTinNguoiDung);
        btnThongBao = findViewById(R.id.frameThongBao);
        btnDangXuat = this.findViewById(R.id.frameDangXuat);
        databaseHelper = new DatabaseHelper(this);
        //setup toolBar
        home = findViewById(R.id.home);
        home.setImageResource(R.drawable.home_1);
        marker = findViewById(R.id.marker);
        marker.setImageResource(R.drawable.marker_1);
        heart = findViewById(R.id.heart_plus);
        heart.setImageResource(R.drawable.heart_plus_1);
        history = findViewById(R.id.order_history);
        history.setImageResource(R.drawable.order_history_1);
        infor = findViewById(R.id.guest_male);
        infor.setImageResource(R.drawable.guest_male_2);
        filldata();
        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMarker = new Intent(ThongTinCaNhan_Activity.this, DiaDiem_Activity.class);
                startActivity(goMarker);
                finish();
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHeart = new Intent(ThongTinCaNhan_Activity.this, DanhSachLichHienMauActivity.class);
                startActivity(goHeart);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHistory = new Intent(ThongTinCaNhan_Activity.this, MainActivityUser.class);
                startActivity(goHistory);
                finish();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent goHistory = new Intent(ThongTinCaNhan_Activity.this, BloodDonationHistoryActivity.class);
                startActivity(goHistory);
                finish();
            }
        });

        btnThongtin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ThongTinCaNhan_Activity.this, ChinhSuaThongTinCaNhan_Activity.class);
                intent.putExtra("data", (Serializable) nguoiHienMau);
                startActivity(intent);
                finish();
            }
        });
        btnDangXuat.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHistory = new Intent(ThongTinCaNhan_Activity.this, LoginActivity.class);
                startActivity(goHistory);
            }
        });
    }
    public void filldata(){
        int id = getSharedPreferences("data", MODE_PRIVATE).getInt("id", 0);
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setId(id);
        nguoiHienMau = databaseHelper.dat_getNguoiHienMau(taiKhoan);
        if(nguoiHienMau.getHoTen() != null && nguoiHienMau.getHoTen().length() > 0) {
            String[] temp = nguoiHienMau.getHoTen().split(" ");
            Character icon = temp[temp.length - 1].charAt(0);
            txtMainTitle.setText(icon.toString());
            txtFullName.setText(nguoiHienMau.getHoTen());
        }
        else {
            Toast.makeText(this, "Chưa có thông tin", Toast.LENGTH_SHORT).show();
        }
    }
}