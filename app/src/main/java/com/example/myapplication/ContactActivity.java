package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.Objects;

public class ContactActivity extends AppCompatActivity {

    RecyclerView recyclerView;

     ContactAdapter adapter;
     EditText firstName;
     EditText lastName;
     EditText phoneNumber;

     Button add,delete;



    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.contact_page);
        Bundle extras=getIntent().getExtras();
        String phone=null;
        if(extras != null) {
            phone = extras.getString("user");}


            recyclerView = findViewById(R.id.contactRecycle);
            firstName = findViewById(R.id.firstContactName);
            lastName = findViewById(R.id.lastContactName);
            phoneNumber = findViewById(R.id.contactPhone);
            add = findViewById(R.id.add);
            delete = findViewById(R.id.delete);


            GridLayoutManager gridLayoutManager = new GridLayoutManager(getApplicationContext(), 2);
            recyclerView.setLayoutManager(gridLayoutManager);
            adapter = new ContactAdapter(this, Options.findUser(phone).getContacts());
            recyclerView.setAdapter(adapter);
            String finalPhone = phone;
            adapter.setOnItemClickListener(new ContactAdapter.OnItemClickListener() {
                @Override
                public void onItemClick(int position) {
                    Dialog editContact;
                    ImageView close;
                    Button set;
                    EditText newF;
                    EditText newL;
                    EditText newP;


                    editContact = new Dialog(ContactActivity.this);
                    editContact.setContentView(R.layout.edit_contact);
                    Objects.requireNonNull(editContact.getWindow()).setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                    editContact.getWindow().setBackgroundDrawable(getDrawable(R.drawable.edittext));
                    editContact.setCancelable(true);

                    close=editContact.findViewById(R.id.closedit);
                    set=editContact.findViewById(R.id.setedit);
                    newF=editContact.findViewById(R.id.newFC);
                    newL=editContact.findViewById(R.id.newLC);
                    newP=editContact.findViewById(R.id.newPC);


                    close.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            editContact.dismiss();
                        }
                    });;


                    EditText finalNewF1 = newF;
                    set.setOnClickListener(new View.OnClickListener() {
                        @Override
                        public void onClick(View view) {
                            Options.findUser(finalPhone).getContacts().get(position).setFirstName(newF.getText().toString());
                            Options.findUser(finalPhone).getContacts().get(position).setLastName(newL.getText().toString());
                            Options.findUser(finalPhone).getContacts().get(position).setPhoneNumber(newP.getText().toString());
                            adapter.notifyItemChanged(position);
                            editContact.dismiss();
                        }
                    });;
                }
            });

            add.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    Contact contact = new Contact(firstName.getText().toString(), lastName.getText().toString(), phoneNumber.getText().toString());
                    Options.findUser(finalPhone).getContacts().add(contact);
                    int position = Options.findUser(finalPhone).getContacts().indexOf(contact);
                    adapter.notifyItemInserted(position);
                    firstName.setText("");
                    lastName.setText("");
                    phoneNumber.setText("");
                }
            });

            delete.setOnClickListener(new View.OnClickListener() {
                int position;

                @Override
                public void onClick(View view) {
                    for (int i = 0; i < Options.findUser(finalPhone).getContacts().size(); i++) {
                        if (Options.findUser(finalPhone).getContacts().get(i).getPhoneNumber().equals(phoneNumber.getText().toString())) {
                            position = i;
                            Options.findUser(finalPhone).getContacts().remove(i);
                        }
                    }
                    adapter.notifyItemRemoved(position);
                    firstName.setText("");
                    lastName.setText("");
                    phoneNumber.setText("");
                }
            });
        }


}
