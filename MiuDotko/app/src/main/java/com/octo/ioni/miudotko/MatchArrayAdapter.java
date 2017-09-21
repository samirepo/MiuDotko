package com.octo.ioni.miudotko;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.octo.ioni.miudotko.Activities.MatchActivity;
import com.octo.ioni.miudotko.Models.Match;

import java.util.ArrayList;

/**
 * Created by ioni on 9/20/17.
 */

public class MatchArrayAdapter extends ArrayAdapter<Match> {
    LayoutInflater mInflater;

    public MatchArrayAdapter(Context context, ArrayList<Match> matches) {
        super(context, 0, matches);
        this.mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        final MatchViewHolder holder;

        if (convertView == null) {
            convertView = mInflater.inflate(R.layout.match_list_item, null);
            holder = new MatchViewHolder();
            holder.idTextView = convertView.findViewById(R.id.textViewMatchID);
            holder.segTextView = convertView.findViewById(R.id.textViewSeqNum);
            holder.chooseButton = convertView.findViewById(R.id.chooseButton);
            holder.matchImageView = convertView.findViewById(R.id.matchImageView);
            convertView.setTag(holder);
        }
        else {
            holder = (MatchViewHolder) convertView.getTag();
        }

        Match match = getItem(position);
        Log.d("ArrayAdapter", "Showing Match: " + match.getMatch_id());

        holder.idTextView.setText(match.getMatch_id());
        holder.segTextView.setText(match.getMatch_seg());
        holder.chooseButton.setTag(match);
        holder.chooseButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Match matchClicked = (Match) view.getTag();
                Intent matchActivity = new Intent(getContext(), MatchActivity.class);
                matchActivity.putExtra("matchID", matchClicked.getMatch_id_long());
                matchActivity.putExtra("matchSeq", matchClicked.getMatch_seg_long());
                getContext().startActivity(matchActivity);
            }
        });

        return convertView;
    }
}