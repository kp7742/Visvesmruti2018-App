package fetr.ac.in.visvesmruti2018.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import fetr.ac.in.visvesmruti2018.R;
import fetr.ac.in.visvesmruti2018.activities.AboutActivity;
import fetr.ac.in.visvesmruti2018.activities.CodersActivity;
import fetr.ac.in.visvesmruti2018.activities.SponsorActivity;

public class RightMenuFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_right, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button share = (Button) view.findViewById(R.id.shareButton);
        Button about = (Button) view.findViewById(R.id.aboutButton);
        Button report = (Button) view.findViewById(R.id.reportButton);
        Button coders = (Button) view.findViewById(R.id.devButton);
        Button sponsor = (Button) view.findViewById(R.id.sponsorButton);

        share.setOnClickListener(this);
        about.setOnClickListener(this);
        report.setOnClickListener(this);
        coders.setOnClickListener(this);
        sponsor.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.shareButton:
                final String string = "Hey, do you like Visvesmruti App but don't know where to download?, then Download Now from Here:- http://visvesmruti18.fetr.ac.in/";
                final Intent intent3 = new Intent("android.intent.action.SEND");
                intent3.setType("text/plain");
                intent3.putExtra("android.intent.extra.SUBJECT", "Visvesmruti");
                intent3.putExtra("android.intent.extra.TEXT", string);
                startActivity(Intent.createChooser(intent3, "Share"));
                break;
            case R.id.aboutButton:
                startActivity(new Intent(getActivity(), AboutActivity.class));
                break;
            case R.id.reportButton:
                final Intent intent2 = new Intent("android.intent.action.SEND");
                intent2.setType("message/rfc822");
                intent2.putExtra("android.intent.extra.EMAIL", new String[]{"patel.kuldip91@gmail.com"});
                intent2.putExtra("android.intent.extra.SUBJECT", "Visvesmruti 2018 App");
                intent2.putExtra("android.intent.extra.TEXT", "");
                try {
                    startActivity(Intent.createChooser(intent2, "Report..."));
                } catch (Exception ex) {
                    Toast.makeText(getActivity(), "Can't find email client.", Toast.LENGTH_SHORT).show();
                }
                break;
            case R.id.devButton:
                startActivity(new Intent(getActivity(), CodersActivity.class));
                break;
            case R.id.sponsorButton:
                startActivity(new Intent(getActivity(), SponsorActivity.class));
                break;
        }
    }
}