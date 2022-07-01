package com.example.mad_n5_t16.Public.model_class;

import java.io.Serializable;

public class DiaDiem implements Serializable {
    private int id;
    private String tenDiaDiem;

    public DiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }

    public DiaDiem() {
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTenDiaDiem() {
        return tenDiaDiem;
    }

    public void setTenDiaDiem(String tenDiaDiem) {
        this.tenDiaDiem = tenDiaDiem;
    }
}
