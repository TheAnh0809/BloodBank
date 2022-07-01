package com.example.mad_n5_t16.Public.model_class;

import java.io.Serializable;

public class LichHienMau implements Serializable {
    private int id;
    private ThoiGian thoiGian;
    private String ghiChu;
    private DiaDiem diaDiem;

    public LichHienMau(ThoiGian thoiGian, String ghiChu, DiaDiem diaDiem) {
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
        this.diaDiem = diaDiem;
    }

    public LichHienMau(int id, ThoiGian thoiGian, String ghiChu, DiaDiem diaDiem) {
        this.id = id;
        this.thoiGian = thoiGian;
        this.ghiChu = ghiChu;
        this.diaDiem = diaDiem;
    }

    public LichHienMau() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public ThoiGian getThoiGian() {
        return thoiGian;
    }

    public void setThoiGian(ThoiGian thoiGian) {
        this.thoiGian = thoiGian;
    }

    public String getGhiChu() {
        return ghiChu;
    }

    public void setGhiChu(String ghiChu) {
        this.ghiChu = ghiChu;
    }

    public DiaDiem getDiaDiem() {
        return diaDiem;
    }

    public void setDiaDiem(DiaDiem diaDiem) {
        this.diaDiem = diaDiem;
    }
}
