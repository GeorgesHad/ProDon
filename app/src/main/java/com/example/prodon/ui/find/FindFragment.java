package com.example.prodon.ui.find;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProvider;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prodon.R;
import com.example.prodon.databinding.FragmentFindBinding;
import com.example.prodon.ui.sqliteHelper.DatabaseHelper;
import com.example.prodon.ui.sqliteHelper.PlayerModel;
import com.example.prodon.ui.sqliteHelper.PlayersRecyclerAdapter;

import java.util.ArrayList;


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
                String first,last;
                first = last = "";
                if (fName.getText() != null)first = fName.getText().toString();
                if (lName.getText() != null)last = lName.getText().toString();
                DatabaseHelper databaseHelper = new DatabaseHelper(v.getContext());
                ArrayList<PlayerModel> ar = databaseHelper.searchPlayer(first,last,v.getContext());
                if (ar.isEmpty()){  Toast.makeText(v.getContext(),"No players match your search.",Toast.LENGTH_LONG).show();}
                else {
                    Toast.makeText(v.getContext(),ar.get(0).getfName(),Toast.LENGTH_LONG).show();
                }
                PlayersRecyclerAdapter adapter = new PlayersRecyclerAdapter(ar);
                rec.setAdapter(adapter);
                rec.setLayoutManager(new LinearLayoutManager(v.getContext()));
                rec.setVisibility(View.VISIBLE);
            }
        });
        return v;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();

    }
}