package com.dm.wallpaper.board.utils;

import android.support.annotation.NonNull;
import android.support.annotation.Nullable;

/**
 * Created by android on 2/7/2018.
 */

public class WallpaperStructure {


    private final String mArrayName;
    private String mName = "name";
    private String mAuthor = "author";
    private String mUrl = "url";
    private String mThumbUrl = "thumbUrl";
    private String mCategory = "category";

    private String mAuthorUrl = null;
    private String mAuthorThumbnail = null;

    public WallpaperStructure(@NonNull String arrayName) {
        mArrayName = arrayName;
    }

    public WallpaperStructure name(@Nullable String name) {
        mName = name;
        return this;
    }

    public WallpaperStructure author(@NonNull String author) {
        mAuthor = author;
        return this;
    }

    //Todo: make it public
    private WallpaperStructure authorUrl(@Nullable String authorUrl) {
        mAuthorUrl = authorUrl;
        return this;
    }

    //Todo: make it public
    private WallpaperStructure authorThumbnail(@Nullable String authorThumbnail) {
        mAuthorThumbnail = authorThumbnail;
        return this;
    }

    public WallpaperStructure url(@NonNull String url) {
        mUrl = url;
        return this;
    }

    public WallpaperStructure thumbUrl(@Nullable String thumbUrl) {
        mThumbUrl = thumbUrl;
        return this;
    }

    public WallpaperStructure category(@NonNull String category) {
        mCategory = category;
        return this;
    }

    @NonNull
    public String getArrayName() {
        return mArrayName;
    }

    @Nullable
    public String getName() {
        return mName;
    }

    @NonNull
    public String getAuthor() {
        return mAuthor;
    }

    @NonNull
    public String getUrl() {
        return mUrl;
    }

    @Nullable
    public String getThumbUrl() {
        return mThumbUrl;
    }

    @NonNull
    public String getCategory() {
        return mCategory;
    }

    //Todo: make it public
    @Nullable
    private String getAuthorUrl() {
        return mAuthorUrl;
    }

    //Todo: make it public
    @Nullable
    private String getAuthorThumbnail() {
        return mAuthorThumbnail;
    }
}
