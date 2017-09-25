package com.octo.ioni.miudotko.Models;

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
    String playersJSON;

    public Match(){
        initValues();
    }

    public Match(long id){
        initValues();
        this.match_id = id;
    }

    public Match(long id, long match_seq_num,int start_time,int lobby_type,int radiant_team_id,int dire_team_id, String playersJSON){
        this.match_id = id;
        this.match_seq_num = match_seq_num;
        this.start_time = start_time;
        this.lobby_type = lobby_type;
        this.radiant_team_id = radiant_team_id;
        this.dire_team_id = dire_team_id;
        this.playersJSON = playersJSON;
    }

    private void initValues(){
        this.match_id = -1;
        this.match_seq_num = -1;
        this.start_time = -1;
        this.lobby_type = -1;
        this.radiant_team_id = -1;
        this.dire_team_id = -1;
        this.playersJSON = "";
    }

    //
    //  GETTERS!
    //
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

    public int getStart_time() {
        return start_time;
    }

    public int getLobby_type() {
        return lobby_type;
    }

    public int getRadiant_team_id() {
        return radiant_team_id;
    }

    public int getDire_team_id() {
        return dire_team_id;
    }

    public String getPlayersJSON() {
        return playersJSON;
    }

    //
    //  SETTERS!
    //
    public void setMatch_id(long match_id) {
        this.match_id = match_id;
    }

    public void setMatch_seq_num(long match_seq_num) {
        this.match_seq_num = match_seq_num;
    }

    public void setStart_time(int start_time) {
        this.start_time = start_time;
    }

    public void setLobby_type(int lobby_type) {
        this.lobby_type = lobby_type;
    }

    public void setRadiant_team_id(int radiant_team_id) {
        this.radiant_team_id = radiant_team_id;
    }

    public void setDire_team_id(int dire_team_id) {
        this.dire_team_id = dire_team_id;
    }

    public void setPlayersJSON(String playersJSON) {
        this.playersJSON = playersJSON;
    }
}
