package com.example.mad_n5_t16.user;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.example.mad_n5_t16.employee.MainActivityEmployee;
import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.TaiKhoan;
import com.example.mad_n5_t16.user.MainActivityUser;

import com.example.mad_n5_t16.R;

public class LoginActivity extends AppCompatActivity {


    public static final String fileName="data";
    EditText ettUsername;
    EditText etpPassword;
    Button btnLogin;
    TextView tvRegister;
    TaiKhoan taiKhoan = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ActionBar actionBar = getSupportActionBar();
        actionBar.hide();

        init();

        btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userName = ettUsername.getText().toString();
                String passWord = etpPassword.getText().toString();
                if (checkValidateAccount(userName, passWord)) {
                    // đăng nhập thành công
                    // kiểm tra xem tài khoản thuộc về nhân viên hay người hiến máu
                    if (taiKhoan.getVaiTro().equals("khachhang")) {

                        SharedPreferences.Editor editor = getSharedPreferences(fileName, MODE_PRIVATE).edit();
                        editor.putInt("id",taiKhoan.getId());
                        editor.commit();
                        // nếu là người hiến máu thì
                        // tạo một NguoiHienMau và put extra sang cho màn hình chính của người hiến máu
                        Intent intent = new Intent(LoginActivity.this, MainActivityUser.class);
                        //intent.putExtra("id", taiKhoan.getId());
                        startActivity(intent);
                        finish();
                    } else if (taiKhoan.getVaiTro().equals("nhanvien")) {
                        // nếu là nhân viên thì
                        // tạo một NhanVien và put extra sang cho màn hình chính của nhân viên
                        Intent intent = new Intent(LoginActivity.this, MainActivityEmployee.class);
                        intent.putExtra("id", taiKhoan.getId());
                        startActivity(intent);
                        finish();
                    }
                } else {
                    Toast.makeText(LoginActivity.this, "Tài khoản hoặc mật khẩu không chính xác", Toast.LENGTH_SHORT).show();
                }
            }
        });

        tvRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginActivity.this, RegisterActivity.class);
                startActivity(intent);
                finish();
            }
        });
    }

    private boolean checkValidateAccount(String userName, String passWord) {
        // viết câu truy vấn tìm xem trong csdl có thông tin tài khoản và mật khẩu không
        // taiKhoan = getTaiKhoanByUsernameAndPassword()
        DatabaseHelper dbh = new DatabaseHelper(getBaseContext());
        taiKhoan = dbh.nam_getTaiKhoanByUserNameAndPassWord(userName, passWord);
        if (taiKhoan != null) {
            return true;
        }
        return false;
    }

    private void init() {
        ettUsername = findViewById(R.id.ettUsername);
        etpPassword = findViewById(R.id.etpPassword);
        btnLogin = findViewById(R.id.btnLogin);
        tvRegister = findViewById(R.id.tvRegister);
    }
}
