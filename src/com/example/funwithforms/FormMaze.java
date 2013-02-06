package com.example.funwithforms;

import java.util.Random;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.Menu;
import android.view.View;
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
						.setMessage("0100100001110101011011010110000101101110001000000110010001100101011101000110010101100011011101000110010101100100")
						.setCancelable(false)
						.setPositiveButton("010010010010011101101101001000000110",
								new DialogInterface.OnClickListener() {
									public void onClick(DialogInterface dialog, int id) {
										// start the handler here! 
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
		int increment = rand.nextInt(10);
		if (increment > 4) {
			bar.incrementProgressBy(increment);
		} else {
			bar.incrementProgressBy(-increment);
		}
	}

}
