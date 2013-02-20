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
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {

	public final static String CRAZY_STRING = "com.example.funwithforms.CRAZYARRAY";
	private boolean genderNeutral = true;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_main, menu);
		return true;
	}

	public void onRadioButtonClicked(View view) {
		// Is the button now checked?
		boolean checked = ((RadioButton) view).isChecked();
		// Get text view to insert
		TextView textView = (TextView)findViewById(R.id.welcome);
		// Check which radio button was clicked
		switch(view.getId()) {
		case R.id.male:
			if (checked)
				textView.setText("Hello sir!");
			break;
		case R.id.female:
			if (checked)
				textView.setText("Hello mam!");
			break;
		}
		//Toast them first time regardless
		if (genderNeutral){
			Context context = getApplicationContext();
			CharSequence text = "Doesn't the phrase 'Gender Neutrality' mean anything these days?!";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			toast.show();
			genderNeutral = false;
		}
		
	}

	public void checkSubmission(View view) {
		// Get input and break into pieces
		EditText userInput = (EditText)findViewById(R.id.name);
		String name = userInput.getText().toString();
		if (name.length() > 0){
			goCrazy(view);
		} else {
			// alert dialog
			AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
			alertDialogBuilder.setTitle("An empty box?");
			alertDialogBuilder
			.setMessage("Give us the name, precious... We wants it.")
			.setCancelable(false)
			.setPositiveButton("If you insist..",
					new DialogInterface.OnClickListener() {
				public void onClick(DialogInterface dialog, int id) {
					Context context = getApplicationContext();
					CharSequence text = "That's a good human..";
					int duration = Toast.LENGTH_SHORT;
					Toast toast = Toast.makeText(context, text, duration);
					toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
					toast.show();
				}
			});
			AlertDialog alertDialog = alertDialogBuilder.create();
			alertDialog.show();
		}
	}

	public void goCrazy(View view) {
		Intent intent = new Intent(this, CrazyName.class);

		// Get input and break into pieces
		EditText userInput = (EditText)findViewById(R.id.name);
		String name = userInput.getText().toString();
		char[] name_pieces = name.toCharArray();
		Integer length = name_pieces.length;

		// loop and create a random sequence stored in new char array
		char [] crazyArray = new char[100];
		Random rand = new Random();
		for (int i=0; i <= 99; i++){
			int next_char = rand.nextInt(length);
			crazyArray[i] = name_pieces[next_char];
		}

		// Convert crazy array to string and render to TextView
		String crazyString = new String(crazyArray);
		crazyString.replaceAll("\\W", "");
		// get rid of white space
		crazyString = crazyString.replace(" ", "");
		intent.putExtra(CRAZY_STRING, crazyString);
		startActivity(intent);
	}

}
