package com.nhpatt.myconference;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nhpatt.myconference.entities.Talk;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Gamarra
 */
public class TalksAdapter extends RecyclerView.Adapter<TalksAdapter.ViewHolder> {

    private List<Talk> talks = new ArrayList<>();

    public TalksAdapter(List<Talk> talks) {
        this.talks = talks;
    }


    @Override
    public TalksAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.talk_item, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(TalksAdapter.ViewHolder viewHolder, int i) {
        viewHolder.bind(talks.get(i));
    }

    @Override
    public int getItemCount() {
        return talks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {

        private final TextView title;
        private final TextView room;

        public ViewHolder(View itemView) {
            super(itemView);

            title = (TextView) itemView.findViewById(R.id.talk_title);
            room = (TextView) itemView.findViewById(R.id.talk_room);
        }

        public void bind(Talk talk) {
            title.setText(talk.getTitle());
            room.setText(talk.getRoom());
        }
    }
}
