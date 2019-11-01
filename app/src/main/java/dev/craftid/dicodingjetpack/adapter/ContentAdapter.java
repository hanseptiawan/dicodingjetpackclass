package dev.craftid.dicodingjetpack.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;
import java.util.List;

import dev.craftid.dicodingjetpack.R;
import dev.craftid.dicodingjetpack.data.ContentEntity;

public class ContentAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private List<ContentEntity> contentEntityList = new ArrayList<>();
    private Context context;
    private LayoutInflater layoutInflater;
    private View.OnClickListener onClickListener;

    public ContentAdapter(Context context) {
        this.context = context;
        this.layoutInflater = LayoutInflater.from(context);
    }

    public void setContentEntityList(List<ContentEntity> contentEntityList) {
        this.contentEntityList = contentEntityList;
        notifyDataSetChanged();
    }

    public void setOnClickListener(View.OnClickListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        RecyclerView.ViewHolder viewHolder;
        View view = layoutInflater.inflate(R.layout.list_content, parent, false);
        viewHolder = new ViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ContentEntity contentEntity = contentEntityList.get(position);
        ViewHolder viewHolder = (ViewHolder) holder;
        viewHolder.bindData(contentEntity);
    }

    @Override
    public int getItemCount() {
        return contentEntityList.size();
    }

    class ViewHolder extends RecyclerView.ViewHolder {

        TextView tvTitle, tvDateRelease, tvOverview, tvScore;
        ImageView ivThumbnail;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvTitle = itemView.findViewById(R.id.tv_movies_title);
            tvDateRelease = itemView.findViewById(R.id.tv_date_release);
            tvOverview = itemView.findViewById(R.id.tv_movies_description);
            tvScore = itemView.findViewById(R.id.tv_movies_score);
            ivThumbnail = itemView.findViewById(R.id.iv_movies_thumbnail);

            itemView.setTag(this);
            itemView.setOnClickListener(onClickListener);
        }

        void bindData(ContentEntity contentEntity){
            tvOverview.setText(contentEntity.getOverview().equals("") ? " - " : contentEntity.getOverview());
            tvScore.setText(contentEntity.getVoteAverage());
            if (contentEntity.getContentType() == ContentEntity.TYPE_MOVIE){
                tvTitle.setText(contentEntity.getTitle());
                tvDateRelease.setText(contentEntity.getReleaseDate());
            } else if (contentEntity.getContentType() == ContentEntity.TYPE_TV_SHOW){
                tvTitle.setText(contentEntity.getName());
                tvDateRelease.setText(contentEntity.getFirstAirDate());
            }

            Glide.with(context)
                    .load(contentEntity.getPosterPath())
                    .thumbnail(0.1f)
                    .diskCacheStrategy(DiskCacheStrategy.ALL)
                    .centerCrop()
                    .error(android.R.drawable.ic_menu_gallery)
                    .into(ivThumbnail);
        }
    }
}
