package com.ptit.btl.moviedb.screen.home;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.Genre;
import com.ptit.btl.moviedb.screen.BaseRecyclerViewAdapter;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class HomeGenresAdapter extends BaseRecyclerViewAdapter<HomeGenresAdapter.ItemViewHolder> {
    private List<Genre> mGenres = new ArrayList<>();
    private LoadGenresAdapterCallback mCallback;

    protected HomeGenresAdapter(@NonNull Context context,
                                LoadGenresAdapterCallback callback) {
        super(context);
        mCallback = callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent,
                                             int viewType) {
        View view = LayoutInflater.from(getContext())
            .inflate(R.layout.item_genres, parent, false);
        return new ItemViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mGenres.get(position));
    }

    @Override
    public int getItemCount() {
        return mGenres == null ? 0 : mGenres.size();
    }

    void updateData(List<Genre> genres){
        if(genres == null) return;
        mGenres.clear();
        mGenres.addAll(genres);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private TextView mTextGenre;
        private Genre mGenre;

        ItemViewHolder(View view, final LoadGenresAdapterCallback callback) {
            super(view);
            mTextGenre = view.findViewById(R.id.text_item_genres);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    callback.onItemClick(mGenre);
                }
            });
        }

        public void setData(Genre genre) {
            if (genre == null) return;
            mGenre = genre;
            mTextGenre.setText(genre.getName());
        }
    }

    public interface LoadGenresAdapterCallback {
        void onItemClick(Genre genre);
    }
}
