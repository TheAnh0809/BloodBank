package com.example.mad_n5_t16.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mad_n5_t16.user.BloodDonationHistoryActivity;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.LichHienMau;
import com.example.mad_n5_t16.Public.model_class.ThoiGian;
import com.example.mad_n5_t16.TheAnh.Activity.User.DiaDiem_Activity;
import com.example.mad_n5_t16.TheAnh.Activity.User.ThongTinCaNhan_Activity;

import java.io.Serializable;
import java.util.ArrayList;

public class DangKyHienMauActivity extends AppCompatActivity {
    ArrayList<ItemModelDangKyHienMauActivity> ls;
    DangKyHienMauItems adapter;
    ListView listView;
    DatabaseHelper dbh;
    ImageView marker, home, heart, history, infor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dotv_layout_dangkyhienmau);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        TextView toolbar = findViewById(R.id.textTitle);
        toolbar.setText("Chọn lịch hiến máu");
        ls = new ArrayList<>();
        dbh = new DatabaseHelper(getBaseContext());
        ArrayList<LichHienMau> lsDiaDiem = dbh.do_laydsdiadiemhienmau();
        ArrayList<LichHienMau> lsCount = dbh.do_getCountnguoidangky();
        // Lay data
        for (int i = 0; i < lsDiaDiem.size(); ++i) {
            int sodondangky = dbh.do_SoLuongDangKyTheoMaLichHienMau(lsDiaDiem.get(i).getId());
            ItemModelDangKyHienMauActivity item = new ItemModelDangKyHienMauActivity(lsDiaDiem.get(i).getDiaDiem().getTenDiaDiem(),
                    "Thời gian :  " + lsDiaDiem.get(i).getThoiGian().getGioBatDau() + " - " + lsDiaDiem.get(i).getThoiGian().getGioKetThuc()
                            + " " + lsDiaDiem.get(i).getThoiGian().getNgay(),
                    "Số người đã đăng ký : " + sodondangky);
            item.setMaLichHienMau(lsDiaDiem.get(i).getId());
            ls.add(item);
        }

        adapter = new DangKyHienMauItems(ls);
        listView = findViewById(R.id.dotv_list_dangkyhienmau);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Intent intent = new Intent(DangKyHienMauActivity.this, DienThongTinDangKyActivity.class);
                intent.putExtra("maLichHienMau", ls.get(i).getMaLichHienMau());
                startActivity(intent);
            }
        });

        //toolbar
        ImageView markerNoti = findViewById(R.id.heart_plus);
        markerNoti.setImageResource(R.drawable.heart_plus_2);
        home = findViewById(R.id.home);
        home.setImageResource(R.drawable.home_1);
        marker = findViewById(R.id.marker);
        marker.setImageResource(R.drawable.marker_1);
        heart = findViewById(R.id.heart_plus);
        history = findViewById(R.id.order_history);
        history.setImageResource(R.drawable.order_history_1);
        infor = findViewById(R.id.guest_male);
        infor.setImageResource(R.drawable.guest_male_1);
        home.setOnClickListener(view -> {
            Intent goHome = new Intent(DangKyHienMauActivity.this, MainActivityUser.class);
            startActivity(goHome);
            finish();
        });
        marker.setOnClickListener(view -> {
            Intent goMarker = new Intent(DangKyHienMauActivity.this, DiaDiem_Activity.class);
            startActivity(goMarker);
            finish();
        });
        history.setOnClickListener(view -> {
            Intent goHistory = new Intent(DangKyHienMauActivity.this, BloodDonationHistoryActivity.class);
            startActivity(goHistory);
            finish();
        });
        infor.setOnClickListener(view -> {
            Intent goInfor = new Intent(DangKyHienMauActivity.this, ThongTinCaNhan_Activity.class);
            startActivity(goInfor);
            finish();
        });
    }
}

class ItemModelDangKyHienMauActivity {
    int maLichHienMau;
    String diadiem, thoigian, songuoidangky;

    public ItemModelDangKyHienMauActivity(String diadiem, String thoigian, String songuoidangky) {
        this.diadiem = diadiem;
        this.thoigian = thoigian;
        this.songuoidangky = songuoidangky;
    }

    public int getMaLichHienMau() {
        return maLichHienMau;
    }

    public void setMaLichHienMau(int maLichHienMau) {
        this.maLichHienMau = maLichHienMau;
    }

    public String getDiadiem() {
        return diadiem;
    }

    public void setDiadiem(String diadiem) {
        this.diadiem = diadiem;
    }

    public String getThoigian() {
        return thoigian;
    }

    public void setThoigian(String thoigian) {
        this.thoigian = thoigian;
    }

    public String getSonguoidangky() {
        return songuoidangky;
    }

    public void setSonguoidangky(String songuoidangky) {
        this.songuoidangky = songuoidangky;
    }
}

class DangKyHienMauItems extends BaseAdapter {

    final ArrayList<ItemModelDangKyHienMauActivity> ls;

    public DangKyHienMauItems(ArrayList<ItemModelDangKyHienMauActivity> ls) {
        this.ls = ls;
    }

    @Override
    public int getCount() {
        return ls.size();
    }

    @Override
    public Object getItem(int i) {
        return ls.get(i);
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View viewItem1;
        if (view == null) {
            viewItem1 = View.inflate(viewGroup.getContext(), R.layout.dotv_layout_items_dangkyhienmau, null);
        } else viewItem1 = view;

        ItemModelDangKyHienMauActivity items = (ItemModelDangKyHienMauActivity) getItem(i);
        ((TextView) viewItem1.findViewById(R.id.dotv_diadiem_dangkyhienmau)).setText(items.getDiadiem());
        ((TextView) viewItem1.findViewById(R.id.dotv_thoigian_dangkyhienmau)).setText(items.getThoigian());
        ((TextView) viewItem1.findViewById(R.id.dotv_luongnguoi_dangkyhienmau)).setText(items.getSonguoidangky());
        return viewItem1;
    }
}