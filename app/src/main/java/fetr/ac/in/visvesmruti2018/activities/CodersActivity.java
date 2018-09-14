package fetr.ac.in.visvesmruti2018.activities;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

import fetr.ac.in.visvesmruti2018.R;

public class CodersActivity extends BaseActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_coders);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        ImageView github1 = (ImageView) findViewById(R.id.github);
        ImageView github2 = (ImageView) findViewById(R.id.github2);
        ImageView github3 = (ImageView) findViewById(R.id.github3);

        ImageView email1 = (ImageView) findViewById(R.id.email);
        ImageView email2 = (ImageView) findViewById(R.id.email2);
        ImageView email3 = (ImageView) findViewById(R.id.email3);

        github1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/kp7742")));
            }
        });
        email1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("message/rfc822");
                intent2.putExtra("android.intent.extra.EMAIL", new String[]{"patel.kuldip91@gmail.com"});
                intent2.putExtra("android.intent.extra.SUBJECT", "Hello Kuldip");
                intent2.putExtra("android.intent.extra.TEXT", "");
                try {
                    startActivity(Intent.createChooser(intent2, "Report..."));
                } catch (Exception ex) {
                    Toast.makeText(CodersActivity.this, "Can't find email client.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        github2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/Dhiren003")));
            }
        });
        email2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("message/rfc822");
                intent2.putExtra("android.intent.extra.EMAIL", new String[]{"dhiren.jummani.jhd@gmail.com"});
                intent2.putExtra("android.intent.extra.SUBJECT", "Hello Dhiren");
                intent2.putExtra("android.intent.extra.TEXT", "");
                try {
                    startActivity(Intent.createChooser(intent2, "Report..."));
                } catch (Exception ex) {
                    Toast.makeText(CodersActivity.this, "Can't find email client.", Toast.LENGTH_SHORT).show();
                }
            }
        });

        github3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent("android.intent.action.VIEW", Uri.parse("https://github.com/1111darsh")));
            }
        });
        email3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("message/rfc822");
                intent2.putExtra("android.intent.extra.EMAIL", new String[]{"1111darsh@gmail.com"});
                intent2.putExtra("android.intent.extra.SUBJECT", "Hello Darshan");
                intent2.putExtra("android.intent.extra.TEXT", "");
                try {
                    startActivity(Intent.createChooser(intent2, "Report..."));
                } catch (Exception ex) {
                    Toast.makeText(CodersActivity.this, "Can't find email client.", Toast.LENGTH_SHORT).show();
                }
            }
        });
    }
}
