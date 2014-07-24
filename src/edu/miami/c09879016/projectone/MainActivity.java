package edu.miami.c09879016.projectone;

import edu.miami.c09879016.projectone.R;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

//=============================================================================
public class MainActivity extends Activity {
	// -----------------------------------------------------------------------------

	private static final int START_GAME = 111;
	private static final int SELECT_PICTURE = 222;
	private static int starcounter_one = 0;
	private static int starcounter_two = 0;;
	private RatingBar theRatingBarOne;
	private RatingBar theRatingBarTwo;

	private Uri selectedURI_one;
	private Uri selectedURI_two;

	private Boolean multi_win = false;
	private String last_winner;
	private String who_won;
	private String playing_now;
	private String player1 = "one";
	private String player2 = "two";
	private String name1 = "Player 1";
	private String name2 = "Player 2";
	private String picone = "Player 1";
	private String pictwo = "Player 2";
	private String timer = "five";

	private double dividing_line = 0.5;

	private final int THE_DIALOG = 1;
	private final int THE_DIALOG2 = 2;
	private AlertDialog theDialog;

	private TextView txt_one;
	private TextView txt_two;

	private Boolean resetcheck = false;

	int tempID;

	// -----------------------------------------------------------------------------
	@Override
	public void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_main);

		theRatingBarOne = (RatingBar) findViewById(R.id.score_one);
		theRatingBarTwo = (RatingBar) findViewById(R.id.score_two);
		theRatingBarOne.setRating(starcounter_one);
		theRatingBarTwo.setRating(starcounter_two);

		registerForContextMenu(findViewById(R.id.playone));
		registerForContextMenu(findViewById(R.id.playtwo));
		registerForContextMenu(findViewById(R.id.pic_one));
		registerForContextMenu(findViewById(R.id.pic_two));

		txt_one = (TextView) findViewById(R.id.playone);
		txt_one.setText(name1);
		txt_two = (TextView) findViewById(R.id.playtwo);
		txt_two.setText(name2);

	}

	// -----------------------------------------------------------------------------
	@Override
	public void onActivityResult(int requestCode, int resultCode, Intent data) {

		ImageView pictureView;

		Bitmap selectedPicture;

		super.onActivityResult(requestCode, resultCode, data);

		switch (requestCode) {

		case START_GAME:

			if (resultCode == Activity.RESULT_OK) {
				ImageButton startit = (ImageButton) findViewById(R.id.start_button);
				startit.setImageResource(R.drawable.startup);
				last_winner = who_won;
				who_won = data.getStringExtra("winner");
				if (last_winner == who_won)
					multi_win = true;
				if (who_won.equals("one")) {
					if ((theRatingBarOne.getRating()) < 4) {
						theRatingBarOne.setRating(starcounter_one + 1);
						starcounter_one++;

					} else {
						theRatingBarOne.setRating(starcounter_one + 1);

						startit.setVisibility(View.INVISIBLE);
						Toast.makeText(this, "GAME OVER - Player 1 Wins",
								Toast.LENGTH_LONG).show();
					}
				} else if (who_won.equals("two")) {

					if ((theRatingBarTwo.getRating()) < 4) {
						theRatingBarTwo.setRating(starcounter_two + 1);
						starcounter_two++;
					} else {
						theRatingBarTwo.setRating(starcounter_two + 1);

						startit.setVisibility(View.INVISIBLE);
						Toast.makeText(this, "GAME OVER - Player 2 Wins",
								Toast.LENGTH_LONG).show();
					}

				} else if (who_won.equals("tie")) {
					Toast.makeText(this, "TIE GAME", Toast.LENGTH_LONG).show();

				}

			} else if (resultCode == Activity.RESULT_CANCELED) {
				Toast.makeText(this, "Cancelled", Toast.LENGTH_LONG).show();
			}
			break;

		case SELECT_PICTURE:
			if (resultCode == Activity.RESULT_OK) {
				if (tempID == R.id.playone || tempID == R.id.pic_one) {

					pictureView = (ImageView) findViewById(R.id.pic_one);
					selectedURI_one = data.getData();
					picone = selectedURI_one.toString();
					try {
						selectedPicture = MediaStore.Images.Media.getBitmap(
								this.getContentResolver(), selectedURI_one);
						pictureView.setImageBitmap(selectedPicture);
					} catch (Exception e) {
					}

				}

				else {
					pictureView = (ImageView) findViewById(R.id.pic_two);
					selectedURI_two = data.getData();
					pictwo = selectedURI_two.toString();
					try {
						selectedPicture = MediaStore.Images.Media.getBitmap(
								this.getContentResolver(), selectedURI_two);
						pictureView.setImageBitmap(selectedPicture);
					} catch (Exception e) {
					}

				}

			}

			else if (resultCode == Activity.RESULT_CANCELED) {
				goToGallery();
			}
			break;

		}

	}

	// -----------------------------------------------------------------------------
	public void myClickHandler(View view) {

		switch (view.getId()) {
		case R.id.start_button:
			resetcheck = false;
			ImageButton startit = (ImageButton) findViewById(R.id.start_button);
			startit.setImageResource(R.drawable.startdown);
			playing_now = nextFirst();
			Intent nexAct = new Intent();
			nexAct.setClassName("edu.miami.c09879016.projectone",
					"edu.miami.c09879016.projectone.GameActivity");
			nexAct.putExtra("playing_now", playing_now);
			nexAct.putExtra("timer", timer);
			nexAct.putExtra("picone", picone);
			nexAct.putExtra("pictwo", pictwo);
			startActivityForResult(nexAct, START_GAME);
			break;
		default:
			break;
		}
	}

	// -----------------------------------------------------------------------------
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {

		MenuInflater inflater = getMenuInflater();
		inflater.inflate(R.menu.main, menu);
		return (true);
	}

	// -----------------------------------------------------------------------------
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case R.id.oneSec:
			timer = "one";
			return (true);
		case R.id.twoSec:
			timer = "two";
			return (true);
		case R.id.fiveSec:
			timer = "five";
			return (true);
		case R.id.tenSec:
			timer = "ten";
			return (true);
		case R.id.reset:
			resetGame();
			return (true);
		default:
			return (super.onOptionsItemSelected(item));
		}
	}

	// -----------------------------
	public void resetGame() {
		starcounter_one = 0;
		starcounter_two = 0;
		theRatingBarOne.setRating(starcounter_one);
		theRatingBarTwo.setRating(starcounter_two);

		player1 = "one";
		player2 = "two";
		name1 = "Player 1";
		name2 = "Player 2";
		picone = "Player 1";
		pictwo = "Player 2";
		timer = "five";
		dividing_line = 0.5;

		multi_win = false;

		txt_one = (TextView) findViewById(R.id.playone);
		txt_one.setText(name1);
		txt_two = (TextView) findViewById(R.id.playtwo);
		txt_two.setText(name2);

		resetcheck = true;

		ImageView picOne = (ImageView) findViewById(R.id.pic_one);
		picOne.setImageResource(R.drawable.red);

		ImageView picTwo = (ImageView) findViewById(R.id.pic_two);
		picTwo.setImageResource(R.drawable.blue);

		ImageButton startbu = (ImageButton) findViewById(R.id.start_button);
		startbu.setVisibility(View.VISIBLE);
	}

	// ---------

	public String nextFirst() {

		double random = ((Math.random() * 10) + 1) / 10;

		if (multi_win == true) {
			if (who_won.equals("one"))
				dividing_line = dividing_line - 0.1;
			else
				dividing_line = dividing_line + 0.1;
		}

		if (random < dividing_line)
			return player1;
		else
			return player2;

	}

	// -----------------------------------------------------------------------------

	public void onCreateContextMenu(ContextMenu menu, View view,
			ContextMenu.ContextMenuInfo menuInfo) {

		super.onCreateContextMenu(menu, view, menuInfo);
		getMenuInflater().inflate(R.menu.context, menu);
		tempID = view.getId();
	}

	// -----------------------------------------------------------------------------
	public boolean onContextItemSelected(MenuItem item) {

		switch (item.getItemId()) {
		case R.id.enter_name:
			if (tempID == R.id.playone || tempID == R.id.pic_one)
				showDialog(THE_DIALOG);
			else
				showDialog(THE_DIALOG2);

			return (true);
		case R.id.select_picture:
			goToGallery();
			return (true);

		default:
			return (super.onOptionsItemSelected(item));
		}
	}

	protected Dialog onCreateDialog(int dialogId) {

		AlertDialog.Builder editalert = new AlertDialog.Builder(this);
		LinearLayout.LayoutParams lp = new LinearLayout.LayoutParams(
				LinearLayout.LayoutParams.FILL_PARENT,
				LinearLayout.LayoutParams.FILL_PARENT);

		switch (dialogId) {
		case THE_DIALOG:

			editalert.setTitle("TicTacToc");
			editalert.setMessage("Please Enter Name:");

			final EditText input = new EditText(this);
			input.setLayoutParams(lp);

			if (resetcheck == true)
				input.setText("  ");

			editalert.setView(input);
			editalert.setPositiveButton("Enter",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {
							name1 = input.getText().toString();
							txt_one = (TextView) findViewById(R.id.playone);
							txt_one.setText(name1);
						}
					});
			break;
		case THE_DIALOG2:

			editalert.setTitle("TicTacToc");
			editalert.setMessage("Please Enter Name:");

			final EditText input2 = new EditText(this);
			input2.setLayoutParams(lp);

			if (resetcheck == true)
				input2.setText("  ");

			editalert.setView(input2);

			editalert.setPositiveButton("Enter",
					new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,
								int whichButton) {

							name2 = input2.getText().toString();
							txt_two = (TextView) findViewById(R.id.playtwo);
							txt_two.setText(name2);

						}
					});
			break;

		}
		theDialog = editalert.create();
		return (theDialog);
	}

	// ------------------------------------------------------
	public void goToGallery() {

		Intent galleryIntent;
		galleryIntent = new Intent(Intent.ACTION_PICK,
				android.provider.MediaStore.Images.Media.INTERNAL_CONTENT_URI);
		startActivityForResult(galleryIntent, SELECT_PICTURE);

	}

	// =============================================================================
}
// =============================================================================