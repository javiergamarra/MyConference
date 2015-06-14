package com.nhpatt.myconference.activities;

import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.nhpatt.myconference.R;
import com.nhpatt.myconference.entities.Talk;
import com.nhpatt.myconference.entities.TalkEvent;
import com.nhpatt.myconference.usecases.TalksUseCase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import de.greenrobot.event.EventBus;

public class AgendaActivity extends AppCompatActivity {

    private DrawerLayout drawerLayout;
    private ListView drawerList;
    private String[] menus = new String[]{"uno", "dos",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.agenda);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.talks);

        List<Talk> talks = new ArrayList<>();
        talks.add(new Talk("Title 1", "Room 1"));
        talks.add(new Talk("Title 2", "Room 2"));
        talks.add(new Talk("Title 3", "Room 2"));

        TalksAdapter adapter = new TalksAdapter(talks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.list_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, menus));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());


        ImageView avatar = (ImageView) findViewById(R.id.avatar);
        Picasso.with(this).load("https://pbs.twimg.com/profile_images/1210256780/avatar.jpg").into(avatar);

        findTalks();

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        ActionBarDrawerToggle drawerToggle = new ActionBarDrawerToggle(
                this, drawerLayout, toolbar,
                R.string.open, R.string.close);
        drawerLayout.setDrawerListener(drawerToggle);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setHomeButtonEnabled(true);
        drawerToggle.syncState();

    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    private void findTalks() {
        TalksUseCase talksUseCase = new TalksUseCase();
        talksUseCase.alternativeRun();
    }

    private class DrawerItemClickListener implements ListView.OnItemClickListener {
        @Override
        public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
            selectItem(position);
        }
    }

    private void selectItem(int position) {
        drawerList.setItemChecked(position, true);
        setTitle(menus[position]);
        drawerLayout.closeDrawer(findViewById(R.id.left_drawer));
    }

    public void onEventMainThread(TalkEvent response) {
        Toast.makeText(this, response.getTalks().toString(), Toast.LENGTH_SHORT).show();

    }

    @Override
    protected void onResume() {
        super.onResume();
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        EventBus.getDefault().unregister(this);
    }
}
