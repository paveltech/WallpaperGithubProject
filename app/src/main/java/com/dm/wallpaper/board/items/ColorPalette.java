package com.dm.wallpaper.board.items;

import android.support.annotation.ColorInt;
import android.support.annotation.Nullable;



import java.util.ArrayList;
import java.util.List;

/*
 * Wallpaper Board
 *
 * Copyright (c) 2017 Dani Mahardhika
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

public class ColorPalette {

    private List<Integer> mColors;

    public ColorPalette() {
        mColors = new ArrayList<>();
    }

    public ColorPalette add(@ColorInt int color) {
        if (color == 0) {

            return this;
        }
        if (!mColors.contains(color)) {
            mColors.add(color);
            return this;
        }
        return this;
    }

    @ColorInt
    public int get(int index) {
        if (index >= 0 && index < mColors.size()) {
            return mColors.get(index);
        }

        return -1;
    }

    @Nullable
    public String getHex(int index) {
        if (index >= 0 && index < mColors.size()) {
            return String.format("#%06X", (0xFFFFFF & get(index)));
        }

        return null;
    }

    public int size() {
        return mColors.size();
    }
}
