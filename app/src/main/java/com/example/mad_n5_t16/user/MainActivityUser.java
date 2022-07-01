package com.example.mad_n5_t16.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.mad_n5_t16.user.BloodDonationHistoryActivity;
import com.example.mad_n5_t16.MainActivity;
import com.example.mad_n5_t16.Public.model_class.TaiKhoan;
import com.example.mad_n5_t16.R;

import com.example.mad_n5_t16.TheAnh.Activity.User.DiaDiem_Activity;
import com.example.mad_n5_t16.TheAnh.Activity.User.ThongTinCaNhan_Activity;
import com.example.mad_n5_t16.ThongBaoActivity;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.NguoiHienMau;



public class MainActivityUser extends AppCompatActivity {
    public static final String fileName="data";
    TextView txtHoVaTen, txtNhomMau, txtSoLanHienMau;
    ImageView marker, home, heart, history, infor;
    ViewPager mViewPager;
    NguoiHienMau nguoiHienMau;
    int soLanHienMau;
    LinearLayout iconTen;

    // images array
    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;

    int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        home = findViewById(R.id.home);
        home.setImageResource(R.drawable.home_2);
        marker = findViewById(R.id.marker);
        marker.setImageResource(R.drawable.marker_1);
        heart = findViewById(R.id.heart_plus);
        heart.setImageResource(R.drawable.heart_plus_1);
        history = findViewById(R.id.order_history);
        history.setImageResource(R.drawable.order_history_1);
        infor = findViewById(R.id.guest_male);
        infor.setImageResource(R.drawable.guest_male_1);

        txtHoVaTen = findViewById(R.id.textTitle);
        txtHoVaTen.setText("Trần Văn Đô");

        txtNhomMau = findViewById(R.id.txtNhomMau);
        txtNhomMau.setText("AB");

        txtSoLanHienMau = findViewById(R.id.txtSoLanHienMau);
        txtSoLanHienMau.setText("4");

        iconTen = findViewById(R.id.iconTen);

        //carousel
        mViewPager = (ViewPager) findViewById(R.id.viewPagerMain);
        mViewPagerAdapter = new ViewPagerAdapter(MainActivityUser.this, images);
        mViewPager.setAdapter(mViewPagerAdapter);

        Intent intent=getIntent();

        SharedPreferences sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
        id = sharedPreferences.getInt("id",0);

        init(id);
        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMarker = new Intent(MainActivityUser.this, DiaDiem_Activity.class);
                startActivity(goMarker);
                finish();
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHeart = new Intent(MainActivityUser.this, DanhSachLichHienMauActivity.class);
                startActivity(goHeart);
//                finish();
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHistory = new Intent(MainActivityUser.this, BloodDonationHistoryActivity.class);
                startActivity(goHistory);
                finish();
            }
        });
        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInfor = new Intent(MainActivityUser.this, ThongTinCaNhan_Activity.class);
                startActivity(goInfor);
                finish();
            }
        });
    }

    protected void init(int id) {

//        SharedPreferences sharedPreferences = getSharedPreferences(fileName, MODE_PRIVATE);
//        id = sharedPreferences.getInt("id",0);
        //lay thong tin nguoi hien mau
        DatabaseHelper db = new DatabaseHelper(getBaseContext());
        TaiKhoan taiKhoan = new TaiKhoan();
        taiKhoan.setId(id);
        nguoiHienMau = db.dat_getNguoiHienMau(taiKhoan);
        soLanHienMau = db.dat_getSoLanHienMau(nguoiHienMau.getId());
        SharedPreferences.Editor editor = getSharedPreferences(fileName, MODE_PRIVATE).edit();
        editor.putInt("idNguoiHienMau", nguoiHienMau.getId());
        editor.commit();
        if(nguoiHienMau.getHoTen()!=null){
            String[] temp = nguoiHienMau.getHoTen().split(" ");
            Character icon = temp[temp.length - 1].charAt(0);
            switch (icon) {
                case 'A':
                    iconTen.setBackgroundResource(R.drawable.icons8_a_50);
                    break;
                case 'B':
                    iconTen.setBackgroundResource(R.drawable.icons8_b_50);
                    break;
                case 'C':
                    iconTen.setBackgroundResource(R.drawable.icons8_c_50);
                    break;
                case 'D':
                    iconTen.setBackgroundResource(R.drawable.icons8_d_50);
                    break;
                case 'E':
                    iconTen.setBackgroundResource(R.drawable.icons8_e_50);
                    break;
                case 'F':
                    iconTen.setBackgroundResource(R.drawable.icons8_f_50);
                    break;
                case 'G':
                    iconTen.setBackgroundResource(R.drawable.icons8_g_50);
                    break;
                case 'H':
                    iconTen.setBackgroundResource(R.drawable.icons8_h_50);
                    break;
                case 'J':
                    iconTen.setBackgroundResource(R.drawable.icons8_j_50);
                    break;
                case 'K':
                    iconTen.setBackgroundResource(R.drawable.icons8_k_50);
                    break;
                case 'L':
                    iconTen.setBackgroundResource(R.drawable.icons8_l_50);
                    break;
                case 'M':
                    iconTen.setBackgroundResource(R.drawable.icons8_m_50);
                    break;
                case 'N':
                    iconTen.setBackgroundResource(R.drawable.icons8_n_50);
                    break;
                case 'O':
                    iconTen.setBackgroundResource(R.drawable.icons8_o_50);
                    break;
                case 'P':
                    iconTen.setBackgroundResource(R.drawable.icons8_p_50);
                    break;
                case 'Q':
                    iconTen.setBackgroundResource(R.drawable.icons8_q_50);
                    break;
                case 'W':
                    iconTen.setBackgroundResource(R.drawable.icons8_w_50);
                    break;
                case 'Y':
                    iconTen.setBackgroundResource(R.drawable.icons8_y_50);
                    break;
                case 'R':
                    iconTen.setBackgroundResource(R.drawable.icons8_r_50);
                    break;
                case 'T':
                    iconTen.setBackgroundResource(R.drawable.icons8_t_50);
                    break;
                case 'S':
                    iconTen.setBackgroundResource(R.drawable.icons8_s_50);
                    break;
                case 'Z':
                    iconTen.setBackgroundResource(R.drawable.icons8_z_50);
                    break;
                case 'X':
                    iconTen.setBackgroundResource(R.drawable.icons8_x_50);
                    break;
                case 'V':
                    iconTen.setBackgroundResource(R.drawable.icons8_v_50);
                    break;
                case 'U':
                    iconTen.setBackgroundResource(R.drawable.icons8_u_48);
                    break;
                case 'I':
                    iconTen.setBackgroundResource(R.drawable.icons8_i_50);
                    break;
            }
            txtHoVaTen.setText(nguoiHienMau.getHoTen());
            txtNhomMau.setText(nguoiHienMau.getNhomMau());
        }
        else{
            txtHoVaTen.setText(" ");
            txtNhomMau.setText("");
        }
        txtSoLanHienMau.setText(soLanHienMau+"");
    }
}