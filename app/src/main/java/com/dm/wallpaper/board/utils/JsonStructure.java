package com.dm.wallpaper.board.utils;

import android.support.annotation.NonNull;

import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;

/**
 * Created by android on 2/7/2018.
 */

public class JsonStructure {

    private Builder mBuilder;

    public JsonStructure (@NonNull Builder builder){
        mBuilder = builder;
    }


    @NonNull
    public String getUrl(){
        return mBuilder.mUrl;
    }

    @NonNull
    public List<NameValuePair> getPosts() {
        return mBuilder.mValues;
    }

    @NonNull
    public WallpaperStructure getCategory(){
        return mBuilder.mWallpaper;
    }
}
