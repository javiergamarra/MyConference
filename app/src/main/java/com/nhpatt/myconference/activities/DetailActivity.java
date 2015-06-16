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

        Bundle bundle = getIntent().getExtras();
        assignTalk((Talk) bundle.get("talk"));
    }

    private void assignTalk(Talk talk) {
        TextView title = (TextView) findViewById(R.id.title);
        title.setText(talk.getTitle());

        TextView description = (TextView) findViewById(R.id.description);
        description.setText(talk.getDescription());

        TextView start = (TextView) findViewById(R.id.start);
        start.setText(talk.getStart());

        TextView end = (TextView) findViewById(R.id.end);
        end.setText(talk.getEnd());

        TextView room = (TextView) findViewById(R.id.room);
        room.setText(talk.getRoom());
    }

}
