package com.dm.wallpaper.board.utils;

import android.support.annotation.NonNull;
import android.webkit.URLUtil;

import com.dm.material.dashboard.candybar.items.Wallpaper;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by android on 2/7/2018.
 */

public class Builder {

    public String mUrl = null;
    public List<NameValuePair> mValues = new ArrayList<>();
    public CategoryStructure mCategory = new CategoryStructure("Categories");
    public WallpaperStructure mWallpaper = new WallpaperStructure("wallpapers");

    public Builder url (@NonNull String url){
        if (url !=null && URLUtil.isValidUrl(url)){
            mUrl = url;
        }

        return this;
    }

    public Builder post (@NonNull String tag , @NonNull String value){
        mValues.add(new BasicNameValuePair(tag
         , value));
        return this;
    }

    public Builder category (@NonNull CategoryStructure categoryStructure){
        mCategory = categoryStructure;
        return this;
    }

    public Builder Wallpaper (@NonNull WallpaperStructure wallpaperStructure){
        mWallpaper = wallpaperStructure;
        return this;
    }
}
