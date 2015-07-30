package cns.utexas.QProject;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class VetoActivity extends Activity {
	private List<Song> songs = new ArrayList<Song>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_party_start);
		populateSongList();
		populateListView();
		
		final Button startpause = (Button) findViewById(R.id.pause_button);		
		startpause.setText("Pause");
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
				Intent intent = new Intent(VetoActivity.this, AddSongActivity.class);
				startActivity(intent);
				return true;
		}
		return super.onOptionsItemSelected(item);
	} 
	   private class MyListAdapter extends ArrayAdapter<Song>{ 
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View itemView = convertView;
				
				
			//make sure to have a view to work with
			if (itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.party_start_song_individual, parent, false);
			}
			
			Song currentSong = songs.get(position);
			ImageView imageView = (ImageView) itemView.findViewById(R.id.party_start_album);
			imageView.setImageResource(currentSong.getAlbumCoverPhoto());
			
			TextView nameText = (TextView) itemView.findViewById(R.id.party_start_song_name);
			nameText.setText(currentSong.getSongName());
			
			TextView artist = (TextView) itemView.findViewById(R.id.party_start_artist);
			artist.setText(currentSong.getSongArtist());
			
			//do something with the veto button 
			ImageButton veto_button = (ImageButton) itemView.findViewById(R.id.veto_button);
			veto_button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
					Intent intent = new Intent(VetoActivity.this, GuestActivity.class);
				    startActivity(intent);
		        }
		    });
			return itemView;
			
			
		}

		public MyListAdapter(){
			super(VetoActivity.this, R.layout.party_start_song_individual, songs);
		}
	   }
		private void populateSongList() {
			songs.add(new Song(1, "All I Want for Christmas", R.drawable.michaelbuble, "Michael Buble", 1, 2));
			songs.add(new Song(2, "Shake it Off", R.drawable.taylorswift, "Taylor Swift", 3, 4));
			//songs.add(new Song(3, "Counting Stars", R.drawable.nativealbum, "One Republic", 5, 6));
			songs.add(new Song(4, "Sky Full of Stars", R.drawable.coldplay, "Coldplay", 7, 8));
			
		}
		private void populateListView(){
			ArrayAdapter<Song> adapter = new MyListAdapter();
			ListView list = (ListView) findViewById(R.id.start_song_list);
			list.setAdapter(adapter);
		}
}