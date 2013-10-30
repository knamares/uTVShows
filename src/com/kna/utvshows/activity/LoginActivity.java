package com.kna.utvshows.activity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.ActivityInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.kna.utvshows.R;
import com.kna.utvshows.helper.AnimationHelper;

public class LoginActivity extends Activity {

	private ProgressBar progressBarLoading;
	private TextView textViewUsername;
	private TextView textViewPassword;
	private EditText editTextUsername;
	private EditText editTextPassword;
	private Button buttonEnter;

	private SharedPreferences sharedPreferences;
	private String authToken;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);

		progressBarLoading = (ProgressBar) findViewById(R.id.progressBarLoginLoading);
		textViewUsername = (TextView) findViewById(R.id.textViewLoginUsername);
		textViewPassword = (TextView) findViewById(R.id.textViewLoginPassword);
		editTextUsername = (EditText) findViewById(R.id.editTextLoginUsername);
		editTextPassword = (EditText) findViewById(R.id.editTextLoginPassword);

		buttonEnter = (Button) findViewById(R.id.buttonLoginEnter);
		

	}

	@Override
	protected void onResume() {
		super.onResume();

		buttonEnter.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				invalidateControls();
				hideLoginInputs();
				new LoginTask().execute();
			}
		});

		sharedPreferences = getSharedPreferences("user", Context.MODE_PRIVATE);

		authToken = sharedPreferences.getString("auth", null);

		if (authToken == null) {
			// Show login inputs
			showLoginInputs();

		} else {
			// Do background login
			new LoginTask().execute();
		}

	}

	private void invalidateControls() {
		buttonEnter.setEnabled(false);
		editTextPassword.setEnabled(false);
		editTextUsername.setEnabled(false);
	}

	private void showLoginInputs() {
		AnimationHelper.showInput(editTextPassword);
		AnimationHelper.showInput(editTextUsername);
		AnimationHelper.showInput(buttonEnter);
		AnimationHelper.showInput(textViewPassword);
		AnimationHelper.showInput(textViewUsername);
	}

	private void hideLoginInputs() {
		buttonEnter.setText("");
		AnimationHelper.hideInput(editTextPassword);
		AnimationHelper.hideInput(editTextUsername);
		AnimationHelper.hideInput(textViewPassword);
		AnimationHelper.hideInput(textViewUsername);
	}

	private void initMainScreen() {
		// Go to MainActivty
		Intent intent = new Intent(this, MainActivity.class);
		startActivity(intent);
		finish();
	}

	private class LoginTask extends AsyncTask<Void, Void, Void> {

		@Override
		protected void onPreExecute() {
			AnimationHelper.showInput(progressBarLoading);
			
			//Block rotation
			setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_NOSENSOR);
		}

		@Override
		protected void onPostExecute(Void result) {
			AnimationHelper.hideInput(progressBarLoading);
			initMainScreen();
		}

		@Override
		protected Void doInBackground(Void... params) {
			for (int i = 0; i < 100; i++) {
				// Simulamos cierto retraso
				try {
					Thread.sleep(200);
				} catch (InterruptedException e) {
				}
			}

			return null;
		}
	}

}
