package chow.nocrastinator;

import android.app.Activity;
import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.CompoundButton;
import android.widget.ListView;
import android.widget.Switch;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    private List<Task> tasks= new ArrayList<Task>();
    private static final String TAG = "Test";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        getWindow().requestFeature(Window.FEATURE_ACTION_BAR);
        getActionBar().hide();

        Calendar calendar = Calendar.getInstance();
        calendar.setTimeInMillis(System.currentTimeMillis());

        calendar.set(Calendar.HOUR_OF_DAY, 19);
        calendar.set(Calendar.MINUTE, 6);
        calendar.set(Calendar.SECOND, 0);

        Intent intent1 = new Intent(MainActivity.this, AlarmReceiver.class);
        PendingIntent pendingIntent = PendingIntent.getBroadcast(MainActivity.this, 0,intent1, PendingIntent.FLAG_UPDATE_CURRENT);
        AlarmManager am = (AlarmManager) MainActivity.this.getSystemService(Context.ALARM_SERVICE);

        am.setRepeating(AlarmManager.RTC_WAKEUP, calendar.getTimeInMillis(), AlarmManager.INTERVAL_DAY, pendingIntent);
        setContentView(R.layout.activity_main);

        Task task = new Task();
        task.setName("Test");

        ArrayAdapter<Task> adapter = new TaskListAdapter(this, R.layout.alarm_single, tasks);
        adapter.notifyDataSetChanged();


        ListView listView = (ListView) findViewById(R.id.list);
        listView.setAdapter(adapter);

        tasks.add(task);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    private class TaskListAdapter extends ArrayAdapter<Task> {
        @Override
        public View getView(final int position, View convertView, ViewGroup parent) {

            final Task task = tasks.get(position);
            View itemView = convertView;

            //make sure to have a view to work with
            if (itemView == null){
                itemView = getLayoutInflater().inflate(R.layout.alarm_single, parent, false);
            }

            TextView nameText = (TextView) itemView.findViewById(R.id.task_name);
            nameText.setText(task.getName());

            Switch taskSwitch = (Switch) itemView.findViewById(R.id.task_switch);
            taskSwitch.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
                @Override
                public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                    Log.i(TAG, "checked");
                }
            });




            return itemView;
        }

        public TaskListAdapter(Context context,int resource, List<Task> objects)
        {
            super(context, resource, objects);
        }
        @Override
        public int getCount()
        {
            return tasks.size();
        }
    }
}
