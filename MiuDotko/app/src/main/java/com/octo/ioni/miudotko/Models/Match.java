package com.octo.ioni.miudotko.Models;

import java.util.ArrayList;

/**
 * Created by ioni on 9/19/17.
 */

public class Match {

    long match_id;
    long match_seq_num;
    int start_time;
    int lobby_type;
    int radiant_team_id;
    int dire_team_id;

    ArrayList<Player> players;

    public Match(long id, long match_seq_num,int start_time,int lobby_type,int radiant_team_id,int dire_team_id, ArrayList<Player> players){
        this.match_id = id;
        this.match_seq_num = match_seq_num;
        this.start_time = start_time;
        this.lobby_type = lobby_type;
        this.radiant_team_id = radiant_team_id;
        this.dire_team_id = dire_team_id;
        this.players = players;
    }

    public String getMatch_id() {
        return Long.toString(this.match_id) ;
    }

    public Long getMatch_id_long() {
        return this.match_id;
    }

    public String getMatch_seg() {
        return Long.toString(this.match_seq_num) ;
    }

    public Long getMatch_seg_long() {
        return this.match_seq_num;
    }
}
