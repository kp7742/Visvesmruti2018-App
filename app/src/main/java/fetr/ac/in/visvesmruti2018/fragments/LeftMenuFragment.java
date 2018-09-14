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

import fetr.ac.in.visvesmruti2018.R;
import fetr.ac.in.visvesmruti2018.activities.ContactActivity;
import fetr.ac.in.visvesmruti2018.activities.EventsActivity;
import fetr.ac.in.visvesmruti2018.activities.GalleryActivity;
import fetr.ac.in.visvesmruti2018.activities.MapsActivity;
import fetr.ac.in.visvesmruti2018.activities.TimeTableActivity;
import fetr.ac.in.visvesmruti2018.activities.WebActivity;

public class LeftMenuFragment extends Fragment implements View.OnClickListener {

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_menu_left, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        Button maps = (Button) view.findViewById(R.id.mapsButton);
        Button contact = (Button) view.findViewById(R.id.contactButton);
        Button timetable = (Button) view.findViewById(R.id.timeButton);
        Button events = (Button) view.findViewById(R.id.eventButton);
        Button website = (Button) view.findViewById(R.id.websiteButton);

        maps.setOnClickListener(this);
        contact.setOnClickListener(this);
        timetable.setOnClickListener(this);
        events.setOnClickListener(this);
        website.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.mapsButton:
                startActivity(new Intent(getActivity(), MapsActivity.class));
                break;
            case R.id.contactButton:
                startActivity(new Intent(getActivity(), ContactActivity.class));
                break;
            case R.id.timeButton:
                startActivity(new Intent(getActivity(), TimeTableActivity.class));
                break;
            case R.id.eventButton:
                startActivity(new Intent(getActivity(), EventsActivity.class));
                break;
            case R.id.websiteButton:
                startActivity(new Intent(getActivity(), WebActivity.class));
                break;
        }
    }
}