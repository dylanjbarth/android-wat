package com.example.funwithforms;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
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
		final Context context = this;
		setContentView(R.layout.activity_form_maze);
		bar=(ProgressBar)findViewById(R.id.progressBar1);
		// alert dialog
				AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(context);
				alertDialogBuilder.setTitle("Warning:");
				alertDialogBuilder
						.setMessage("010010000111010101101101011000010110111000100000011001000110010101110100011001010110")
						.setCancelable(false)
						.setPositiveButton("0100100100?",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										Context context = getApplicationContext();
										CharSequence text = "Human detected!!!";
										int duration = Toast.LENGTH_LONG;
										Toast toast = Toast.makeText(context, text, duration);
										toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
										toast.show();
									}
								});
				AlertDialog alertDialog = alertDialogBuilder.create();
				alertDialog.show();
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
		if (prog >= max){
			Context context = getApplicationContext();
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
