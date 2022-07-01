package com.example.mad_n5_t16.Public.model_class;

import java.io.Serializable;

public class DangKyHienMau implements Serializable {
    private int id;
    private LichHienMau lichHienMau;
    private NguoiHienMau nguoiHienMau;
    private int luongMau;

    public DangKyHienMau(LichHienMau lichHienMau, NguoiHienMau nguoiHienMau, int luongMau) {
        this.lichHienMau = lichHienMau;
        this.nguoiHienMau = nguoiHienMau;
        this.luongMau = luongMau;
    }

    public DangKyHienMau() {
        this.luongMau=0;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LichHienMau getLichHienMau() {
        return lichHienMau;
    }

    public void setLichHienMau(LichHienMau lichHienMau) {
        this.lichHienMau = lichHienMau;
    }

    public NguoiHienMau getNguoiHienMau() {
        return nguoiHienMau;
    }

    public void setNguoiHienMau(NguoiHienMau nguoiHienMau) {
        this.nguoiHienMau = nguoiHienMau;
    }

    public int getLuongMau() {
        return luongMau;
    }

    public void setLuongMau(int luongMau) {
        this.luongMau = luongMau;
    }
}
