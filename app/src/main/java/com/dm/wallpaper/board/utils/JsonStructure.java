package com.dm.wallpaper.board.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.webkit.URLUtil;

import java.util.ArrayList;
import java.util.List;

import cz.msebera.android.httpclient.NameValuePair;
import cz.msebera.android.httpclient.message.BasicNameValuePair;

/**
 * Created by android on 2/7/2018.
 */

public class JsonStructure {

    private Builder mBuilder;

    private JsonStructure(@NonNull Builder builder) {
        mBuilder = builder;
    }

    @Nullable
    public String getUrl() {
        return mBuilder.mUrl;
    }

    //@NonNullfile:/D:/Studio/WallpaperGithubProject/app/src/main/java/com/dm/wallpaper/board/utils/InAppBillingProcessor.java
    public List<NameValuePair> getPosts() {
        return mBuilder.mValues;
    }

    @NonNull
    public CategoryStructure getCategory() {
        return mBuilder.mCategory;
    }

    @NonNull
    public WallpaperStructure getWallpaper() {
        return mBuilder.mWallpaper;
    }



    public class Builder {

        private String mUrl = null;
        private List<NameValuePair> mValues = new ArrayList<>();
        private CategoryStructure mCategory = new CategoryStructure("Categories");
        private WallpaperStructure mWallpaper = new WallpaperStructure("Wallpapers");

        public Builder url (@NonNull String url){
            if (url!=null && URLUtil.isValidUrl(url)){
                mUrl = url;
            }

            return this;
        }


        public Builder post (@NonNull String tag , @NonNull String value){
            mValues.add(new BasicNameValuePair(tag , value));
            return this;
        }

        public Builder wallpaper(@NonNull WallpaperStructure wallpaperStructure){
            mWallpaper = wallpaperStructure;
            return this;
        }

        public Builder category (@NonNull CategoryStructure categoryStructure){
            mCategory = categoryStructure;
            return this;
        }

        public JsonStructure build(){
            return new JsonStructure(this);
        }
    }
}
