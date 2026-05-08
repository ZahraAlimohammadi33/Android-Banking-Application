package com.example.myapplication;

import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class TransactionActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras=getIntent().getExtras();
        String phone=null;
        if(extras != null){
            phone= extras.getString("user");
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.trancections);

        ListView tra;
        tra = findViewById(R.id.transection_list);
        TraAdapter adapter=new TraAdapter(Objects.requireNonNull(Options.findUser(phone)).getTransactions());
        tra.setAdapter(adapter);
    }


}

class TraAdapter extends BaseAdapter {
    ArrayList<Transaction> items;

    public TraAdapter(ArrayList<Transaction> items) {
        super();
        this.items = items;
    }

    @Override
    public int getCount() {
        return items.size();
    }

    @Override
    public Object getItem(int i) {
        return items.get(i);
    }

    @Override
    public long getItemId(int i) {
        return items.get(i).hashCode();
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        TextView textView = new TextView(viewGroup.getContext());
        textView.setText(items.get(i).toString());
        return textView;
    }}
