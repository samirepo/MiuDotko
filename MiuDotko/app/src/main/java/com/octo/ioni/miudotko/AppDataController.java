package com.octo.ioni.miudotko;

/**
 * Created by ioni on 9/21/17.
 */

public class AppDataController {
    private static final AppDataController ourInstance = new AppDataController();

    public static AppDataController getInstance() {
        return ourInstance;
    }

    private AppDataController() {
    }
}
