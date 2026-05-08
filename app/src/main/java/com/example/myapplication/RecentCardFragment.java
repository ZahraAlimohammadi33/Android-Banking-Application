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
import java.util.Objects;


public class RecentCardFragment extends Fragment {

    DataTransferInterface dataTransferInterface;


    public RecentCardFragment() {

    }

    public void sendDataToActivity(String data) {
        dataTransferInterface.sendData(data);
    }

    public interface DataTransferInterface {
        void sendData(String data);
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        String phone = getArguments().getString("user");
        View view=inflater.inflate(R.layout.fragment_recent_card, container, false);
        ListView listView=view.findViewById(R.id.recent_card_list);
        CustomAdapter adapter=new CustomAdapter(Objects.requireNonNull(Options.findUser(phone)).getRecent());
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(getActivity(), Transfer.class);
                intent.putExtra("index_r", position);
                startActivity(intent);
            }
        });
        return view;
    }
}

class CustomAdapter extends BaseAdapter {
    ArrayList<String> items;

    public CustomAdapter(ArrayList<String> items) {
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