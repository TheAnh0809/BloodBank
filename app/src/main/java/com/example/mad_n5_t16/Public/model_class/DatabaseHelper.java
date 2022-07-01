package com.example.mad_n5_t16.Public.model_class;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import android.widget.Toast;

import com.example.mad_n5_t16.Model.History;
import com.example.mad_n5_t16.Public.model_class.DangKyHienMau;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;
import com.example.mad_n5_t16.Public.model_class.LichHienMau;
import com.example.mad_n5_t16.Public.model_class.NguoiHienMau;
import com.example.mad_n5_t16.Public.model_class.TaiKhoan;
import com.example.mad_n5_t16.Public.model_class.ThoiGian;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Calendar;

public final class DatabaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "mad";
    private static final String ID = "id";
    private Context context;

    public DatabaseHelper(Context context) {
        super(context, DATABASE_NAME, null, 1);
        Log.d("DB Manager", "DB Manager");

        this.context = context;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String sqlQueryTK = "CREATE TABLE tbltaikhoan ( " +
                "id INTEGER, " +
                "taiKhoantTEXT, " +
                "matKhau TEXT, " +
                "hoTen TEXT, " +
                "vaiTro TEXT, " +
                "PRIMARY KEY(id) " +
                ")";
        String sqlQueryNV = "CREATE TABLE tblnhanvien ( " +
                "id INTEGER, " +
                "chucVu TEXT, " +
                "maTaiKhoan INTEGER, " +
                "FOREIGN KEY(maTaiKhoan) REFERENCES tbltaikhoan (id), " +
                "PRIMARY KEY(id) " +
                ")";
        String sqlQueryNHM = "CREATE TABLE tblnguoihienmau ( " +
                "id INTEGER, " +
                "ngaySinh TEXT, " +
                "email TEXT, " +
                "soCCCD TEXT, " +
                "nhomMau TEXT, " +
                "dienThoai TEXT, " +
                "maTaiKhoan INTEGER, " +
                "PRIMARY KEY(id), " +
                "FOREIGN KEY(maTaiKhoan) REFERENCES tbltaikhoan(id) " +
                ");";
        String sqlQueryDD = "CREATE TABLE tbldiadiem ( " +
                "id INTEGER, " +
                "tenDiaDiem TEXT, " +
                "PRIMARY KEY(id) " +
                ")";
        String sqlQueryTG = "CREATE TABLE tblthoigian ( " +
                "id INTEGER, " +
                "ngay TEXT, " +
                "gioBatDa tTEXT, " +
                "gioKetThuc TEXT, " +
                "PRIMARY KEY(id) " +
                ")";
        String sqlQuerySDM = "CREATE TABLE tblsudungmau ( " +
                "id INTEGER, " +
                "ngaySuDung TEXT, " +
                "PRIMARY KEY(id) " +
                ")";
        String sqlQueryDKHM = "CREATE TABLE tbldangkyhienmau ( " +
                "id INTEGER, " +
                "maNguoiHienMau INTEGER, " +
                "maLichHienMau INTEGER, " +
                "luongMau INTEGER, " +
                "maSuDungMau INTEGER, " +
                "PRIMARY KEY(id), " +
                "FOREIGN KEY(maLichHienMau) REFERENCES tbllichhienmau(id), " +
                "FOREIGN KEY(maNguoiHienMau) REFERENCES tblnguoihienmau(id), " +
                "FOREIGN KEY(maSuDungMau) REFERENCES tblsudungmau(id) " +
                ")";
        String sqlQueryLHM = "CREATE TABLE tbllichhienmau ( " +
                "id INTEGER, " +
                "ghiChu TEXT, " +
                "maThoiGian INTEGER, " +
                "maDiaDiem INTEGER, " +
                "FOREIGN KEY(maThoiGian) REFERENCES tblthoigian(id), " +
                "FOREIGN KEY(maDiaDiem) REFERENCES tbldiadiem(id), " +
                "PRIMARY KEY(id) " +
                ")";

        db.execSQL(sqlQueryTG);
        db.execSQL(sqlQueryDD);
        db.execSQL(sqlQueryNV);
        db.execSQL(sqlQueryNHM);
        db.execSQL(sqlQueryLHM);
        db.execSQL(sqlQueryDKHM);
        db.execSQL(sqlQueryTK);
        db.execSQL(sqlQuerySDM);
        Toast.makeText(context, "Create Database successfully", Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS tblthoigian");
        db.execSQL("DROP TABLE IF EXISTS tbldiadiem");
        db.execSQL("DROP TABLE IF EXISTS tblnhanvien");
        db.execSQL("DROP TABLE IF EXISTS tblnguoihienmau");
        db.execSQL("DROP TABLE IF EXISTS tbllichhienmau");
        db.execSQL("DROP TABLE IF EXISTS tbldangkyhienmau");
        db.execSQL("DROP TABLE IF EXISTS tbltaikhoan");
        db.execSQL("DROP TABLE IF EXISTS tblsudungmau");

        onCreate(db);
//        Toast.makeText(context, "Drop successfully", Toast.LENGTH_SHORT).show();
    }

    // Tai khoan
    public TaiKhoan getTaiKhoanByTK(String nameTK) {
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tbltaikhoan WHERE taiKhoan=?";
        String[] selectionArgs = {String.valueOf(nameTK)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        TaiKhoan temp = new TaiKhoan();
        if (cursor.moveToNext()) {
            temp.setId(cursor.getInt(0));
            temp.setTaiKhoan(cursor.getString(1));
            temp.setMatKhau(cursor.getString(2));
            temp.setHoTen(cursor.getString(5));
            temp.setVaiTro(cursor.getString(6));
        }
        cursor.close();
        db.close();
        return temp;
    }
    public void creatTaiKhoan(TaiKhoan taiKhoan){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("taiKhoan", taiKhoan.getTaiKhoan());
        values.put("matKhau", taiKhoan.getMatKhau());
        values.put("hoTen", taiKhoan.getHoTen());
        values.put("vaiTro", taiKhoan.getVaiTro());
        //Neu de null thi khi value bang null thi loi
        db.insert("tbltaikhoan",null,values);
//        if(taiKhoan.getVaiTro() != "NhanVien"){
//            TaiKhoan taiKhoanNew = this.getTaiKhoanByTK(taiKhoan.getTaiKhoan());
//            ContentValues values1 = new ContentValues();
//            values1.put("id", taiKhoan.getId());
//            values1.put("ngaySinh", "01/01/2000");
//            values1.put("ngaySinh", "01/01/2000");
//            values1.put("email", "@gmail.com");
//            values1.put("soCCCD", "000000000000");
//            values1.put("nhomMau", "A");
//            values1.put("dienThoai", "0000000000");
//            //Neu de null thi khi value bang null thi loi
//            db.insert("tblnguoihienmau",null,values1);
//        }
        db.close();
    }
    public void addTK() {

        SQLiteDatabase db = this.getReadableDatabase();

        String query = "SELECT * FROM tblnguoihienmau";
        Cursor cursor = db.rawQuery(query, null);
        int t = cursor.getCount();


        //Neu de null thi khi value bang null thi loi
        db.close();
    }

    // nguoi hien mau
    public void suaThongTinNguoiHienMau(NguoiHienMau nguoiHienMau){
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("ngaySinh", nguoiHienMau.getNgaySinh());
        values.put("email", nguoiHienMau.getEmail());
        values.put("soCCCD", nguoiHienMau.getSoCCCD());
        values.put("nhomMau", nguoiHienMau.getNhomMau());
        values.put("dienThoai", nguoiHienMau.getDienThoai());
        db.update("tblnguoihienmau" , values, ID + "=?", new String[]{String.valueOf(nguoiHienMau.getId())});

//        SQLiteDatabase db1 = this.getWritableDatabase();
        ContentValues values1 = new ContentValues();
        values1.put("hoTen", nguoiHienMau.getHoTen());
        db.update("tbltaikhoan", values1, ID + "=?", new String[]{String.valueOf(nguoiHienMau.getId())});

    }
    public NguoiHienMau dat_getNguoiHienMau(TaiKhoan taiKhoan) {
        SQLiteDatabase db = this.getReadableDatabase();
        //String query = "SELECT * FROM tblnguoihienmau WHERE id=?";
        String query = "SELECT tblnguoihienmau.id, tblnguoihienmau.ngaySinh, tblnguoihienmau.email, " +
                "tblnguoihienmau.soCCCD, tblnguoihienmau.nhomMau, tblnguoihienmau.dienThoai, tbltaikhoan.hoTen, tbltaikhoan.vaiTro " +
                "FROM tblnguoihienmau inner join " +
                "tbltaikhoan on tbltaikhoan.id = tblnguoihienmau.maTaiKhoan " +
                "WHERE tbltaikhoan.id=?";
        String[] selectionArgs = {String.valueOf(taiKhoan.getId())};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        NguoiHienMau temp = new NguoiHienMau();
//        temp.setHoTen(taiKhoan.getHoTen());
//        temp.setVaiTro(taiKhoan.getVaiTro());
        if (cursor.moveToFirst()) {
            do {
                temp.setId(cursor.getInt(0));
                temp.setNgaySinh(cursor.getString(1));
                temp.setEmail(cursor.getString(2));
                temp.setSoCCCD(cursor.getString(3));
                temp.setNhomMau(cursor.getString(4));
                temp.setDienThoai(cursor.getString(5));
                //fix cung
                temp.setHoTen(cursor.getString(6));
//                temp.setVaiTro(cursor.getString(7));

            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return temp;
    }

    public int dat_getSoLanHienMau(int maNguoiHienMau) {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tbldangkyhienmau WHERE maNguoiHienMau =? AND luongMau>0";
        String[] selectionArgs = {String.valueOf(maNguoiHienMau)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        result = cursor.getCount();

        return result;
    }

    public void themDangKyHienMau(DangKyHienMau dangKyHienMau) {
        NguoiHienMau tempNHM = new NguoiHienMau("taiKhoan1", "1", "Trịnh Tiến Đạt", "1",
                "23122000", "abc@gmail.com", "123456789", " ", "0368257596");
        ThoiGian tempTG = new ThoiGian("25042022", "07:00", "11h:00");
        DiaDiem tempDD = new DiaDiem("Viện Huyết học và truyền máu trung ương");
        LichHienMau tempLHM = new LichHienMau(tempTG, " ", tempDD);
        DangKyHienMau temp = new DangKyHienMau(tempLHM, tempNHM, 350);
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = new ContentValues();
        //values.put("");

    }

    public ArrayList<DangKyHienMau> dat_layDSDangKyHienMau() {
        ArrayList<DangKyHienMau> result = new ArrayList<DangKyHienMau>();
        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT tbldiadiem.tenDiaDiem, tbltaikhoan.hoTen, tblnguoihienmau.dienThoai " +
//                "FROM tblthoigian inner join tbllichhienmau " +
//                "on tblthoigian.id=tbllichhienmau.maThoiGian " +
//                "inner join tbldiadiem on tbllichhienmau.maDiaDiem = tbldiadiem.id " +
//                "inner join tbldangkyhienmau on tbllichhienmau.maDangKyHienMau= tbldangkyhienmau.id " +
//                "inner join tblnguoihienmau on tbldangkyhienmau.maNguoiHienMau = tblnguoihienmau.id " +
//                "inner join tbltaikhoan on tblnguoihienmau.id = tbltaikhoan.maNguoiHienMau " +
//                "WHERE tblthoigian.ngay=?";

        String query = "SELECT tbldiadiem.tenDiaDiem, tbltaikhoan.hoTen, tblnguoihienmau.dienThoai " +
                "FROM tblthoigian inner join tbllichhienmau " +
                "on tblthoigian.id=tbllichhienmau.maThoiGian " +
                "inner join tbldiadiem on tbllichhienmau.maDiaDiem = tbldiadiem.id " +
                "inner join tbldangkyhienmau on tbllichhienmau.id= tbldangkyhienmau.maLichHienMau " +
                "inner join tblnguoihienmau on tbldangkyhienmau.maNguoiHienMau = tblnguoihienmau.id " +
                "inner join tbltaikhoan on tblnguoihienmau.maTaiKhoan = tbltaikhoan.id " +
                "WHERE tblthoigian.ngay=?";
        Calendar cal = Calendar.getInstance();
        int nam = cal.get(Calendar.YEAR);
        int thang = cal.get(Calendar.MONTH) + 1;
        int ngay = cal.get(Calendar.DAY_OF_MONTH);
        String[] selectionArgs = {dat_makeDateString(ngay, thang, nam)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                DiaDiem tempDiaDiem = new DiaDiem();
                tempDiaDiem.setTenDiaDiem(cursor.getString(0));
                LichHienMau tempLichHienMau = new LichHienMau();
                tempLichHienMau.setDiaDiem(tempDiaDiem);
                NguoiHienMau tempNguoiHienMau = new NguoiHienMau();
                tempNguoiHienMau.setHoTen(cursor.getString(1));
                tempNguoiHienMau.setDienThoai(cursor.getString(2));
                DangKyHienMau tempDangKyHienMau = new DangKyHienMau();
                tempDangKyHienMau.setLichHienMau(tempLichHienMau);
                tempDangKyHienMau.setNguoiHienMau(tempNguoiHienMau);
                result.add(tempDangKyHienMau);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public ArrayList<LichHienMau> do_layDsLichHienMau(int id) {
        ArrayList<LichHienMau> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query =
//            "SELECT tblthoigian.ngay, tblthoigian.gioBatDau, tblthoigian.gioKetThuc, tbldiadiem.tenDiaDiem, tbllichhienmau.id FROM tbllichhienmau" +
//            " INNER JOIN tblthoigian ON tbllichhienmau.maThoiGian = tblthoigian.id" +
//            " INNER JOIN tbldiadiem ON tbllichhienmau.maDiaDiem = tbldiadiem.id " +
//            "WHERE tbllichhienmau.maDangKyHienMau=
        "SELECT tblthoigian.ngay, tblthoigian.gioBatDau, tblthoigian.gioKetThuc, tbldiadiem.tenDiaDiem, tbllichhienmau.id FROM tbldangkyhienmau "+
        " INNER JOIN tbllichhienmau ON tbldangkyhienmau.maLichHienMau = tbllichhienmau.id "+
        " INNER JOIN tblthoigian ON tblthoigian.id = tbllichhienmau.id "+
        " INNER JOIN tbldiadiem ON tbldiadiem.id = tbllichhienmau.id " +
        "WHERE tbldangkyhienmau.maNguoiHienMau=?";
        String[] selectionArgs = {id+""};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                ThoiGian tg = new ThoiGian();
                tg.setNgay(cursor.getString(0));
                tg.setGioBatDau(cursor.getString(1));
                tg.setGioKetThuc(cursor.getString(2));
                DiaDiem dd = new DiaDiem();
                dd.setTenDiaDiem(cursor.getString(3));
                LichHienMau lhm = new LichHienMau();
                lhm.setId(4);
                lhm.setThoiGian(tg);
                lhm.setDiaDiem(dd);
                result.add(lhm);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public ArrayList<LichHienMau> do_laydsdiadiemhienmau() {
        ArrayList<LichHienMau> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT tbllichhienmau.*, tbldiadiem.*, tblthoigian.* from tbllichhienmau " +
                "inner join tblthoigian on tbllichhienmau.maThoiGian=tblthoigian.id " +
                "inner join tbldiadiem on tbllichhienmau.maDiaDiem=tbldiadiem.id";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ThoiGian tg = new ThoiGian();
                tg.setId(cursor.getInt(6));
                tg.setNgay(cursor.getString(7));
                tg.setGioBatDau(cursor.getString(8));
                tg.setGioKetThuc(cursor.getString(9));
                DiaDiem dd = new DiaDiem();
                dd.setId(cursor.getInt(4));
                dd.setTenDiaDiem(cursor.getString(5));
                LichHienMau lhm = new LichHienMau();
                lhm.setId(cursor.getInt(0));
                lhm.setThoiGian(tg);
                lhm.setDiaDiem(dd);
                lhm.setGhiChu(cursor.getString(1));
                result.add(lhm);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public ArrayList<LichHienMau> do_getCountnguoidangky() {
        ArrayList<LichHienMau> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "select maDiaDiem, maThoiGian,b.gioBatDau,b.gioKetThuc,b.ngay,c.tenDiaDiem, count(*) AS soNguoiDangKy from tbllichhienmau a \n" +
                "inner join tblthoigian b on a.maThoiGian = b.id\n" +
                "inner join tbldiadiem c on a.maDiaDiem = c.id\n" +
                "GROUP by maDiaDiem, maThoiGian ;";
        Cursor cursor = db.rawQuery(query, null);
        if (cursor.moveToFirst()) {
            do {
                ThoiGian tg = new ThoiGian();
                tg.setNgay(cursor.getString(4));
                tg.setId(cursor.getInt(1));
                tg.setGioBatDau(cursor.getString(2));
                tg.setGioKetThuc(cursor.getString(3));
                DiaDiem dd = new DiaDiem();
                dd.setId(cursor.getInt(0));
                dd.setTenDiaDiem(cursor.getString(5));
                LichHienMau lhm = new LichHienMau();
                lhm.setThoiGian(tg);
                lhm.setDiaDiem(dd);
                lhm.setGhiChu(cursor.getString(6));
                result.add(lhm);
            } while (cursor.moveToNext());
        }
        return result;
    }
    public int do_SoLuongDangKyTheoMaLichHienMau(int id) {
        ArrayList<LichHienMau> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT tbldangkyhienmau.* from tbldangkyhienmau inner join tbllichhienmau " +
                "on tbldangkyhienmau.maLichHienMau=tbllichhienmau.id " +
                "WHERE tbllichhienmau.id=?";
        String[] selectionArgs = {id+""};
        Cursor cursor = db.rawQuery(query, selectionArgs);

        return cursor.getCount();
    }
    public void luuDangKyHienMau( int maNguoiHienMau, int maLichHienMau){
        SQLiteDatabase db = this.getWritableDatabase();
        String query="INSERT into tbldangkyhienmau (maNguoiHienMau, maLichHienMau, luongMau) VALUES " +
                "(?,?,?)";
        String[] selectionArgs = {maNguoiHienMau+"",maLichHienMau+"", 0+""};
        db.execSQL(query, selectionArgs);
    }

    public int dat_laySoLuongDangKyHienMau() {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
//        String query = "SELECT tbldiadiem.tenDiaDiem, tbltaikhoan.hoTen FROM tblthoigian inner join tbllichhienmau " +
//                "on tblthoigian.id=tbllichhienmau.maThoiGian " +
//                "inner join tbldiadiem on tbllichhienmau.maDiaDiem = tbldiadiem.id " +
//                "inner join tbldangkyhienmau on tbllichhienmau.maDangKyHienMau= tbldangkyhienmau.id " +
//                "inner join tblnguoihienmau on tbldangkyhienmau.maNguoiHienMau = tblnguoihienmau.id " +
//                "inner join tbltaikhoan on tblnguoihienmau.id = tbltaikhoan.maNguoiHienMau " +
//                "WHERE tblthoigian.ngay=?";

        String query = "SELECT tbldiadiem.tenDiaDiem, tbltaikhoan.hoTen, tblnguoihienmau.dienThoai " +
                "FROM tblthoigian inner join tbllichhienmau " +
                "on tblthoigian.id=tbllichhienmau.maThoiGian " +
                "inner join tbldiadiem on tbllichhienmau.maDiaDiem = tbldiadiem.id " +
                "inner join tbldangkyhienmau on tbllichhienmau.id= tbldangkyhienmau.maLichHienMau " +
                "inner join tblnguoihienmau on tbldangkyhienmau.maNguoiHienMau = tblnguoihienmau.id " +
                "inner join tbltaikhoan on tblnguoihienmau.maTaiKhoan = tbltaikhoan.id " +
                "WHERE tblthoigian.ngay=?";
        Calendar cal = Calendar.getInstance();
        int nam = cal.get(Calendar.YEAR);
        int thang = cal.get(Calendar.MONTH) + 1;
        int ngay = cal.get(Calendar.DAY_OF_MONTH);
        String[] selectionArgs = {dat_makeDateString(ngay, thang, nam)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        result = cursor.getCount();
        return result;
    }

    public int dat_laySoLuongMauConLaiTheoNhomMau(String nhomMau) {
        int result = 0;
        result = dat_laySoLuongMauDaHienTheoNhomMau(nhomMau) - dat_laySoLuongMauDaSuDungTheoNhomMau(nhomMau);
        return result;
    }

    public int dat_laySoLuongMauDaHienTheoNhomMau(String nhomMau) {
        SQLiteDatabase db = this.getReadableDatabase();
        int result = 0;
        String query = "SELECT tbldangkyhienmau.luongMau FROM tbldangkyhienmau " +
                "inner join tblnguoihienmau on tbldangkyhienmau.maNguoiHienMau = tblNguoiHienMau.id " +
                "WHERE tblnguoihienmau.nhomMau =?";
        String[] selectionArgs = {nhomMau};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                result = result + cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public int dat_laySoLuongMauDaSuDungTheoNhomMau(String nhomMau) {
        SQLiteDatabase db = this.getReadableDatabase();
        int result = 0;
        String query = "SELECT tbldangkyhienmau.luongMau FROM tbldangkyhienmau " +
                "inner join tblsudungmau on tblsudungmau.id = tbldangkyhienmau.maSuDungMau " +
                "inner join tblnguoihienmau on tbldangkyhienmau.maNguoiHienMau = tblNguoiHienMau.id " +
                "WHERE tblnguoihienmau.nhomMau =?";
        String[] selectionArgs = {nhomMau};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            do {
                result = result + cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return result;
    }

    public int dat_thongKeSuDungMauTheoNhomMau(String nhomMau, String ngayBatDau, String ngayKetThuc) {
        int result = 0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT tbldangkyhienmau.luongMau FROM tblsudungmau " +
                "inner join tbldangkyhienmau on tbldangkyhienmau.maSuDungMau=tblsudungmau.id " +
                "inner join tblnguoihienmau on tbldangkyhienmau.maNguoiHienMau=tblnguoihienmau.id " +
                "WHERE tblnguoihienmau.nhomMau=? " +
                "AND strftime('%Y/%m/%d', tblsudungmau.ngaySuDung)>=?" +
                "AND strftime('%Y/%m/%d', tblsudungmau.ngaySuDung)<=?";
        String[] selecStrings = {nhomMau, ngayBatDau, ngayKetThuc};
        Cursor cursor = db.rawQuery(query, selecStrings);
        if (cursor.moveToFirst()) {
            do {
                result = result + cursor.getInt(0);
            } while (cursor.moveToNext());
        }
        return result;

    }


    public String dat_makeDateString(int day, int month, int year) {
        return String.valueOf(year) + "-" + dat_getMonthFormat(month) + "-" + String.valueOf(day);
    }

    private String dat_getMonthFormat(int month) {
        if (month == 1)
            return "01";
        if (month == 2)
            return "02";
        if (month == 3)
            return "03";
        if (month == 4)
            return "04";
        if (month == 5)
            return "05";
        if (month == 6)
            return "06";
        if (month == 7)
            return "07";
        if (month == 8)
            return "08";
        if (month == 9)
            return "09";
        if (month == 10)
            return "010";
        if (month == 11)
            return "11";
        if (month == 12)
            return "12";

        //default should never happen
        return "Tháng 1";
    }

    public TaiKhoan nam_getTaiKhoanByUserNameAndPassWord(String userName, String passWord) {
        TaiKhoan taiKhoan = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tbltaikhoan " +
                "WHERE tbltaikhoan.taiKhoan =? AND tbltaikhoan.matKhau =?";
        String[] selectionArgs = {userName, passWord};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            taiKhoan = new TaiKhoan();
            taiKhoan.setId(cursor.getInt(0));
            taiKhoan.setTaiKhoan(cursor.getString(1));
            taiKhoan.setMatKhau(cursor.getString(2));
            taiKhoan.setVaiTro(cursor.getString(4));
        }
        return taiKhoan;
    }

    public boolean nam_checkTaiKhoanByUsername(String username) {
        boolean result = true;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tbltaikhoan " +
                "WHERE tbltaikhoan.taiKhoan =?";
        String[] selectionArgs = {username};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            result = false;
        }
        return result;
    }

    public boolean nam_checkNguoiHienMauByCCCD(String CCCD) {
        boolean result = true;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tblnguoihienmau " +
                "WHERE tblnguoihienmau.soCCCD =?";
        String[] selectionArgs = {CCCD};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            result = false;
        }
        return result;
    }

    public NguoiHienMau nam_getNguoiHienMauByCCCD(String CCCD) {
        NguoiHienMau nhm = null;
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tblnguoihienmau " +
                "WHERE tblnguoihienmau.soCCCD =?";
        String[] selectionArgs = {CCCD};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        if (cursor.moveToFirst()) {
            nhm = new NguoiHienMau();
            nhm.setId(cursor.getInt(0));
            nhm.setSoCCCD(cursor.getString(3));
        }
        return nhm;
    }
    //dat viet lai sau khi sua csdl
    public void nam_addNguoiHienMau(NguoiHienMau nhm, long id) {
        SQLiteDatabase db = this.getWritableDatabase();
        String query = "INSERT INTO tblnguoihienmau (ngaySinh, email, soCCCD, nhomMau, dienThoai, maTaiKhoan) VALUES " +
                "(?, ?, ?, ?, ?, ?)";
        String[] selectionArgs = {null, null, nhm.getSoCCCD(), null, null, id+""};
        db.execSQL(query, selectionArgs);
    }
    //dat viet lai sau khi sua csdl
    public long nam_addTaiKhoan(TaiKhoan taiKhoan) {
        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "INSERT INTO tbltaikhoan (taiKhoan, matKhau, vaiTro) VALUES " +
//                "(?, ?, ?)";
//        String[] selectionArgs = {taiKhoan.getTaiKhoan(), taiKhoan.getMatKhau(), taiKhoan.getVaiTro()};

        ContentValues value = new ContentValues();
        value.put("taiKhoan", taiKhoan.getTaiKhoan());
        value.put("matKhau", taiKhoan.getMatKhau());
        value.put("vaiTro", taiKhoan.getVaiTro());
        long respons = db.insert("tbltaikhoan", null, value);
        db.close();
        return respons;
    }


//    public void nam_addNguoiHienMau(NguoiHienMau nhm) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "INSERT INTO tblnguoihienmau (ngaySinh, email, soCCCD, nhomMau, dienThoai) VALUES " +
//                "(?, ?, ?, ?, ?)";
//        String[] selectionArgs = {null, null, nhm.getSoCCCD(), null, null};
//        db.execSQL(query, selectionArgs);
//    }
//
//    public void nam_addTaiKhoan(TaiKhoan taiKhoan, int idNguoiHienMau) {
//        SQLiteDatabase db = this.getWritableDatabase();
//        String query = "INSERT INTO tbltaikhoan (taiKhoan, matKhau, maNguoiHienMau, vaiTro) VALUES " +
//                "(?, ?, ?, ?)";
//        String[] selectionArgs = {taiKhoan.getTaiKhoan(), taiKhoan.getMatKhau(),
//                idNguoiHienMau + "", taiKhoan.getVaiTro()};
//        db.execSQL(query, selectionArgs);
//    }

    public ArrayList<History> nam_getLichSuHienMauByIdNguoiHienMau(int idNguoiHienMau) {
        ArrayList<History> listHistory = new ArrayList<>();

        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT tbldangkyhienmau.luongMau, tbldiadiem.tenDiaDiem, tblthoigian.ngay  FROM tbldangkyhienmau " +
                "inner join tbllichhienmau on tbldangkyhienmau.maLichHienMau = tbllichhienmau.id " +
                "inner join tbldiadiem on tbllichhienmau.maDiaDiem = tbldiadiem.id " +
                "inner join tblthoigian on tbllichhienmau.maThoiGian = tblthoigian.id " +
                "WHERE tbldangkyhienmau.luongMau > 0 AND tbldangkyhienmau.maNguoiHienMau = ?";
        String[] selectionArgs = {String.valueOf(idNguoiHienMau)};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        int i = 1;
        while (cursor.moveToNext()) {
            History h = new History();
            h.setNumber(i);
            h.setAmount(cursor.getInt(0));
            h.setDonationDate(cursor.getString(2));
            h.setLocation(cursor.getString(1));
            System.out.println(h.getLocation());
            i++;
            listHistory.add(h);
        }

        return listHistory;
    }

    public ArrayList<ThoiGian> do_getThoiGian(int idDiaDiem) {
        ArrayList<ThoiGian> result = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT * FROM tblthoigian " +
                "INNER JOIN tbllichhienmau ON tbllichhienmau.maThoiGian = tblthoigian.id " +
                "INNER JOIN tbldiadiem ON tbllichhienmau.maDiaDiem=tbldiadiem.id " +
                "WHERE tbldiadiem.id=?";
        Cursor cursor = db.rawQuery(query,  new String[]{String.valueOf(idDiaDiem)});
        int i = 1;
        while (cursor.moveToNext()) {
            ThoiGian tg = new ThoiGian();
            tg.setId(i);
            tg.setNgay(cursor.getString(1));
            tg.setGioBatDau(cursor.getString(2));
            tg.setGioKetThuc(cursor.getString(3));
            Log.i("ThoiGian", "do_getThoiGian: " + i + " " + tg.getNgay() + " " + tg.getGioBatDau());
            i++;
            result.add(tg);
        }
        ;
        return result;
    }

    // Thế Anh
    // Dia diem
    public ArrayList<LichHienMau> getLichHienMau(DiaDiem diaDiem) {
        ArrayList<LichHienMau> lichHienMau = new ArrayList<>();
        SQLiteDatabase db = this.getReadableDatabase();
        String query = "SELECT tbllichhienmau.id, tbllichhienmau.ghiChu, tblthoigian.ngay, tblthoigian.gioBatDau, " +
                "tblthoigian.gioKetThuc , tbldiadiem.tenDiaDiem FROM tbllichhienmau " +
                "inner join tblthoigian on tblthoigian.id = tbllichhienmau.maThoiGian " +
                "inner join tbldiadiem on tbllichhienmau.maDiaDiem = tbldiadiem.id " +
                "WHERE tbllichhienmau.maDiaDiem=?";
        String[] selectionArgs = {String.valueOf(diaDiem.getId())};
        Cursor cursor = db.rawQuery(query, selectionArgs);
        while (cursor.moveToNext()) {
            DiaDiem diaDiem1 = new DiaDiem(cursor.getString(5));
            ThoiGian thoiGian = new ThoiGian(cursor.getString(2), cursor.getString(3), cursor.getString(4));
            LichHienMau lich = new LichHienMau(cursor.getInt(0),thoiGian, cursor.getString(1), diaDiem1);
            lichHienMau.add(lich);
        }
        return lichHienMau;
    }
    public void deleteLichHienMau(LichHienMau lichHienMau){
        SQLiteDatabase db = this.getWritableDatabase();
        String querry = "DELETE FROM tbllichhienmau WHERE id = ?";
        String[] selectionArgs = {String.valueOf(lichHienMau.getId())};
        db.rawQuery(querry, selectionArgs);
    }
    public void addLichHienMau(LichHienMau lichHienMau) {

        ContentValues values = new ContentValues();
        long idTG = dat_checkThoiGian(lichHienMau.getThoiGian());
        long idDD = lichHienMau.getDiaDiem().getId();
        if(idTG==0){
            idTG = addThoiGian(lichHienMau.getThoiGian());
        }
//        long idDD = lichHienMau.getDiaDiem().getId();
        values.put("ghiChu", lichHienMau.getGhiChu());
        values.put("maThoiGian", idTG);
        values.put("maDiaDiem", idDD);
        //Neu de null thi khi value bang null thi loi
        SQLiteDatabase db = this.getWritableDatabase();
        db.insert("tbllichhienmau", null, values);
        db.close();
    }

    public long addThoiGian(ThoiGian thoiGian) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues valueThoiGian = new ContentValues();
        valueThoiGian.put("ngay", thoiGian.getNgay());
        valueThoiGian.put("gioBatDau", thoiGian.getGioBatDau());
        valueThoiGian.put("gioKetThuc", thoiGian.getGioKetThuc());
        //Neu de null thi khi value bang null thi loi
        long respons = db.insert("tblthoigian", null, valueThoiGian);
        db.close();
        return respons;
    }

    //0 la thoi gian chua ton tai, phai them moi
    //#0 la thoi gian da ton tai
    public long dat_checkThoiGian(ThoiGian thoiGian){
        long result =0;
        SQLiteDatabase db = this.getReadableDatabase();
        String query="SELECT * FROM tblthoigian WHERE ngay=? AND gioBatDau=? AND gioKetThuc=?";
        String[] selectionArgs={thoiGian.getNgay(), thoiGian.getGioBatDau(), thoiGian.getGioKetThuc()};
        Cursor cursor= db.rawQuery(query, selectionArgs);
        while(cursor.moveToNext()){
            result=cursor.getInt(0);
        }
        return result;
    }

    public long addDiaDiem(DiaDiem diaDiem) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("tenDiaDiem", diaDiem.getTenDiaDiem());
        //Neu de null thi khi value bang null thi loi
        long respons = db.insert("tbldiadiem", null, values);
        db.close();
        return respons;
    }

    public ArrayList<DiaDiem> getAllDiaDiem() {
        ArrayList<DiaDiem> list = new ArrayList<>();
        String selectQuery = "SELECT  * FROM  " + "tbldiadiem";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DiaDiem diaDiem = new DiaDiem(cursor.getString(1));
                diaDiem.setId(cursor.getInt(0));
                list.add(diaDiem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return list;
    }

    public ArrayList<DiaDiem> searchDiaDiem(String tendd) {
        ArrayList<DiaDiem> list = new ArrayList<>();
        String selectQuery = "SELECT  * FROM  tbldiadiem WHERE tenDiaDiem LIKE " + "'%" + tendd + "%'";
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);
        if (cursor.moveToFirst()) {
            do {
                DiaDiem diaDiem = new DiaDiem(cursor.getString(1));
                diaDiem.setId(cursor.getInt(0));
                list.add(diaDiem);
            } while (cursor.moveToNext());
        }
        cursor.close();
        return list;
    }
}
