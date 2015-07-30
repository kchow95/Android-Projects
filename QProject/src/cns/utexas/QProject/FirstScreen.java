package cns.utexas.QProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
public class FirstScreen extends Activity{
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_screen);
        
        //what to do when start a party
        final Button button = (Button) findViewById(R.id.start_party_button);
		View.OnClickListener hal = new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(FirstScreen.this, LoginActivity.class);
			    startActivity(intent);
			}
		};
		button.setOnClickListener(hal);
		
        //what to do when start a party
	    final Button button2 = (Button) findViewById(R.id.join_party_button);
	    View.OnClickListener hal2 = new View.OnClickListener() {
	    	public void onClick(View v) {
	    		Intent intent = new Intent(FirstScreen.this, EnterCodeActivity.class);
				startActivity(intent);
			}
		};
		button2.setOnClickListener(hal2);
    }

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.first_screen, menu);
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
