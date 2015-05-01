package com.nhpatt.myconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.nhpatt.myconference.entities.Talk;


/**
 * @author Javier Gamarra
 */
public class DashboardActivity extends Activity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dashboard);

        findViewById(R.id.first_row).setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        Intent intent = new Intent(this, DetailActivity.class);
        intent.putExtra("objecto", new Talk());
        startActivity(intent);
    }
}
