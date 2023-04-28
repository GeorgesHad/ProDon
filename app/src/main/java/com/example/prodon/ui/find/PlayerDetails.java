package com.example.prodon.ui.find;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.Toast;

import com.example.prodon.R;
import com.example.prodon.ui.sqliteHelper.PlayerModel;

public class PlayerDetails extends AppCompatActivity {
    private Intent i;
    private PlayerModel playerModel;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_details);
        i = this.getIntent();
        playerModel = (PlayerModel) i.getSerializableExtra("player");
        Toast.makeText(this,playerModel.getfName(),Toast.LENGTH_SHORT).show();
    }
}