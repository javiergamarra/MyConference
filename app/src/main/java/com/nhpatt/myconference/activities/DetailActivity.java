package com.nhpatt.myconference.activities;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import com.nhpatt.myconference.R;
import com.nhpatt.myconference.entities.Talk;


public class DetailActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.talk_detail);

        Talk talk = (Talk) getIntent().getExtras().get("talk");


        ((TextView) findViewById(R.id.title)).setText(talk.getTitle());
    }


}
