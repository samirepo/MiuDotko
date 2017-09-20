package com.octo.ioni.miudotko.Utils;

/**
 * Created by ioni on 9/20/17.
 */

enum UrlPath {
    MATCH_DETAILS("GetMatchDetails"),
    MATCH_HISTORY("GetMatchHistory");

    private String stringValue;
    UrlPath(String toString) {
        stringValue = toString;
    }

    @Override
    public String toString() {
        return stringValue;
    }
}