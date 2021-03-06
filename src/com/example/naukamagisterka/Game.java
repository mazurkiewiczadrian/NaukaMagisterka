package com.example.naukamagisterka;

import java.sql.Connection;

import android.app.Activity;
import android.app.Dialog;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

public class Game extends Activity {
	
	ActivityLogin al = new ActivityLogin();
	
	private GameWorld gw;
	private UpdateColor updtCol;
	
	@SuppressWarnings("unused")
	private UpdateGameObject updtGameObj;
	private LoginDataBaseAdapter data;
	
	private Button pause_btn, undo_btn, hint_btn, close_btn, next_level_btn,
	exit_btn, icon_btn;
	private ToggleButton sound_btn;
	private TextView timer_txt, move_cnt, best_cnt, score_cnt,
	toast_title_txt, level_txt;
	private Dialog dialog_box, menu_dialog_box;
	private Handler time_handler = new Handler();
	private Handler hint_handler = new Handler();
	Toast toast;
	private long mill_sec=0, buffer = 0, updt_time=0;
	private boolean sound = true, menu_show=false;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		//Testowe wyświetlenie nazwy zalogowanego użytkownika
		TextView tvUser = (TextView)findViewById(R.id.textView1);
		String user = al.getLoggedUser();
		tvUser.setText(user);
		
		//Testowe wyświetlenie levelu zalogowanego użytkownika
		TextView tvLevel = (TextView)findViewById(R.id.textViewLevel);
		String userLevel = al.getUserLevel();
		tvLevel.setText(userLevel);
		
		//Testowe wyświetlenie punktów zalogowanego użytkownika
		TextView tvPoints = (TextView)findViewById(R.id.textViewPoints);
		String userPoints = al.getUserPoints();
		tvPoints.setText(userPoints);
		
		
		
	}
	
	private Runnable timer_thread = new Runnable() {
		
		@Override
		public void run() {
			if (Global.PAUSE == false) {
				mill_sec = SystemClock.uptimeMillis() - Global.START_TIME;
				updt_time = buffer + mill_sec;
			}else {
				int secs = (int)(updt_time/1000);
				int mins = secs/60;
				int hours = secs%60;
				timer_txt.setText(hours+":"+mins+":"+String.format("%02d", secs));
				time_handler.postDelayed(this, 0);
			}
		}
	};

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.game, menu);
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
