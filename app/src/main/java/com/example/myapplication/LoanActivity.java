package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class LoanActivity extends AppCompatActivity {

    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.loan);
        Bundle extras = getIntent().getExtras();
        String phone = null;
        if (extras != null) {
            phone = extras.getString("user");
        }

        EditText amount,time,loanType;
        ListView listView;

        Button add;

        amount=findViewById(R.id.loan_amount);
        time=findViewById(R.id.loan_time);
        loanType=findViewById(R.id.loan_kind);
        listView=findViewById(R.id.loanRequest_list);

        add=findViewById(R.id.add_loan);

        String finalPhone = phone;
        add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (android.os.Build.VERSION.SDK_INT >= android.os.Build.VERSION_CODES.O) {
                    Loan loan=new Loan(Integer.parseInt(amount.getText().toString()),Integer.parseInt(time.getText().toString()),Calender.now(), loanType.getText().toString(),LoanCondition.Pending);
                    Options.findUser(finalPhone).getLoans().add(loan);
                }
            }
        });
        LoanAdapter adapter=new LoanAdapter(Objects.requireNonNull(Options.findUser(phone)).getLoans().toString());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(LoanActivity.this, LoanManagment.class);
                intent.putExtra("index_r", position);
                startActivity(intent);
            }
        });


    }
}

class LoanAdapter extends BaseAdapter {
    ArrayList<String> items;

    public LoanAdapter(ArrayList<String> items) {
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
        textView.setText(items.get(i));
        return textView;
    }
}
