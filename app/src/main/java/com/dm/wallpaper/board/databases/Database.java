package com.dm.wallpaper.board.databases;

import android.content.Context;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by lolipop on 2/20/2018.
 */

public class Database extends SQLiteOpenHelper {


    private static final String DATABASE_NAME = "wallpaper_board_database";
    private static final int DATABASE_VERSION = 5;

    private static final String TABLE_WALLPAPERS = "wallpapers";
    private static final String TABLE_CATEGORIES = "categories";

    private static final String KEY_URL = "url";
    private static final String KEY_THUMB_URL = "thumbUrl";
    private static final String KEY_MIME_TYPE = "mimeType";
    private static final String KEY_SIZE = "size";
    private static final String KEY_COLOR = "color";
    private static final String KEY_WIDTH = "width";
    private static final String KEY_HEIGHT = "height";
    private static final String KEY_COUNT = "count";

    public static final String KEY_ID = "id";
    public static final String KEY_NAME = "name";
    public static final String KEY_AUTHOR = "author";
    public static final String KEY_CATEGORY = "category";

    private static final String KEY_FAVORITE = "favorite";
    private static final String KEY_SELECTED = "selected";
    private static final String KEY_MUZEI_SELECTED = "muzeiSelected";
    private static final String KEY_ADDED_ON = "addedOn";

    private final Context mContext;

}
