package com.octo.ioni.miudotko;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.octo.ioni.miudotko.Activities.DotkoActivity;
import com.octo.ioni.miudotko.Activities.DotkoRequestInterface;
import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.Models.Player;
import com.octo.ioni.miudotko.Utils.SteamURLController;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends DotkoActivity {
    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeRequest(SteamURLController.url_matchHistoryLast25("Ioni"), new DotkoRequestInterface() {
            @Override
            public void callback(JSONObject response) {
                handleResponse(response);
            }
        });
    }

    protected void setupListView(ArrayList<Match> matches){
        final ListView listview = (ListView) findViewById(R.id.match_list_view);
        final MatchArrayAdapter adapter = new MatchArrayAdapter(this, matches);
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
        if (parsedMatches.size() > 0) setupListView(parsedMatches);
    }

    private ArrayList<Match> parseMatchesJSON(JSONArray matchArray){
        ArrayList<Match> matches = new ArrayList<>();

        for (int i=0; i < matchArray.length(); i++)
        {
            try {
                JSONObject match = matchArray.getJSONObject(i);

                long matchID = match.getLong("match_id");
                long matchSEG = match.getLong("match_seq_num");
                Log.d(TAG, "Found Match: " + matchID + ", and Seg: " + matchSEG);


                matches.add(new Match(matchID, matchSEG, -1, -1, -1, -1, new ArrayList<Player>()) );

                /*for (int j = 0; j < match.getJSONArray("players").length(); j++){
                    JSONObject player = match.getJSONArray("players").getJSONObject(j);
                    long playerAccountID = player.getLong("account_id");
                    int playerSlotInMatch = player.getInt("player_slot");
                    int playerHeroID = player.getInt("hero_id");
                    requestUserName(playerAccountID);
                } */

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
