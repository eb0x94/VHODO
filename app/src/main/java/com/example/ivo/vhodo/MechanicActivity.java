package com.example.ivo.vhodo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ivo.vhodo.R;
import com.example.ivo.vhodo.adapters.MechanicAdapter;
import com.example.ivo.vhodo.adapters.MessageAdapter;
import com.example.ivo.vhodo.models.Mechanic;
import com.example.ivo.vhodo.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MechanicActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Mechanic> mechanicList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mechanic);

        mRecyclerView = (RecyclerView) findViewById(R.id.mechanicRecyclerView);
        mRecyclerView.setHasFixedSize(true);

        mechanicList = new ArrayList<>();

        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);

        mechanicList.add(new Mechanic("Stefan Energoto Ltd.","0894555666","Electric master"));
        mechanicList.add(new Mechanic("Gosho Vodolaza Ltd.","0894555777","Water bender"));
        mechanicList.add(new Mechanic("Miro Podolski Ltd.","0894555888","Floor master"));
        mechanicList.add(new Mechanic("Pesho Ltd.","0894555999","Everything else master"));
        mechanicList.add(new Mechanic("Filka","0897223456","Master of brooms"));

        mAdapter = new MechanicAdapter(mechanicList,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}
