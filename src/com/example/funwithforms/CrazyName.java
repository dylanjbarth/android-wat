package com.example.funwithforms;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Menu;
import android.widget.TextView;

@SuppressLint("HandlerLeak")
public class CrazyName extends Activity {
	final Context context = this;
	TextView streamingText;
	Handler handler = new Handler() {
		@Override 
		public void handleMessage(Message msg){
			TextView view = (TextView)findViewById(R.id.crazy_name);
//			streamingText.append((CharSequence) msg.obj);
			if (msg.what <= 1){
				view.append((CharSequence) msg.obj);
			} else if (msg.what == 2 ){
				view.setText("");
			} else {
				// alert dialog
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Success:");
				alertDialogBuilder
						.setMessage("All your base are belong to us.")
						.setCancelable(false)
						.setPositiveButton(":(",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										nextScreen();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
				System.out.println("After alert dialog");
			}
		}
	};
	AtomicBoolean isRunning=new AtomicBoolean(false);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_crazy_name);
		streamingText = (TextView) findViewById(R.id.crazy_name);
		// alert dialog
		AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
		alertDialogBuilder.setTitle("Warning:");
		alertDialogBuilder
				.setMessage("Identity cracking in progress...")
				.setCancelable(false)
				.setPositiveButton("Oh dear...",
						new DialogInterface.OnClickListener() {
							public void onClick(DialogInterface dialog, int id) {
								startHandler();
							}
						});
		AlertDialog alertDialog = alertDialogBuilder.create();
		alertDialog.show();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_crazy_name, menu);
		return true;
	}

	// Handler functions
	public void startHandler() {
		super.onStart();
		
		Thread background=new Thread(new Runnable() {
			public void run() {
				try {
					// Get context 
					Intent intent = getIntent();
					String crazyString = intent.getStringExtra(MainActivity.CRAZY_STRING);
					
					// Set stage for looping
					String[] split = crazyString.split("");
					Integer length = crazyString.length();
					
					Random rand = new Random();
					for(int j=0; j<4; j++){
						for(int i=0; i < 700; i++){
							int char1 = rand.nextInt(length);
							int char2 = rand.nextInt(length);
							String c = split[char1];
							c += split[char2];
							Message msg = handler.obtainMessage();
							msg.obj = c;
							handler.sendMessage(msg); // want to send string c to be appended to text view
							Thread.sleep(3);
						}
						Message msg = handler.obtainMessage();
						msg.what = 2;
						handler.sendMessage(msg); // to delete textView
						Thread.sleep(3);
					}
					Message msg = handler.obtainMessage();
					msg.what = 3;
					handler.sendMessage(msg); // to throw alert dialog
					
				}
				catch (Throwable t) {
					// just end the background thread
				}
			}
		});
		
		isRunning.set(true);
		background.start();
	}
	
	
	public void onStop() {
		super.onStop();
		isRunning.set(false);
	}
	
	public void nextScreen(){
		Intent intent = new Intent(this, FormMaze.class);
		startActivity(intent);
	};
	
	
}
