package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class SignIn extends AppCompatActivity {
    Button signUpButton;

    EditText phone;
    EditText pass;
    EditText username;
    EditText firstName;
    EditText lastName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup);
        signUpButton=findViewById(R.id.signupButton);
        pass=findViewById(R.id.pass);
        phone=findViewById(R.id.phone);
        username=findViewById(R.id.username);
        lastName=findViewById(R.id.lastName);
        firstName=findViewById(R.id.firstName);

        signUpButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                checkPass();
                checkInformation(username);
                checkInformation(lastName);
                checkInformation(firstName);
                checkPhone();
                if(!checkPass() || !checkInformation(username) || !checkInformation(lastName) || !checkInformation(firstName) || !checkPhone()){
                    Toast.makeText(SignIn.this, "Signup Failed", Toast.LENGTH_SHORT).show();
                    return;
                } else {
                    User user= new User(firstName.getText().toString(),lastName.getText().toString(),phone.getText().toString(),username.getText().toString(),pass.getText().toString());
                    Bank.getUsers().add(user);
                    Toast.makeText(SignIn.this, "Sign up Successful!", Toast.LENGTH_SHORT).show();
                    Intent i = new Intent(view.getContext(),MainActivity.class);
//                    i.putExtra("user",phone.getText().toString());
                    startActivity(i);
                }
            }
        });
    }

    public boolean checkPass(){
        String passInput= pass.getText().toString().trim();
        if(passInput.isEmpty()){
            pass.setError("Field can not be empty");
            return false;
        }
        if(!Bank.checkPassword(pass.getText().toString())){
            pass.setError("Password is too weak");
            return false;
        }
        pass.setError(null);
        return true;
    }

    public boolean checkInformation(EditText input){
        String str= input.getText().toString().trim();
        if(str.isEmpty()){
            input.setError("Field can not be empty");
            return false;
        }
        input.setError(null);
        return true;
    }

    public boolean checkPhone(){
        if(phone.getText().toString().trim().isEmpty()){
            phone.setError("Field can not be empty");
            return false;
        }
        if(phone.getText().toString().split("").length!=11){
            phone.setError("PhoneNumber is incrroct");
            return false;
        }
        for (int i=0; i<Bank.getUsers().size() ; i++){
            if(Bank.getUsers().get(i).getPhoneNumber().equals(phone.getText().toString())){
                phone.setError("PhoneNumber is already exist");
                return false;
            }
        }
        phone.setError(null);
        return true;
    }
}
