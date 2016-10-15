package com.example.ivo.vhodo;

import android.support.design.widget.NavigationView;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.ivo.vhodo.R;
import com.example.ivo.vhodo.adapters.MessageAdapter;
import com.example.ivo.vhodo.adapters.NeighbourAdapter;
import com.example.ivo.vhodo.models.Message;
import com.example.ivo.vhodo.models.Neighbour;

import java.util.ArrayList;
import java.util.List;

public class NeighbourActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Neighbour> neighbourList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_neighbour);

        mRecyclerView = (RecyclerView) findViewById(R.id.neighbourRecycler);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        // TODO: 13.10.16 entering values for testing need to get from db later
        neighbourList = new ArrayList<>();
        for (int i = 0; i < 5; i++) {
            neighbourList.add(new Neighbour("Pesho" + i, "99999999","gosho@" + i + ".com"));
        }
        mAdapter = new NeighbourAdapter(neighbourList,getApplicationContext());
        mRecyclerView.setAdapter(mAdapter);
    }
}
