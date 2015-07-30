package edu.utexas.Chow.wherewolf;

import android.support.v7.app.ActionBarActivity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


public class LoginActivity extends ActionBarActivity {

	private static final String TAG = "loginactivity";
	public final static String EXTRA_MESSAGE = "com.example.Wherewolf.HOMESCREEN";
	private EditText username=null;
	private EditText password=null;
	private Button login;
	int counter = 3;
	
	public void startHomescreen(){
		Intent intent = new Intent(this, HomeScreenActivity.class);
		EditText editText = (EditText) findViewById(R.id.username);
	    String message = editText.getText().toString();
	    intent.putExtra(EXTRA_MESSAGE, message);
	    startActivity(intent);
	}
	
	public void login(View view){
		if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
			Toast.makeText(getApplicationContext(), "Redirecting...", 
		    Toast.LENGTH_SHORT).show();
			startHomescreen();
			
			
		}	
		else{
			Toast.makeText(getApplicationContext(), "Wrong Credentials",
			Toast.LENGTH_SHORT).show();
		}
			      
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
	    setContentView(R.layout.activity_login);
	    username = (EditText)findViewById(R.id.username);
	    password = (EditText)findViewById(R.id.password);
	    final Button button = (Button) findViewById(R.id.loginButton);

		View.OnClickListener jim = new View.OnClickListener() {
			public void onClick(View v) {
				login(v);
			}
		};
		button.setOnClickListener(jim);

	}
	
	
	@Override
	protected void onStart() {
		Log.i(TAG, "started the login activity");
		super.onStart();
	}

	@Override
	protected void onRestart() {
		Log.i(TAG, "restarted the login activity");
		super.onRestart();
	}

	@Override
	protected void onResume() {
		Log.i(TAG, "resumed the login activity");
		super.onResume();
	}

	@Override
	protected void onPause() {
		Log.i(TAG, "pause the login activity");
		super.onPause();
	}

	@Override
	protected void onStop() {
		Log.i(TAG, "stopped the login activity");
		super.onStop();
	}

	@Override
	protected void onDestroy() {
		Log.i(TAG, "destroyed the login activity");
		super.onDestroy();
	}
//    @Override
//    public boolean onCreateOptionsMenu(Menu menu) {
//        // Inflate the menu; this adds items to the action bar if it is present.
//        getMenuInflater().inflate(R.menu.login, menu);
//        return true;
//    }
//
//    @Override
//    public boolean onOptionsItemSelected(MenuItem item) {
//        // Handle action bar item clicks here. The action bar will
//        // automatically handle clicks on the Home/Up button, so long
//        // as you specify a parent activity in AndroidManifest.xml.
//        int id = item.getItemId();
//        if (id == R.id.action_settings) {
//            return true;
//        }
//        return super.onOptionsItemSelected(item);
//    }
}
