package com.dm.wallpaper.board.services;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;

import com.dm.material.dashboard.candybar.utils.LogUtil;
import com.dm.wallpaper.board.databases.Database;

/**
 * Created by lolipop on 2/21/2018.
 */

public class WallpaperBoardService extends Service{

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public void onTaskRemoved(Intent rootIntent) {
        LogUtil.d("App removed from recent task, database connection closed");
        Database.get(this).closeDatabase();

        stopSelf();
    }
}
