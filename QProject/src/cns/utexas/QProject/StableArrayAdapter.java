package cns.utexas.QProject;

/*
 * Copyright (C) 2013 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

import java.util.HashMap;
import java.util.List;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

public class StableArrayAdapter extends ArrayAdapter<Song> {

    HashMap<Song, Integer> mIdMap = new HashMap<Song, Integer>();
    View.OnTouchListener mTouchListener;

    public StableArrayAdapter(Context context, int textViewResourceId,
            List<Song> objects, View.OnTouchListener listener) {
        super(context, textViewResourceId);
        mTouchListener = listener;
        for (int i = 0; i < objects.size(); ++i) {
            mIdMap.put(objects.get(i), i);
        }
    }

    @Override
    public long getItemId(int position) {
        Song item = getItem(position);
        return mIdMap.get(item);
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        View view = super.getView(position, convertView, parent);
        if (view != convertView) {
            // Add touch listener to every new view to track swipe motion
            view.setOnTouchListener(mTouchListener);
        }
        return view;
    }
    public void vote_down(Song song){
    	song.setNumDownVotes(song.getNumDownVotes()-1);
    }

    public void vote_up(Song song){
    	song.setNumUpVotes(song.getNumUpVotes()+1);
    }
}
