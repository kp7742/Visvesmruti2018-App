package fetr.ac.in.visvesmruti2018.fragments;

import android.graphics.Typeface;
import android.os.Bundle;
import android.os.Handler;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

import fetr.ac.in.visvesmruti2018.R;
import fetr.ac.in.visvesmruti2018.VSApp;

public class ContentFragment extends Fragment {

    private String EVENT_DATE_TIME = "2018-09-14 08:00:00";
    private String DATE_FORMAT = "yyyy-MM-dd HH:mm:ss";
    private Handler handler = new Handler();
    private Runnable runnable;

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_content, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        TextView vsname = view.findViewById(R.id.vsname);
        Typeface font = Typeface.createFromAsset(VSApp.getContext().getAssets(), "fonts/CinzelDecorative-Regular.ttf");
        vsname.setTypeface(font);
        vsname.setText("Visvesmruti");

        countDownStart(view);
    }

    private void countDownStart(View v) {
        final TextView countdown = v.findViewById(R.id.countdown);
        runnable = new Runnable() {
            @Override
            public void run() {
                try {
                    handler.postDelayed(this, 1000);
                    SimpleDateFormat dateFormat = new SimpleDateFormat(DATE_FORMAT, Locale.getDefault());
                    Date event_date = dateFormat.parse(EVENT_DATE_TIME);
                    Date current_date = new Date();
                    if (!current_date.after(event_date)) {
                        long diff = event_date.getTime() - current_date.getTime();
                        long Days = diff / (24 * 60 * 60 * 1000);
                        long Hours = diff / (60 * 60 * 1000) % 24;
                        long Minutes = diff / (60 * 1000) % 60;
                        long Seconds = diff / 1000 % 60;

                        String countTime = String.format(Locale.getDefault(),"%dD %02dH %02dM %02dS", Days, Hours, Minutes, Seconds);
                        countdown.setText(countTime);
                    } else {
                        countdown.setText("Welcome!");
                        handler.removeCallbacks(runnable);
                    }
                } catch (Exception e) {
                    handler.postDelayed(this, 0);
                }
            }
        };
        handler.postDelayed(runnable, 0);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        handler.removeCallbacks(runnable);
    }

    @Override
    public void onResume() {
        super.onResume();
        handler.postDelayed(runnable, 0);
    }
}