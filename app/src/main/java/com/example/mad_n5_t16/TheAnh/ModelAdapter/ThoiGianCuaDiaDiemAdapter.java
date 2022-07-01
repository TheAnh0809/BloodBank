package com.example.mad_n5_t16.TheAnh.ModelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import com.example.mad_n5_t16.Public.model_class.DatabaseHelper;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;
import com.example.mad_n5_t16.Public.model_class.LichHienMau;
import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.TheAnh.Activity.Employee.DanhSachLichHM_Activity;

import java.util.ArrayList;

public class ThoiGianCuaDiaDiemAdapter extends BaseAdapter {
    final ArrayList<LichHienMau> listData;
    private Context context;
    private int layout;
    private DatabaseHelper databaseHelper;
    private DanhSachLichHM_Activity danhSachLichHM_activity;
    public ThoiGianCuaDiaDiemAdapter(ArrayList<LichHienMau> listData, Context context, int layout, DatabaseHelper databaseHelper) {
        this.listData = listData;
        this.context = context;
        this.layout = layout;
        this.databaseHelper = databaseHelper;
    }
    public ThoiGianCuaDiaDiemAdapter(ArrayList<LichHienMau> listData, Context context, int layout, DatabaseHelper databaseHelper, DanhSachLichHM_Activity danhSachLichHM_activity) {
        this.listData = listData;
        this.context = context;
        this.layout = layout;
        this.databaseHelper = databaseHelper;
        this.danhSachLichHM_activity = danhSachLichHM_activity;
    }
    @Override
    public int getCount() {
        return  listData.size();
    }
    @Override
    public Object getItem(int position) {
        return listData.get(position);
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }
    private class ViewHolder{
        TextView textNgay, textThang, textGioDiaDiem;
        ImageButton btnXoa;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ThoiGianCuaDiaDiemAdapter.ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater infalter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalter.inflate(layout, null);
            viewHolder = new ThoiGianCuaDiaDiemAdapter.ViewHolder();

            viewHolder.textNgay = convertView.findViewById(R.id.textNgay);
            viewHolder.textThang = convertView.findViewById(R.id.textThangNawm);
            viewHolder.textGioDiaDiem = convertView.findViewById(R.id.textViewGioDiaDiem);
            viewHolder.btnXoa = convertView.findViewById(R.id.imageButtonDelete);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ThoiGianCuaDiaDiemAdapter.ViewHolder) convertView.getTag();
        }


        LichHienMau lichHienMau = listData.get(position);
        viewHolder.textNgay.setText(lichHienMau.getThoiGian().ThoiGianNgay());
        viewHolder.textThang.setText(lichHienMau.getThoiGian().ThoiGianThang()+"-"+lichHienMau.getThoiGian().ThoiGianNam());
        viewHolder.textGioDiaDiem.setText(lichHienMau.getThoiGian().getGioBatDau()+" - " + lichHienMau.getThoiGian().getGioKetThuc() +
                "\n" + lichHienMau.getDiaDiem().getTenDiaDiem());
        viewHolder.btnXoa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseHelper.deleteLichHienMau(lichHienMau);
                danhSachLichHM_activity.fillData();
            }
        });
        return convertView;
    }
}
