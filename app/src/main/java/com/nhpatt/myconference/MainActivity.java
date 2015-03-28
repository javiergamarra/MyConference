package com.nhpatt.myconference;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;


public class MainActivity extends ActionBarActivity implements View.OnClickListener {

	private static final String MY_TAG = "MY_CONFERENCE";

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		Log.e(MY_TAG, "I'm paranoid");

		Toast.makeText(this, "Hi!", Toast.LENGTH_SHORT).show();

		findViewById(R.id.save_button).setOnClickListener(this);
		findViewById(R.id.web_page).setOnClickListener(this);
		findViewById(R.id.to_second).setOnClickListener(this);
	}

	@Override
	protected void onStart() {
		super.onStart();

		Log.e(MY_TAG, "I'm starting");
	}

	@Override
	protected void onResume() {
		super.onResume();

		Log.e(MY_TAG, "I'm resuming");
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.menu_main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();

		//noinspection SimplifiableIfStatement
		if (id == R.id.action_settings) {
			return true;
		}

		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		if (v.getId() == R.id.web_page) {
			startActivity(new Intent(Intent.ACTION_VIEW, Uri.parse("http://www.nhpatt.com")));
		} else if (v.getId() == R.id.save_button) {
			Log.e(MY_TAG, "saving...");
		} else if (v.getId() == R.id.to_second) {
			startActivity(new Intent(this, SecondActivity.class));
		}

	}
}
