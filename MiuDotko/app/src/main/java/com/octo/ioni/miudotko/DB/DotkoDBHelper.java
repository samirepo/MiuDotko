package com.octo.ioni.miudotko.DB;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import com.octo.ioni.miudotko.DB.MatchesTable;
import com.octo.ioni.miudotko.Models.Match;
import com.octo.ioni.miudotko.Models.Player;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by ioni on 9/22/17.
 */

public class DotkoDBHelper extends SQLiteOpenHelper {
    public DotkoDBHelper(Context context) {
        super(context, "DotkoDB5", null, 5);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        sqLiteDatabase.execSQL(MatchesTable.CREATE_MATCHES_TABLE);
        sqLiteDatabase.execSQL(PlayerTable.CREATE_PLAYERS_TABLE);
        seedMatches(sqLiteDatabase);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int prevVersion, int newVersion) {
        sqLiteDatabase.execSQL(MatchesTable.DROP_QUERY);
        sqLiteDatabase.execSQL(PlayerTable.DROP_QUERY);
        sqLiteDatabase.execSQL(MatchesTable.CREATE_MATCHES_TABLE);
        sqLiteDatabase.execSQL(PlayerTable.CREATE_PLAYERS_TABLE);
    }

    // Adding new contact
    public boolean addMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = contentValues(match);
        boolean playerInDb = CheckIfDataInDB(db, MatchesTable.TABLE_MATCHES, MatchesTable.KEY_MATCH_ID, Long.toString(match.getMatch_id_long()));
        if (playerInDb) return false;

        db.insert(MatchesTable.TABLE_MATCHES, null, values);
        db.close();
        return true;
    }

    // Adding new player
    public boolean addPlayer(Player player) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = contentValues(player);

        boolean playerInDb = CheckIfDataInDB(db, PlayerTable.TABLE_PLAYERS, PlayerTable.KEY_ACCOUNT_ID, Long.toString(player.getAccount_id()));
        if (playerInDb) return false;

        db.insert(PlayerTable.TABLE_PLAYERS, null, values);
        db.close();
        return true;
    }

    public Player getPlayer(long id){
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(
                PlayerTable.TABLE_PLAYERS,
                new String[] {
                        PlayerTable.KEY_ACCOUNT_ID,
                        PlayerTable.KEY_PLAYER_SLOT,
                        PlayerTable.KEY_HERO_ID,
                        PlayerTable.KEY_ITEM_0,
                        PlayerTable.KEY_ITEM_1,
                        PlayerTable.KEY_ITEM_2,
                        PlayerTable.KEY_ITEM_3,
                        PlayerTable.KEY_ITEM_4,
                        PlayerTable.KEY_ITEM_5,
                        PlayerTable.KEY_BACKPACk_0,
                        PlayerTable.KEY_BACKPACk_1,
                        PlayerTable.KEY_BACKPACk_2,
                        PlayerTable.KEY_KILLS,
                        PlayerTable.KEY_DEATHS,
                        PlayerTable.KEY_ASSISTS,
                        PlayerTable.KEY_LEAVER_STATUS,
                        PlayerTable.KEY_LAST_HITS,
                        PlayerTable.KEY_DENIES,
                        PlayerTable.KEY_GPM,
                        PlayerTable.KEY_XPM,
                        PlayerTable.KEY_LEVEL,
                        PlayerTable.KEY_HERO_DAMAGE,
                        PlayerTable.KEY_TOWER_DAMAGE,
                        PlayerTable.KEY_HEALING,
                        PlayerTable.KEY_GOLD,
                        PlayerTable.KEY_GOLD_SPENT,
                        PlayerTable.KEY_SCALED_HERO_DAMAGE,
                        PlayerTable.KEY_SCALED_TOWER_DAMAGE,
                        PlayerTable.KEY_SCALED_HERO_HEALING
                },
                PlayerTable.KEY_ACCOUNT_ID + "=?",
                new String[] { String.valueOf(id) },
                null,
                null,
                null,
                null
        );

        if (cursor != null)
            cursor.moveToFirst();

        Player player = playerFromCursor(cursor);

        return player;
    }

    public Match getMatch(long id) {
        SQLiteDatabase db = this.getReadableDatabase();

        Cursor cursor = db.query(MatchesTable.TABLE_MATCHES, new String[] { MatchesTable.KEY_MATCH_ID, MatchesTable.KEY_MATCH_SEQ, MatchesTable.KEY_MATCH_START, MatchesTable.KEY_MATCH_LOBBY, MatchesTable.KEY_MATCH_RADIANT, MatchesTable.KEY_MATCH_DIRE, MatchesTable.KEY_MATCH_PLAYERS }, MatchesTable.KEY_MATCH_ID + "=?",
                new String[] { String.valueOf(id) }, null, null, null, null);
        if (cursor != null)
            cursor.moveToFirst();

        Match match = matchFromCursor(cursor);

        // return contact
        return match;
    }

    // Getting All Matches
    public ArrayList<Player> getAllPlayers() {
        ArrayList<Player> playerList = new ArrayList<>();

        String selectQuery = "SELECT  * FROM " + PlayerTable.TABLE_PLAYERS;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Player player = playerFromCursor(cursor);
                playerList.add(player);
            } while (cursor.moveToNext());
        }
        return playerList;
    }

    // Getting All Matches
    public ArrayList<Match> getAllMatches() {
        ArrayList<Match> matchList = new ArrayList<Match>();
        // Select All Query
        String selectQuery = "SELECT  * FROM " + MatchesTable.TABLE_MATCHES;

        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        // looping through all rows and adding to list
        if (cursor.moveToFirst()) {
            do {
                Match match = matchFromCursor(cursor);
                // Adding contact to list
                matchList.add(match);
            } while (cursor.moveToNext());
        }

        // return contact list
        return matchList;
    }

    // Getting Matches Count
    public int getMatchesCount() {
        String countQuery = "SELECT  * FROM " + MatchesTable.TABLE_MATCHES;
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.rawQuery(countQuery, null);
        cursor.close();

        // return count
        return cursor.getCount();
    }

    // Updating single match
    public int updateMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();

        ContentValues values = contentValues(match);

        // updating row
        return db.update(MatchesTable.TABLE_MATCHES, values, MatchesTable.KEY_MATCH_ID + " = ?",
                new String[] { String.valueOf(match.getMatch_id()) });
    }

    // Deleting single match
    public void deleteMatch(Match match) {
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(MatchesTable.TABLE_MATCHES, MatchesTable.KEY_MATCH_ID + " = ?",
                new String[] { String.valueOf(match.getMatch_id()) });
        db.close();
    }

    //
    // REST!
    //

    private void seedMatches(SQLiteDatabase sqLiteDatabase){
        //TODO! Make Seed stuff -->
        for (Match match : MatchesTable.seedData()) {
            ContentValues values = contentValues(match);
            sqLiteDatabase.insert(MatchesTable.TABLE_MATCHES, null, values);
        }
    }

    public Cursor getProductCursor() {
        return this.getWritableDatabase().rawQuery(MatchesTable.SElECT_QUERY, null);
    }

    protected ContentValues contentValues(Match match){
        ContentValues values = new ContentValues();
        values.put(MatchesTable.KEY_MATCH_ID, match.getMatch_id());
        values.put(MatchesTable.KEY_MATCH_SEQ, match.getMatch_seg());
        values.put(MatchesTable.KEY_MATCH_START, match.getStart_time());
        values.put(MatchesTable.KEY_MATCH_LOBBY, match.getLobby_type());
        values.put(MatchesTable.KEY_MATCH_RADIANT, match.getRadiant_team_id());
        values.put(MatchesTable.KEY_MATCH_DIRE, match.getDire_team_id());
        values.put(MatchesTable.KEY_MATCH_PLAYERS, match.getPlayersJSON());
        return values;
    }

    protected ContentValues contentValues(Player player){
        ContentValues values = new ContentValues();

        values.put(PlayerTable.KEY_ACCOUNT_ID, player.getAccount_id());
        values.put(PlayerTable.KEY_DEATHS, player.getDeaths());
        // TODO Add the rest!
        return values;
    }

    private Player playerFromCursor(Cursor cursor){
        return new Player(
                cursor.getLong(cursor.getColumnIndex(PlayerTable.KEY_ACCOUNT_ID)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_PLAYER_SLOT)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_HERO_ID)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_ITEM_0)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_ITEM_1)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_ITEM_2)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_ITEM_3)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_ITEM_4)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_ITEM_5)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_BACKPACk_0)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_BACKPACk_1)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_BACKPACk_2)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_KILLS)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_DEATHS)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_ASSISTS)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_LEAVER_STATUS)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_LAST_HITS)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_DENIES)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_GPM)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_XPM)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_LEVEL)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_HERO_DAMAGE)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_TOWER_DAMAGE)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_HEALING)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_GOLD)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_GOLD_SPENT)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_SCALED_HERO_DAMAGE)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_SCALED_TOWER_DAMAGE)),
                cursor.getInt(cursor.getColumnIndex(PlayerTable.KEY_SCALED_HERO_HEALING))
        );
    }

    private Match matchFromCursor(Cursor cursor){
        return new Match(
                cursor.getLong(cursor.getColumnIndex(MatchesTable.KEY_MATCH_ID)),
                cursor.getLong(cursor.getColumnIndex(MatchesTable.KEY_MATCH_SEQ)),
                cursor.getInt(cursor.getColumnIndex(MatchesTable.KEY_MATCH_START)),
                cursor.getInt(cursor.getColumnIndex(MatchesTable.KEY_MATCH_LOBBY)),
                cursor.getInt(cursor.getColumnIndex(MatchesTable.KEY_MATCH_RADIANT)),
                cursor.getInt(cursor.getColumnIndex(MatchesTable.KEY_MATCH_DIRE)),
                cursor.getString(cursor.getColumnIndex(MatchesTable.KEY_MATCH_PLAYERS))
        );
    }

    public static boolean CheckIfDataInDB(SQLiteDatabase sqLiteDatabase, String TableName, String dbfield, String fieldValue) {
        String Query = "Select * from " + TableName + " where " + dbfield + " = " + fieldValue;
        Cursor cursor = sqLiteDatabase.rawQuery(Query, null);
        if(cursor.getCount() <= 0){
            cursor.close();
            return false;
        }
        cursor.close();
        return true;
    }
}

