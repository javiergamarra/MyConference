package com.nhpatt.myconference.activities;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.nhpatt.myconference.R;
import com.nhpatt.myconference.entities.Talk;
import com.nhpatt.myconference.fragments.TalkClickListener;

import java.util.ArrayList;
import java.util.List;

/**
 * @author Javier Gamarra
 */
public class TalksAdapter extends RecyclerView.Adapter<TalksAdapter.ViewHolder> {

    private List<Talk> talks = new ArrayList<>();
    private TalkClickListener listener;

    public TalksAdapter(List<Talk> talks, TalkClickListener listener) {
        this.talks = talks;
        this.listener = listener;
    }

    @Override
    public TalksAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.talk_item, viewGroup, false);
        return new ViewHolder(view, listener);
    }

    @Override
    public void onBindViewHolder(TalksAdapter.ViewHolder viewHolder, int position) {
        viewHolder.bind(talks.get(position));
    }

    @Override
    public int getItemCount() {
        return talks.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private final TextView title;
        private final TextView room;
        private TalkClickListener listener;

        public ViewHolder(View itemView, TalkClickListener listener) {
            super(itemView);

            this.listener = listener;

            title = (TextView) itemView.findViewById(R.id.talk_title);
            room = (TextView) itemView.findViewById(R.id.talk_room);

            itemView.setOnClickListener(this);
        }

        public void bind(Talk talk) {
            title.setText(talk.getTitle());
            room.setText(talk.getRoom());
        }

        @Override
        public void onClick(View v) {
            listener.onClick(talks.get(getAdapterPosition()));
        }
    }
}
