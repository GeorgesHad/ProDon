package com.example.prodon.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prodon.R;
import com.example.prodon.databinding.FragmentFindBinding;
import com.example.prodon.ui.sqliteHelper.DatabaseHelper;


public class FindFragment extends Fragment {
    private View v;
    private EditText fName,lName;
    private TextView txtEmpty;
    private Button searchBtn;
    private RecyclerView rec;

    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {
        v = inflater.inflate(R.layout.fragment_find,container,false);
        fName = v.findViewById(R.id.searchFName);
        lName = v.findViewById(R.id.searchLName);
        txtEmpty = v.findViewById(R.id.txtEmpty);
        searchBtn = v.findViewById(R.id.button2);
        rec = v.findViewById(R.id.recycler);
        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
                databaseHelper.searchPlayer("geo",v.getContext());
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}