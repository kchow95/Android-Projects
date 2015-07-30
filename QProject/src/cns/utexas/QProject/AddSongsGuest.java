package cns.utexas.QProject;

import java.util.ArrayList;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class AddSongsGuest extends Activity {

	private List<Song> songs = new ArrayList<Song>();
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_add_songs_guest);
		populateSongList();
		populateListView();
		
        //to add songs
        final Button button = (Button) findViewById(R.id.add_buton);
		View.OnClickListener hal = new View.OnClickListener() {
			public void onClick(View v) {
				Intent intent = new Intent(AddSongsGuest.this, GuestActivity.class);
			    startActivity(intent);
			}
		};
		button.setOnClickListener(hal);
	}
	   private class MyListAdapter extends ArrayAdapter<Song>{ 
			@Override
			public View getView(int position, View convertView, ViewGroup parent) {
				View itemView = convertView;
				
				
			//make sure to have a view to work with
			if (itemView == null){
				itemView = getLayoutInflater().inflate(R.layout.add_song_individual, parent, false);
			}
			
			Song currentSong = songs.get(position);
			ImageView imageView = (ImageView) itemView.findViewById(R.id.add_album_cover);
			imageView.setImageResource(currentSong.getAlbumCoverPhoto());
			
			TextView nameText = (TextView) itemView.findViewById(R.id.add_song_name);
			nameText.setText(currentSong.getSongName());
			
			TextView artist = (TextView) itemView.findViewById(R.id.add_song_artist);
			artist.setText(currentSong.getSongArtist());
			
			return itemView;
			
			
		}

		public MyListAdapter(){
			super(AddSongsGuest.this, R.layout.add_song_individual, songs);
		}
	   }
	private void populateSongList() {
		songs.add(new Song(1, "It is Well", R.drawable.itiswell, "Kutless", 1, 2));
		songs.add(new Song(2, "All About that Bass", R.drawable.allaboutthatbass, "Meghan Trainor", 3, 4));
		songs.add(new Song(3, "Blank Space", R.drawable.blankspace, "Taylor Swift", 5, 6));
		songs.add(new Song(4, "Animals", R.drawable.animals, "Maroon 5", 7, 8));
		
	}
	private void populateListView(){
		ArrayAdapter<Song> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.add_song_list);
		list.setAdapter(adapter);
	}

//	@Override
//	public boolean onCreateOptionsMenu(Menu menu) {
//		// Inflate the menu; this adds items to the action bar if it is present.
//		getMenuInflater().inflate(R.menu.add_song, menu);
//		return true;
//	}
//
//	@Override
//	public boolean onOptionsItemSelected(MenuItem item) {
//		// Handle action bar item clicks here. The action bar will
//		// automatically handle clicks on the Home/Up button, so long
//		// as you specify a parent activity in AndroidManifest.xml.
//		int id = item.getItemId();
//		if (id == R.id.action_settings) {
//			return true;
//		}
//		return super.onOptionsItemSelected(item);
//	}
	
}

