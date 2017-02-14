package com.example.naukamagisterka;

import android.app.Activity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class SignUPActivity extends Activity {

	EditText editTextUserName, editTextPassword, editTextConfirmPassword;
	Button btnCreateAccount;
	Intent intent;
	LoginDataBaseAdapter loginDataBaseAdapter;

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_sign_up);
		
		loginDataBaseAdapter = new LoginDataBaseAdapter(this);
		loginDataBaseAdapter=loginDataBaseAdapter.open();
		
		editTextUserName = (EditText)findViewById(R.id.emailSignUp);
		editTextPassword = (EditText)findViewById(R.id.passwordSignUp);
		editTextConfirmPassword = (EditText)findViewById(R.id.confirmPasswordSignUp);
		
		btnCreateAccount = (Button)findViewById(R.id.buttonSignUp);
		btnCreateAccount.setOnClickListener(new View.OnClickListener() {
			
			
			public void onClick(View v) {
				final String userName = editTextUserName.getText().toString();
				String password = editTextPassword.getText().toString();
				String confirmPassword = editTextConfirmPassword.getText().toString();
				String usernames = loginDataBaseAdapter.getSingleEntryUsers(userName);
				
				if (userName.equals("") || password.equals("") || confirmPassword.equals("")) {
					Toast.makeText(getApplicationContext(), "Jedno lub wiêcej pole jest puste", Toast.LENGTH_LONG).show();
					return;
				}if (usernames.equals(userName)) {
					Toast.makeText(getApplicationContext(), "U¿ytkownik o takim adresie e-mail ju¿ istnieje!", Toast.LENGTH_LONG).show();
					return;
				}
				
				if (!password.equals(confirmPassword)) {
					Toast.makeText(getApplicationContext(), "Podane has³a nie s¹ zgodne!", Toast.LENGTH_LONG).show();
					return;
				}
				
				else{
					loginDataBaseAdapter.insertEntry(userName, password);
					Toast.makeText(getApplicationContext(), "Konto zosta³o utworzone. Mo¿esz siê zalogowaæ.", Toast.LENGTH_LONG).show();
				}
			}
		});
	}
	


	@Override
	protected void onDestroy() {
		super.onDestroy();
		loginDataBaseAdapter.close();
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.sign_u, menu);
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
	
	public void backToActivityLogin(View view){
		startActivity(new Intent(SignUPActivity.this, ActivityLogin.class));
	}
	
	
}
