package edu.miami.c09879016.projectone;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Handler;
import android.provider.MediaStore;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ProgressBar;

//=============================================================================
public class GameActivity extends Activity {
	// -----------------------------------------------------------------------------
	private ProgressBar myProgressBar;
	private int tictac[] = { 0, 0, 0, 0, 0, 0, 0, 0, 0 };
	private int player_one = 1;
	private int player_two = 2;
	private int current_player;
	private int barTime;
	private int barClickTime;
	private Bitmap selectedPicture1;
	private Bitmap selectedPicture2;
	private String uriString1;
	private String uriString2;
	private Uri myUri1;
	private Uri myUri2;

	// -----------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.game);

		uriString1 = this.getIntent().getStringExtra("picone");
		myUri1 = Uri.parse(uriString1);
		uriString2 = this.getIntent().getStringExtra("pictwo");
		myUri2 = Uri.parse(uriString2);

		String currentplay = this.getIntent().getStringExtra("playing_now");

		if (currentplay.equals("one"))
			current_player = player_one;
		else
			current_player = player_two;

		String currenttimer = this.getIntent().getStringExtra("timer");

		if (currenttimer.equals("one"))
			barTime = 1000;
		else if (currenttimer.equals("two"))
			barTime = 2000;
		else if (currenttimer.equals("ten"))
			barTime = 10000;
		else
			barTime = 5000;

		ImageView image1 = (ImageView) findViewById(R.id.image_p1);
		try {
			selectedPicture1 = MediaStore.Images.Media.getBitmap(
					this.getContentResolver(), myUri1);
			image1.setImageBitmap(selectedPicture1);
		} catch (Exception e) {
			image1.setImageResource(R.drawable.red);
		}
		image1.setVisibility(View.INVISIBLE);

		ImageView image2 = (ImageView) findViewById(R.id.image_p2);
		try {
			selectedPicture2 = MediaStore.Images.Media.getBitmap(
					this.getContentResolver(), myUri2);
			image2.setImageBitmap(selectedPicture2);
		} catch (Exception e) {
			image2.setImageResource(R.drawable.blue);
		}
		image2.setVisibility(View.INVISIBLE);

		setPlayerImage(current_player);

		barClickTime = getResources().getInteger(R.integer.bar_click_time);
		myProgressBar = (ProgressBar) findViewById(R.id.time_left);
		myProgressBar.setMax(barTime);
		myProgressBar.setProgress(barTime);
		myProgresser.run();

	}

	// -----------------------------------------------------------------------------
	private final Runnable myProgresser = new Runnable() {

		private Handler myHandler = new Handler();

		public void run() {

			myProgressBar.setProgress(myProgressBar.getProgress()
					- barClickTime);
			if (myProgressBar.getProgress() == 0) {
				if (current_player == player_one) {
					current_player = player_two;
					setPlayerImage(current_player);

				} else {
					current_player = player_one;
					setPlayerImage(current_player);

				}
				myProgressBar.setProgress(barTime);
			}

			if (!myHandler.postDelayed(myProgresser, barClickTime)) {
				Log.e("ERROR", "Cannot postDelayed");
			}
		}
	};

	// -----------------------------------------------------------------------------
	public void myClickListener(View view) {

		ImageButton button;

		switch (view.getId()) {
		case R.id.up_left:
			if (tictac[0] == 0) {
				button = (ImageButton) findViewById(R.id.up_left);
				if (current_player == player_one) {
					tictac[0] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);

				} else {
					tictac[0] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);

				}
				endOfGameCheck();

			}
			break;
		case R.id.up_center:
			if (tictac[1] == 0) {

				button = (ImageButton) findViewById(R.id.up_center);
				if (current_player == player_one) {
					tictac[1] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);

				} else {
					tictac[1] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);

				}
				endOfGameCheck();
			}
			break;

		case R.id.up_right:
			if (tictac[2] == 0) {

				button = (ImageButton) findViewById(R.id.up_right);
				if (current_player == player_one) {
					tictac[2] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				} else {
					tictac[2] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				}
				endOfGameCheck();
			}
			break;

		case R.id.mid_left:
			if (tictac[3] == 0) {

				button = (ImageButton) findViewById(R.id.mid_left);
				if (current_player == player_one) {
					tictac[3] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				} else {
					tictac[3] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				}
				endOfGameCheck();

			}
			break;
		case R.id.mid_center:
			if (tictac[4] == 0) {

				button = (ImageButton) findViewById(R.id.mid_center);
				if (current_player == player_one) {
					tictac[4] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				} else {
					tictac[4] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				}
				endOfGameCheck();
			}
			break;

		case R.id.mid_right:
			if (tictac[5] == 0) {

				button = (ImageButton) findViewById(R.id.mid_right);
				if (current_player == player_one) {
					tictac[5] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				} else {
					tictac[5] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				}
				endOfGameCheck();
			}
			break;

		case R.id.low_left:
			if (tictac[6] == 0) {

				button = (ImageButton) findViewById(R.id.low_left);
				if (current_player == player_one) {
					tictac[6] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				} else {
					tictac[6] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				}
				endOfGameCheck();

			}
			break;
		case R.id.low_center:
			if (tictac[7] == 0) {

				button = (ImageButton) findViewById(R.id.low_center);
				if (current_player == player_one) {
					tictac[7] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				} else {
					tictac[7] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				}
				endOfGameCheck();
			}
			break;

		case R.id.low_right:
			if (tictac[8] == 0) {

				button = (ImageButton) findViewById(R.id.low_right);
				if (current_player == player_one) {
					tictac[8] = 1;
					drawOne(button);
					current_player = player_two;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				} else {
					tictac[8] = 2;
					drawTwo(button);
					current_player = player_one;
					setPlayerImage(current_player);
					myProgressBar.setProgress(barTime);
				}
				endOfGameCheck();
			}
			break;

		default:

			break;
		}
	}

	// ----------------------------------------

	public String isThereAWinner(int array[]) {
		String one = "one";
		String two = "two";

		if (array[0] == 1 && array[1] == 1 && array[2] == 1)
			return one;
		else if (array[3] == 1 && array[4] == 1 && array[5] == 1)
			return one;
		else if (array[6] == 1 && array[7] == 1 && array[8] == 1)
			return one;
		else if (array[0] == 1 && array[3] == 1 && array[6] == 1)
			return one;
		else if (array[1] == 1 && array[4] == 1 && array[7] == 1)
			return one;
		else if (array[2] == 1 && array[5] == 1 && array[8] == 1)
			return one;
		else if (array[0] == 1 && array[4] == 1 && array[8] == 1)
			return one;
		else if (array[2] == 1 && array[4] == 1 && array[6] == 1)
			return one;
		else if (array[0] == 2 && array[1] == 2 && array[2] == 2)
			return two;
		else if (array[3] == 2 && array[4] == 2 && array[5] == 2)
			return two;
		else if (array[6] == 2 && array[7] == 2 && array[8] == 2)
			return two;
		else if (array[0] == 2 && array[3] == 2 && array[6] == 2)
			return two;
		else if (array[1] == 2 && array[4] == 2 && array[7] == 2)
			return two;
		else if (array[2] == 2 && array[5] == 2 && array[8] == 2)
			return two;
		else if (array[0] == 2 && array[4] == 2 && array[8] == 2)
			return two;
		else if (array[2] == 2 && array[4] == 2 && array[6] == 2)
			return two;
		else if (array[0] != 0 && array[1] != 0 && array[2] != 0
				&& array[3] != 0 && array[4] != 0 && array[5] != 0
				&& array[6] != 0 && array[7] != 0 && array[8] != 0)
			return "tie";

		else
			return "false";
	}

	public void endOfGameCheck() {
		String winner = isThereAWinner(tictac);
		Intent retIntent;
		if (winner.equals("one")) {
			retIntent = new Intent();
			retIntent.putExtra("winner", "one");
			setResult(RESULT_OK, retIntent);

			finish();
		} else if (winner.equals("two")) {
			retIntent = new Intent();
			retIntent.putExtra("winner", "two");
			setResult(RESULT_OK, retIntent);

			finish();
		} else if (winner.equals("tie")) {
			retIntent = new Intent();
			retIntent.putExtra("winner", "tie");
			setResult(RESULT_OK, retIntent);
			finish();
		}
	}

	// -------
	public void setPlayerImage(int current) {

		if (current == 1) {
			ImageView image1 = (ImageView) findViewById(R.id.image_p1);
			image1.setVisibility(View.VISIBLE);
			ImageView image2 = (ImageView) findViewById(R.id.image_p2);
			image2.setVisibility(View.INVISIBLE);
		} else {
			ImageView image2 = (ImageView) findViewById(R.id.image_p2);
			image2.setVisibility(View.VISIBLE);
			ImageView image1 = (ImageView) findViewById(R.id.image_p1);
			image1.setVisibility(View.INVISIBLE);

		}

	}

	//
	public void drawOne(ImageButton image1) {

		try {
			selectedPicture1 = MediaStore.Images.Media.getBitmap(
					this.getContentResolver(), myUri1);
			image1.setImageBitmap(selectedPicture1);
		} catch (Exception e) {
			image1.setImageResource(R.drawable.red);
		}

	}

	public void drawTwo(ImageButton image2) {

		try {
			selectedPicture2 = MediaStore.Images.Media.getBitmap(
					this.getContentResolver(), myUri2);
			image2.setImageBitmap(selectedPicture2);
		} catch (Exception e) {
			image2.setImageResource(R.drawable.blue);
		}

	}

	// ------------
}

// =============================================================================