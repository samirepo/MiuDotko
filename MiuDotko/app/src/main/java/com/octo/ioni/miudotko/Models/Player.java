package com.octo.ioni.miudotko.Models;

/**
 * Created by ioni on 9/19/17.
 */

public class Player {
    long account_id;
    int player_slot;
    int hero_id;
    int item_0;
    int item_1;
    int item_2;
    int item_3;
    int item_4;
    int item_5;
    int backpack_0;
    int backpack_1;
    int backpack_2;
    int kills;
    int deaths;
    int assists;
    int leaver_status;
    int last_hits;
    int denies;
    int gold_per_min;
    int xp_per_min;
    int level;
    int hero_damage;
    int tower_damage;
    int hero_healing;
    int gold;
    int gold_spent;
    int scaled_hero_damage;
    int scaled_tower_damage;
    int scaled_hero_healing;
        //TODO! abis
        //int ability_upgrades;
        /*"ability_upgrades": [
            {
            "ability": 5190,
                "time": 146,
                "level": 1
            }
        ]*/
    public Player(
            long account_id,
            int player_slot,
            int hero_id,
            int item_0,
            int item_1,
            int item_2,
            int item_3,
            int item_4,
            int item_5,
            int backpack_0,
            int backpack_1,
            int backpack_2,
            int kills,
            int deaths,
            int assists,
            int leaver_status,
            int last_hits,
            int denies,
            int gold_per_min,
            int xp_per_min,
            int level,
            int hero_damage,
            int tower_damage,
            int hero_healing,
            int gold,
            int gold_spent,
            int scaled_hero_damage,
            int scaled_tower_damage,
            int scaled_hero_healing){
        this.account_id = account_id;
        this.player_slot = player_slot;
        this.hero_id = hero_id;
        this.item_0 = item_0;
        this.item_1 = item_1;
        this.item_2 = item_2;
        this.item_3 = item_3;
        this.item_4 = item_4;
        this.item_5 = item_5;
        this.backpack_0 = backpack_0;
        this.backpack_1 = backpack_1;
        this.backpack_2 = backpack_2;
        this.kills = kills;
        this.deaths = deaths;
        this.assists = assists;
        this.leaver_status = leaver_status;
        this.last_hits = last_hits;
        this.denies = denies;
        this.gold_per_min = gold_per_min;
        this.xp_per_min = xp_per_min;
        this.level = level;
        this.hero_damage = hero_damage;
        this.tower_damage = tower_damage;
        this.hero_healing = hero_healing;
        this.gold = gold;
        this.gold_spent = gold_spent;
        this.scaled_hero_damage = scaled_hero_damage;
        this.scaled_tower_damage = scaled_tower_damage;
        this.scaled_hero_healing = scaled_hero_healing;
    }

    public int getDeaths() {
        return deaths;
    }

    public long getAccount_id() {
        return account_id;
    }
}
