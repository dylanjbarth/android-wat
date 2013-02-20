package com.example.funwithforms;

import java.util.Random;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ProgressBar;
import android.widget.Toast;

public class FormMaze extends Activity {

	ProgressBar bar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_form_maze);
		bar=(ProgressBar)findViewById(R.id.progressBar1);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_form_maze, menu);
		return true;
	}

	public void updateBar(View view){
		System.out.println("updateBar()");
		Random rand = new Random();
		int increment = rand.nextInt(20);
		if (increment > 8) {
			bar.incrementProgressBy(increment);
			Animation a = AnimationUtils.loadAnimation(this, R.anim.up);
			bar.startAnimation(a);
		} else {
			bar.incrementProgressBy(-increment);
			Animation a = AnimationUtils.loadAnimation(this, R.anim.down);
			bar.startAnimation(a);
		}
		int prog = bar.getProgress();
		int max = bar.getMax();
		Context context = getApplicationContext();
		if (prog >= max){
			CharSequence text = "Test Passed. Moving On.";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			toast.show();
			nextScreen();
		} 
	}
	public void nextScreen(){
		Intent intent = new Intent(this, BinaryStream.class);
		startActivity(intent);
	}

}
