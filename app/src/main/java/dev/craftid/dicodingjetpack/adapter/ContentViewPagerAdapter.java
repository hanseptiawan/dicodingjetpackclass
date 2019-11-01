package dev.craftid.dicodingjetpack.adapter;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;

import java.util.List;

import dev.craftid.dicodingjetpack.data.ContentEntity;
import dev.craftid.dicodingjetpack.ui.fragments.MovieContentFragment;
import dev.craftid.dicodingjetpack.ui.fragments.TvShowContentFragment;

public class ContentViewPagerAdapter extends FragmentStatePagerAdapter {

    private String[] pageTitles;

    public ContentViewPagerAdapter(@NonNull FragmentManager fm, int behavior, String[] pageTitles) {
        super(fm, behavior);
        this.pageTitles = pageTitles;
    }

    @Override
    public int getItemPosition(@NonNull Object object) {
        return super.getItemPosition(object);
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        if (position == 0){
            return new MovieContentFragment().newInstance();
        } else if (position == 1){
            return new TvShowContentFragment().newInstance();
        } else {
            return new MovieContentFragment().newInstance();
        }
    }

    @Override
    public int getCount() {
        return pageTitles.length;
    }

    @Nullable
    @Override
    public CharSequence getPageTitle(int position) {
        return pageTitles[position];
    }
}
