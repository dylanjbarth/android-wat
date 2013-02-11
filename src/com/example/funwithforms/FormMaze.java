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
import android.widget.CheckBox;
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
	
	public void moveCheckbox(View view){
		Random rand = new Random();
		int box = rand.nextInt(6) + 1;
		int direction = rand.nextInt(4);
		CheckBox c = (CheckBox) findViewById(R.id.checkBox1);
		Animation a = AnimationUtils.loadAnimation(this, R.anim.up);
		switch(box){
			case 1: c = (CheckBox) findViewById(R.id.checkBox1);
			case 2: c = (CheckBox) findViewById(R.id.CheckBox02);
			case 3: c = (CheckBox) findViewById(R.id.CheckBox01);
			case 4: c = (CheckBox) findViewById(R.id.CheckBox05);
			case 5: c = (CheckBox) findViewById(R.id.CheckBox04);
			case 6: c = (CheckBox) findViewById(R.id.CheckBox03);
		}
		switch(direction){
			case 0: a = AnimationUtils.loadAnimation(this, R.anim.up);
			case 1: a = AnimationUtils.loadAnimation(this, R.anim.down);
			case 2: a = AnimationUtils.loadAnimation(this, R.anim.left);
			case 3: a = AnimationUtils.loadAnimation(this, R.anim.right);
		}
		c.startAnimation(a);
		updateBar(view);
	}
	
	public void updateBar(View view){
		System.out.println("updateBar()");
		Random rand = new Random();
		int increment = rand.nextInt(20);
		if (increment > 8) {
			bar.incrementProgressBy(increment);
		} else {
			bar.incrementProgressBy(-increment);
		}
		int prog = bar.getProgress();
		int max = bar.getMax();
		if (prog >= max){
			Context context = getApplicationContext();
			CharSequence text = "010101110110010100100000011000010111001001100101011011100010011101110100001000000111001101101111001000000110010001101001011001100110011001100101011100100110010101101110011101000010110000100000011110010110111101110101001000000110000101101110011001000010000001001001001011100010111000101110";
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
