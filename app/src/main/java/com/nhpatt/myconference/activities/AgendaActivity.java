package com.nhpatt.myconference.activities;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.nhpatt.myconference.R;
import com.nhpatt.myconference.SettingsActivity;
import com.nhpatt.myconference.entities.Talk;
import com.nhpatt.myconference.network.TalkService;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

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
        RestAdapter restAdapter = new RestAdapter.Builder()
                .setEndpoint("http://private-5d28cf-talks.apiary-mock.com/")
                .build();

        TalkService service =
                restAdapter.create(TalkService.class);
        service.listTalks(new Callback<JsonArray>() {
            @Override
            public void success(JsonArray jsonObject, Response response) {
                Toast.makeText(AgendaActivity.this, jsonObject.toString(), Toast.LENGTH_SHORT).show();
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("TAG", error.toString());
            }
        });
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
        drawerLayout.closeDrawer(drawerList);
        startActivity(new Intent(this, SettingsActivity.class));
    }

}
