package com.example.mad_n5_t16;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.NguoiHienMau;
import com.example.mad_n5_t16.Public.model_class.TaiKhoan;
import com.example.mad_n5_t16.user.BloodDonationHistoryActivity;
import com.example.mad_n5_t16.user.DanhSachLichHienMauActivity;
import com.example.mad_n5_t16.user.MainActivityUser;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;


public class ThongBaoActivity extends AppCompatActivity {
    ArrayList<ItemModelThongBaoActivity> ls;
    DangKyHienMauItems adapter;
    ListView listView;
    DatabaseHelper dbh;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dotv_layout_thongbao);
        TextView titleBar = findViewById(R.id.textTitle);
        titleBar.setText("Thông báo");
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();
        ImageView markerNoti = findViewById(R.id.marker);
        markerNoti.setImageResource(R.drawable.marker_2);
        ImageView homeGo = findViewById(R.id.home);
        ImageView locationGo = findViewById(R.id.marker);
        ImageView historyRegisterGo = findViewById(R.id.heart_plus);
        ImageView bloodHistoryGo = findViewById(R.id.order_history);
        ImageView infoGo = findViewById(R.id.guest_male);

        homeGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHome = new Intent(ThongBaoActivity.this, MainActivityUser.class);
                startActivity(goHome);
            }
        });
//        locationGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goHome = new Intent(ThongBaoActivity.this, MainActivityUser.class);
//                startActivity(goHome);
//            }
//        });
        historyRegisterGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHome = new Intent(ThongBaoActivity.this, DanhSachLichHienMauActivity.class);
                startActivity(goHome);
            }
        });
        bloodHistoryGo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHome = new Intent(ThongBaoActivity.this, BloodDonationHistoryActivity.class);
                startActivity(goHome);
            }
        });
//        infoGo.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Intent goHome = new Intent(ThongBaoActivity.this, MainActivityUser.class);
//                startActivity(goHome);
//            }
//        });


        ls = new ArrayList<>();
        dbh = new DatabaseHelper((getBaseContext()));
//        if(checkBirthday())
            ls.add(new ItemModelThongBaoActivity("Chúc mừng sinh nhật","Kính chúc bạn ngày sinh nhật ý nghĩa, ngập tràn niềm vui, hạnh phúc. Mong anh tiếp tục hiến máu cứu người !!!"));
//        if(checkHienMau())
            ls.add(new ItemModelThongBaoActivity("Bạn chú ý giữ sức khỏe!!!", "Hôm nay bạn có lịch hiến máu tại Viện huyết học Truyền máu TW. Bạn nhớ ăn uống đầy đủ, đi ngủ sớm để đảm bảo sức khỏe nhé!!!"));
        adapter = new DangKyHienMauItems(ls);
        listView = findViewById(R.id.dotv_danhsachthongbao);
        listView.setAdapter(adapter);
    }
    public boolean checkBirthday(){
        TaiKhoan taikhoanTest = new TaiKhoan("1","1","Trịnh Tiến Đạt", "khachhang");
        taikhoanTest.setId(1);
        TaiKhoan tk = dbh.nam_getTaiKhoanByUserNameAndPassWord("1", "1");
        if(taikhoanTest.getMatKhau().equals(tk.getMatKhau())){
            NguoiHienMau ttcn = dbh.dat_getNguoiHienMau(taikhoanTest);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(ttcn.getNgaySinh()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(c.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                    c.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)){
                //happy birthday
                return true;
            }
        }
        return false;
    }
    public boolean checkHienMau(){
        TaiKhoan taikhoanTest = new TaiKhoan("1","1","Trịnh Tiến Đạt", "khachhang");
        taikhoanTest.setId(1);
        TaiKhoan tk = dbh.nam_getTaiKhoanByUserNameAndPassWord("1", "1");
        if(taikhoanTest.getMatKhau().equals(tk.getMatKhau())){
            NguoiHienMau ttcn = dbh.dat_getNguoiHienMau(taikhoanTest);
            SimpleDateFormat sdf = new SimpleDateFormat("yyyy/mm/dd");
            Calendar c = Calendar.getInstance();
            try {
                c.setTime(sdf.parse(ttcn.getNgaySinh()));
            } catch (ParseException e) {
                e.printStackTrace();
            }
            if(c.get(Calendar.DAY_OF_MONTH) == Calendar.getInstance().get(Calendar.DAY_OF_MONTH) &&
                    c.get(Calendar.MONTH) == Calendar.getInstance().get(Calendar.MONTH)){
                //hien mau
                return true;
            }
        }
        return false;
    }
}

class ItemModelThongBaoActivity{
    String tieude,thongbao;

    public ItemModelThongBaoActivity(String tieude, String thongbao) {
        this.tieude = tieude;
        this.thongbao = thongbao;
    }

    public String getTieude() {
        return tieude;
    }

    public void setTieude(String tieude) {
        this.tieude = tieude;
    }

    public String getThongbao() {
        return thongbao;
    }

    public void setThongbao(String thongbao) {
        this.thongbao = thongbao;
    }
}

class DangKyHienMauItems extends BaseAdapter {

    final ArrayList<ItemModelThongBaoActivity> ls;

    public DangKyHienMauItems(ArrayList<ItemModelThongBaoActivity> ls) {
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
            viewItem1 = View.inflate(viewGroup.getContext(), R.layout.dotv_layout_items_thongbao, null);
        } else viewItem1 = view;

        ItemModelThongBaoActivity items = (ItemModelThongBaoActivity) getItem(i);
        ((TextView)viewItem1.findViewById(R.id.dotv_tieude_thongbao)).setText(items.getTieude());
        ((TextView)viewItem1.findViewById(R.id.dotv_thongtin_thongbao)).setText(items.getThongbao());
        return viewItem1;
    }
}