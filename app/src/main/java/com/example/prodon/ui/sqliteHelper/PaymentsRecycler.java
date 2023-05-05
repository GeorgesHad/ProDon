package com.example.prodon.ui.sqliteHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prodon.R;

import java.util.ArrayList;

public class PaymentsRecycler extends RecyclerView.Adapter<PaymentsRecycler.ViewHolder> {
private ArrayList<Payment> ar;

    public PaymentsRecycler(ArrayList<Payment> ar) {
        this.ar = ar;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.payment_rec_element, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;

    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        String s;
        s="Amount: "+ar.get(position).getAmount();
        holder.amount.setText(s);
        s="Date: "+ar.get(position).getDate();
        holder.date.setText(s);
        s="This payment covered the month "+ar.get(position).getMonth()+"-"+ar.get(position).getYear();
        holder.month.setText(s);
    }

    @Override
    public int getItemCount() {
        return ar.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private TextView date,month,amount;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            date = itemView.findViewById(R.id.date);
            month = itemView.findViewById(R.id.monthPaid);
            amount = itemView.findViewById(R.id.amount);
        }
    }

}
