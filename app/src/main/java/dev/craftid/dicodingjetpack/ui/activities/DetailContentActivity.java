package dev.craftid.dicodingjetpack.ui.activities;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.lifecycle.ViewModelProviders;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.data.ContentEntity;
import dev.craftid.dicodingjetpack.viewmodels.ContentViewModel;

public class DetailContentActivity extends AppCompatActivity {

    private ContentViewModel contentViewModel;

    private TextView tvDescription;
    private ImageView ivMoviesTrailer;
    private ImageView ivMoviesThumbnail;
    private TextView tvTitle;
    private TextView tvDateRelease;
    private TextView tvUserScore;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        contentViewModel = ViewModelProviders.of(this).get(ContentViewModel.class);

        initView();
        initData();
    }

    private void initView() {
        tvDescription = findViewById(R.id.tv_description);
        ivMoviesTrailer = findViewById(R.id.iv_movies_trailer);
        ivMoviesThumbnail = findViewById(R.id.iv_movies_thumbnail);
        tvTitle = findViewById(R.id.tv_title);
        tvDateRelease = findViewById(R.id.tv_date_release);
        tvUserScore = findViewById(R.id.tv_user_score);
        Toolbar toolbarDetailContent = findViewById(R.id.toolbar_detail_content);

        setSupportActionBar(toolbarDetailContent);
    }

    private void initData() {
        Intent intent = getIntent();
        if (intent != null){
            int contentID = intent.getIntExtra("contentID", 0);
            int contentType = intent.getIntExtra("contentType", 0);

            ContentEntity contentEntity = contentViewModel.getDetailContent(contentID);

            tvDescription.setText(contentEntity.getOverview().equals("") ? " - " : contentEntity.getOverview());
            tvUserScore.setText(contentEntity.getVoteAverage());

            if (contentType == ContentEntity.TYPE_MOVIE){
                tvTitle.setText(contentEntity.getTitle());
                tvDateRelease.setText(contentEntity.getReleaseDate());
            } else if (contentType == ContentEntity.TYPE_TV_SHOW){
                tvTitle.setText(contentEntity.getName());
                tvDateRelease.setText(contentEntity.getFirstAirDate());
            }

            Glide.with(this)
                    .load(contentEntity.getBackdropPath())
                    .thumbnail(0.1f)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(ivMoviesTrailer);

            Glide.with(this)
                    .load(contentEntity.getPosterPath())
                    .thumbnail(0.1f)
                    .centerCrop()
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(ivMoviesThumbnail);
        }
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return super.onSupportNavigateUp();
    }
}
