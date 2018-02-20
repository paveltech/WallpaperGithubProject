package com.dm.wallpaper.board.applications;

import android.app.Application;

import com.dm.material.dashboard.candybar.databases.Database;


/**
 * Created by lolipop on 2/20/2018.
 */

public abstract class WallpaperBoardApplication extends Application implements ApplicationCallback{

    public static boolean sIsClickable = true;
    private static boolean mIsLatestWallpapersLoaded;
    private static WallpaperBoardConfiguration mConfiguration;
    private Thread.UncaughtExceptionHandler mHandler;

    @Override
    public void onCreate() {
        super.onCreate();
        Database.get(this).openDatabase();
    }
}
