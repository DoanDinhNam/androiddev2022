package vn.edu.usth.weather;

import androidx.annotation.RequiresApi;
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
import android.graphics.BitmapFactory;
import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.ImageRequest;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import android.widget.Toast;

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
        RunImag();
        getAPIWeatherJson();

    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu, menu);
        return true;
    }

    @SuppressLint("NonConstantResourceId")
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
    public void RunImag() {
        RequestQueue Requestqueue = Volley.newRequestQueue(this);
        Response.Listener<Bitmap> listeners = new Response.Listener<Bitmap>() {
                    @Override
                    public void onResponse(Bitmap response) {
                        ImageView iv = (ImageView) findViewById(R.id.logo);
                        iv.setImageBitmap(response);
                    }
                };
        ImageRequest imageRequest = new ImageRequest(
                "https://usth.edu.vn/uploads/logo_1_vi_1.png",
                listeners, 1, 0, ImageView.ScaleType.CENTER,
                Bitmap.Config.ARGB_8888,null);
        Requestqueue.add(imageRequest);
    }
    private void getAPIWeatherJson()
    {
        String url = "https://api.openweathermap.org/data/2.5/weather?q=hanoi&appid=e3fc7cd1499bf87b25c7829f2ff41639";
        RequestQueue Requestqueue = Volley.newRequestQueue(this);
        JsonObjectRequest js = new JsonObjectRequest(
                Request.Method.GET, url, null, new Response.Listener<JSONObject>() {
                    @RequiresApi(api = Build.VERSION_CODES.O)
                    @Override
                    public void onResponse(JSONObject response) {
                        TextView textview = (TextView) findViewById(R.id.city_name);
                        String out = "";
                        try {
                            String name = response.getString("name");
                            double temp = response.getJSONObject("main").getDouble("temp");

                            out = name + "\n" +  String.valueOf(temp) + "USTH Weather";

                        } catch (  JSONException e) {
                            e.printStackTrace();
                        }
                        textview.setText(out);
                    }
                }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
               Toast toast= Toast.makeText(getApplicationContext(), "Wrong...", Toast.LENGTH_SHORT);
               toast.show();
            }
        });
        Requestqueue.add(js);
   }
}