package com.example.mad_n5_t16.Public.model_class;

import java.io.Serializable;

public class NguoiHienMau extends TaiKhoan implements Serializable {

    private int id;
    private String ngaySinh;
    private String email;
    private String soCCCD;
    private String nhomMau;
    private String dienThoai;
    public NguoiHienMau(int id,String taiKhoan, String matKhau, String hoTen, String vaiTro, String ngaySinh, String email, String soCCCD, String nhomMau, String dienThoai) {
        super(taiKhoan, matKhau, hoTen, vaiTro);
        this.id = id;
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.soCCCD = soCCCD;
        this.nhomMau = nhomMau;
        this.dienThoai = dienThoai;
    }
    public NguoiHienMau(String taiKhoan, String matKhau, String hoTen, String vaiTro, String ngaySinh, String email, String soCCCD, String nhomMau, String dienThoai) {
        super(taiKhoan, matKhau, hoTen, vaiTro);
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.soCCCD = soCCCD;
        this.nhomMau = nhomMau;
        this.dienThoai = dienThoai;
    }

    public NguoiHienMau(String taiKhoan, String matKhau, String hoTen, String vaiTro) {
        super(taiKhoan, matKhau, hoTen, vaiTro);
    }

    public NguoiHienMau() {
    }

    public NguoiHienMau(String ngaySinh, String email, String soCCCD, String nhomMau, String dienThoai) {
        this.ngaySinh = ngaySinh;
        this.email = email;
        this.soCCCD = soCCCD;
        this.nhomMau = nhomMau;
        this.dienThoai = dienThoai;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(String ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getSoCCCD() {
        return soCCCD;
    }

    public void setSoCCCD(String soCCCD) {
        this.soCCCD = soCCCD;
    }

    public String getNhomMau() {
        return nhomMau;
    }

    public void setNhomMau(String nhomMau) {
        this.nhomMau = nhomMau;
    }

    public String getDienThoai() {
        return dienThoai;
    }

    public void setDienThoai(String dienThoai) {
        this.dienThoai = dienThoai;
    }
}
