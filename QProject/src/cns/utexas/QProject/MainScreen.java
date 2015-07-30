package cns.utexas.QProject;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewConfiguration;
import android.view.ViewGroup;
import android.view.ViewTreeObserver;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

public class MainScreen extends Activity {
	StableArrayAdapter mAdapter;
    private ListView mListView;
    private BackgroundContainer mBackgroundContainer;
    private List<Song> songs = new ArrayList<Song>();
    private boolean mSwiping = false;
    private boolean mItemPressed = false;
    HashMap<Long, Integer> mItemIdTopMap = new HashMap<Long, Integer>();
    private static final String TAG = "QProject";

    private static final int SWIPE_DURATION = 250;
    private static final int MOVE_DURATION = 150;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main_screen);
		populateSongList();
		populateListView();
		
		
       
        mAdapter = new StableArrayAdapter(this,R.layout.opaque_text_view, songs,
                mTouchListener);
        mListView.setAdapter(mAdapter);
		
	}

	 private View.OnTouchListener mTouchListener = new View.OnTouchListener() {
	        
	        float mDownX;
	        private int mSwipeSlop = -1;
	        
	        @Override
	        public boolean onTouch(final View v, MotionEvent event) {
	        	Log.i(TAG, "Got here");
	            if (mSwipeSlop < 0) {
	                mSwipeSlop = ViewConfiguration.get(MainScreen.this).
	                        getScaledTouchSlop();
	            }
	            switch (event.getAction()) {
	            case MotionEvent.ACTION_DOWN:
	                if (mItemPressed) {
	                    // Multi-item swipes not handled
	                    return false;
	                }
	                mItemPressed = true;
	                mDownX = event.getX();
	                break;
	            case MotionEvent.ACTION_CANCEL:
	                v.setAlpha(1);
	                v.setTranslationX(0);
	                mItemPressed = false;
	                break;
	            case MotionEvent.ACTION_MOVE:
	                {
	                    float x = event.getX() + v.getTranslationX();
	                    float deltaX = x - mDownX;
	                    float deltaXAbs = Math.abs(deltaX);
	                    if (!mSwiping) {
	                        if (deltaXAbs > mSwipeSlop) {
	                            mSwiping = true;
	                            mListView.requestDisallowInterceptTouchEvent(true);
	                            mBackgroundContainer.showBackground(v.getTop(), v.getHeight());
	                        }
	                    }
	                    if (mSwiping) {
	                        v.setTranslationX((x - mDownX));
	                        v.setAlpha(1 - deltaXAbs / v.getWidth());
	                    }
	                }
	                break;
	            case MotionEvent.ACTION_UP:
	                {
	                    // User let go - figure out whether to animate the view out, or back into place
	                    if (mSwiping) {
	                        float x = event.getX() + v.getTranslationX();
	                        float deltaX = x - mDownX;
	                        float deltaXAbs = Math.abs(deltaX);
	                        float fractionCovered;
	                        float endX;
	                        float endAlpha;
	                        final boolean remove;
	                        if (deltaXAbs > v.getWidth() / 4) {
	                            // Greater than a quarter of the width - animate it out
	                            fractionCovered = deltaXAbs / v.getWidth();
	                            endX = deltaX < 0 ? -v.getWidth() : v.getWidth();
	                            endAlpha = 0;
	                            remove = true;
	                        } else {
	                            // Not far enough - animate it back
	                            fractionCovered = 1 - (deltaXAbs / v.getWidth());
	                            endX = 0;
	                            endAlpha = 1;
	                            remove = false;
	                        }
	                        // Animate position and alpha of swiped item
	                        // NOTE: This is a simplified version of swipe behavior, for the
	                        // purposes of this demo about animation. A real version should use
	                        // velocity (via the VelocityTracker class) to send the item off or
	                        // back at an appropriate speed.
	                        long duration = (int) ((1 - fractionCovered) * SWIPE_DURATION);
	                        mListView.setEnabled(false);
	                        v.animate().setDuration(duration).
	                                alpha(endAlpha).translationX(endX).
	                                withEndAction(new Runnable() {
	                                    @Override
	                                    public void run() {
	                                        // Restore animated values
	                                        v.setAlpha(1);
	                                        v.setTranslationX(0);
	                                        if (remove) {
	                                            animateVote(mListView, v);
	                                        } else {
	                                            mBackgroundContainer.hideBackground();
	                                            mSwiping = false;
	                                            mListView.setEnabled(true);
	                                        }
	                                    }
	                                });
	                    }
	                }
	                mItemPressed = false;
	                break;
	            default: 
	                return false;
	            }
	            return true;
	        }
	    };
	    
	    private void animateVote(final ListView listview, View viewToVote) {
	        int firstVisiblePosition = listview.getFirstVisiblePosition();
	        for (int i = 0; i < listview.getChildCount(); ++i) {
	            View child = listview.getChildAt(i);
	            if (child != viewToVote) {
	                int position = firstVisiblePosition + i;
	                long itemId = mAdapter.getItemId(position);
	                mItemIdTopMap.put(itemId, child.getTop());
	            }
	        }
	        // Delete the item from the adapter
	        int position = mListView.getPositionForView(viewToVote);
	        mAdapter.vote_down(mAdapter.getItem(position));
	        viewToVote.setBackgroundColor(0xFF00FF00);
	        final ViewTreeObserver observer = listview.getViewTreeObserver();
	        observer.addOnPreDrawListener(new ViewTreeObserver.OnPreDrawListener() {
	            public boolean onPreDraw() {
	                observer.removeOnPreDrawListener(this);
	                
	                mItemIdTopMap.clear();
	                return true;
	            }
	        });
	    }
   private class MyListAdapter extends ArrayAdapter<Song>{ 
		@Override
		public View getView(int position, View convertView, ViewGroup parent) {
			View itemView = convertView;
			
		//make sure to have a view to work with
		if (itemView == null){
			itemView = getLayoutInflater().inflate(R.layout.song_individual, parent, false);
		}
		
		Song currentSong = songs.get(position);
		ImageView imageView = (ImageView) itemView.findViewById(R.id.album_cover);
		imageView.setImageResource(currentSong.getAlbumCoverPhoto());
		
		TextView nameText = (TextView) itemView.findViewById(R.id.song_name);
		nameText.setText(currentSong.getSongName());
		return itemView;
	}

	public MyListAdapter(){
		super(MainScreen.this, R.layout.song_individual, songs);
	}
   }
	private void populateSongList() {
		songs.add(new Song(1, "It is Well", R.drawable.ic_launcher, "ho", 1, 2));
		songs.add(new Song(2, "All about that bass", R.drawable.ic_launcher, "ho", 3, 4));
		songs.add(new Song(3, "Booty", R.drawable.ic_launcher, "ho", 5, 6));
		songs.add(new Song(4, "Ex-boyfriend", R.drawable.ic_launcher, "ho", 7, 8));
		
	}
	private void populateListView(){
		ArrayAdapter<Song> adapter = new MyListAdapter();
		ListView list = (ListView) findViewById(R.id.song_list);
		list.setAdapter(adapter);
	}

}
