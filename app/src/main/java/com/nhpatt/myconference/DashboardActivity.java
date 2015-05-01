package com.nhpatt.myconference;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.nhpatt.myconference.entities.Talk;

import java.util.ArrayList;
import java.util.List;


/**
 * @author Javier Gamarra
 */
public class DashboardActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        RecyclerView recyclerView = (RecyclerView) findViewById(R.id.talks);

        List<Talk> talks = new ArrayList<>();
        talks.add(new Talk("Title 1", "Room 1"));

        TalksAdapter adapter = new TalksAdapter(talks);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

    }

}
