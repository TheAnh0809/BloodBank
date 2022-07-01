package com.example.mad_n5_t16.TheAnh.ModelAdapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.example.mad_n5_t16.R;
import com.example.mad_n5_t16.Public.model_class.DiaDiem;

import java.util.ArrayList;

public class DiaDiemHienMauAdapter extends BaseAdapter {
    final ArrayList<DiaDiem> listData;
    private Context context;
    private int layout;
    public DiaDiemHienMauAdapter(ArrayList<DiaDiem> listData, Context context, int layout) {
        this.listData = listData;
        this.context = context;
        this.layout = layout;
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
    class ViewHolder{
        TextView textView;
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder;
        if(convertView == null){
            LayoutInflater infalter = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = infalter.inflate(layout, null);
            viewHolder = new ViewHolder();

            viewHolder.textView = convertView.findViewById(R.id.textĐiaiemItem);
            convertView.setTag(viewHolder);
        } else {
            viewHolder = (ViewHolder) convertView.getTag();
        }


        DiaDiem diaDiem = listData.get(position);
        viewHolder.textView.setText(diaDiem.getTenDiaDiem());
        return convertView;
    }
}
