package com.octo.ioni.miudotko;

import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import com.octo.ioni.miudotko.Activities.DotkoActivity;
import com.octo.ioni.miudotko.Activities.DotkoRequestInterface;
import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.Utils.SteamURLController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends DotkoActivity {
    String TAG = "MainActivity";

    AppDataController appData;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        appData = new AppDataController(this);

        makeRequest(SteamURLController.url_matchHistoryLast25("Ioni"), new DotkoRequestInterface() {
            @Override
            public void callback(JSONObject response) {
                handleResponse(response);
            }
        });

        setupListView();
    }

    protected void setupListView(){
        final ListView listview = (ListView) findViewById(R.id.match_list_view);
        final MatchArrayAdapter adapter = new MatchArrayAdapter(this, appData.getAllMatches());
        listview.setAdapter(adapter);
    }

    private void handleResponse(JSONObject response){

        JSONObject result;
        ArrayList<Match> parsedMatches = new ArrayList<>();
        try {
            result = response.getJSONObject("result");
            parsedMatches = parseMatchesJSON(result.getJSONArray("matches"));
        } catch (JSONException e) {
            Log.e(TAG, "Json parsing error: " + e.getMessage());
        }

        for (Match match : parsedMatches){
            appData.saveMatch(match);
        }

        setupListView();
    }

    private ArrayList<Match> parseMatchesJSON(JSONArray matchArray){
        ArrayList<Match> matches = new ArrayList<>();

        for (int i=0; i < matchArray.length(); i++)
        {
            try {
                JSONObject match = matchArray.getJSONObject(i);

                long matchID = match.getLong("match_id");
                long matchSEG = match.getLong("match_seq_num");
                int matchStart = match.getInt("start_time");
                int matchLobby = match.getInt("lobby_type");
                int matchRadiant = match.getInt("radiant_team_id");
                int matchDire = match.getInt("dire_team_id");
                String matchPlayersJSON = match.getString("players");
                Log.d(TAG, "Found Match: " + matchID + ", and Seg: " + matchSEG);

                appData.saveMatch(new Match(matchID, matchSEG, matchStart, matchLobby, matchRadiant, matchDire, matchPlayersJSON));

            } catch (JSONException e) {
                Log.e(TAG + ": parseErr", "Json parsing error: " + e.getMessage());
            }
        }

        return matches;
    }

    protected void requestUserName(long accountID32){
        long accId64 = accountID32 + 76561197960265728L;
        makeRequest(SteamURLController.url_playerSummaries(Long.toString(accId64)), new DotkoRequestInterface() {
            @Override
            public void callback(JSONObject response) {
                Log.d(TAG, "Profile: " + response);
            }
        });
    }
}
