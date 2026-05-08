package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
    EditText phoneNumber;
    EditText password;
    Button loginButton;

    TextView textView;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        phoneNumber = findViewById(R.id.phonenumber);
        password = findViewById(R.id.password);
        loginButton = findViewById(R.id.loginButton);
        textView =(TextView) findViewById(R.id.signup);



        textView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent(MainActivity.this, SignIn.class);
                startActivity(i);
            }
        });


        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phone=phoneNumber.getText().toString();
                Bank bank=new Bank();
                checkPhone();
                checkPass();
                if (Bank.checkUserLogin(phoneNumber.getText().toString(), password.getText().toString(), Bank.getUsers())) {
                    Toast.makeText(MainActivity.this, "Login Successful!", Toast.LENGTH_SHORT).show();
                    Intent menu = new Intent(MainActivity.this,Menu.class);
                    menu.putExtra("user",phone);
                    startActivity(menu);

                } else {
                    Toast.makeText(MainActivity.this, "Login Failed", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }


    public boolean checkPhone(){
        if(phoneNumber.getText().toString().trim().isEmpty()){
            phoneNumber.setError("Field can not be empty");
            return false;
        }
        if(phoneNumber.getText().toString().split("").length!=11){
            phoneNumber.setError("PhoneNumber is incrroct");
            return false;
        }

        phoneNumber.setError(null);
        return true;
    }

    public boolean checkPass(){
        String passInput= password.getText().toString().trim();
        if(passInput.isEmpty()){
            password.setError("Field can not be empty");
            return false;
        }

        password.setError(null);
        return true;
    }


}