package com.octo.ioni.miudotko.Activities;

import android.os.Bundle;
import android.util.Log;

import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.R;
import com.octo.ioni.miudotko.Utils.SteamURLController;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;

public class MatchActivity extends DotkoActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_match);

        makeRequest(SteamURLController.url_matchDetails(getExt), new DotkoRequestInterface() {
            @Override
            public void callback(JSONObject response) {
                handleResponse(response);
            }
        });
    }

    private void handleResponse(JSONObject response){
        Log.d(TAG + ": Response", response.toString());
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
}
