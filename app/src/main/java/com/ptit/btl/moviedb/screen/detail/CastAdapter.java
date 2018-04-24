package com.ptit.btl.moviedb.screen.detail;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.credit.Cast;
import com.ptit.btl.moviedb.screen.BaseRecyclerViewAdapter;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class CastAdapter extends BaseRecyclerViewAdapter<CastAdapter.ItemViewHolder> {
    private List<Cast> mCasts = new ArrayList<>();
    private LoadCastDataCallback mCallback;

    CastAdapter(@NonNull Context context,
                LoadCastDataCallback callback) {
        super(context);
        mCallback = callback;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(
            getContext()).inflate(R.layout.item_credit, parent, false);
        return new ItemViewHolder(view, mCallback);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(mCasts.get(position));
    }

    @Override
    public int getItemCount() {
        return mCasts == null ? 0 : mCasts.size();
    }

    void updateData(List<Cast> casts) {
        if (casts == null) return;
        mCasts.clear();
        mCasts.addAll(casts);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextName, mTextRole;
        private Cast mCast;

        ItemViewHolder(View view, final LoadCastDataCallback callback) {
            super(view);
            mImageView = view.findViewById(R.id.image_card_credit);
            mTextName = view.findViewById(R.id.text_card_credit_name);
            mTextRole = view.findViewById(R.id.text_card_credit_role);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback == null) return;
                    callback.onItemCastClicked(mCast);
                }
            });
        }

        public void setData(Cast cast) {
            if (cast == null) return;
            mCast = cast;
            ImageUtils.loadImageFromUrl(
                mImageView,
                cast.getProfilePath(),
                R.drawable.ic_avatar_man);
            mTextName.setText(cast.getName());
            mTextRole.setText(cast.getCharacter());
        }
    }

    interface LoadCastDataCallback {
        void onItemCastClicked(Cast cast);
    }
}
