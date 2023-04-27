package com.example.prodon.ui.sqliteHelper;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prodon.R;

import java.util.ArrayList;

public class PlayersRecyclerAdapter extends RecyclerView.Adapter<PlayersRecyclerAdapter.ViewHolder> {

    private ArrayList<PlayerModel> players;


    public PlayersRecyclerAdapter(ArrayList<PlayerModel> players) {
        this.players = players;
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
        private Button btn, startBtn;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);

            txtParent = itemView.findViewById(R.id.txtParentName);
            txtYear = itemView.findViewById(R.id.txtYear);
            txtName = itemView.findViewById(R.id.txtPlayerName);
            btn = itemView.findViewById(R.id.btnStatus);
            startBtn = itemView.findViewById(R.id.btnPay);
            v= itemView.findViewById(R.id.parent);

        }
    }
}

