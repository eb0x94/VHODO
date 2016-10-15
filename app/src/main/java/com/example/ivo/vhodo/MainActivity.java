package com.example.ivo.vhodo;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.NavigationView;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import com.example.ivo.vhodo.adapters.MessageAdapter;
import com.example.ivo.vhodo.models.Message;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener {

    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;
    private List<Message> messageList;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        mRecyclerView = (RecyclerView) findViewById(R.id.mainRecyclerView);
        mRecyclerView.setHasFixedSize(true);
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        messageList = getMessages();
        messageList.add(new Message("Hora, plyha mnogo mirishe, viknete Filka da izchisti, molq.",2,"Petko Ivanov","10/10/2016 12:48"));
        messageList.add(new Message("Ima umrql plyh mejdu etajite",1,"Petko Ivanov","10/10/2016 10:36"));
        messageList.add(new Message("Nqkoi za kafe?",0,"Petkana Ivanova","09/10/2016 16:42"));
        messageList.add(new Message("Utre v 9:00 shte se pryska protiv plyhove",1,"Alexandyr Petkov","09/10/2016 13:37"));
        mAdapter = new MessageAdapter(messageList);
        mRecyclerView.setAdapter(mAdapter);
    }

    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        // Handle navigation view item clicks here.
        Intent intent = null;
        switch (item.getItemId()) {
            case R.id.nav_account:
                intent = new Intent(MainActivity.this, AccountActivity.class);
                break;
            case R.id.nav_neighbour:
                intent = new Intent(MainActivity.this, NeighbourActivity.class);
                break;
            case R.id.nav_mechanic:
                intent = new Intent(MainActivity.this, MechanicActivity.class);
                break;
            case R.id.nav_report:
                intent = new Intent(MainActivity.this, ReportActivity.class);
                break;
            case R.id.nav_aboutinfo:
                intent = new Intent(MainActivity.this,AboutActivity.class);
                break;
            case R.id.nav_logout:
                intent = new Intent(MainActivity.this, LoginActivity.class);
                break;
        }


        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        if (intent != null)
            startActivity(intent);
        return true;
    }

    private List<Message> getMessages(){
        return GlobalData.getMessages();
    }
}
