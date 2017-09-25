package com.octo.ioni.miudotko.DB;

import android.provider.BaseColumns;

import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.Models.Player;

import java.util.ArrayList;

/**
 * Created by ioni on 9/25/17.
 */

public class PlayerTable implements BaseColumns {

    // Contacts table name
    public static final String TABLE_PLAYERS = "players";

    // Contacts Table Columns names
    public static final String KEY_ACCOUNT_ID =  "account_id";
    public static final String KEY_PLAYER_SLOT =  "player_slot";
    public static final String KEY_HERO_ID =  "hero_id";
    public static final String KEY_ITEM_0 = "item_0";
    public static final String KEY_ITEM_1 = "item_1";
    public static final String KEY_ITEM_2 = "item_2";
    public static final String KEY_ITEM_3 = "item_3";
    public static final String KEY_ITEM_4 = "item_4";
    public static final String KEY_ITEM_5 = "item_5";
    public static final String KEY_BACKPACk_0 =  "backpack_0";
    public static final String KEY_BACKPACk_1 =  "backpack_1";
    public static final String KEY_BACKPACk_2 =  "backpack_2";
    public static final String KEY_KILLS = "kills";
    public static final String KEY_DEATHS = "deaths";
    public static final String KEY_ASSISTS = "assists";
    public static final String KEY_LEAVER_STATUS = "leaver_status";
    public static final String KEY_LAST_HITS = "last_hits";
    public static final String KEY_DENIES = "denies";
    public static final String KEY_GPM = "gold_per_min";
    public static final String KEY_XPM = "xp_per_min";
    public static final String KEY_LEVEL = "level";
    public static final String KEY_HERO_DAMAGE = "hero_damage";
    public static final String KEY_TOWER_DAMAGE = "tower_damage";
    public static final String KEY_HEALING = "hero_healing";
    public static final String KEY_GOLD = "gold";
    public static final String KEY_GOLD_SPENT = "gold_spent";
    public static final String KEY_SCALED_HERO_DAMAGE = "scaled_hero_damage";
    public static final String KEY_SCALED_TOWER_DAMAGE = "scaled_tower_damage";
    public static final String KEY_SCALED_HERO_HEALING = "scaled_hero_healing";



    public static final String CREATE_PLAYERS_TABLE = "CREATE TABLE " + TABLE_PLAYERS + "("
            + KEY_ACCOUNT_ID + " INTEGER PRIMARY KEY,"
            + KEY_PLAYER_SLOT + " INTEGER,"
            + KEY_HERO_ID + " INTEGER,"
            + KEY_ITEM_0 + " INTEGER,"
            + KEY_ITEM_1 + " INTEGER,"
            + KEY_ITEM_2 + " INTEGER,"
            + KEY_ITEM_3 + " INTEGER,"
            + KEY_ITEM_4 + " INTEGER,"
            + KEY_ITEM_5 + " INTEGER,"
            + KEY_BACKPACk_0 + " INTEGER,"
            + KEY_BACKPACk_1 + " INTEGER,"
            + KEY_BACKPACk_2 + " INTEGER,"
            + KEY_KILLS + " INTEGER,"
            + KEY_DEATHS + " INTEGER,"
            + KEY_ASSISTS + " INTEGER,"
            + KEY_LEAVER_STATUS + " INTEGER,"
            + KEY_LAST_HITS + " INTEGER,"
            + KEY_DENIES + " INTEGER,"
            + KEY_GPM + " INTEGER,"
            + KEY_XPM + " INTEGER,"
            + KEY_LEVEL + " INTEGER,"
            + KEY_HERO_DAMAGE + " INTEGER,"
            + KEY_TOWER_DAMAGE + " INTEGER,"
            + KEY_HEALING + " INTEGER,"
            + KEY_GOLD + " INTEGER,"
            + KEY_GOLD_SPENT + " INTEGER,"
            + KEY_SCALED_HERO_DAMAGE + " INTEGER,"
            + KEY_SCALED_TOWER_DAMAGE + " INTEGER,"
            + KEY_SCALED_HERO_HEALING + " INTEGER"
            + ")";
    public static final String DROP_QUERY = "drop table " + TABLE_PLAYERS;
    public static final String SElECT_QUERY = "select * from " + TABLE_PLAYERS;

    public static ArrayList<Player> seedData(){
        ArrayList<Player> players = new ArrayList<>();
        players.add(new Player(25252525));
        players.add(new Player(141114));
        players.add(new Player(150010));
        players.add(new Player(14122114));
        players.add(new Player(1533000));
        return players;
    }
}