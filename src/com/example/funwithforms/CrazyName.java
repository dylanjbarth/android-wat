package com.example.funwithforms;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.widget.TextView;

public class CrazyName extends Activity {
	final Context context = this;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		System.out.println("onCreate()");
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crazy_name);

		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
				context);
		// set title
		alertDialogBuilder.setTitle("Warning:");

		// set dialog message
		alertDialogBuilder
				.setMessage("All your base are belong to us.")
				.setCancelable(false)
				.setPositiveButton("I know :(",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								// call streamText
								streamText();
							}
						});

		// create alert dialog
		AlertDialog alertDialog = alertDialogBuilder.create();

		// show it
		alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_crazy_name, menu);
		return true;
	}

	public void streamText() {
		System.out.println("streamText()");
		// Get the text view
		TextView textView = (TextView) findViewById(R.id.crazy_name);

		// Get the crazyString from the intent, turn it into an array
		Intent intent = getIntent();
		String crazyString = intent.getStringExtra(MainActivity.CRAZY_STRING);
		String[] split = crazyString.split("");
		Integer length = crazyString.length();

		// Start rendering text randomly
		Random rand = new Random();
		int i = 0;
		int j = 0;
		while (true) {
			Integer nextChar = rand.nextInt(length);
			textView.append(split[nextChar]);
			if (i >= 99) {
				i = 0;
				j ++;
				if (j >= 50) {
					break;
				}
			}
			i++;
		}
	}

}
