package com.dm.wallpaper.board.utils;

import android.annotation.SuppressLint;
import android.app.DownloadManager;
import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.util.Log;
import android.webkit.URLUtil;

import com.danimahardhika.android.helpers.core.ColorHelper;
import com.danimahardhika.android.helpers.core.FileHelper;
import com.danimahardhika.cafebar.CafeBar;
import com.danimahardhika.cafebar.CafeBarTheme;
import com.dm.material.dashboard.candybar.helpers.TypefaceHelper;
import com.dm.material.dashboard.candybar.helpers.WallpaperHelper;
import com.dm.material.dashboard.candybar.items.Wallpaper;
import com.dm.material.dashboard.candybar.preferences.Preferences;
import com.dm.material.dashboard.candybar.utils.LogUtil;

import com.playoffstudio.wallpapergithubproject.R;

import java.io.File;

/**
 * Created by android on 2/8/2018.
 */

public class WallpaperDownloader {


    public final Context mContext;
    public Wallpaper mWallpaper;

    public WallpaperDownloader(Context context){
        mContext = context;
    }

    public WallpaperDownloader wallpaper(@NonNull Wallpaper wallpaper){
        mWallpaper = wallpaper;
        return this;
    }

    public void start(){
        String fileName = mWallpaper.getName()+ "." +WallpaperHelper.getFormat(mWallpaper.getMimeType());
        File directory = WallpaperHelper.getDefaultWallpapersDirectory(mContext);
        File target = new File(directory , fileName);
        if (!directory.exists()){
            showCafeBar(R.string.wallpaper_download_failed);
            return;
        }


        if (WallpaperHelper.isWallpaperSaved(mContext, mWallpaper)) {
            CafeBar.builder(mContext)
                    .theme(Preferences.get(mContext).isDarkTheme() ? CafeBarTheme.LIGHT : CafeBarTheme.DARK)
                    .floating(true)
                    .fitSystemWindow()
                    .duration(CafeBar.Duration.MEDIUM)
                    .typeface(TypefaceHelper.getRegular(mContext), TypefaceHelper.getBold(mContext))
                    .content(R.string.wallpaper_already_downloaded)
                    .neutralText(R.string.open)
                    .onNeutral(cafeBar -> {
                        Uri uri = FileHelper.getUriFromFile(mContext, mContext.getPackageName(), target);
                        if (uri == null) {
                            cafeBar.dismiss();
                            return;
                        }

                        try {
                            mContext.startActivity(new Intent()
                                    .setAction(Intent.ACTION_VIEW)
                                    .setDataAndType(uri, "image/*")
                                    .setFlags(Intent.FLAG_GRANT_READ_URI_PERMISSION));
                        } catch (ActivityNotFoundException e) {
                            LogUtil.e(Log.getStackTraceString(e));
                        }

                        cafeBar.dismiss();
                    })
                    .show();
            return;
        }


        if (!URLUtil.isValidUrl(mWallpaper.getURL())){
            return;
        }

        DownloadManager.Request request = new DownloadManager.Request(Uri.parse(mWallpaper.getURL()));
        request.setMimeType(mWallpaper.getMimeType());
        request.setTitle(fileName);
        request.setDescription(mContext.getResources().getString(R.string.wallpaper_downloading));
        request.allowScanningByMediaScanner();
        request.setVisibleInDownloadsUi(false);
        request.setNotificationVisibility(DownloadManager.Request.VISIBILITY_VISIBLE_NOTIFY_COMPLETED);
        request.setDestinationUri(Uri.fromFile(target));
        @SuppressLint("ServiceCast") DownloadManager downloadManager = (DownloadManager) mContext.getSystemService(Context.DISPLAY_SERVICE);

        try{
            if (downloadManager!=null){
                downloadManager.enqueue(request);
                return;
            }
        }catch (IllegalArgumentException e){
            e.printStackTrace();
            return;
        }

        showCafeBar(R.string.wallpaper_downloading);
    }


    private void showCafeBar(int res) {
        CafeBar.builder(mContext)
                .theme(Preferences.get(mContext).isDarkTheme() ? CafeBarTheme.LIGHT : CafeBarTheme.DARK)
                .contentTypeface(TypefaceHelper.getRegular(mContext))
                .content(res)
                .floating(true)
                .fitSystemWindow()
                .show();
    }


    public static WallpaperDownloader prepare (@NonNull Context context){
        return new WallpaperDownloader(context);
    }
}
