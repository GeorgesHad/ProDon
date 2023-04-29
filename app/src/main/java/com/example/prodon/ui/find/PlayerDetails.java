package com.example.prodon.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prodon.R;
import com.example.prodon.ui.sqliteHelper.DatabaseHelper;
import com.example.prodon.ui.sqliteHelper.PlayerModel;

public class PlayerDetails extends AppCompatActivity {
    private Intent i;
    private PlayerModel playerModel;
    private EditText playerName,parentName,playerPhone,parentPhone,startDate,birthYear,status,statusSince,groupName;
    private Button edit,search;
    private DatabaseHelper databaseHelper;
    private boolean editing;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        i = this.getIntent();
        playerModel = (PlayerModel) i.getSerializableExtra("player");
        databaseHelper = new DatabaseHelper(this);
        editing = false;
        playerName = findViewById(R.id.txtPlaName);
        String s ="Player Name: "+playerModel.getfName()+" "+playerModel.getlName();
        playerName.setText(s);
        parentName = findViewById(R.id.txtParent);
        if(!playerModel.getParentName().equals("")){s  ="Parent Name:"+playerModel.getParentName()+" "+playerModel.getlName();}
        else s ="Parent Name: Not specified.";
        parentName.setText(s);
        playerPhone = findViewById(R.id.phonePlayer);
        if(!playerModel.getPlayerPhone().equals("")){s  ="Player Phone: "+playerModel.getPlayerPhone();}
        else s ="Player Phone: Not specified.";
        playerPhone.setText(s);
        parentPhone = findViewById(R.id.phoneParent);
        if(!playerModel.getParentPhone().equals("")){s  ="Player Phone: "+playerModel.getParentPhone();}
        else s ="Parent Phone: Not specified.";
        parentPhone.setText(s);
        startDate = findViewById(R.id.startDate);
        s  ="Start Date: "+playerModel.getDateJoined();
        startDate.setText(s);
        birthYear = findViewById(R.id.birthYear);
        s="Birth Year: "+playerModel.getYear();
        birthYear.setText(s);
        status=findViewById(R.id.status);
        s ="Status: "+playerModel.getStatus();
        status.setText(s);
        statusSince = findViewById(R.id.statusSince);
        s = playerModel.getStatus() + " since: "+playerModel.getStatusSince();
        statusSince.setText(s);
        groupName = findViewById(R.id.groupName);
        s = "Group Name: "+ playerModel.getGroupName();
        groupName.setText(s);
        edit = findViewById(R.id.btnEdit);
        edit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!editing)
                {
                    editing = true;
                    playerName.setFocusable(true);parentName.setFocusable(true);playerPhone.setFocusable(true);
                    parentPhone.setFocusable(true);birthYear.setFocusable(true);
                }
                else {
                    editing = false;
                    playerName.setFocusable(false);parentName.setFocusable(false);playerPhone.setFocusable(false);
                    parentPhone.setFocusable(false);birthYear.setFocusable(false);
                }
            }
        });
    }
}