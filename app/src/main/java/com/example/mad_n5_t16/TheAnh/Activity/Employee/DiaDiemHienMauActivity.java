package com.example.mad_n5_t16.TheAnh.Activity.Employee;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.SearchView;
import android.widget.TextView;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.TheAnh.ModelAdapter.DiaDiemHienMauAdapter;
import com.example.mad_n5_t16.employee.LichSuSuDungMauActivity;
import com.example.mad_n5_t16.employee.MainActivityEmployee;

import java.io.Serializable;
import java.util.ArrayList;

public class DiaDiemHienMauActivity extends AppCompatActivity {

    DatabaseHelper databaseHelper;
    ArrayList<DiaDiem> listDiaDiem;
    ListView listView;
    SearchView txtSearch;
    TextView txtNameTitle;
    ImageView markerEmployee, hospitalEmployee, order_historyEmployee;
    DiaDiemHienMauAdapter diaDiemHienMauAdapter;
    @RequiresApi(api = Build.VERSION_CODES.N)
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mh_employee_diadiemhienmau);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        txtNameTitle = findViewById(R.id.textTitle);
        txtNameTitle.setText("Địa điểm hiến máu");
        listView = findViewById(R.id.listDiaDiem);
        txtSearch = findViewById(R.id.txtSearch);
        markerEmployee = findViewById(R.id.markerEmployee);
        hospitalEmployee = findViewById(R.id.hospitalEmployee);
        order_historyEmployee = findViewById(R.id.order_historyEmployee);

        markerEmployee.setImageResource(R.drawable.marker_2);
        //getdata
        databaseHelper = new DatabaseHelper(this);
        listDiaDiem = new ArrayList<>();
        getData("");

        hospitalEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHospital = new Intent(DiaDiemHienMauActivity.this, MainActivityEmployee.class);
                startActivity(goHospital);
                finish();
            }
        });
        order_historyEmployee.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHistory = new Intent(DiaDiemHienMauActivity.this, LichSuSuDungMauActivity.class);
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

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(DiaDiemHienMauActivity.this, DanhSachLichHM_Activity.class);
                intent.putExtra("diadiem", (Serializable) listDiaDiem.get(position));
                startActivity(intent);
                finish();
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
    }
}