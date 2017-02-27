package com.example.naukamagisterka;

import android.app.Activity;
import android.app.Dialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.text.method.DialerKeyListener;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ActivityLogin extends Activity {
	Button btnLogin;
	TextView btnSingIn;
	LoginDataBaseAdapter loginDataBaseAdapter;
	Intent intent;
	
	public static String loggedUser;
	public static String userPoints;
	public static String userLevel;
	
	
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_login);
		
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		btnLogin = (Button)findViewById(R.id.email_sign_in_button);
		btnSingIn = (TextView)findViewById(R.id.signUpTextView);
		
		final EditText editTextEmail = (EditText)findViewById(R.id.email);
		final EditText editTextPassword = (EditText)findViewById(R.id.password);
		
		btnSingIn.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				
				Intent intentSignIn = new Intent(getApplicationContext(), SignUPActivity.class);
				startActivity(intentSignIn);
				
			}
		});
		
		btnLogin.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				 String userName = editTextEmail.getText().toString();
				 final String userPassword = editTextPassword.getText().toString();
				  String storedPassword = loginDataBaseAdapter.getSingleEntry(userName);
				  
					if (storedPassword.equals(userPassword)){
						loggedUser = userName;
						
						userLevel = loginDataBaseAdapter.getSingleEntryLevel(userName);
						userPoints = loginDataBaseAdapter.getSingleEntryPoints(userName);
						//Toast.makeText(ActivityLogin.this, "Udana próba zalogowania.", Toast.LENGTH_LONG).show();
						intent = new Intent(ActivityLogin.this, HomeAfterLogin.class);
						startActivity(intent);
						
						
						}	
					else{
						Toast.makeText(ActivityLogin.this, "Niepoprawny e-mail lub has³o! Spróbuj ponownie.", Toast.LENGTH_LONG).show();
						}
				}
		
		});
	}
	
	public String getLoggedUser() {
		return loggedUser;
	}
	
	
	public  String getUserLevel() {
		return userLevel;
	}
	
	public  String getUserPoints() {
		return userPoints;
	}
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		
		loginDataBaseAdapter.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_login, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
		
}
