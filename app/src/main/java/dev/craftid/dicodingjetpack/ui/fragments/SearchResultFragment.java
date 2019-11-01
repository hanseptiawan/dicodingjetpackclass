package dev.craftid.dicodingjetpack.ui.fragments;

import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityOptionsCompat;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.DefaultItemAnimator;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;
import java.util.Objects;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.adapter.ContentAdapter;
import dev.craftid.dicodingjetpack.data.ContentEntity;
import dev.craftid.dicodingjetpack.ui.activities.DetailContentActivity;
import dev.craftid.dicodingjetpack.ui.activities.MainActivity;
import dev.craftid.dicodingjetpack.viewmodels.ContentViewModel;

public class SearchResultFragment extends Fragment {

    private MainActivity activity;
    private ContentAdapter contentAdapter;
    private ContentViewModel contentViewModel;

    private List<ContentEntity> entities;

    private String query;

    public SearchResultFragment() {
    }

    public static SearchResultFragment newInstance(Bundle bundle) {
        SearchResultFragment searchResultFragment = new SearchResultFragment();
        searchResultFragment.setArguments(bundle);

        return searchResultFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        activity = (MainActivity) getActivity();

        Bundle bundle = getArguments();
        if (bundle != null) {
            query = bundle.getString("query");
        }
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_search_result, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initAction();
    }

    private void initView(View view) {
        RecyclerView sfmRecyclerView = view.findViewById(R.id.sfm_recycler_view);

        contentAdapter = new ContentAdapter(getActivity());
        sfmRecyclerView.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        sfmRecyclerView.setLayoutManager(linearLayoutManager);
        sfmRecyclerView.setItemAnimator(new DefaultItemAnimator());
        sfmRecyclerView.setNestedScrollingEnabled(false);
        sfmRecyclerView.setAdapter(contentAdapter);
    }

    private void initAction() {
        activity.setOnSearchListener(this::getNewSearch);

        contentAdapter.setOnClickListener(v ->{
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();

            ImageView imageView = v.findViewById(R.id.iv_movies_thumbnail);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    Objects.requireNonNull(getActivity()), imageView, "contentImg");

            Intent intentDetail = new Intent(getActivity(), DetailContentActivity.class);

            intentDetail.putExtra("contentID", entities.get(position).getIdContent());
            intentDetail.putExtra("contentType", entities.get(position).getContentType());
            startActivity(intentDetail, optionsCompat.toBundle());
        });
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null){
            contentViewModel = ViewModelProviders.of(getActivity()).get(ContentViewModel.class);

            entities = contentViewModel.getSearchResult(query);

            if (entities != null){
                contentAdapter.setContentEntityList(entities);
            }
        }
    }

    private void getNewSearch(String query){
        entities = contentViewModel.getSearchResult(query);
        contentAdapter.setContentEntityList(entities);
    }
}
