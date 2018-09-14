package fetr.ac.in.visvesmruti2018.fragments;

import android.graphics.drawable.Drawable;
import android.os.Build;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.github.florent37.materialviewpager.header.MaterialViewPagerHeaderDecorator;

import java.util.ArrayList;
import java.util.List;

import fetr.ac.in.visvesmruti2018.R;
import fetr.ac.in.visvesmruti2018.adapters.EventRecyclerViewAdapter;
import fetr.ac.in.visvesmruti2018.models.EventModel;

public class EventRecyclerViewFragment extends Fragment {

    private static final boolean GRID_LAYOUT = true;
    private int pos = 0;

    RecyclerView mRecyclerView;
    List<EventModel> items = new ArrayList<>();

    public static EventRecyclerViewFragment newInstance(int position) {
        Bundle bundle = new Bundle();
        bundle.putInt("pos", position);
        EventRecyclerViewFragment object = new EventRecyclerViewFragment();
        object.setArguments(bundle);
        return object;
    }

    private void readBundle(Bundle bundle) {
        if (bundle != null) {
            pos = bundle.getInt("pos");
        }
    }

    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_recyclerview, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        mRecyclerView = view.findViewById(R.id.recyclerView);

        items.clear();
        readBundle(getArguments());

        switch (pos) {
            case 0:
                items.add(new EventModel("Paper Presentation","computer/paper.html",getDrawable(R.drawable.paper)));
                items.add(new EventModel("Poster Presentation","computer/poster.html",getDrawable(R.drawable.poster)));
                items.add(new EventModel("Programing Date","computer/program_date.html",getDrawable(R.drawable.programming)));
                items.add(new EventModel("Web Design","computer/web_design.html",getDrawable(R.drawable.web)));
                items.add(new EventModel("Virtual Placement","computer/virtual.html",getDrawable(R.drawable.virtual)));
                items.add(new EventModel("Quiz","computer/quiz.html",getDrawable(R.drawable.snakeladders)));
                break;
            case 1:
                items.add(new EventModel("Paper Presentation","civil/paper.html",getDrawable(R.drawable.paper)));
                items.add(new EventModel("Poster Presentation","civil/poster.html",getDrawable(R.drawable.poster)));
                items.add(new EventModel("Model Presentation","civil/model.html",getDrawable(R.drawable.model)));
                items.add(new EventModel("Reveal ME","civil/reveal_me.html",getDrawable(R.drawable.reveal_me)));
                items.add(new EventModel("Bridge Structure","civil/bridge.html",getDrawable(R.drawable.bridge)));
                items.add(new EventModel("Photo Mania","civil/photo.html",getDrawable(R.drawable.photom)));
                break;
            case 2:
                items.add(new EventModel("Chem O Car","chemical/Chem_o_car.html",getDrawable(R.drawable.chemocar)));
                items.add(new EventModel("Chem O Quiz","chemical/Chem_o_quiz.html",getDrawable(R.drawable.chem_o_quiz)));
                items.add(new EventModel("Contraption","chemical/contraption.html",getDrawable(R.drawable.contraption)));
                items.add(new EventModel("Dexters Laboratory","chemical/dexters.html",getDrawable(R.drawable.dexters_lab)));
                items.add(new EventModel("Model Presentation","chemical/model.html",getDrawable(R.drawable.model)));
                items.add(new EventModel("Paper Presentation","chemical/paper.html",getDrawable(R.drawable.paper)));
                items.add(new EventModel("Poster Presentation","chemical/poster.html",getDrawable(R.drawable.poster)));
                items.add(new EventModel("Corrosion","chemical/corrosion.html",getDrawable(R.drawable.corrosion)));
                items.add(new EventModel("Chem O missile","chemical/Chem_o_missile.html",getDrawable(R.drawable.chemomissile)));
                break;
            case 3:
                items.add(new EventModel("Paper Presentation","mechanical/paper.html",getDrawable(R.drawable.paper)));
                items.add(new EventModel("Poster Presentation","mechanical/poster.html",getDrawable(R.drawable.poster)));
                items.add(new EventModel("Model Presentation","mechanical/model.html",getDrawable(R.drawable.model)));
                items.add(new EventModel("Junkyard War","mechanical/junkyard.html",getDrawable(R.drawable.junkyard_war)));
                items.add(new EventModel("Arcania","mechanical/arcania.html",getDrawable(R.drawable.arcania)));
                items.add(new EventModel("Lathe War","mechanical/lathe_war.html",getDrawable(R.drawable.lathe_war)));
                break;
            case 4:
                items.add(new EventModel("Paper Presentation","electrical/paper.html",getDrawable(R.drawable.paper)));
                items.add(new EventModel("Poster Presentation","electrical/poster.html",getDrawable(R.drawable.poster)));
                items.add(new EventModel("Model Presentation","electrical/model.html",getDrawable(R.drawable.model)));
                items.add(new EventModel("E Google","electrical/egoogle.html",getDrawable(R.drawable.e_google)));
                items.add(new EventModel("Robo Soccer","electrical/robo_soccer.html",getDrawable(R.drawable.robo_soccer)));
                items.add(new EventModel("Robo Climber","electrical/robo_climber.html",getDrawable(R.drawable.robo_climber)));
                break;
            case 5:
                items.add(new EventModel("Elocution","sciandhum/elocution.html",getDrawable(R.drawable.elocution)));
                items.add(new EventModel("Laser Maniac","sciandhum/laser.html",getDrawable(R.drawable.laser_mania)));
                items.add(new EventModel("Musing Fizik","sciandhum/fizik.html",getDrawable(R.drawable.fizik)));
                items.add(new EventModel("Quiz","sciandhum/quiz.html",getDrawable(R.drawable.quiz)));
                break;
        }

        if (GRID_LAYOUT) {
            mRecyclerView.setLayoutManager(new GridLayoutManager(getActivity(), 2));
        } else {
            mRecyclerView.setLayoutManager(new LinearLayoutManager(getActivity()));
        }
        mRecyclerView.setHasFixedSize(true);

        mRecyclerView.addItemDecoration(new MaterialViewPagerHeaderDecorator());
        mRecyclerView.setAdapter(new EventRecyclerViewAdapter(items,getActivity()));
    }

    private Drawable getDrawable(int id){
        if(Build.VERSION.SDK_INT >= 22){
            return getResources().getDrawable(id,null);
        } else {
            return getResources().getDrawable(id);
        }
    }
}
