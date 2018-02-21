package com.dm.wallpaper.board.adapters;

import android.content.ActivityNotFoundException;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.CardView;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.danimahardhika.android.helpers.core.ColorHelper;


import com.danimahardhika.android.helpers.core.DrawableHelper;
import com.dm.material.dashboard.candybar.adapters.AboutSocialAdapter;
import com.dm.material.dashboard.candybar.fragments.dialog.CreditsFragment;
import com.dm.material.dashboard.candybar.fragments.dialog.LicensesFragment;
import com.dm.material.dashboard.candybar.utils.LogUtil;
import com.dm.wallpaper.board.preferences.Preferences;
import com.dm.wallpaper.board.utils.Extras;
import com.mikhaellopez.circularimageview.CircularImageView;
import com.playoffstudio.wallpapergithubproject.R;

import org.sufficientlysecure.htmltextview.HtmlTextView;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Created by lolipop on 2/22/2018.
 */

public class AboutAdapter  {

    public final Context mContext;
    public int mItemCount;
    public final boolean mShowContributors;

    private static final int TYPE_HEADER = 0;
    private static final int TYPE_CONTRIBUTORS = 1;
    private static final int TYPE_FOOTER = 2;
    private static final int TYPE_BOTTOM_SHADOW = 3;


    public AboutAdapter (@NonNull Context context , int spanCount){
        mContext = context;
        mItemCount =2;
        boolean cardMode = (spanCount > 1);
        if (!cardMode){
            mItemCount+=1;
        }

        mShowContributors = mContext.getResources().getBoolean(R.bool.show_contributors_dialog);
        if (mShowContributors){
            mItemCount +=1;
        }
    }


    public class HeaderViewHolder extends RecyclerView.ViewHolder{

        @BindView(R.id.image)
        ImageView image;
        @BindView(R.id.profile)
        CircularImageView profile;
        @BindView(R.id.subtitle)
        HtmlTextView subtitle;

        public HeaderViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this , itemView);
            RecyclerView recyclerView = itemView.findViewById(R.id.recyclerview);
            recyclerView.setItemAnimator( new DefaultItemAnimator());
            recyclerView.setLayoutManager(new LinearLayoutManager(mContext , LinearLayoutManager.HORIZONTAL , true));
            recyclerView.setHasFixedSize(true);

            String[] urls = mContext.getResources().getStringArray(R.array.about_social_links);
            if (urls.length == 0){
                recyclerView.setVisibility(View.GONE);

                subtitle.setPadding(
                        subtitle.getPaddingLeft(),
                        subtitle.getPaddingTop(),
                        subtitle.getPaddingRight(),
                        subtitle.getPaddingBottom() + mContext.getResources().getDimensionPixelSize(R.dimen.content_margin));
            }
            else {
                if (recyclerView.getLayoutParams() instanceof LinearLayout.LayoutParams) {
                    LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) recyclerView.getLayoutParams();
                    if (urls.length < 7) {
                        params.width = LinearLayout.LayoutParams.WRAP_CONTENT;
                        params.gravity = Gravity.CENTER_HORIZONTAL;
                        recyclerView.setOverScrollMode(View.OVER_SCROLL_NEVER);
                    }
                }
                recyclerView.setAdapter(new AboutSocialAdapter(mContext, urls));
            }

            subtitle.setHtml(mContext.getResources().getString(R.string.about_desc));

            CardView card = itemView.findViewById(R.id.card);
            if (!Preferences.get(mContext).isShadowEnabled()) {
                if (card != null) card.setCardElevation(0);

                profile.setShadowRadius(0f);
                profile.setShadowColor(Color.TRANSPARENT);
            }
        }
    }


    public class ContributorsViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        public ContributorsViewHolder(View itemView) {
            super(itemView);

            TextView title = itemView.findViewById(R.id.title);
            CardView card = itemView.findViewById(R.id.card);
            if (!Preferences.get(mContext).isShadowEnabled() && card !=null){
                card.setCardElevation(0);
            }

            int color = ColorHelper.getAttributeColor(mContext , android.R.attr.textColorPrimary);
            title.setCompoundDrawablesWithIntrinsicBounds(DrawableHelper.getTintedDrawable(
                    mContext, R.drawable.ic_toolbar_people, color), null, null, null);
            title.setText(mContext.getResources().getString(R.string.about_contributors_title));
            title.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            CreditsFragment.showCreditsDialog(((AppCompatActivity) mContext).getSupportFragmentManager(),
                    Extras.TYPE_CONTRIBUTORS);
        }
    }



    public class FooterViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        @BindView(R.id.about_dev_github)
        ImageView github;
        @BindView(R.id.about_dev_google_plus)
        ImageView googlePlus;
        @BindView(R.id.about_dev_instagram)
        ImageView instagram;
        @BindView(R.id.about_dashboard_licenses)
        TextView licenses;
        @BindView(R.id.about_dashboard_contributors)
        TextView contributors;
        @BindView(R.id.about_dashboard_translator)
        TextView translator;


        public FooterViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);

            CardView card = itemView.findViewById(R.id.card);
            if (!Preferences.get(mContext).isShadowEnabled() && card != null) {
                card.setCardElevation(0);
            }

            int color = ColorHelper.getAttributeColor(mContext, android.R.attr.textColorPrimary);
            TextView title = itemView.findViewById(R.id.about_dashboard_title);
            title.setCompoundDrawablesWithIntrinsicBounds(DrawableHelper.getTintedDrawable(
                    mContext, R.drawable.ic_toolbar_dashboard, color), null, null, null);


            instagram.setImageDrawable(DrawableHelper.getTintedDrawable(mContext, R.drawable.ic_toolbar_instagram, color));
            googlePlus.setImageDrawable(DrawableHelper.getTintedDrawable(mContext, R.drawable.ic_toolbar_google_plus, color));
            github.setImageDrawable(DrawableHelper.getTintedDrawable(mContext, R.drawable.ic_toolbar_github, color));

            instagram.setOnClickListener(this);
            googlePlus.setOnClickListener(this);
            github.setOnClickListener(this);
            licenses.setOnClickListener(this);
            contributors.setOnClickListener(this);
            translator.setOnClickListener(this);

        }

        @Override
        public void onClick(View view) {
            int id = view.getId();
            if (id == R.id.about_dashboard_licenses) {
                LicensesFragment.showLicensesDialog(((AppCompatActivity) mContext).getSupportFragmentManager());
                return;
            }

            if (id == R.id.about_dashboard_contributors) {
                CreditsFragment.showCreditsDialog(((AppCompatActivity) mContext).getSupportFragmentManager(),
                        Extras.TYPE_DASHBOARD_CONTRIBUTORS);
                return;
            }

            if (id == R.id.about_dashboard_translator) {
                CreditsFragment.showCreditsDialog(((AppCompatActivity) mContext).getSupportFragmentManager(),
                        Extras.TYPE_DASHBOARD_TRANSLATOR);
                return;
            }

            Intent intent = null;
            if (id == R.id.about_dev_google_plus) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mContext
                        .getResources().getString(R.string.about_dashboard_dev_google_plus_url)));
            } else if (id == R.id.about_dev_github) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mContext
                        .getResources().getString(R.string.about_dashboard_dev_github_url)));
            } else if (id == R.id.about_dev_instagram) {
                intent = new Intent(Intent.ACTION_VIEW, Uri.parse(mContext
                        .getResources().getString(R.string.about_dashboard_dev_instagram_url)));
            }

            try {
                mContext.startActivity(intent);
            } catch (NullPointerException | ActivityNotFoundException e) {
                LogUtil.e(Log.getStackTraceString(e));
            }
        }
    }

    public class ShadowViewHolder extends RecyclerView.ViewHolder{

        public ShadowViewHolder(View itemView) {
            super(itemView);
            if (!Preferences.get(mContext).isShadowEnabled()){
                View shadow = itemView.findViewById(R.id.shadow);
                shadow.setVisibility(View.GONE);
                View root = shadow.getRootView();
                root.setPadding(0,0,0,0);
            }
        }
    }
}
