package com.example.funwithforms;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;

public class BouncingBalls extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_bouncing_balls);
		View bouncingBallView = new BouncingBallsView(this);
		setContentView(bouncingBallView);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_bouncing_balls, menu);
		return true;
	}

}
