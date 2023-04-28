package com.example.prodon.ui.sqliteHelper;

import android.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prodon.R;

import java.util.ArrayList;

public class PlayersRecyclerAdapter extends RecyclerView.Adapter<PlayersRecyclerAdapter.ViewHolder> {

    private ArrayList<PlayerModel> players;
    private LayoutInflater inflater;

    public PlayersRecyclerAdapter(ArrayList<PlayerModel> players,LayoutInflater inflater) {
        this.players = players;
        this.inflater = inflater;
    }


    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.drive_parent_recycler_layout, parent, false);
        ViewHolder holder = new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {

        String a, b, c;

        a = "Player Name: " + players.get(position).getfName() +" "+players.get(position).getlName();

        b = "Parent Name: " + players.get(position).getParentName()+" "+players.get(position).getlName();

        c = "Year: " + players.get(position).getYear();

        holder.txtParent.setText(b);
        holder.txtYear.setText(c);
        holder.txtName.setText(a);
        int s = players.get(position).getId();
        holder.payBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                builder.setTitle("Details about the drive");

                // set the custom layout
                final View customLayout =inflater.inflate(R.layout.dialogpayment, null);
                builder.setView(customLayout);
                AlertDialog dialog = builder.create();
                Button closeBtn = customLayout.findViewById(R.id.closeBtn);
                Button submitBtn = customLayout.findViewById(R.id.submitBtn);
                NumberPicker numberPicker = customLayout.findViewById(R.id.numberPicker);
                EditText value = customLayout.findViewById(R.id.editValue);
                EditText year = customLayout.findViewById(R.id.editYear);
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });
                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                    }
                });
                numberPicker.setMinValue(1);
                numberPicker.setMaxValue(12);
                dialog.show();

            }
        });
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(v.getContext(),"Id is: " + s,Toast.LENGTH_LONG).show();
            }
        });
    }

    @Override
    public int getItemCount() {
        return players.size();
    }

    public ArrayList getPlayers() {
        return players;
    }

    public void setPlayers(ArrayList players) {
        this.players = players;
        notifyDataSetChanged();
    }

    public class ViewHolder extends RecyclerView.ViewHolder { //view holder to set the txt views and listeners
        private TextView txtParent, txtYear, txtName;
        private CardView v;
        private Button statusBtn, payBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtParent = itemView.findViewById(R.id.txtParentName);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtName = itemView.findViewById(R.id.txtPlayerName);
            statusBtn = itemView.findViewById(R.id.btnStatus);
            payBtn = itemView.findViewById(R.id.btnPay);
            v= itemView.findViewById(R.id.parent);

        }
    }
}

