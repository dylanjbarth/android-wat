package com.example.funwithforms;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.Menu;
import android.widget.TextView;

public class CrazyName extends Activity {
	final Context context = this;
	final Handler mHandler = new Handler();

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
								System.out.println("just before streamText.start");
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
		// Get context 
		TextView textView = (TextView) findViewById(R.id.crazy_name);
		Intent intent = getIntent();
		String crazyString = intent.getStringExtra(MainActivity.CRAZY_STRING);
		
		// Set stage for looping
		String[] split = crazyString.split("");
		Integer length = crazyString.length();
		
		Random rand = new Random();
		int i = 0;
		int j = 0;
		while (true) {
			Integer nextChar = rand.nextInt(length);
			String c = split[nextChar];
			textView.append(split[nextChar]);
			if (i >= 99) {
				i = 0;
				j++;
				if (j >= 50) {
					break;
				}
			}
			i++;
		}
	}

	Thread streamText = new Thread() {
		public void run() {
			System.out.println("inside thread streamText()");

			System.out.println("1");
			
			// Get the crazyString from the intent, turn it into an array
			Intent intent = getIntent();
			
			System.out.println("2");
			
			String crazyString = intent.getStringExtra(MainActivity.CRAZY_STRING);
			
			System.out.println("3");
			
			String[] split = crazyString.split("");
			
			System.out.println("4");
			
			Integer length = crazyString.length();

			System.out.println("5");
			
			// Start rendering text randomly
			Random rand = new Random();
			
			System.out.println("6");
			
			int i = 0;
			int j = 0;
			
			System.out.println("7");
			
			while (true) {
				System.out.print("While; i: ");
				System.out.println(i);
				
				Integer nextChar = rand.nextInt(length);
				String c = split[nextChar];
				runOnUiThread(new Runnable() {
				     public void run() {
				    	Intent intent = getIntent();
				    	String crazyString = intent.getStringExtra(MainActivity.CRAZY_STRING);
				    	String[] split = crazyString.split("");
				    	// Get the text view
						TextView textView = (TextView) findViewById(R.id.crazy_name);
//				    	textView.append(split[nextChar]);
				    }
				});
				
				
				System.out.print("Appended: ");
				System.out.println(split[nextChar]);
				
				if (i >= 99) {
					i = 0;
					j++;
					if (j >= 50) {
						break;
					}
				}
				i++;
			}
		}
	};

}
