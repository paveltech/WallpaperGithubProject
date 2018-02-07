package com.dm.wallpaper.board.items;

import android.support.annotation.DrawableRes;
import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;

/**
 * Created by android on 2/7/2018.
 */

public class Collection {
    private final int mIcon;
    private final Fragment mFragment;
    private final String mTag;

    public Collection(@DrawableRes int icon, @NonNull Fragment fragment, @NonNull String tag) {
        mIcon = icon;
        mFragment = fragment;
        mTag = tag;
    }

    @DrawableRes
    public int getIcon() {
        return mIcon;
    }

    @NonNull
    public Fragment getFragment() {
        return mFragment;
    }

    public String getTag() {
        return mTag;
    }
}
