package fetr.ac.in.visvesmruti2018.activities;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.List;

import fetr.ac.in.visvesmruti2018.R;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class GalleryActivity extends BaseActivity {
    static List<Banner> banners = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_gallery);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider2);
        ProgressBar pb = (ProgressBar) findViewById(R.id.progressBar2);
        TextView tv = (TextView) findViewById(R.id.nointv2);

        banners.add(new DrawableBanner(R.drawable.ad1).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad2).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        bannerSlider.setBanners(banners);
        bannerSlider.setVisibility(View.GONE);

        new DownloadImageTask(this, bannerSlider, pb, tv).execute("http://visvesmruti18.fetr.ac.in/vs_photos/gallery/count.txt");
    }

    static class DownloadImageTask extends AsyncTask<String, Void, String> {
        private Context activity;
        private BannerSlider bannerSlider;
        private ProgressBar pb;
        private TextView tv;

        DownloadImageTask(Context activity, BannerSlider bannerSlider, ProgressBar pb, TextView tv) {
            this.activity = activity;
            this.bannerSlider = bannerSlider;
            this.pb = pb;
            this.tv = tv;
        }

        @Override
        protected String doInBackground(String... urls) {
            String imageCount = "0";
            if(isInternetAvailable()){
                try {
                    URLConnection conexion = new URL(urls[0]).openConnection();
                    conexion.setConnectTimeout(4000);
                    conexion.setReadTimeout(1000);
                    conexion.connect();

                    InputStream input = new BufferedInputStream(conexion.getInputStream());

                    StringBuilder responseBuffer = new StringBuilder();
                    byte[] byteArray = new byte[1024];
                    while (input.read(byteArray) != -1)
                    {
                        String res = new String(byteArray, "UTF-8");
                        responseBuffer.append(res);
                        byteArray = new byte[1024];
                    }

                    imageCount = responseBuffer.toString().trim();
                    if(Integer.parseInt(imageCount) > 0){
                        banners.clear();
                        for(int i=1;i<=Integer.parseInt(imageCount);i++){
                            banners.add(new RemoteBanner("http://visvesmruti18.fetr.ac.in/vs_photos/gallery/" + i + ".png"));
                        }
                        bannerSlider.setBanners(banners);
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                    imageCount = "0";
                }
            }
            return imageCount;
        }

        private boolean isInternetAvailable() {
            ConnectivityManager connectivityManager
                    = (ConnectivityManager) activity.getSystemService(Context.CONNECTIVITY_SERVICE);
            NetworkInfo activeNetworkInfo = null;
            if (connectivityManager != null) {
                activeNetworkInfo = connectivityManager.getActiveNetworkInfo();
            }
            return activeNetworkInfo != null && activeNetworkInfo.isConnected();
        }

        @Override
        protected void onPostExecute(String s) {
            Toast.makeText(activity,s,Toast.LENGTH_SHORT).show();
            pb.setVisibility(View.GONE);
            if(Integer.parseInt(s) > 0){
                bannerSlider.setVisibility(View.VISIBLE);
            } else {
                tv.setVisibility(View.VISIBLE);
            }
        }
    }
}
