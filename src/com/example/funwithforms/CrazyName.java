package com.example.funwithforms;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class CrazyName extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crazy_name);
		
		// Get the crazyString from the intent
	    Intent intent = getIntent();
	    String crazyString = intent.getStringExtra(MainActivity.CRAZY_STRING);
	    
	    // Get the text view
	    TextView textView = (TextView)findViewById(R.id.crazy_name);
	    textView.setText(crazyString);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_crazy_name, menu);
		return true;
	}

}
