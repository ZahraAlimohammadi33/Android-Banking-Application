package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

import java.util.Objects;

public class Transfer extends AppCompatActivity {

    @SuppressLint("StaticFieldLeak")
    static EditText amount;
    @SuppressLint("StaticFieldLeak")
    private static EditText accountNumber;

    private static boolean transfered;
    int index_r , index_c;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_page);

        Bundle extras = getIntent().getExtras();
        String phone = null;
        if (extras != null) {
            phone = extras.getString("user");
        }

        Intent intent = getIntent();
        int itemIndex = intent.getIntExtra("index_r", -1);
        int itemIndexx = intent.getIntExtra("index_c", -1);
        if(itemIndex != -1){

           index_r=itemIndex;
           index_c=itemIndexx;

        }


        Spinner spinner;
        TraSpinnerAdapter adapter;
        Button button;
        ImageButton imageButton;


        amount = findViewById(R.id.amount_tra);
        setAccountNumber(findViewById(R.id.accountNumber));
        spinner = findViewById(R.id.tra_spinner);
        Options.addTraMethods();
        adapter = new TraSpinnerAdapter(Transfer.this, Options.getTransferMethods());
        spinner.setAdapter(adapter);
        button = findViewById(R.id.transferboutton);
        imageButton = findViewById(R.id.contacttransfer);

        String finalPhone = phone;

        imageButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent transfer = new Intent(Transfer.this, Transfer_sec.class);
                transfer.putExtra("user", finalPhone);
                startActivity(transfer);
            }
        });


        Dialog charge;
        Button accept;
        Button reject;
        TextView trancection;

        charge = new Dialog(Transfer.this);
        charge.setContentView(R.layout.accept_tra);
        Objects.requireNonNull(charge.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
        charge.getWindow().setBackgroundDrawable(getDrawable(R.drawable.edittext));
        charge.setCancelable(true);

        accept=charge.findViewById(R.id.accept);
        reject=charge.findViewById(R.id.reject);
        trancection=charge.findViewById(R.id.trancection);

        accept.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                transferTrac(finalPhone,index_r,index_c,spinner);
                if(transfered){
                    Toast.makeText(Transfer.this, "The amount has been transferred successfully", Toast.LENGTH_SHORT).show();
                    setTransfered(false);
                    TransferThread transferThread=new TransferThread(){
                        @Override
                        public void run() {
                            runOnUiThread(new Runnable() {
                                @Override
                                public void run() {
                                    try {
                                        sleep(3000);
                                    } catch (InterruptedException e) {
                                        throw new RuntimeException(e);
                                    }
                                    Administrator.setPayasTransaction();
                                    Toast.makeText(Transfer.this, "The amount has been transferred successfully", Toast.LENGTH_SHORT).show();
                                }

                            });
                        }
                    };
                transferThread.start();
                }
                charge.dismiss();
            }
        });


        reject.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                charge.dismiss();
            }
        });




        button.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("SetTextI18n")
            @Override
            public void onClick(View view) {
                trancection.setText(amount.getText().toString() + "\n" + "To:" + accountNumber.getText().toString());
                charge.show();
            }
        });
}

public void transferTrac(String Phone, int index_r, int index_c, Spinner spinner){
        String finalPhone=Menu.getFinalPhone();
    if (index_c==-1 && index_r == -1) {
        for (int i = 0; i < Bank.getUsers().size(); i++) {
            if (Bank.getUsers().get(i).getAccountNumber().equals(accountNumber.getText().toString())) {
                Objects.requireNonNull(Options.findUser(finalPhone)).transfer(Bank.getUsers().get(i), Integer.parseInt(amount.getText().toString()), spinner.getSelectedItem().toString());
            } else if (i == Bank.getUsers().size() - 1) {
                accountNumber.setError("The accountNumber is invalid");
            }
        }
    } else if (index_r ==-1) {
        accountNumber.setText(Options.findUser(finalPhone).getContacts().get(index_c).getName());
        for (int i = 0; i < Bank.getUsers().size(); i++) {
            if (Bank.getUsers().get(i).getPhoneNumber().equals(Objects.requireNonNull(Options.findUser(finalPhone)).getContacts().get(index_c).getPhoneNumber())) {
                Objects.requireNonNull(Options.findUser(finalPhone)).transfer(Bank.getUsers().get(i), Integer.parseInt(amount.getText().toString()), spinner.getSelectedItem().toString());
            }else if (i == Bank.getUsers().size() - 1) {
                accountNumber.setError("Cant find the user");
            }
        }} else {
        accountNumber.setText(Options.findUser(finalPhone).getRecent().get(index_r));
        for (int i = 0; i < Bank.getUsers().size(); i++) {
            if (Bank.getUsers().get(i).getAccountNumber().equals(Objects.requireNonNull(Options.findUser(finalPhone)).getRecent().get(index_r))){
                Objects.requireNonNull(Options.findUser(finalPhone)).transfer(Bank.getUsers().get(i), Integer.parseInt(amount.getText().toString()), spinner.getSelectedItem().toString());
            }else if (i == Bank.getUsers().size() - 1) {
                accountNumber.setError("The accountNumber is invalid");
            }
        }}
}


    public static EditText getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(EditText accountNumber) {
        Transfer.accountNumber = accountNumber;
    }

    public static EditText getAmount() {
        return amount;
    }

    public static void setTransfered(boolean transfered) {
        Transfer.transfered = transfered;
    }


}


