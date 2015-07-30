package cns.utexas.QProject;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class GuestBeforeAdd extends Activity {
	private static final String TAG = "Guest Activity";
	private List<Song> songs = new ArrayList<Song>();
	@Override
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
				Intent intent = new Intent(GuestBeforeAdd.this, AddSongsGuest.class);
				startActivity(intent);
				return true;
		}
		return super.onOptionsItemSelected(item);
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_guest);
		populateSongList();
		populateListView();
	}
	   private class MyListAdapter extends ArrayAdapter<Song>{ 
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View itemView = convertView;
				
				
			//make sure to have a view to work with
			if (itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.song_individual, parent, false);
			}
			
			//do something with the down button 
			ImageButton down_button = (ImageButton) itemView.findViewById(R.id.vote_down);
			down_button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
					Intent intent = new Intent(GuestBeforeAdd.this, LoginActivity.class);
				    startActivity(intent);
		        }
		    });
			
			//do something with the up button 
			ImageButton up_button = (ImageButton) itemView.findViewById(R.id.vote_up);
			up_button.setOnClickListener(new View.OnClickListener() {
		        @Override
		        public void onClick(View v) {
					Intent intent = new Intent(GuestBeforeAdd.this, GuestUpVote.class);
				    startActivity(intent);
		        }
		    });
			
			Song currentSong = songs.get(position);
			ImageView imageView = (ImageView) itemView.findViewById(R.id.album_cover);
			imageView.setImageResource(currentSong.getAlbumCoverPhoto());
			
			TextView nameText = (TextView) itemView.findViewById(R.id.song_name);
			nameText.setText(currentSong.getSongName());
			
			TextView artist = (TextView) itemView.findViewById(R.id.artist);
			artist.setText(currentSong.getSongArtist());
			
            Log.v(TAG, "breaks here");
			TextView numDown = (TextView) itemView.findViewById(R.id.down_count);
			numDown.setText(Integer.toString(currentSong.getNumDownVotes()));
			
			TextView numUp = (TextView) itemView.findViewById(R.id.up_count);
			numUp.setText(Integer.toString(currentSong.getNumUpVotes()));
			return itemView;			
			
		}

		public MyListAdapter(){
			super(GuestBeforeAdd.this, R.layout.song_individual, songs);
		}
	   }
		private void populateSongList() {
			songs.add(new Song(1, "All I want for Christmas", R.drawable.michaelbuble, "Michael Buble", 10, 1));
			songs.add(new Song(2, "Shake it Off", R.drawable.taylorswift, "Taylor Swift", 10, 2));
			songs.add(new Song(3, "Counting Stars", R.drawable.nativealbum, "One Republic", 4, 1));
			songs.add(new Song(5, "Sky Full of Stars", R.drawable.coldplay, "Coldplay", 2, 7));
			//songs.add(new Song(4, "Blank Space", R.drawable.blankspace, "Taylor Swift", 0, 0));
			
		}
		private void populateListView(){
			ArrayAdapter<Song> adapter = new MyListAdapter();
			ListView list = (ListView) findViewById(R.id.song_list);
			list.setAdapter(adapter);
		}
}

