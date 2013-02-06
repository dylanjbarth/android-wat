package com.example.funwithforms;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class BinaryStream extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_binary_stream);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_binary_stream, menu);
		return true;
	}

}
