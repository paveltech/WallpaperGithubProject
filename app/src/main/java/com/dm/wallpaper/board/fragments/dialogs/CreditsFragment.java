package com.dm.wallpaper.board.fragments.dialogs;

import android.app.DialogFragment;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.ListView;

import com.dm.wallpaper.board.utils.Extras;
import com.playoffstudio.wallpapergithubproject.R;

import butterknife.BindView;

/**
 * Created by lolipop on 2/22/2018.
 */

public class CreditsFragment extends DialogFragment{


    @BindView(R.id.listview)
    ListView mListView;

    public int mType;
    public AsyncTask mAsyncTask;
    private static final String TAG = "com.field.guide.dialog.credits";


    public static CreditsFragment newInstance(int type){
        CreditsFragment fragment = new CreditsFragment();
        Bundle bundle = new Bundle();
        bundle.putInt(Extras.EXTRA_TYPE , type);
        fragment.setArguments(bundle);
    }
}
