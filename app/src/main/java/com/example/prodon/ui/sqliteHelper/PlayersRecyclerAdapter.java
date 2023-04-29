package com.example.prodon.ui.sqliteHelper;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.content.pm.ResolveInfo;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.fragment.app.FragmentActivity;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prodon.R;
import com.example.prodon.ui.find.PlayerDetails;

import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

public class PlayersRecyclerAdapter extends RecyclerView.Adapter<PlayersRecyclerAdapter.ViewHolder> {

    private ArrayList<PlayerModel> players;
    private LayoutInflater inflater;
    private DatabaseHelper db;
    private FragmentActivity i;

    public PlayersRecyclerAdapter(ArrayList<PlayerModel> players,LayoutInflater inflater,DatabaseHelper db,FragmentActivity i) {
        this.players = players;
        this.inflater = inflater;
        this.db = db;
        this.i = i;
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

       if (!players.get(position).getParentName().equals("")){b = "Parent Name: " + players.get(position).getParentName()+" "+players.get(position).getlName();}
        else b= "Parent Name: Not specified.";
        c = "Year: " + players.get(position).getYear();

        holder.txtParent.setText(b);
        holder.txtYear.setText(c);
        holder.txtName.setText(a);
        int s = players.get(position).getId();
        holder.statusBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
           }
            });
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
                CheckBox parentCheck = customLayout.findViewById(R.id.parentCheck);
                CheckBox playerCheck= customLayout.findViewById(R.id.playerCheck);
                EditText phone = customLayout.findViewById(R.id.otherPhone);
                if (players.get(holder.getAdapterPosition()).getPlayerPhone() != null)
                {
                    playerCheck.setText(players.get(holder.getAdapterPosition()).getPlayerPhone());
                }
                else
                {
                    playerCheck.setClickable(false);
                    playerCheck.setText("No number.");
                }
                if (players.get(holder.getAdapterPosition()).getParentPhone() != null)
                {
                    parentCheck.setText(players.get(holder.getAdapterPosition()).getParentPhone());
                }
                else
                {
                    playerCheck.setClickable(false);
                    playerCheck.setText("No number.");
                }
                parentCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        playerCheck.setChecked(false);
                    }
                });
                playerCheck.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                    @Override
                    public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                        parentCheck.setChecked(false);
                    }
                });
                numberPicker.setMinValue(1);
                numberPicker.setMaxValue(12);
                closeBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        dialog.dismiss();
                    }
                });



                submitBtn.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        int val,y,m;
                       if (!value.getText().toString().equals("") && !year.getText().toString().equals("")){ val = Integer.parseInt(value.getText().toString());  y =  Integer.parseInt(year.getText().toString());m = numberPicker.getValue();}
                       else {Toast.makeText(i.getBaseContext(), "Please input all required values.", Toast.LENGTH_SHORT).show(); return;}
                    db.addNewPayment(s,y,m,val);
                        String mobileNumber;
                        if (parentCheck.isChecked()){mobileNumber = parentCheck.getText().toString();}
                        else if (playerCheck.isChecked()){mobileNumber=playerCheck.getText().toString();}
                        else if(phone.getText()!=null) mobileNumber = phone.getText().toString();
                        else {Toast.makeText(i.getBaseContext(), "Please select a phone number.", Toast.LENGTH_SHORT).show(); return;}

                        String message = "Player Name: "+players.get(holder.getAdapterPosition()).getfName()+" "+players.get(holder.getAdapterPosition()).getlName()  +"\nPayment made for: "+m+"-"+y + "Value: "+val;
                        boolean installed = true;//appInstalledOrNot("com.whatsapp");
                        if (installed) {
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse("http://api.whatsapp.com/send?phone=" +  mobileNumber + "&text=" + message));
                            i.startActivity(intent);
                        } else {
                            Toast.makeText(i.getBaseContext(), "Whats app not installed on your device", Toast.LENGTH_SHORT).show();
                        }
                        Toast.makeText(i.getBaseContext(), "Payment made successfully.", Toast.LENGTH_SHORT).show();
                    }
                });

                dialog.show();

            }
        });
        holder.v.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(i, PlayerDetails.class);
                intent.putExtra("player",db.getPlayerById(s));
                i.startActivity(intent);
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
    private boolean appInstalledOrNot(String url){
        PackageManager packageManager =i.getPackageManager();
        boolean app_installed;
        try {
            packageManager.getPackageInfo(url,PackageManager.GET_ACTIVITIES);
            app_installed = true;
        }catch (PackageManager.NameNotFoundException e){
            app_installed = false;
        }
        return app_installed;
    }
}

