package com.nhpatt.myconference.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhpatt.myconference.R;
import com.nhpatt.myconference.activities.DetailActivity;
import com.nhpatt.myconference.activities.TalksAdapter;
import com.nhpatt.myconference.entities.Talk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Gamarra
 */
public class DayFragment extends Fragment implements TalkClickListener {

    private int color;
    private TalksAdapter adapter;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        color = getArguments().getInt("color");

        View view = inflater.inflate(R.layout.talks_list, container, false);
        RecyclerView recyclerView = (RecyclerView) view.findViewById(R.id.talks);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity().getBaseContext());
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setHasFixedSize(true);

        List<Talk> talks = new ArrayList<>();
        talks.add(new Talk("Title 1", "Room 1"));
        talks.add(new Talk("asdklj askldjka sljkd jaslkdjasd 2", "Room 2"));
        talks.add(new Talk("Title asdasd asd asd 3", "Room 2"));

        adapter = new TalksAdapter(talks, this);
        recyclerView.setAdapter(adapter);
        return view;
    }

    @Override
    public void onClick(Talk talk) {
        Intent intent = new Intent(getActivity(), DetailActivity.class);
        intent.putExtra("talk", talk);
        getActivity().startActivity(intent);
    }
}
