package fetr.ac.in.visvesmruti2018.activities;

import android.content.Intent;
import android.os.Bundle;
import android.webkit.WebView;

import fetr.ac.in.visvesmruti2018.R;

public class EventPageActivity extends BaseActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_event_page);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        Intent intent = getIntent();
        if(intent != null) {
            setTitle(intent.getStringExtra("name"));
            WebView wv = (WebView) findViewById(R.id.webview);
            wv.clearCache(true);
            wv.loadUrl("file:///android_asset/events/" + intent.getStringExtra("webpage"));
        } else {
            finish();
        }
    }
}
