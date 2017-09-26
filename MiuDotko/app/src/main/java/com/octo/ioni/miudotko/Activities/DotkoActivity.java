package com.octo.ioni.miudotko.Activities;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.VolleyLog;
import com.android.volley.toolbox.JsonObjectRequest;
import com.octo.ioni.miudotko.AppController;
import com.octo.ioni.miudotko.AppDataController;
import com.octo.ioni.miudotko.Utils.SteamURLController;

import org.json.JSONObject;

/**
 * Created by ioni on 9/20/17.
 */

public class DotkoActivity extends AppCompatActivity {
    String TAG = "DotkoActivity";
    public AppDataController appData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        appData = new AppDataController(this);
        super.onCreate(savedInstanceState);
    }

    protected void makeRequest(String url, final DotkoRequestInterface requestInterface){
        String tag_json_obj = "json_obj_req";
        final ProgressDialog pDialog = new ProgressDialog(this);
        pDialog.setMessage("Loading...");
        pDialog.show();
        Log.d(TAG,"Making a request. URL: " + url);
        JsonObjectRequest jsonObjReq = new JsonObjectRequest(Request.Method.GET,
                url, null,
                new Response.Listener<JSONObject>() {

                    @Override
                    public void onResponse(JSONObject response) {
                        requestInterface.callback(response);
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
}
