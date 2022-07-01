package com.example.mad_n5_t16.user;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import com.example.mad_n5_t16.MainActivity;
import com.example.mad_n5_t16.R;

public class MainActivityUser extends AppCompatActivity {

    TextView txtHoVaTen, txtNhomMau, txtSoLanHienMau;
    ImageView marker, home, heart, history, infor;
    ViewPager mViewPager;

    // images array
    int[] images = {R.drawable.img1, R.drawable.img2, R.drawable.img3, R.drawable.img4,
            R.drawable.img5, R.drawable.img6};

    // Creating Object of ViewPagerAdapter
    ViewPagerAdapter mViewPagerAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_user);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        home = findViewById(R.id.home);
        home.setImageResource(R.drawable.home_2);
        marker =findViewById(R.id.marker);
        marker.setImageResource(R.drawable.marker_1);
        heart =findViewById(R.id.heart_plus);
        heart.setImageResource(R.drawable.heart_plus_1);
        history =findViewById(R.id.order_history);
        history.setImageResource(R.drawable.order_history_1);
        infor =findViewById(R.id.guest_male);
        infor.setImageResource(R.drawable.guest_male_1);

        marker.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goMarker = new Intent(MainActivityUser.this, ThongBaoActivity.class);
                startActivity(goMarker);
            }
        });
        heart.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHeart = new Intent(MainActivityUser.this, MainActivity.class);
                startActivity(goHeart);
            }
        });
        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goHistory = new Intent(MainActivityUser.this, MainActivity.class);
                startActivity(goHistory);
            }
        });
        infor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent goInfor = new Intent(MainActivityUser.this, MainActivity.class);
                startActivity(goInfor);
            }
        });

        txtHoVaTen = findViewById(R.id.textTitle);
        txtHoVaTen.setText("Trần Văn Đô");

        txtNhomMau = findViewById(R.id.txtSoLanHienMau);
        txtNhomMau.setText("AB");

        txtSoLanHienMau = findViewById(R.id.txtSoLanHienMau);
        txtSoLanHienMau.setText("4");

            // Initializing the ViewPager Object
            mViewPager = (ViewPager)findViewById(R.id.viewPagerMain);

            // Initializing the ViewPagerAdapter
            mViewPagerAdapter = new ViewPagerAdapter(MainActivityUser.this, images);

            // Adding the Adapter to the ViewPager
            mViewPager.setAdapter(mViewPagerAdapter);

    }
}