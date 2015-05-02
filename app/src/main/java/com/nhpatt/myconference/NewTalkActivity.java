package com.nhpatt.myconference;

import android.app.AlertDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;


public class NewTalkActivity extends AppCompatActivity implements View.OnClickListener, AdapterView.OnItemClickListener {

    private AlertDialog dialog;
    private String[] rooms = {"room1", "room2",};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_talk);

        findViewById(R.id.room).setOnClickListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        if (dialog != null) {
            dialog.dismiss();
        }
    }

    @Override
    public void onClick(View v) {
        dialog = new AlertDialog.Builder(this).setView(createListView()).create();
        dialog.show();
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

    }

    private ListView createListView() {
        ListView view = (ListView) getLayoutInflater().inflate(R.layout.rooms, null);
        view.setAdapter(new ArrayAdapter<>(this, android.R.layout.simple_list_item_1, rooms));
        view.setOnItemClickListener(this);
        return view;
    }

}
