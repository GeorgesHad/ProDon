package com.example.prodon.ui.coaches_groups;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.widget.SwitchCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prodon.R;
import com.example.prodon.ui.sqliteHelper.PlayerModel;


public class CoachFragment extends Fragment {
    private Button addBtn,searchBtn;
    private EditText fName,lName;
    private SwitchCompat sw;
    private TextView header;
    private RecyclerView rec;
    private View v;
    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v =inflater.inflate(R.layout.fragment_coaches, container, false);
        fName = v.findViewById(R.id.coachFirstName);
        lName = v.findViewById(R.id.coachLastName);
        sw = v.findViewById(R.id.switch1);
        header = v.findViewById(R.id.header);
        rec = v.findViewById(R.id.recycler);
        searchBtn = v.findViewById(R.id.searchBtn);
        addBtn = v.findViewById(R.id.addBtn);
        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
    }
}