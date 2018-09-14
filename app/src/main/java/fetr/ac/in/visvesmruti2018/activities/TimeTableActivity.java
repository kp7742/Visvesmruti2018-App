package fetr.ac.in.visvesmruti2018.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;

import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;

import fetr.ac.in.visvesmruti2018.R;

public class TimeTableActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timetable);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        final ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar);
        final ImageView timetable = (ImageView) findViewById(R.id.timetable);
        new DownloadImageTask(this,timetable,pb).execute("http://visvesmruti18.fetr.ac.in/img/timetable.png");
    }

    static class DownloadImageTask extends AsyncTask<String, Void, Bitmap> {
        private ImageView bmImage;
        private ProgressBar pb;
        private Context ctx;

        DownloadImageTask(Context ctx, ImageView bmImage, ProgressBar pb) {
            this.bmImage = bmImage;
            this.ctx = ctx;
            this.pb = pb;
        }

        @Override
        protected Bitmap doInBackground(String... urls) {
            Bitmap image = null;
            image = BitmapFactory.decodeResource(ctx.getResources(), R.drawable.timetable);
            if(isInternetAvailable()){
                try {
                    HttpURLConnection conn= (HttpURLConnection) new URL(urls[0]).openConnection();
                    conn.setDoInput(true);
                    conn.connect();
                    InputStream is = conn.getInputStream();
                    image = BitmapFactory.decodeStream(is);
                } catch (Exception e) {
                    e.printStackTrace();
                    image = BitmapFactory.decodeResource(ctx.getResources(),R.drawable.timetable);
                }
            }
            return image;
        }

        private boolean isInternetAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) ctx.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = null;
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        protected void onPostExecute(Bitmap result) {
            pb.setVisibility(View.GONE);
            bmImage.setImageBitmap(result);
        }
    }
}
