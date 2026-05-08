package com.example.myapplication;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.Button;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.bottomnavigation.BottomNavigationView;

public class Transfer_sec extends AppCompatActivity implements BottomNavigationView
        .OnNavigationItemSelectedListener {


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        Bundle extras=getIntent().getExtras();
        String phone=null;
        if(extras != null){
            phone= extras.getString("user");
            Intent transfer = new Intent(Transfer_sec.this,Transfer.class);
            transfer.putExtra("user", phone);
        }

        super.onCreate(savedInstanceState);
        setContentView(R.layout.transfer_page2);

        BottomNavigationView tra;
        Button button;

        tra=findViewById(R.id.navi);
        tra.setOnNavigationItemSelectedListener(this);
        tra.setSelectedItemId(R.id.recent_card);

        Bundle bundle = new Bundle();
        bundle.putString("user", phone);

        fragment1.setArguments(bundle);
        fragment2.setArguments(bundle);

    }
    RecentCardFragment fragment1=new RecentCardFragment();
    ContactTraFragment fragment2=new ContactTraFragment();

    String f1=Integer.toString(R.id.recent_card);
    String f2=Integer.toString(R.id.tra_contact);
    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.recent_card){

            getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.frame_layout, fragment1)
                    .commit();
            return true;
        }
        if(item.getItemId()==R.id.tra_contact){
                getSupportFragmentManager()
                        .beginTransaction()
                        .replace(R.id.frame_layout, fragment2)
                        .commit();
                return true;}

        return false;

    }


}
