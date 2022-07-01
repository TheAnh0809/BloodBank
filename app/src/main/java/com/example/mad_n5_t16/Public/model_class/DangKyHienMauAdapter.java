package com.example.mad_n5_t16.Public.model_class;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mad_n5_t16.R;

import java.util.ArrayList;

public class DangKyHienMauAdapter extends RecyclerView.Adapter<DangKyHienMauAdapter.ViewHolder> {


    public ArrayList<DangKyHienMau> dsDangKyHienMau;

    public DangKyHienMauAdapter(Context context, ArrayList<DangKyHienMau> dsDangKyHienMau) {
        this.dsDangKyHienMau = dsDangKyHienMau;
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        TextView txtHoTen_Sdt_NguoiHienMau, txtDiaDiemHienMau;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtHoTen_Sdt_NguoiHienMau =itemView.findViewById(R.id.txtHoTen_Sdt_NguoiHienMau);
            txtDiaDiemHienMau =itemView.findViewById(R.id.txtDiaDiemHienMau);

        }
    }
    @NonNull
    @Override
    public DangKyHienMauAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v= LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item_dshienmau_employee, viewGroup, false);
        return new ViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull DangKyHienMauAdapter.ViewHolder viewHolder, int i) {
        viewHolder.itemView.setTag(dsDangKyHienMau.get(i));

        viewHolder.txtHoTen_Sdt_NguoiHienMau.setText(dsDangKyHienMau.get(i).getNguoiHienMau().getHoTen()+" - "+ dsDangKyHienMau.get(i).getNguoiHienMau().getDienThoai());
        viewHolder.txtDiaDiemHienMau.setText(dsDangKyHienMau.get(i).getLichHienMau().getDiaDiem().getTenDiaDiem());
    }

    @Override
    public int getItemCount() {
        return dsDangKyHienMau.size();
    }

}
