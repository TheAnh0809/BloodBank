package com.example.mad_n5_t16.Public.model_class;

import java.util.ArrayList;

public class SuDungMau {
    private int id;
    private ArrayList<DangKyHienMau> dsDangKyHienMau;
    private String ngaySuDung;

    public SuDungMau() {
    }

    public SuDungMau(int id, ArrayList<DangKyHienMau> dsDangKyHienMau, String ngaySuDung) {
        this.id = id;
        this.dsDangKyHienMau = dsDangKyHienMau;
        this.ngaySuDung = ngaySuDung;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ArrayList<DangKyHienMau> getDsDangKyHienMau() {
        return dsDangKyHienMau;
    }

    public void setDsDangKyHienMau(ArrayList<DangKyHienMau> dsDangKyHienMau) {
        this.dsDangKyHienMau = dsDangKyHienMau;
    }

    public String getNgaySuDung() {
        return ngaySuDung;
    }

    public void setNgaySuDung(String ngaySuDung) {
        this.ngaySuDung = ngaySuDung;
    }
}
