package cns.utexas.QProject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class HostActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_host);
	
	final Button button = (Button) findViewById(R.id.host_start_party);
	View.OnClickListener hal = new View.OnClickListener() {
		public void onClick(View v) {
			Intent intent = new Intent(HostActivity.this, PartyStartActivity.class);
		    startActivity(intent);
		}
	};
	button.setOnClickListener(hal);
	}
	public boolean onCreateOptionsMenu(Menu menu)
	{
		MenuInflater mif = getMenuInflater();
		mif.inflate(R.menu.guest_action, menu);
		return super.onCreateOptionsMenu(menu);
		
	}
	public boolean onOptionsItemSelected(MenuItem item){
		switch(item.getItemId())
		{
			case R.id.add_icon:
				Intent intent = new Intent(HostActivity.this, AddSongActivity.class);
				startActivity(intent);
				return true;
		}
		return super.onOptionsItemSelected(item);
	} 
}
