package com.octo.ioni.miudotko.Models;

import java.util.ArrayList;

/**
 * Created by ioni on 9/21/17.
 */

public class MatchDetails {

    boolean radiant_win;
    int duration;
    int pre_game_duration;
    long start_time;
    long match_id;
    long match_seq_num;
    int tower_status_radiant;
    int tower_status_dire;
    int barracks_status_radiant;
    int barracks_status_dire;
    int cluster;
    int first_blood_time;
    int lobby_type;
    int human_players;
    int leagueid;
    int positive_votes;
    int negative_votes;
    int game_mode;
    int flags;
    int engine;
    int radiant_score;
    int dire_score;
    ArrayList<Player> players;

    public MatchDetails(){

    }
    public MatchDetails(
            boolean radiant_win,
            int duration,
            int pre_game_duration,
            long start_time,
            long match_id,
            long match_seq_num,
            int tower_status_radiant,
            int tower_status_dire,
            int barracks_status_radiant,
            int barracks_status_dire,
            int cluster,
            int first_blood_time,
            int lobby_type,
            int human_players,
            int leagueid,
            int positive_votes,
            int negative_votes,
            int game_mode,
            int flags,
            int engine,
            int radiant_score,
            int dire_score,
            ArrayList<Player> players){
        this.radiant_win = radiant_win;
        this.duration = duration;
        this.pre_game_duration = pre_game_duration;
        this.start_time = start_time;
        this.match_id = match_id;
        this.match_seq_num = match_seq_num;
        this.tower_status_radiant = tower_status_radiant;
        this.tower_status_dire = tower_status_dire;
        this.barracks_status_radiant = barracks_status_radiant;
        this.barracks_status_dire = barracks_status_dire;
        this.cluster = cluster;
        this.first_blood_time = first_blood_time;
        this.lobby_type = lobby_type;
        this.human_players = human_players;
        this.leagueid = leagueid;
        this.positive_votes = positive_votes;
        this.negative_votes = negative_votes;
        this.game_mode = game_mode;
        this.flags = flags;
        this.engine = engine;
        this.radiant_score = radiant_score;
        this.dire_score = dire_score;
        this.players = players;
    }

    public String getMatch_id() {
        return Long.toString(this.match_id) ;
    }

    public ArrayList<Player> getPlayers() {
        return players;
    }

    public Player getPlayer(int index) {
        return players.get(index);
    }

    public int getDire_score() {
        return dire_score;
    }
}
