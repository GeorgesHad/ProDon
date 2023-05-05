package com.example.prodon.ui.find;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.AlertDialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.NumberPicker;
import android.widget.Toast;

import com.example.prodon.R;
import com.example.prodon.ui.sqliteHelper.DatabaseHelper;
import com.example.prodon.ui.sqliteHelper.Payment;
import com.example.prodon.ui.sqliteHelper.PaymentsRecycler;
import com.example.prodon.ui.sqliteHelper.PlayerModel;

import java.util.ArrayList;

public class PlayerDetails extends AppCompatActivity {
    private Intent i;
    private PlayerModel playerModel;
    private EditText playerName,parentName,playerPhone,parentPhone,startDate,birthYear,status,statusSince,groupName,yearSearch;
    private NumberPicker month;
    private Button edit,search;
    private DatabaseHelper databaseHelper;
    private boolean editing;
    private int id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        i = this.getIntent();
        playerModel = (PlayerModel) i.getSerializableExtra("player");
        id = i.getIntExtra("id",0);
        databaseHelper = new DatabaseHelper(this);

        editing = false;
        playerName = findViewById(R.id.txtPlaName);
        String s =playerModel.getfName()+" "+playerModel.getlName();
        playerName.setText(s);
        parentName = findViewById(R.id.txtParent);
        if(!playerModel.getParentName().equals("")){s  =playerModel.getParentName()+" "+playerModel.getlName();}
        else s = "Not specified.";
        parentName.setText(s);
        playerPhone = findViewById(R.id.phonePlayer);
        if(!playerModel.getPlayerPhone().equals("")){s  =playerModel.getPlayerPhone();}
        else s ="Not specified.";
        playerPhone.setText(s);
        parentPhone = findViewById(R.id.phoneParent);
        if(!playerModel.getParentPhone().equals("")){s  =playerModel.getParentPhone();}
        else s ="Not specified.";
        parentPhone.setText(s);
        startDate = findViewById(R.id.startDate);
        s  =playerModel.getDateJoined();
        startDate.setText(s);
        birthYear = findViewById(R.id.birthYear);
        s=""+playerModel.getYear();
        birthYear.setText(s);
        status=findViewById(R.id.status);
        s =playerModel.getStatus();
        status.setText(s);
        statusSince = findViewById(R.id.statusSince);
        s =playerModel.getStatusSince();
        statusSince.setText(s);
        groupName = findViewById(R.id.groupName);
        s = playerModel.getGroupName();
        groupName.setText(s);
        edit = findViewById(R.id.btnEdit);
        search = findViewById(R.id.searchBtn);
        month = findViewById(R.id.searchMonth);
        month.setMinValue(0);
        month.setMaxValue(12);
        yearSearch = findViewById(R.id.searchYear);
        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ArrayList<Payment> ar;
                String s = yearSearch.getText().toString();
                int n = month.getValue();
                if (n == 0 && s.equals(""))ar = databaseHelper.getPaymentsByPlayerId(id);
                else if(n==0) ar=databaseHelper.searchPaymentsByYearAndPlayerId(Integer.parseInt(s),id);
                else ar=databaseHelper.searchPaymentsByYearMonthAndPlayerId(Integer.parseInt(s),n,id);
               if(ar.isEmpty()) Toast.makeText(v.getContext(), "No results match your search.", Toast.LENGTH_SHORT).show();
               else {
                   Toast.makeText(v.getContext(), ""+ar.size(), Toast.LENGTH_SHORT).show();
                   AlertDialog.Builder builder = new AlertDialog.Builder(v.getContext());
                   builder.setTitle(playerModel.getfName()+" "+playerModel.getlName()+ " payments");
                   final View customLayout =getLayoutInflater().inflate(R.layout.payment_search_results, null);
                   builder.setView(customLayout);
                   AlertDialog dialog = builder.create();
                   RecyclerView rec = customLayout.findViewById(R.id.recyclerView);
                   PaymentsRecycler adapter = new PaymentsRecycler(ar);
                   rec.setAdapter(adapter);
                   rec.setLayoutManager(new LinearLayoutManager(v.getContext()));
                   Button btn = customLayout.findViewById(R.id.button3);
                   btn.setOnClickListener(new View.OnClickListener() {
                       @Override
                       public void onClick(View v) {
                           dialog.dismiss();
                       }
                   });
                    dialog.show();
               }

            }
        });
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editing)
                {
                    Toast.makeText(v.getContext(), "editing", Toast.LENGTH_SHORT).show();
                    editing = true;
                }
                else {
                    editing = false;
                    Toast.makeText(v.getContext(), "! editing", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}