package com.octo.ioni.miudotko.Activities;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.Models.MatchDetails;
import com.octo.ioni.miudotko.Models.Player;
import com.octo.ioni.miudotko.Models.SteamAccount;
import com.octo.ioni.miudotko.R;
import com.octo.ioni.miudotko.Utils.SteamURLController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MatchActivity extends DotkoActivity {

    public static String playerSummaryUrl = "http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/";
    Long matchID = -1L;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        Long defVal = -1L;
        matchID = this.getIntent().getLongExtra("matchID", defVal);
        initView(matchID);

        makeRequest(SteamURLController.url_matchDetails(Long.toString(matchID)), new DotkoRequestInterface() {
            @Override
            public void callback(JSONObject response) {
                handleResponse(response);
            }
        });

        fetchPlayerINFO();
    }

    private void initView(Long id){
        TextView matchid = (TextView) findViewById(R.id.tw_match);
        TextView twrest = (TextView) findViewById(R.id.tw_rest);

        matchid.setText(Long.toString(id));
        twrest.setText("*NaN*");


        TextView direTW1 = (TextView) findViewById(R.id.tw_dire1);
        TextView direTW2 = (TextView) findViewById(R.id.tw_dire2);
        TextView direTW3 = (TextView) findViewById(R.id.tw_dire3);
        TextView direTW4 = (TextView) findViewById(R.id.tw_dire4);
        TextView direTW5 = (TextView) findViewById(R.id.tw_dire5);
        TextView radiantTW1 = (TextView) findViewById(R.id.tw_radiant1);
        TextView radiantTW2 = (TextView) findViewById(R.id.tw_radiant2);
        TextView radiantTW3 = (TextView) findViewById(R.id.tw_radiant3);
        TextView radiantTW4 = (TextView) findViewById(R.id.tw_radiant4);
        TextView radiantTW5 = (TextView) findViewById(R.id.tw_radiant5);

        direTW1.setText("NaN");
        direTW2.setText("NaN");
        direTW3.setText("NaN");
        direTW4.setText("NaN");
        direTW5.setText("NaN");
        radiantTW1.setText("NaN");
        radiantTW2.setText("NaN");
        radiantTW3.setText("NaN");
        radiantTW4.setText("NaN");
        radiantTW5.setText("NaN");
    }

    public void updatePlayerSummaries(ArrayList<SteamAccount> accounts){

    }

    private void populateData(MatchDetails mDetails){
        TextView matchid = (TextView) findViewById(R.id.tw_match);
        TextView twrest = (TextView) findViewById(R.id.tw_rest);

        matchid.setText(mDetails.getMatch_id());

        int direScore = mDetails.getDire_score();
        twrest.setText(Integer.toString(direScore));


    }


    protected boolean fetchPlayerINFO(){
        makeRequest(playerSummaryUrl + "?" + "steamids=" + appData.steamIDsInMatch(matchID) + "&key=8E68DFCFBBCC0DC11378E1AAB7068FA1", new DotkoRequestInterface() {
            @Override
            public void callback(JSONObject response) {
                Log.d(TAG, "GetPlayerSummaries: " + response);
                updatePlayerSummaries(parseAccounts(response));
            }
        });
        return true;
    }

    private void handleResponse(JSONObject response){
        Log.d(TAG + ": Response", response.toString());
        try {
            JSONObject result = response.getJSONObject("result");
            MatchDetails matchDetails = parseMatchDetails(result);

            populateData(matchDetails);
        } catch (JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
    }

    protected ArrayList<SteamAccount> parseAccounts(JSONObject response){
        ArrayList<SteamAccount> accounts = new ArrayList<>();
        try {
            JSONObject resp = response.getJSONObject("response");
            JSONArray accountsArray = resp.getJSONArray("players");
            for (int i = 0; i < accountsArray.length() - 1; i++){
                JSONObject account = accountsArray.getJSONObject(i);
                accounts.add(parseAccountFrom(account));
            }
        } catch (JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
        return accounts;
    }

    protected SteamAccount parseAccountFrom(JSONObject json){
        SteamAccount account = new SteamAccount();
        try {
            account = new SteamAccount(
                    json.getLong("steamid"),
                    json.getInt("communityvisibilitystate"),
                    json.getInt("profilestate"),
                    json.getString("personaname"),
                    json.getLong("lastlogoff"),
                    json.getString("profileurl"),
                    json.getString("avatar"),
                    json.getString("avatarmedium"),
                    json.getString("avatarfull"),
                    json.getInt("personastate"),
                    json.getString("realname"),
                    json.getString("primaryclanid"),
                    json.getLong("timecreated"),
                    json.getInt("personastateflags"),
                    json.getString("gameextrainfo"),
                    json.getString("gameid"),
                    json.getString("loccountrycode")
            );
        } catch (JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }
        return account;
    }

    private MatchDetails parseMatchDetails(JSONObject json){
        MatchDetails details = new MatchDetails();

        try {

            details = new MatchDetails(
                    json.getBoolean("radiant_win"),
                    json.getInt("duration"),
                    json.getInt("pre_game_duration"),
                    json.getLong("start_time"),
                    json.getLong("match_id"),
                    json.getLong("match_seq_num"),
                    json.getInt("tower_status_radiant"),
                    json.getInt("tower_status_dire"),
                    json.getInt("barracks_status_radiant"),
                    json.getInt("barracks_status_dire"),
                    json.getInt("cluster"),
                    json.getInt("first_blood_time"),
                    json.getInt("lobby_type"),
                    json.getInt("human_players"),
                    json.getInt("leagueid"),
                    json.getInt("positive_votes"),
                    json.getInt("negative_votes"),
                    json.getInt("game_mode"),
                    json.getInt("flags"),
                    json.getInt("engine"),
                    json.getInt("radiant_score"),
                    json.getInt("dire_score"),
                    parsePlayers(json.getJSONArray("players"))
                    );
        } catch (JSONException e) {
            Log.e(TAG + ": parseErr", "Json parsing error: " + e.getMessage());
        }

        return details;
    }

    protected ArrayList<Player> parsePlayers(JSONArray players){
        ArrayList<Player> playersList = new ArrayList<>();

        for (int i = 0; i < players.length() - 1; i++){
            try {
                JSONObject player = players.getJSONObject(i);

                Player playerObject = new Player(
                        player.getLong("account_id"),
                        player.getInt("player_slot"),
                        player.getInt("hero_id"),
                        player.getInt("item_0"),
                        player.getInt("item_1"),
                        player.getInt("item_2"),
                        player.getInt("item_3"),
                        player.getInt("item_4"),
                        player.getInt("item_5"),
                        player.getInt("backpack_0"),
                        player.getInt("backpack_1"),
                        player.getInt("backpack_2"),
                        player.getInt("kills"),
                        player.getInt("deaths"),
                        player.getInt("assists"),
                        player.getInt("leaver_status"),
                        player.getInt("last_hits"),
                        player.getInt("denies"),
                        player.getInt("gold_per_min"),
                        player.getInt("xp_per_min"),
                        player.getInt("level"),
                        player.getInt("hero_damage"),
                        player.getInt("tower_damage"),
                        player.getInt("hero_healing"),
                        player.getInt("gold"),
                        player.getInt("gold_spent"),
                        player.getInt("scaled_hero_damage"),
                        player.getInt("scaled_tower_damage"),
                        player.getInt("scaled_hero_healing")
                );
                playersList.add(playerObject);
            } catch (JSONException e) {
                Log.e(TAG + ": parseErr", "Json parsing error: " + e.getMessage());
            }
        }

        return playersList;
    }
}
