package com.example.mad_n5_t16.Public.model_class;

import java.io.Serializable;

public class ThoiGian implements Serializable {
    private int id;
    private String ngay;
    private String gioBatDau;
    private String gioKetThuc;

    public ThoiGian(String ngay, String gioBatDau, String gioKetThuc) {
        this.ngay = ngay;
        this.gioBatDau = gioBatDau;
        this.gioKetThuc = gioKetThuc;
    }
    public ThoiGian(){
    }
    public String ThoiGianNgay() {
        return ngay.substring(8,10);
    }
    public String ThoiGianThang() {
        return ngay.substring(5,7);
    }
    public String ThoiGianNam() {
        return ngay.substring(0,4);
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNgay() {
        return ngay;
    }

    public void setNgay(String ngay) {
        this.ngay = ngay;
    }

    public String getGioBatDau() {
        return gioBatDau;
    }

    public void setGioBatDau(String gioBatDau) {
        this.gioBatDau = gioBatDau;
    }

    public String getGioKetThuc() {
        return gioKetThuc;
    }

    public void setGioKetThuc(String gioKetThuc) {
        this.gioKetThuc = gioKetThuc;
    }
}
