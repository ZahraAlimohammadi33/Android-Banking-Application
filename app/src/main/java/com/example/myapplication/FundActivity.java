package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.DividerItemDecoration;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;


import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import java.util.ArrayList;


public class FundActivity extends AppCompatActivity {

    private static EditText amount;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        Bundle extras = getIntent().getExtras();
        String phone = null;
        if (extras != null) {
            phone = extras.getString("user");
        }


        super.onCreate(savedInstanceState);
        setContentView(R.layout.funds);

        Spinner spinner;
        EditText time;
        Button add;
        RecyclerView recyclerView;
        FundAdapter adapter;
        FundSpinnerAdapter adapterSpinner;

        spinner=findViewById(R.id.fund_spinner);
        time=findViewById(R.id.periodAmount);
        amount=findViewById(R.id.fund_first_amount);
        add=findViewById(R.id.addFund);
        recyclerView=findViewById(R.id.fundRecycle);


        GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
        recyclerView.setLayoutManager(gridLayoutManager);
        adapter = new FundAdapter(this, Options.findUser(phone).getFunds());
        recyclerView.setAdapter(adapter);
        recyclerView.addItemDecoration(new DividerItemDecoration(this, DividerItemDecoration.VERTICAL));
        Options.addFundsKind();
        adapterSpinner = new FundSpinnerAdapter(FundActivity.this, Options.getFundsKind());
        spinner.setAdapter(adapterSpinner);
        String finalPhone = phone;

        adapter.setOnItemClickListener(new FundAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(int position) {

            }
            });

        String finalPhone1 = phone;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (spinner.getSelectedItem().toString().equals("2")) {
                    int position = Options.addBounosFund(Integer.parseInt(amount.getText().toString()), spinner.getSelectedItem().toString(), Options.findUser(finalPhone1), Integer.parseInt(time.getText().toString()));
                    adapter.notifyItemInserted(position);
                    amount.setText("");
                    time.setText("");
                } else {

                        int position = Options.addFund(spinner.getSelectedItem().toString(), Options.findUser(finalPhone1));
                        adapter.notifyItemInserted(position);
                        amount.setText("");
                        time.setText("");
                }
            }
        });

        FundThread thread=new FundThread();
        thread.start();


}

    public static EditText getAmount() {
        return amount;
    }
}


