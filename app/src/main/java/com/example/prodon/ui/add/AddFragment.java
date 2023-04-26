package com.example.prodon.ui.add;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import com.example.prodon.R;
import com.example.prodon.ui.sqliteHelper.DatabaseHelper;
import com.example.prodon.ui.sqliteHelper.PlayerModel;


public class AddFragment extends Fragment {
    private Button submitBtn;
    private EditText fName,lName,parentName,parentPhone,playerPhone,year;
    private View v;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_add, container, false);
        fName = v.findViewById(R.id.FName);
        lName = v.findViewById(R.id.LName);
        parentName  = v.findViewById(R.id.parentName);
        parentPhone  = v.findViewById(R.id.parentPhone);
        playerPhone = v.findViewById(R.id.playerPhone);
        year = v.findViewById(R.id.year);
        submitBtn = v.findViewById(R.id.button);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PlayerModel playerModel = null;
                try {
                     playerModel = new PlayerModel(fName.getText().toString(),lName.getText().toString(),parentName.getText().toString(),
                            parentPhone.getText().toString(),playerPhone.getText().toString(),Integer.parseInt(year.getText().toString()));
                    Toast.makeText(v.getContext(),"success creating player",Toast.LENGTH_SHORT).show();
                } catch (Exception e)
                {
                    Toast.makeText(v.getContext(),"error",Toast.LENGTH_SHORT).show();
                }

                DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
                Boolean success = databaseHelper.addPlayer(playerModel);
                Toast.makeText(v.getContext(),"success:" + success,Toast.LENGTH_SHORT).show();
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}