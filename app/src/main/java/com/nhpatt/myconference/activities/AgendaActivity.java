package com.nhpatt.myconference.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;

import com.nhpatt.myconference.R;
import com.nhpatt.myconference.entities.Talk;
import com.nhpatt.myconference.usecases.TalksUseCase;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

public class AgendaActivity extends Activity {

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

        TalksAdapter adapter = new TalksAdapter(talks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        drawerLayout = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawerList = (ListView) findViewById(R.id.left_drawer);
        drawerList.setAdapter(new ArrayAdapter<>(this, R.layout.drawer_list_item, menus));
        drawerList.setOnItemClickListener(new DrawerItemClickListener());


        ImageView avatar = (ImageView) findViewById(R.id.avatar);
        Picasso.with(this).load("https://pbs.twimg.com/profile_images/1210256780/avatar.jpg").into(avatar);

        findTalks();
    }

    private void findTalks() {


        TalksUseCase talksUseCase = new TalksUseCase();
        talksUseCase.run();
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
        drawerLayout.closeDrawer(findViewById(R.id.drawer_frame));
        startActivity(new Intent(this, SettingsActivity.class));
    }

}
