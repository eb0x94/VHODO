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

        mechanicList.add(new Mechanic("Stefan Energoto Ltd.","0894555","Pipa jicite"));
        mechanicList.add(new Mechanic("Gosho Vodolazo Ltd.","0894555","Pipa vodata"));
        mechanicList.add(new Mechanic("Desi Parketo Ltd.","0894555","Pipa podo"));
        mechanicList.add(new Mechanic("Mitko Gipsokartono Ltd.","0894555","Pipa tavano i stenite"));

        mAdapter = new MechanicAdapter(mechanicList,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}
