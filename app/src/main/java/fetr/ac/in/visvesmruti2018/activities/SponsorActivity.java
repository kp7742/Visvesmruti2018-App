package fetr.ac.in.visvesmruti2018.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import java.util.ArrayList;
import java.util.List;

import fetr.ac.in.visvesmruti2018.R;
import ss.com.bannerslider.banners.Banner;
import ss.com.bannerslider.banners.DrawableBanner;
import ss.com.bannerslider.banners.RemoteBanner;
import ss.com.bannerslider.views.BannerSlider;

public class SponsorActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_sponsor);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        BannerSlider bannerSlider = (BannerSlider) findViewById(R.id.banner_slider);
        List<Banner> banners = new ArrayList<>();
        banners.add(new DrawableBanner(R.drawable.ad1).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad2).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad3).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad4).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad5).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad6).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad7).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        banners.add(new DrawableBanner(R.drawable.ad8).setScaleType(ImageView.ScaleType.CENTER_INSIDE));
        bannerSlider.setBanners(banners);
    }
}
