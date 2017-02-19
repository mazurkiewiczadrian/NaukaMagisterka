package com.example.naukamagisterka;

import android.accounts.Account;
import android.accounts.OnAccountsUpdateListener;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.DialogInterface;
import android.content.DialogInterface.OnClickListener;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class HomeAfterLogin extends Activity {
	ImageView logo;
	LoginDataBaseAdapter loginDataBaseAdapter;
	Button button;
	Intent intent;
	Button button_start; 
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_home_after_login);
		
		
		button = (Button)findViewById(R.id.button_logout);
		button_start = (Button) findViewById(R.id.button_start);
		button.setOnClickListener(new Button.OnClickListener() {
			//Wylogowanie
			@Override
			public void onClick(View arg0) {
				try {
					HomeAfterLogin.this.finish();
					loginDataBaseAdapter.close();
				} catch (Exception e) {
				}
			}
		});
		
		button_start.setOnClickListener(new Button.OnClickListener() {
			
			@Override
			public void onClick(View arg0) {
				intent = new Intent(HomeAfterLogin.this, Game.class);
				startActivity(intent);
			}
		});
		
		
		logo = (ImageView)findViewById(R.id.imageView1);
		logo.setOnClickListener(new ImageView.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				displayAbout(logo);
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.home_after_login, menu);
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
	
	public void exitApplication(View arg0){
		AlertDialog.Builder builder = new AlertDialog.Builder(HomeAfterLogin.this);
		builder.setMessage("Czy na pewno chcesz zakoñczyæ?");
		builder.setCancelable(true);
		
		builder.setPositiveButton("Tak", new DialogInterface.OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				//HomeAfterLogin.this.finish();
				try {
					finishAffinity();
				} catch (Exception e) {
				}
			}
		});
		
			builder.setNegativeButton("Anuluj", new OnClickListener() {
			
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dialog.cancel();
			}
		});
		AlertDialog alert = builder.create();
		alert.show();
	}
	
	public void displayInstructions(View arg0){
		Dialog dialog = new Dialog(HomeAfterLogin.this);
		dialog.setContentView(R.layout.instructions);
		dialog.setTitle("Zasady gry");
		dialog.show();
	}
	
	public void displayAbout(View arg0){
		Dialog dialog = new Dialog(HomeAfterLogin.this);
		dialog.setContentView(R.layout.about);
		dialog.setTitle("O grze");
		dialog.show();
		
	}
	

}
