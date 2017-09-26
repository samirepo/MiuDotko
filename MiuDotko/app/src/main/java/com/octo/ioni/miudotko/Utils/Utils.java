package com.octo.ioni.miudotko.Utils;

/**
 * Created by ioni on 9/25/17.
 */

public class Utils {
    public static String steamID_from32to64(long id){
        return Long.toString(id + 76561197960265728L);
    }
}
