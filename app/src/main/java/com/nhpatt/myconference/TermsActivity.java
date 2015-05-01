package com.nhpatt.myconference;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

/**
 * @author Javier Gamarra
 */
public class TermsActivity extends Activity implements View.OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.terms);

		findViewById(R.id.accept_terms).setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		startActivity(new Intent(this, DashboardActivity.class));
	}
}
