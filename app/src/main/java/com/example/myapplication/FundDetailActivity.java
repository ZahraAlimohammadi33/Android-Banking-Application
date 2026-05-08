package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.Objects;

public class FundDetailActivity extends AppCompatActivity {

    private static EditText amountFund;
    private static boolean done;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras = getIntent().getExtras();
        String phone = null;
        int position = 0;
        if (extras != null) {
            phone = extras.getString("phone");
            position=extras.getInt("index");
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.fund_detail_page);

        Button withdraw,deposit;
        ListView listView;

        withdraw=findViewById(R.id.withdraw);
        deposit=findViewById(R.id.deposit);
        listView=findViewById(R.id.fund_tra_list);

        FundAdapterTra adapter=new FundAdapterTra(Objects.requireNonNull(Options.findUser(phone)).getFunds().get(position).getTransactions());
        listView.setAdapter(adapter);

        String finalPhone = phone;
        int finalPosition = position;


        if (Options.findUser(phone).getFunds().get(position).getKind().equals("BonusFund") && Options.findUser(phone).getFunds().get(position).checkDueDate()){

            Dialog charge;
            Button set;

            charge = new Dialog(FundDetailActivity.this);
            charge.setContentView(R.layout.due_date_fund);
            Objects.requireNonNull(charge.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
            charge.getWindow().setBackgroundDrawable(getDrawable(R.drawable.edittext));
            charge.setCancelable(true);

            set=charge.findViewById(R.id.ok);


            set.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ///admin
                    TextView accountCharge=(TextView) findViewById(R.id.accountBalunce);
                    accountCharge.setText("$" + Options.findUser(finalPhone).getAccountCharge());
                }
            });

        }

        withdraw.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog charge;
                ImageView exit;
                Button set;


                charge = new Dialog(FundDetailActivity.this);
                charge.setContentView(R.layout.fund_diouloge);
                Objects.requireNonNull(charge.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                charge.getWindow().setBackgroundDrawable(getDrawable(R.drawable.edittext));
                charge.setCancelable(true);

                exit=charge.findViewById(R.id.closeFund);
                set=charge.findViewById(R.id.setfund);
                amountFund=charge.findViewById(R.id.amount_fund);

                charge.show();
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        charge.dismiss();
                    }
                });


                set.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        Options.getAmount(Objects.requireNonNull(Options.findUser(finalPhone)).getFunds().get(finalPosition),Options.findUser(finalPhone),Integer.parseInt(amountFund.getText().toString()));
//                        TextView accountCharge=(TextView) findViewById(R.id.accountBalunce);
//                        accountCharge.setText("$" + Objects.requireNonNull(Options.findUser(finalPhone)).getAccountCharge());
                        if(done){
                            charge.dismiss();
                        }
                    }
                });
            }
        });

        deposit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog charge;
                ImageView exit;
                Button set;


                charge = new Dialog(FundDetailActivity.this);
                charge.setContentView(R.layout.fund_diouloge);
                Objects.requireNonNull(charge.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                charge.getWindow().setBackgroundDrawable(getDrawable(R.drawable.edittext));
                charge.setCancelable(true);

                exit=charge.findViewById(R.id.closeFund);
                set=charge.findViewById(R.id.setfund);
                amountFund=charge.findViewById(R.id.amount_fund);

                charge.show();
                exit.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        charge.dismiss();
                        setDone(false);
                    }
                });


                set.setOnClickListener(new View.OnClickListener() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void onClick(View view) {
                        Options.addAmountToFund(Objects.requireNonNull(Options.findUser(finalPhone)).getFunds().get(finalPosition),Options.findUser(finalPhone),Integer.parseInt(amountFund.getText().toString()));
//                        TextView accountCharge=(TextView) findViewById(R.id.accountBalunce);
//                        accountCharge.setText("$" + Objects.requireNonNull(Options.findUser(finalPhone)).getAccountCharge());
                        if(done){
                            charge.dismiss();
                            setDone(false);
                        }
                    }
                });
            }
        });
    }

    public static EditText getAmountFund() {
        return amountFund;
    }

    public static void setDone(boolean done) {
        FundDetailActivity.done = done;
    }
}

class FundAdapterTra extends BaseAdapter {
    ArrayList<Transaction> items;

    public FundAdapterTra(ArrayList<Transaction> items) {
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
    }
}


