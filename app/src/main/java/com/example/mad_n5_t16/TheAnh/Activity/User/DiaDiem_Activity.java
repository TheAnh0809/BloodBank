package com.example.mad_n5_t16.TheAnh.Activity.User;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.user.BloodDonationHistoryActivity;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;
import com.example.mad_n5_t16.TheAnh.ModelAdapter.DiaDiemHienMauAdapter;
import com.example.mad_n5_t16.user.ChiTietDiaDiemHienMauActivity;
import com.example.mad_n5_t16.user.DangKyHienMauActivity;
import com.example.mad_n5_t16.user.DanhSachLichHienMauActivity;
import com.example.mad_n5_t16.user.DienThongTinDangKyActivity;
import com.example.mad_n5_t16.user.MainActivityUser;

import java.util.ArrayList;

public class DiaDiem_Activity extends AppCompatActivity {
    DatabaseHelper databaseHelper;
    ArrayList<DiaDiem> listDiaDiem;
    ListView listView;
    SearchView txtSearch;
    ImageView marker, home, heart, history, infor;
    DiaDiemHienMauAdapter diaDiemHienMauAdapter;
    TextView txtNameTitle;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh_diadiemhienmau);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        listView = findViewById(R.id.listDiadiemuser);
        txtSearch = findViewById(R.id.txtSearch);
        //getdata
        databaseHelper = new DatabaseHelper(this);
        listDiaDiem = new ArrayList<>();
        getData("");
        txtNameTitle = findViewById(R.id.textTitle);
        txtNameTitle.setText("Địa điểm hiến máu");
        home = findViewById(R.id.home);
        home.setImageResource(R.drawable.home_1);
        marker = findViewById(R.id.marker);
        marker.setImageResource(R.drawable.marker_2);
        heart = findViewById(R.id.heart_plus);
        heart.setImageResource(R.drawable.heart_plus_1);
        history = findViewById(R.id.order_history);
        history.setImageResource(R.drawable.order_history_1);
        infor = findViewById(R.id.guest_male);
        infor.setImageResource(R.drawable.guest_male_1);

        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMarker = new Intent(DiaDiem_Activity.this, ThongTinCaNhan_Activity.class);
                startActivity(goMarker);
                finish();
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHeart = new Intent(DiaDiem_Activity.this, DanhSachLichHienMauActivity.class);
                startActivity(goHeart);
                finish();
            }
        });
        home.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent goHistory = new Intent(DiaDiem_Activity.this, MainActivityUser.class);
                startActivity(goHistory);
                finish();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                Intent goHistory = new Intent(DiaDiem_Activity.this, BloodDonationHistoryActivity.class);
                startActivity(goHistory);
                finish();
            }
        });
        txtSearch.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                getData(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                getData(newText);
                return true;
            }
        });
    }
    public void  getData(String search){
        if(search.length() == 0){
            listDiaDiem = databaseHelper.getAllDiaDiem();
        } else {
            listDiaDiem = databaseHelper.searchDiaDiem(search);
        }
        diaDiemHienMauAdapter = new DiaDiemHienMauAdapter(listDiaDiem ,this, R.layout.item_diadiemhienmau);
        listView.setAdapter(diaDiemHienMauAdapter);
        listView.setOnItemClickListener((adapterView, view, i, l) -> {
            Intent intent = new Intent(DiaDiem_Activity.this, ChiTietDiaDiemHienMauActivity.class);
            intent.putExtra("idDiaDiem",listDiaDiem.get(i).getId());
            startActivity(intent);
        });
    }
}