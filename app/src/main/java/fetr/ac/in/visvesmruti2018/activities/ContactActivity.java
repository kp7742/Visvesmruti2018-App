package fetr.ac.in.visvesmruti2018.activities;

import android.os.Bundle;
import android.support.annotation.Nullable;

import fetr.ac.in.visvesmruti2018.R;

public class ContactActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_contact);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }
}
