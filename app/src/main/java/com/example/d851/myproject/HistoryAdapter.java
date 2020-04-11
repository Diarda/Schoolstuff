package com.example.d851.myproject;


import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v4.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.List;

public class HistoryAdapter extends ArrayAdapter
{
    private  int resource;
    private  Context context;

    public HistoryAdapter(@NonNull Context context, int resource, @NonNull List objects)
    {
        super(context, resource, objects);
        this.resource=resource;
        this.context=context;

    }
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        View v =convertView;
        if(v==null)
        {
            v= LayoutInflater.from(this.getContext()).inflate(resource,null);
        }
        Pair<String, String> s = (Pair) getItem(pos);
        TextView dateView = (TextView)v.findViewById(R.id.tvDate);
        TextView productView = (TextView)v.findViewById(R.id.tvPrn);
        productView.setText(s.first);
        dateView.setText(s.second);
        return v;
    }
}
