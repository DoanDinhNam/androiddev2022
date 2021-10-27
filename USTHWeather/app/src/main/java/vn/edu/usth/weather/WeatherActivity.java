package vn.edu.usth.weather;

import androidx.appcompat.app.AppCompatActivity;
import android.view.Menu;
import android.widget.Toast;
import android.view.MenuInflater;
import androidx.viewpager.widget.PagerAdapter;
import android.view.MenuItem;
import android.content.Intent;
import androidx.viewpager.widget.ViewPager;
import android.os.Bundle;
import android.util.Log;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.media.MediaPlayer;
import android.annotation.SuppressLint;
import android.os.AsyncTask;

public class WeatherActivity extends AppCompatActivity {
    public static final String mess="Android";
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_weather);
        Log.i(mess,"onCreate");
    PagerAdapter adapter = new AdapterPage(getSupportFragmentManager());
        ViewPager pager = (ViewPager) findViewById(R.id.pager);
        pager.setAdapter(adapter);
        MediaPlayer mp = MediaPlayer.create(getBaseContext(), R.raw.QC);
        mp.start();
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.refresh:
                Toast toast = Toast.makeText(this, "Refresh", Toast.LENGTH_SHORT);
                toast.show();
                super.onRestart();

            case R.id.settings:
                Intent intent = new Intent(this, PrefActivity.class);
                startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onStart() {
        super.onStart();
        Log.i("Weather", "onStart() called");
    }
    @Override
    protected void onStop() {
        super.onStop();
        Log.i("Weather", "onStop() called");
    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        Log.i("Weather", "onDestroy() called");
    }
    @Override
    protected void onPause() {
        super.onPause();
        Log.i("Weather", "onPause() called");
    }
    @Override
    protected void onResume() {
        super.onResume();
        Log.i("Weather", "onResume() called");
    }

    @Override
    protected void onRestart() {
        super.onRestart();
        Log.i("Weather", "onRestart() called");
    }
    public void Refresh() {
        final Handler handler = new Handler(Looper.getMainLooper()) {
            @Override
            public void handleMessage(Message msg) {
                String content = msg.getData().getString("T1 VO DICH");
                Toast toast = Toast.makeText(getApplicationContext(), content, Toast.LENGTH_SHORT);
                toast.show();
            }
        };
    @SuppressLint("StaticFieldLeak")
    public void Refresh() {
        AsyncTask<Integer, Integer, Integer> taskA = new AsyncTask<Integer, Integer, Integer>() {

            @Override
            protected void onPreExecute() {
                Toast toast = Toast.makeText(getApplicationContext(), "Starting delay Android", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            protected void onPostExecute(Integer integer) {
                Toast toast = Toast.makeText(getApplicationContext(),  "Finished delay Android", Toast.LENGTH_SHORT);
                toast.show();
            }

            @Override
            protected void onProgressUpdate(Integer... values) {
                Toast toast = Toast.makeText(getApplicationContext(), "Uppdate  Android",+ values[0]);
                toast.show();
            }
            @SuppressLint("StaticFieldLeak")
            @Override
            protected Integer doInBackground(Integer... integers) {
                try {
                    int i;
                    for (i = 0; i < 10; i++) {
                        Thread.sleep(1000);
                        ProgressHandlerMessage.sendEmptyMessage(i);
                    }

                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return 0;
            }
        };
    }

}