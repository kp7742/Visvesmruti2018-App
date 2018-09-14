package fetr.ac.in.visvesmruti2018.activities;

import android.Manifest;
import android.graphics.Color;
import android.graphics.Point;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v4.view.ViewPager;
import android.support.v7.app.ActionBar;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.TextView;

import com.github.amlcurran.showcaseview.ShowcaseView;
import com.github.amlcurran.showcaseview.targets.PointTarget;
import com.master.permissionhelper.PermissionHelper;

import fetr.ac.in.visvesmruti2018.utils.Prefs;
import fetr.ac.in.visvesmruti2018.R;
import fetr.ac.in.visvesmruti2018.adapters.HomePagerAdapter;
import fetr.ac.in.visvesmruti2018.fragments.ContentFragment;
import fetr.ac.in.visvesmruti2018.fragments.LeftMenuFragment;
import fetr.ac.in.visvesmruti2018.fragments.RightMenuFragment;

public class VSActivity extends BaseActivity {

    ViewPager viewPager;
    HomePagerAdapter myPagerAdapter;
    PermissionHelper permissionHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vs);
        setUpToolbar();
        viewPager = (ViewPager)findViewById(R.id.viewpager);

        myPagerAdapter = new HomePagerAdapter(getSupportFragmentManager());

        myPagerAdapter.addFragments(new LeftMenuFragment());
        myPagerAdapter.addFragments(new ContentFragment());
        myPagerAdapter.addFragments(new RightMenuFragment());

        viewPager.setPageTransformer(true, new ViewPager.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                //Hiding those pages which are way off-screen to the left or to the right.
                if (position < -1) {
                    page.setAlpha(0);
                } else if (position > 1) {
                    page.setAlpha(0);
                } else {
                    page.setAlpha(1);
                }

                if (page.getId() == R.id.menuFirst){
                    //putting fragment to the start of the screen
                    page.setTranslationX(-position*page.getWidth());
                } else if (page.getId() == R.id.contentPage){
                    //when we swipe to left this code applied
                    if (position <= 0){
                        //first we do scaling to 50%
                        float scale = Math.max(0.6f,1-Math.abs(position));
                        page.setScaleX(scale);
                        page.setScaleY(scale);

                        float deltaWidth = page.getWidth() - scale*page.getWidth();

                        //place the fragment to the start of the screen and move the fragment to left
                        page.setTranslationX(-position*page.getWidth() - deltaWidth/2);
                    }
                    //when we swipe to right this code applied
                    else if (position > 0){
                        //first we do scaling to 50%
                        float scale = Math.max(0.6f,1-Math.abs(position));
                        page.setScaleX(scale);
                        page.setScaleY(scale);

                        float deltaWidth = page.getWidth() - scale*page.getWidth();

                        //place the fragment to the start of the screen and move the fragment to right
                        page.setTranslationX(-position*page.getWidth() + deltaWidth/2);
                    }
                }
                else if (page.getId() == R.id.menuSecond){
                    //putting fragment to the start of the screen
                    page.setTranslationX(-position*page.getWidth());
                }
            }
        });
        viewPager.setAdapter(myPagerAdapter);
        viewPager.setCurrentItem(1);

        permissionHelper = new PermissionHelper(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE, Manifest.permission.ACCESS_FINE_LOCATION}, 100);
        permissionHelper.request(new PermissionHelper.PermissionCallback() {
            @Override
            public void onPermissionGranted() {}
            @Override
            public void onIndividualPermissionGranted(String[] grantedPermission) {}
            @Override
            public void onPermissionDenied() {}
            @Override
            public void onPermissionDeniedBySystem() {}
        });

        Showcase();
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (permissionHelper != null) {
            permissionHelper.onRequestPermissionsResult(requestCode, permissions, grantResults);
        }
    }

    @Override
    public void onBackPressed() {
        if(viewPager.getCurrentItem() != 1){
            viewPager.setCurrentItem(1);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_vs, menu);
        return super.onCreateOptionsMenu(menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_menu_right:
                viewPager.setCurrentItem(2);
                break;
            case android.R.id.home:
                viewPager.setCurrentItem(0);
                break;
        }
        return true;
    }

    private void setUpToolbar(){
        final Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);

        if (toolbar != null) {
            toolbar.setTitle(getTitle());
            toolbar.setTitleTextColor(Color.WHITE);
            toolbar.setNavigationIcon(android.R.drawable.arrow_down_float);
            toolbar.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    for (int i = 0; i < toolbar.getChildCount(); i++) {
                        View child = toolbar.getChildAt(i);
                        if (child instanceof TextView) {
                            TextView tv = ((TextView) child);
                            if(tv.getText().equals(toolbar.getTitle())) {
                                DisplayMetrics metrics = getResources().getDisplayMetrics();
                                Toolbar.LayoutParams txvPars = (Toolbar.LayoutParams) tv.getLayoutParams();
                                txvPars.gravity = Gravity.CENTER;
                                txvPars.width = Toolbar.LayoutParams.MATCH_PARENT;
                                tv.setLayoutParams(txvPars);
                                tv.setGravity(Gravity.CENTER_HORIZONTAL);
                            }
                        }
                    }
                    toolbar.getViewTreeObserver().removeOnGlobalLayoutListener(this);
                }
            });
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

    public void showPage(View view){
        viewPager.setCurrentItem(1);
    }

    private Point getScreenPoint() {
        Point size = new Point();
        getWindowManager().getDefaultDisplay().getSize(size);
        return size;
    }

    private Point getViewPoint(boolean left) {
        Point size = getScreenPoint();
        if(left) {
            size.x = 0;
        }
        size.y = (size.y/2);
        return size;
    }

    private void Showcase(){
        Prefs prefs = Prefs.with(this);
        if(!prefs.readBoolean("tut_isShow",false)) {
            final ShowcaseView showcaseView = new ShowcaseView.Builder(this)
                    .setTarget(new PointTarget(getViewPoint(true)))
                    .setContentText("Swipe to left for Left Menu!")
                    .build();

            final ShowcaseView.Builder showcaseView2 = new ShowcaseView.Builder(this)
                    .setTarget(new PointTarget(getViewPoint(false)))
                    .setContentText("Swipe to right for Right Menu!");
            showcaseView.overrideButtonClick(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    showcaseView.hide();
                    showcaseView2.build();
                }
            });
            prefs.writeBoolean("tut_isShow",true);
        }
    }
}
