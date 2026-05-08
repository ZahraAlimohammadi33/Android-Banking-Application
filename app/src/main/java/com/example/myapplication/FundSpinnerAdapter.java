package com.example.myapplication;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class FundSpinnerAdapter extends BaseAdapter {
    Context context;
    ArrayList<String> ways;

    public FundSpinnerAdapter(Context context, ArrayList<String > arrayList){
        this.context=context;
        this.ways=arrayList;
    }

    @Override
    public int getCount() {
        return ways!=null ?ways.size() :0 ;
    }

    @Override
    public Object getItem(int i) {
        return i;
    }

    @Override
    public long getItemId(int i) {
        return i;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        View rootview= LayoutInflater.from(context).inflate(R.layout.spinner_fund, viewGroup, false);

        TextView textView=rootview.findViewById(R.id.spinner_text_fund);

        textView.setText(ways.get(i));

        return rootview;
    }
}
