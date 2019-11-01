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
import dev.craftid.dicodingjetpack.viewmodels.ContentViewModel;

public class MovieContentFragment extends Fragment {

    private ContentAdapter contentAdapter;
    private List<ContentEntity> contentEntityList;

    public MovieContentFragment() {
    }

    public MovieContentFragment newInstance() {
        return new MovieContentFragment();
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.content_main, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        initView(view);
        initAction();
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        if (getActivity() != null) {
            ContentViewModel contentViewModel = ViewModelProviders.of(getActivity()).get(ContentViewModel.class);

            contentEntityList = contentViewModel.getMovies();

            contentAdapter.setContentEntityList(contentEntityList);
        }
    }

    private void initView(View view) {
        RecyclerView cmRvContent = view.findViewById(R.id.cm_rv_content);

        contentAdapter = new ContentAdapter(getActivity());
        cmRvContent.setHasFixedSize(true);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(getActivity(), RecyclerView.VERTICAL, false);
        cmRvContent.setLayoutManager(linearLayoutManager);
        cmRvContent.setItemAnimator(new DefaultItemAnimator());
        cmRvContent.setNestedScrollingEnabled(false);
        cmRvContent.setAdapter(contentAdapter);
    }

    private void initAction() {
        contentAdapter.setOnClickListener(v -> {
            RecyclerView.ViewHolder viewHolder = (RecyclerView.ViewHolder) v.getTag();
            int position = viewHolder.getAdapterPosition();

            ImageView imageView = v.findViewById(R.id.iv_movies_thumbnail);

            ActivityOptionsCompat optionsCompat = ActivityOptionsCompat.makeSceneTransitionAnimation(
                    Objects.requireNonNull(getActivity()), imageView, "contentImg");

            Intent intentDetail = new Intent(getActivity(), DetailContentActivity.class);

            intentDetail.putExtra("contentID", contentEntityList.get(position).getIdContent());
            intentDetail.putExtra("contentType", contentEntityList.get(position).getContentType());
            startActivity(intentDetail, optionsCompat.toBundle());

        });
    }
}
