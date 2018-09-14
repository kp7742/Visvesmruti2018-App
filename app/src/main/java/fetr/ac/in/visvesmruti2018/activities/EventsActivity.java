package fetr.ac.in.visvesmruti2018.activities;

import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;

import com.github.florent37.materialviewpager.MaterialViewPager;

import fetr.ac.in.visvesmruti2018.R;
import fetr.ac.in.visvesmruti2018.fragments.EventRecyclerViewFragment;

public class EventsActivity extends BaseActivity {

    private MaterialViewPager mViewPager;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_events);
        mViewPager = (MaterialViewPager) findViewById(R.id.materialViewPager);
        setUpToolbar();
        setUpViewPager();
    }

    private void setUpToolbar(){
        Toolbar toolbar = mViewPager.getToolbar();

        if (toolbar != null) {
            toolbar.setTitle("Events");
            toolbar.setTitleTextColor(Color.WHITE);
            setSupportActionBar(toolbar);

            ActionBar actionBar = getSupportActionBar();
            if (actionBar != null) {
                actionBar.setDisplayHomeAsUpEnabled(true);
                actionBar.setDisplayShowHomeEnabled(true);
                actionBar.setDisplayShowTitleEnabled(true);
                actionBar.setDisplayUseLogoEnabled(false);
                actionBar.setHomeButtonEnabled(true);
            }
        }
    }

    private void setUpViewPager(){
        ViewPager viewPager = mViewPager.getViewPager();
        viewPager.setAdapter(new FragmentPagerAdapter(getSupportFragmentManager()) {
            @Override
            public Fragment getItem(int position) {
                return EventRecyclerViewFragment.newInstance(position);
            }

            @Override
            public int getCount() {
                return 6;
            }

            @NonNull
            @Override
            public CharSequence getPageTitle(int position) {
                switch (position) {
                    case 0:
                        return "Computer";
                    case 1:
                        return "Civil";
                    case 2:
                        return "Chemical";
                    case 3:
                        return "Mechenical";
                    case 4:
                        return "Electrical";
                    case 5:
                        return "Sci&Hum";
                }
                return "";
            }
        });
        viewPager.setOffscreenPageLimit(viewPager.getAdapter().getCount());
        mViewPager.getPagerTitleStrip().setTextColor(Color.WHITE);
        mViewPager.getPagerTitleStrip().setIndicatorColor(Color.WHITE);
        mViewPager.getPagerTitleStrip().setViewPager(viewPager);
    }
}
