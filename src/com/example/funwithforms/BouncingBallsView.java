package com.example.funwithforms;

import android.content.Context;
import android.content.Intent;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Toast;

/* 
 * The excellent foundations of animation code below are thanks to a tutorial hosted at: 
 * http://www3.ntu.edu.sg/home/ehchua/programming/android/Android_2D.html
 */
public class BouncingBallsView extends View {
	// This view's bounds
	private int xMin = 0;          
	private int xMax;
	private int yMin = 0;
	private int yMax;
	private float ballRadius = 20; // Ball's radius
	private float ballX = ballRadius + 20;  // Ball's center (x,y)
	private float ballY = ballRadius + 20;
	private float ballSpeedX = 1;  // Ball's speed (x,y)
	private float ballSpeedY = 1;
	private float previousX;
	private float previousY;
	private RectF ballBounds;      // Needed for Canvas.drawOval
	private float enemyRadius = 20; // Ball's radius
	private float enemyX = enemyRadius + 100;  // Ball's center (x,y)
	private float enemyY = enemyRadius + 100;
	private float enemySpeedX = 3;
	private float enemySpeedY = 3;// Ball's speed (x,y)
	private RectF enemyBallBounds;      // Needed for Canvas.drawOval
	private Paint paint;           // The paint (e.g. style, color) used for drawing
	private Paint enemyPaint;
	private float enemyLeft = enemyX - enemyRadius;
	private float enemyRight = enemyX + enemyRadius;
	private float enemyTop = enemyY + enemyRadius;
	private float enemyBottom = enemyY - enemyRadius;
	private float ballLeft = ballX - ballRadius;
	private float ballRight = ballX + ballRadius;
	private float ballTop = ballY + ballRadius;
	private float ballBottom = ballY - ballRadius;
	private int smack = 0;
	private int smack1 = 0;
	private int black = -16777216;
	private int white = -1;
	private int blue = -16776961;
	private int magenta = -65281;
	private int yellow = -256;
	private int[] colors = new int[]{white, blue, magenta, yellow, black};

	public BouncingBallsView(Context context) {
		super(context);
		ballBounds = new RectF();
		enemyBallBounds = new RectF();
		paint = new Paint();
		enemyPaint = new Paint();
		this.setFocusableInTouchMode(true); // touch mode!
	}

	// Called back to draw the view. Also called by invalidate().
	@Override
	protected void onDraw(Canvas canvas) {
		// Draw the balls
		ballBounds.set(ballX-ballRadius, ballY-ballRadius, ballX+ballRadius, ballY+ballRadius);
		enemyBallBounds.set(enemyX-enemyRadius, enemyY-enemyRadius, enemyX+enemyRadius, enemyY+enemyRadius);
		paint.setColor(Color.GREEN);
		enemyPaint.setColor(Color.RED);
		canvas.drawOval(ballBounds, paint);
		canvas.drawOval(enemyBallBounds, enemyPaint);

		// Update the position of the ball, including collision detection and reaction.
		updateBall();
		updateEnemy();

		// Delay
		try {  
			Thread.sleep(3);  
		} catch (InterruptedException e) { }

		invalidate();  // Force a re-draw
	}

	// Detect collision and update the position of the ball.
	private void updateBall() {
		// Get new (x,y) position
		ballX += ballSpeedX;
		ballY += ballSpeedY;
		// Detect collision and react
		if (ballX + ballRadius > xMax) {
			ballSpeedX = -ballSpeedX;
			ballX = xMax-ballRadius;
			wallCollision(this);
		} else if (ballX - ballRadius < xMin) {
			ballSpeedX = -ballSpeedX;
			ballX = xMin+ballRadius;
			wallCollision(this);
		}
		if (ballY + ballRadius > yMax) {
			ballSpeedY = -ballSpeedY;
			ballY = yMax - ballRadius;
			wallCollision(this);
		} else if (ballY - ballRadius < yMin) {
			ballSpeedY = -ballSpeedY;
			ballY = yMin + ballRadius;
			wallCollision(this);
		}
	}

	private void updateEnemy() {
		enemyX += enemySpeedX;
		enemyY += enemySpeedY;
		// Detect wall collision and react
		if (enemyX + enemyRadius > xMax) {
			enemySpeedX = -enemySpeedX;
			enemyX = xMax-enemyRadius;
		} else if (enemyX - enemyRadius < xMin) {	
			enemySpeedX = -enemySpeedX;
			enemyX = xMin+enemyRadius;
		}
		if (enemyY + enemyRadius > yMax) {
			enemySpeedY = -enemySpeedY;
			enemyY = yMax - enemyRadius;
		} else if (enemyY - enemyRadius < yMin) {
			enemySpeedY = -enemySpeedY;
			enemyY = yMin + enemyRadius;
		}
		// Check for ball collisions: help ==> http://devmag.org.za/2009/04/13/basic-collision-detection-in-2d-part-1/
		// 1. Calculate how far centers of circle are from one another 
		float diffX = ballX - enemyX;
		float diffY = ballY - enemyY;
		// 2. Square root each difference squared
		float diff = (float) Math.sqrt((diffX*diffX) + (diffY*diffY));
		if (diff <= (ballRadius + enemyRadius)){
			ballCollision(this);
		}
	}
	
	public void ballCollision(View view){
		Context context = getContext();
		CharSequence text = "COLLISION! SEVERE ERROR - RESTARTING?";
		int duration = Toast.LENGTH_SHORT;
		Toast toast = Toast.makeText(context, text, duration);
		toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
		toast.show();
		Intent i = new Intent(context, MainActivity.class);
		context.startActivity(i);
	}

	public void wallCollision(View view){
		view.setBackgroundColor(colors[smack]);
		smack += 1;	
		Context context = getContext();
		if(smack == 4){
			smack = 0;
			smack1 += 1; 
		}
		if (smack1 == 3){
			CharSequence text = "So... close... to what exactly?";
			int duration = Toast.LENGTH_SHORT;
			Toast toast = Toast.makeText(context, text, duration);
			toast.setGravity(Gravity.BOTTOM|Gravity.CENTER, 0, 0);
			toast.show();
		} else if (smack1 == 4){
			Intent i = new Intent(context, MainActivity.class);
			context.startActivity(i);
		}
	}

	// Called back when the view is first created or its size changes.
	@Override
	public void onSizeChanged(int w, int h, int oldW, int oldH) {
		// Set the movement bounds for the ball
		xMax = w-1;
		yMax = h-1;
	}

	// Touch-input handler
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		float currentX = event.getX();
		float currentY = event.getY();
		float deltaX, deltaY;
		float scalingFactor = 5.0f / ((xMax > yMax) ? yMax : xMax);
		switch (event.getAction()) {
		case MotionEvent.ACTION_MOVE:
			// Modify rotational angles according to movement
			deltaX = currentX - previousX;
			deltaY = currentY - previousY;	
			ballSpeedX += deltaX * scalingFactor;
			ballSpeedY += deltaY * scalingFactor;
		}
		// Save current x, y
		previousX = currentX;
		previousY = currentY;
		return true;  // Event handled
	}
}
