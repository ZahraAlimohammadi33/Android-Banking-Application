package com.example.myapplication;


import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Objects;

public class Menu extends AppCompatActivity {

    static String finalPhone;


    @SuppressLint({"WrongViewCast", "UseCompatLoadingForDrawables", "SetTextI18n"})
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.daushboard);
        TextView accountCharge = findViewById(R.id.accountBalunce);;
        Bundle extras=getIntent().getExtras();
        String phone=null;
        if(extras != null){
            phone= extras.getString("user");
            accountCharge=(TextView) findViewById(R.id.accountBalunce);
            accountCharge.setText("$" + Objects.requireNonNull(Options.findUser(phone)).getAccountCharge());
        }

        accountCharge.setText("$" + Objects.requireNonNull(Options.findUser(phone)).getAccountCharge());
        Button chargeClick;
        ImageButton transfer;
        ImageButton contacts;
        ImageButton funds;
        ImageButton requests;
        ImageButton tracsaitions;

        chargeClick=findViewById(R.id.charge);
        transfer=findViewById(R.id.transfer);
        contacts=findViewById(R.id.cotactmanage);
        funds=findViewById(R.id.funds);
        requests=findViewById(R.id.requestButton);
        tracsaitions=findViewById(R.id.traButton);


        Dialog charge;
        ImageView exit;
        Button set;
        EditText amount;

        charge = new Dialog(Menu.this);
        charge.setContentView(R.layout.chargetheaccount);
        Objects.requireNonNull(charge.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        charge.getWindow().setBackgroundDrawable(getDrawable(R.drawable.edittext));
        charge.setCancelable(true);

        exit=charge.findViewById(R.id.closechrge);
        set=charge.findViewById(R.id.setcharge);
        amount=charge.findViewById(R.id.amount);


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charge.dismiss();
            }
        });

        finalPhone = phone;
        String finalPhone1 = phone;
        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Options.findUser(finalPhone).chargeTheAccount(Integer.parseInt(amount.getText().toString()));
                charge.dismiss();
                TextView accountCharge=(TextView) findViewById(R.id.accountBalunce);
                accountCharge.setText("$" + Options.findUser(finalPhone1).getAccountCharge());
            }
        });
        chargeClick.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charge.show();
                }
            });

        String finalPhone2 = phone;
        transfer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transfer = new Intent(Menu.this,Transfer.class);
                transfer.putExtra("user", finalPhone);
                startActivity(transfer);
            }
        });

        contacts.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent contact = new Intent(Menu.this,ContactActivity.class);
                contact.putExtra("user", finalPhone2);
                startActivity(contact);
            }
        });

        funds.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fund = new Intent(Menu.this,FundActivity.class);
                fund.putExtra("user", finalPhone2);
                startActivity(fund);
            }
        });


        requests.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent request = new Intent(Menu.this,RequestActivity.class);
                request.putExtra("user", finalPhone2);
                startActivity(request);
            }
        });

        tracsaitions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent request = new Intent(Menu.this,TransactionActivity.class);
                request.putExtra("user", finalPhone2);
                startActivity(request);
            }
        });
    }

    public static String getFinalPhone() {
        return finalPhone;
    }

    //    public void setaccountBalnce(){
//
//       accountCharge.setText("200");
//    }


    }


