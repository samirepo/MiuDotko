package com.octo.ioni.miudotko.Utils;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import java.util.ArrayList;

import static com.octo.ioni.miudotko.AppController.TAG;

/**
 * Created by ioni on 9/20/17.
 */

public class SteamURLController {

    //
    // RequestURLs
    public static String url_matchDetails = url_matchDetails("3453474022");
    public static String url_matchDetails(String id) {return steamUrl(UrlPath.MATCH_DETAILS, param_matchDetails(id));}
    public static String url_matchHistoryLast25 = url_matchHistoryLast25("Ioni");
    public static String url_matchHistoryLast25(String name) {return steamUrl(UrlPath.MATCH_HISTORY, param_history25(name));}
    public static String url_playerSummaries(String steamID64) {return steamUrl(UrlPath.MATCH_HISTORY, param_pSummaries(steamID64));}

    //http://api.steampowered.com/ISteamUser/GetPlayerSummaries/v0002/?steamids=76561198098509664&key=8E68DFCFBBCC0DC11378E1AAB7068FA1
    //String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchDetails/V001/?match_id=3453474022&key=8E68DFCFBBCC0DC11378E1AAB7068FA1";
    //String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?account_id=59162503&key=8E68DFCFBBCC0DC11378E1AAB7068FA1";
    //String url = "https://api.steampowered.com/IDOTA2Match_570/GetMatchHistory/V001/?player_name=Ioni&key=8E68DFCFBBCC0DC11378E1AAB7068FA1";

    //
    // PARAMETERS
    private static ArrayList<URLControllerParameter> param_history25(String name){
        ArrayList<URLControllerParameter> returnable = new ArrayList<>();
        returnable.add(new URLControllerParameter(Constants.playerName, name));
        return returnable;
    }
    public static ArrayList<URLControllerParameter> param_matchDetails(String id){
        ArrayList<URLControllerParameter> returnable = new ArrayList<>();
        returnable.add(new URLControllerParameter(Constants.matchID, id));
        return returnable;
    }
    public static ArrayList<URLControllerParameter> param_accountID(String id){
        ArrayList<URLControllerParameter> returnable = new ArrayList<>();
        returnable.add(new URLControllerParameter(Constants.matchID, id));
        return returnable;
    }
    public static ArrayList<URLControllerParameter> param_pSummaries(String id){
        ArrayList<URLControllerParameter> returnable = new ArrayList<>();
        returnable.add(new URLControllerParameter(Constants.steamIDs, id));
        return returnable;
    }

    //
    // BUILDER -- PATH -> URL
    private static String createURL(UrlPath path){
        return Constants.SteamApiURL + path.toString() + Constants.SteamWebAPIVersion;
    }

    private static String steamUrl(UrlPath path, ArrayList<URLControllerParameter> parameters){
        StringBuilder strBuilder = new StringBuilder(createURL(path));

        strBuilder.append("?");
        for (URLControllerParameter param : parameters){
            strBuilder.append(param.pair());
        }
        strBuilder.append("&key=");
        strBuilder.append(Constants.SteamWebAPIKey);

        return strBuilder.toString();
    }

    private static String steamUrl(UrlPath path){
        return steamUrl(path, new ArrayList<URLControllerParameter>());
    }


}