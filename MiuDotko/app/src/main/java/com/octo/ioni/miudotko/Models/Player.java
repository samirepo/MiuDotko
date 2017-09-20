package com.octo.ioni.miudotko.Models;

/**
 * Created by ioni on 9/19/17.
 */

public class Player {
    int account_id;
    int player_slot;
    int hero_id;

    Player(int account_id, int player_slot,int hero_id){
        this.account_id = account_id;
        this.player_slot = player_slot;
        this.hero_id = hero_id;
    }
}
