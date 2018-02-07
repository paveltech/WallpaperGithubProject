package com.dm.wallpaper.board.utils;

import android.support.annotation.NonNull;

/**
 * Created by android on 2/7/2018.
 */

public class CategoryStructure {
    public final String mArrayName;
    public String mName = "name";

    public CategoryStructure(@NonNull String mArrayName){
        this.mArrayName = mArrayName;
    }

    public CategoryStructure name (@NonNull String name){
        mName = name;
        return this;
    }

    public String getmArrayName() {
        return mArrayName;
    }

    public String getmName() {
        return mName;
    }

}
