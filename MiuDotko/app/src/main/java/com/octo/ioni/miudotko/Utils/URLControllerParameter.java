package com.octo.ioni.miudotko.Utils;

/**
 * Created by ioni on 9/20/17.
 */

class URLControllerParameter {
    String key;
    String value;
    URLControllerParameter(String key, String value){
        this.key = key;
        this.value = value;
    }

    String pair(){
        return this.key + "=" + this.value;
    }
}