package com.example.myapplication;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

 class ContactAdapter extends RecyclerView.Adapter<MyViewHolder> {
    Context context;
    ArrayList<Contact> contacts;
    OnItemClickListener listener;

    public interface OnItemClickListener{
        void  onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener clickListener){
        listener =clickListener;
    }


    public ContactAdapter(Context context ,ArrayList<Contact> contacts){
        this.context=context;
        this.contacts=contacts;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.contact_item, parent, false);

        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, @SuppressLint("RecyclerView") int position) {
        holder.contact.setImageResource(contacts.get(position).getImage());
        holder.name.setText(contacts.get(position).getName());
        holder.phoneNumber.setText(contacts.get(position).getPhoneNumber());
        holder.contactCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Dialog editContact;
                ImageView close;
                Button set;
                EditText newF;
                EditText newL;
                EditText newP;


                editContact = new Dialog(context);
                editContact.setContentView(R.layout.edit_contact);
                editContact.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT,ViewGroup.LayoutParams.MATCH_PARENT);
                editContact.getWindow().setBackgroundDrawable(AppCompatResources.getDrawable(context,R.drawable.edittext));
                editContact.setCancelable(true);
                editContact.show();
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
                        contacts.get(position).setFirstName(newF.getText().toString());
                        contacts.get(position).setLastName(newL.getText().toString());
                        contacts.get(position).setPhoneNumber(newP.getText().toString());
                        notifyItemChanged(position);
                        editContact.dismiss();
                    }
                });;
            }
        });
            }




    @Override
    public int getItemCount() {
        return contacts.size();
    }
}


class MyViewHolder extends RecyclerView.ViewHolder {

    TextView name, phoneNumber;

    ImageView contact;

    CardView contactCard;

    public MyViewHolder(@NonNull View itemView) {
        super(itemView);
        name = itemView.findViewById(R.id.contacNeme);
        phoneNumber = itemView.findViewById(R.id.contactNumber);
        contact = itemView.findViewById(R.id.contactImage);
        contactCard = itemView.findViewById(R.id.contactCard);
    }
}
