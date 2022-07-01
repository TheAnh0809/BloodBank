package com.example.mad_n5_t16.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import com.example.mad_n5_t16.Public.model_class.NguoiHienMau;
import com.example.mad_n5_t16.Public.model_class.TaiKhoan;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.NguoiHienMau;
import com.example.mad_n5_t16.Public.model_class.TaiKhoan;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
import com.example.mad_n5_t16.R;

public class RegisterActivity extends AppCompatActivity {

    EditText ettUsernameregister, etpPasswordregister, etpPasswordregisteragain, ettCCCD;
    Button btnRegister, btnRegisterBack;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        init();

        btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String validateMes = new String(checkValidRegisterInput(ettUsernameregister, etpPasswordregister, etpPasswordregisteragain, ettCCCD));
                if(validateMes.equals("")){
                    String addAccountMes = addNewAccount(ettUsernameregister, etpPasswordregister, ettCCCD);

                    if(addAccountMes.equals("")){
                        Toast.makeText(RegisterActivity.this, "Đăng ký tài khoản thành công", Toast.LENGTH_SHORT).show();
                    }else {
                        Toast.makeText(RegisterActivity.this, addAccountMes, Toast.LENGTH_SHORT).show();
                    }

                }else {
                    Toast.makeText(RegisterActivity.this, validateMes, Toast.LENGTH_SHORT).show();
                }
            }
        });

        btnRegisterBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterActivity.this, LoginActivity.class);
                startActivity(intent);
                finish();
            }
        });

    }

    private String addNewAccount(EditText ettUsernameregister, EditText etpPasswordregister, EditText ettCCCD) {
        // trong method nay kiem tra ten tai khoan ton tai, them tai khoan
        String result = "";
        String txtUsername = ettUsernameregister.getText().toString();
        String txtPassword = etpPasswordregister.getText().toString();
        String txtCCCD = ettCCCD.getText().toString();

        // 1.lấy danh sách tài khoản, nguoi hien mau lên và lưu vào một List
        boolean checkTaiKhoan = true;
        boolean checkCCCD = true;
        DatabaseHelper dbh = new DatabaseHelper(getBaseContext());
        checkTaiKhoan = dbh.nam_checkTaiKhoanByUsername(txtUsername);
        checkCCCD = dbh.nam_checkNguoiHienMauByCCCD(txtCCCD);

        if(checkCCCD == false) {
            result += "Căn cước này đã có tài khoản";
        }

        if(checkTaiKhoan == false){
            result += "\nTên tài khoản đã tồn tại";
        }

        // 3. Nếu tên tài khoản chưa tồn tại thì thực hiện thêm tài khoản.
        //dat viet lai sau khi sua csdl
        if(result.equals("")) {
            TaiKhoan taiKhoan = new TaiKhoan();
            taiKhoan.setTaiKhoan(txtUsername);
            taiKhoan.setMatKhau(txtPassword);
            taiKhoan.setVaiTro("khachhang");
            long id = dbh.nam_addTaiKhoan(taiKhoan);

            NguoiHienMau nhm = new NguoiHienMau();
            nhm.setSoCCCD(txtCCCD);
            //NguoiHienMau nhm2 = dbh.nam_getNguoiHienMauByCCCD(txtCCCD);
            dbh.nam_addNguoiHienMau(nhm, id);

        }
//        if(result.equals("")) {
//            NguoiHienMau nhm = new NguoiHienMau();
//            nhm.setSoCCCD(txtCCCD);
//
//            dbh.nam_addNguoiHienMau(nhm);
//
//            TaiKhoan taiKhoan = new TaiKhoan();
//            taiKhoan.setTaiKhoan(txtUsername);
//            taiKhoan.setMatKhau(txtPassword);
//            taiKhoan.setVaiTro("khachhang");
//
//            NguoiHienMau nhm2 = dbh.nam_getNguoiHienMauByCCCD(txtCCCD);
//
//            dbh.nam_addTaiKhoan(taiKhoan, nhm2.getId());
//        }
        return result;
    }

    private String checkValidRegisterInput(EditText ettUsernameregister, EditText etpPasswordregister, EditText etpPasswordregisteragain,
                                           EditText ettCCCD) {
        // trong method nay kiem tr xau rong
        String result = "";
        String txtUsername = ettUsernameregister.getText().toString();
        String txtPassword = etpPasswordregister.getText().toString();
        String txtPasswordagain = etpPasswordregisteragain.getText().toString();
        String txtettCCCD = ettCCCD.getText().toString();

        if(txtUsername.equals("")||txtPassword.equals("")||txtPasswordagain.equals("")||txtettCCCD.equals("")){
            result = "Cần nhập đủ tất cả các trường";
        }else if (!txtPassword.equals(txtPasswordagain)){
            result = "Mật khẩu nhập lại không đúng";
        }else if (txtUsername.split(" ").length > 1 ||
                txtPassword.split(" ").length > 1 ||
                txtPasswordagain.split(" ").length > 1||
                txtettCCCD.split(" ").length > 1){
            result = "Không nhập khoảng trắng";
        }else if(txtPassword.length() < 8) {
            result = "Mật khẩu cần dài ít nhất 8 ký tự";
        }
        return result;
    }

    private void init() {
        ettUsernameregister = findViewById(R.id.ettUsernameregister);
        etpPasswordregister = findViewById(R.id.etpPasswordregister);
        etpPasswordregisteragain = findViewById(R.id.etpPasswordregisteragain);
        ettCCCD = findViewById(R.id.ettCCCD);
        btnRegister = findViewById(R.id.btnRegister);
        btnRegisterBack = findViewById(R.id.btnRegisterBack);
    }
}