package com.example.funwithforms;

import java.util.Random;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity {

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
    
    public void goCrazy(View view) {
    	System.out.println("GoCrazy");
    	// Get input and break into pieces
    	EditText userInput = (EditText)findViewById(R.id.name);
    	String name = userInput.getText().toString();
    	System.out.print("name: ");
		System.out.println(name);
    	char[] name_pieces = name.toCharArray();
    	Integer length = name_pieces.length;
    	System.out.print("name_pieces length: ");
		System.out.println(length);
    	
    	
    	System.out.println("1");
    	// loop and create a random sequence stored in new char array
    	System.out.println("2");
    	char [] crazyArray = new char[100];
    	System.out.println("3");
    	Random rand = new Random();
    	System.out.println("4");
    	for (int i=0; i <= 99; i++){
    		System.out.print("i: ");
    		System.out.println(i);
    		int next_char = rand.nextInt(length);
    		System.out.print("next_char: ");
    		System.out.println(next_char);
    		crazyArray[i] = name_pieces[next_char];
    		System.out.print("Corresponding letter: ");
    		System.out.println(name_pieces[next_char]);
    	}
    	
    	// Convert crazy array to string and render to TextView
    	String crazyString = new String(crazyArray);
    	System.out.println(crazyString);
    	TextView crazyOutput = (TextView)findViewById(R.id.craziness);
    	crazyOutput.setText(crazyString);    	
    }
    
}
