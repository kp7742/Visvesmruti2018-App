package fetr.ac.in.visvesmruti2018.adapters;

import android.app.Activity;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.List;

import fetr.ac.in.visvesmruti2018.R;
import fetr.ac.in.visvesmruti2018.activities.EventPageActivity;
import fetr.ac.in.visvesmruti2018.models.EventModel;

public class EventRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<EventModel> contents;
    //private int pos;
    private Activity act;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CELL = 1;

    public EventRecyclerViewAdapter(List<EventModel> contents, /*int position,*/ Activity activity) {
        this.contents = contents;
        //this.pos = position;
        this.act = activity;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = null;

        switch (viewType) {
            case TYPE_HEADER: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_big, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
            case TYPE_CELL: {
                view = LayoutInflater.from(parent.getContext())
                        .inflate(R.layout.list_item_card_small, parent, false);
                return new RecyclerView.ViewHolder(view) {
                };
            }
        }
        return null;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        View view = holder.itemView.findViewById(R.id.card_view);
        final EventModel eventModel = contents.get(position);
        view.setBackground(eventModel.getIcon());
        view.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(act, EventPageActivity.class);
                intent.putExtra("name", eventModel.getName());
                intent.putExtra("webpage", eventModel.getWebPage());
                act.startActivity(intent);
            }
        });
    }

    @Override
    public int getItemCount() {
        return contents.size();
    }
}
