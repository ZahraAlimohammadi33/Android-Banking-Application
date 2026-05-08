package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;


public class ContactTraFragment extends Fragment {


    public ContactTraFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        String phone = getArguments().getString("user");
        View view=inflater.inflate(R.layout.fragment_contact_tra, container, false);
        ListView listView=view.findViewById(R.id.Contact_list);
        Adapter adapter=new Adapter(Options.findUser(phone).getContacts());
        listView.setAdapter(adapter);

        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                Transfer.getAccountNumber().setText(Options.findContact(Options.findUser(phone).getContacts().get(i).getPhoneNumber()));
                Intent intent=new Intent(getActivity(),Transfer.class);
                intent.putExtra("index_C",i);
                startActivity(intent);
            }
        });
        return view;
    }
}
class Adapter extends BaseAdapter {
    ArrayList<Contact> items;

    public Adapter(ArrayList<Contact> items) {
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
        textView.setText(items.get(i).getName());
        return textView;
    }
}