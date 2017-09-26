package com.octo.ioni.miudotko.Models;

/**
 * Created by ioni on 9/25/17.
 */

public class SteamAccount {
    long steamid;
    int communityvisibilitystate;
    int profilestate;
    String personaname;
    long lastlogoff;
    String profileurl;
    String avatar;
    String avatarmedium;
    String avatarfull;
    int personastate;
    String realname;
    String primaryclanid;
    long timecreated;
    int personastateflags;
    String gameextrainfo;
    String gameid;
    String loccountrycode;

    public SteamAccount(){
        initValues();
    }

    public SteamAccount(long steamid,
                        int communityvisibilitystate,
                        int profilestate,
                        String personaname,
                        long lastlogoff,
                        String profileurl,
                        String avatar,
                        String avatarmedium,
                        String avatarfull,
                        int personastate,
                        String realname,
                        String primaryclanid,
                        long timecreated,
                        int personastateflags,
                        String gameextrainfo,
                        String gameid,
                        String loccountrycode){
        initValues();

        this.steamid=steamid;
        this.communityvisibilitystate=communityvisibilitystate;
        this.profilestate=profilestate;
        this.personaname=personaname;
        this.lastlogoff=lastlogoff;
        this.profileurl=profileurl;
        this.avatar=avatar;
        this.avatarmedium=avatarmedium;
        this.avatarfull=avatarfull;
        this.personastate=personastate;
        this.realname=realname;
        this.primaryclanid=primaryclanid;
        this.timecreated=timecreated;
        this.personastateflags=personastateflags;
        this.gameextrainfo=gameextrainfo;
        this.gameid=gameid;
        this.loccountrycode=loccountrycode;
    }

    private void initValues(){
        this.steamid= -1;
        this.communityvisibilitystate= -1;
        this.profilestate= -1;
        this.personaname= "";
        this.lastlogoff= -1;
        this.profileurl= "";
        this.avatar= "";
        this.avatarmedium= "";
        this.avatarfull= "";
        this.personastate= -1;
        this.realname= "";
        this.primaryclanid= "";
        this.timecreated= -1;
        this.personastateflags= -1;
        this.gameextrainfo= "";
        this.gameid= "";
        this.loccountrycode= "";
    }
}