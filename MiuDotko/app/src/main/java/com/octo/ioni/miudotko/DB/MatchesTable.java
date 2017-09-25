package com.octo.ioni.miudotko.DB;

import android.provider.BaseColumns;

import com.octo.ioni.miudotko.Models.Match;

import java.util.ArrayList;

/**
 * Created by ioni on 9/22/17.
 */

public class MatchesTable implements BaseColumns {

    // Contacts table name
    public static final String TABLE_MATCHES = "matches";

    // Contacts Table Columns names
    public static final String KEY_MATCH_ID = "match_id";
    public static final String KEY_MATCH_SEQ = "match_seq_num";
    public static final String KEY_MATCH_START = "start_time";
    public static final String KEY_MATCH_LOBBY = "lobby_type";
    public static final String KEY_MATCH_RADIANT = "radiant_team_id";
    public static final String KEY_MATCH_DIRE = "dire_team_id";
    public static final String KEY_MATCH_PLAYERS = "players";

    public static final String CREATE_MATCHES_TABLE = "CREATE TABLE " + TABLE_MATCHES + "("
            + KEY_MATCH_ID + " INTEGER PRIMARY KEY,"
            + KEY_MATCH_SEQ + " INTEGER,"
            + KEY_MATCH_START + " INTEGER,"
            + KEY_MATCH_LOBBY + " INTEGER,"
            + KEY_MATCH_RADIANT + " INTEGER,"
            + KEY_MATCH_DIRE + " INTEGER,"
            + KEY_MATCH_PLAYERS + " TEXT"
            + ")";
    public static final String DROP_QUERY = "drop table " + TABLE_MATCHES;
    public static final String SElECT_QUERY = "select * from " + TABLE_MATCHES;

    public static ArrayList<Match> seedData(){
        ArrayList<Match> matches = new ArrayList<>();
        matches.add(new Match(141114));
        matches.add(new Match(150010));
        matches.add(new Match(14122114));
        matches.add(new Match(1533000));
        return matches;
    }
}