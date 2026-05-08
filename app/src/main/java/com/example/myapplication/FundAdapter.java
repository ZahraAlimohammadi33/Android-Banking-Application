package com.example.myapplication;

import static androidx.core.content.ContextCompat.startActivity;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.content.res.AppCompatResources;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;


public class FundAdapter extends RecyclerView.Adapter<MyViewHolderFund> {
        Context context;
        ArrayList<Fund> funds;
        OnItemClickListener listener;

public interface OnItemClickListener{
    void  onItemClick(int position);
}



    public void setOnItemClickListener(FundAdapter.OnItemClickListener clickListener){
        listener =clickListener;
    }


    public FundAdapter(Context context , ArrayList<Fund> funds){
        this.context=context;
        this.funds=funds;
    }

    @NonNull
    @Override
    public MyViewHolderFund onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.fund_item, parent, false);
        return new MyViewHolderFund(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolderFund holder, @SuppressLint("RecyclerView") int position) {
        holder.fund.setImageResource(funds.get(position).getImage());
        holder.fundBalance.setText(String.valueOf(funds.get(position).toString()));
        holder.fundCard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent fund = new Intent(context,FundDetailActivity.class);
                fund.putExtra("index", position);
                fund.putExtra("phone",funds.get(position).getPhone());
                context.startActivity(fund);
            }
        });
    }




    @Override
    public int getItemCount() {
        return funds.size();
    }
}


class MyViewHolderFund extends RecyclerView.ViewHolder {

    TextView fundKind, fundBalance;

    ImageView fund;

    CardView fundCard;

    public MyViewHolderFund(@NonNull View itemView) {
        super(itemView);
        fundBalance = itemView.findViewById(R.id.fundBalance);
        fund= itemView.findViewById(R.id.fundImage);
        fundCard = itemView.findViewById(R.id.fundCard);

    }

}

