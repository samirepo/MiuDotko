package com.octo.ioni.miudotko;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.Models.Player;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        makeRequest();
    }

    protected void setupListView(ArrayList<Match> matches){
        final ListView listview = (ListView) findViewById(R.id.match_list_view);
        final MatchArrayAdapter adapter = new MatchArrayAdapter(this, matches);
        listview.setAdapter(adapter);
    }

    protected void makeRequest(){
        String tag_json_obj = "json_obj_req";
        String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?key=8E68DFCFBBCC0DC11378E1AAB7068FA1";

        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();

        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        handleResponse(response);
                        pDialog.hide();
                    }
                }, new Response.ErrorListener() {

            @Override
            public void onErrorResponse(VolleyError error) {
                VolleyLog.d(TAG, "Error: " + error.getMessage());
                pDialog.hide();
            }
        });

        AppController.getInstance().addToRequestQueue(jsonObjReq, tag_json_obj);
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

            } catch (JSONException e) {
                Log.e(TAG + ": parseErr", "Json parsing error: " + e.getMessage());
            }
        }


        return matches;
    }
}
