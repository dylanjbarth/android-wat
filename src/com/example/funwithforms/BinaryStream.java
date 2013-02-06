package com.example.funwithforms;

import java.util.Random;
import java.util.concurrent.atomic.AtomicBoolean;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

@SuppressLint("HandlerLeak")
public class BinaryStream extends Activity {
	final Context context = this;
	Handler handler = new Handler() {
		@Override 
		public void handleMessage(Message msg){
			TextView view = (TextView)findViewById(R.id.binary);
			String s = Integer.toString(msg.what);
			view.append(s);
		}
	};
	AtomicBoolean isRunning=new AtomicBoolean(false);

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_binary_stream);
		startHandler();
		Context context = getApplicationContext();
		CharSequence text = "We aren't so different, you and I..";
		int duration = Toast.LENGTH_LONG;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.TOP|Gravity.CENTER, 0, 0);
		toast.show();
	}
	
	public void startHandler() {
		super.onStart();
		
		Thread background=new Thread(new Runnable() {
			public void run() {
				try {
					Random rand = new Random();
					for(int i=0; i < 1500; i++){
						boolean c = rand.nextBoolean();
						int n = c? 1 : 0;
						Message msg = handler.obtainMessage();
						msg.what = n;
						handler.sendMessage(msg); // want to send string c to be appended to text view
						Thread.sleep(3);
					}
				}
				catch (Throwable t) {
					// just end the background thread
				}
			}
		});
		
		isRunning.set(true);
		background.start();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_binary_stream, menu);
		return true;
	}
	
	public void conclusion(View view){
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
	}

}
