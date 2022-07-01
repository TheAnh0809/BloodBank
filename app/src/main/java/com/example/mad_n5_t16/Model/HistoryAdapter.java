package com.example.mad_n5_t16.Model;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.example.mad_n5_t16.R;

public class HistoryAdapter extends ArrayAdapter<History> {
    private Context context;
    private History[] list;
    public HistoryAdapter(@NonNull Context context, History[] list) {
        super(context, R.layout.item_history, list);
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        LayoutInflater inflater =LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.item_history, null, true);
        TextView tvTimes = (TextView) view.findViewById(R.id.tvTimes);
        TextView tvDatedonate = (TextView) view.findViewById(R.id.tvDatedonate);
        TextView tvAmountdonate = (TextView) view.findViewById(R.id.tvAmountdonate);
        TextView tvLocationdonate = (TextView) view.findViewById(R.id.tvLocationdonate);

        History h = list[position];
        tvTimes.setText(h.getNumber()+"");
        tvDatedonate.setText(h.getDonationDate());
        tvAmountdonate.setText(h.getAmount()+"");
        tvLocationdonate.setText(h.getLocation());

        return view;
    }
}