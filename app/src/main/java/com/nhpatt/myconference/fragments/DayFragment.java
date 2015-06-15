package com.nhpatt.myconference.fragments;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.nhpatt.myconference.R;
import com.nhpatt.myconference.activities.TalksAdapter;
import com.nhpatt.myconference.entities.Talk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Gamarra
 */
public class DayFragment extends Fragment {

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

        adapter = new TalksAdapter(talks);
        recyclerView.setAdapter(adapter);
        return view;
    }
}
