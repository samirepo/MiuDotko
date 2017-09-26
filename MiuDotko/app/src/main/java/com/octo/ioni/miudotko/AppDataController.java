package com.octo.ioni.miudotko;

import android.content.Context;
import android.util.Log;

import com.octo.ioni.miudotko.DB.DotkoDBHelper;
import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.Models.MatchPlayer;
import com.octo.ioni.miudotko.Models.Player;
import com.octo.ioni.miudotko.Utils.Utils;

import java.util.ArrayList;

/**
 * Created by ioni on 9/21/17.
 */

public class AppDataController {

    DotkoDBHelper dbHelper;
    long AccountID32 = -1L;

    public AppDataController(Context context){
        dbHelper = new DotkoDBHelper(context);
    }

    public ArrayList<Match> getAllMatches(){
        return dbHelper.getAllMatches();
    }

    public ArrayList<Player> getAllPlayers(){
        return dbHelper.getAllPlayers();
    }

    public Match getMatch(int id){
        return dbHelper.getMatch(id);
    }

    public String steamIDsInMatch(long id){
        String IDs = "";
        for (MatchPlayer player : dbHelper.playersInMatch(id)){
            IDs = IDs + Utils.steamID_from32to64(player.getAccount_id()) + ",";
            //IDs = IDs + player.getAccount_id() + ",";
        }
        return IDs;
    }

    public Player getPlayer(int accountID){
        return dbHelper.getPlayer(accountID);
    }

    public void savePlayer(Player player){
        boolean success = dbHelper.addPlayer(player);
        Log.d("AppDataController", "Saving MATCH into DataBase... Operation was success: " + success);
    }

    public void saveMatch(Match match){
        boolean success = dbHelper.addMatch(match);
        Log.d("AppDataController", "Saving MATCH into DataBase... Operation was success: " + success);
    }
}
