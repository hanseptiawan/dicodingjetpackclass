package dev.craftid.dicodingjetpack.ui.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;
import androidx.viewpager2.widget.ViewPager2;

import com.google.android.material.tabs.TabLayout;

import java.util.Objects;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.adapter.ContentViewPagerAdapter;

public class MainContentFragment extends Fragment {

    public MainContentFragment() {
    }

    public static MainContentFragment newInstance() {
        return new MainContentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
    }

    private void initView(View view) {
        TabLayout fmTabLayout = view.findViewById(R.id.fm_tab_layout);
        ViewPager fmVpContent = view.findViewById(R.id.fm_vp_content);

        fmVpContent.setOffscreenPageLimit(2);
        String[] pageTitles = Objects.requireNonNull(getActivity()).getResources().getStringArray(R.array.tab_layout_titles);

        ContentViewPagerAdapter contentViewPagerAdapter = new ContentViewPagerAdapter(getChildFragmentManager(), ViewPager2.ORIENTATION_HORIZONTAL, pageTitles);
        fmVpContent.setAdapter(contentViewPagerAdapter);
        fmTabLayout.setupWithViewPager(fmVpContent);
    }
}
