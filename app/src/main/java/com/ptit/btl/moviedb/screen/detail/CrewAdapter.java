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
import com.ptit.btl.moviedb.data.model.credit.Crew;
import com.ptit.btl.moviedb.screen.BaseRecyclerViewAdapter;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class CrewAdapter extends BaseRecyclerViewAdapter<CrewAdapter.ItemViewHolder> {
    private List<Crew> mCrews = new ArrayList<>();
    private LoadCrewDataCallback mCallback;

    public CrewAdapter(@NonNull Context context,
                LoadCrewDataCallback callback) {
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
        holder.setData(mCrews.get(position));
    }

    @Override
    public int getItemCount() {
        return mCrews == null ? 0 : mCrews.size();
    }

    public void updateData(List<Crew> crews) {
        if (crews == null) return;
        mCrews.clear();
        mCrews.addAll(crews);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextName, mTextRole;
        private Crew mCrew;

        ItemViewHolder(View view,
                       final LoadCrewDataCallback callback) {
            super(view);
            mImageView = view.findViewById(R.id.image_card_credit);
            mTextName = view.findViewById(R.id.text_card_credit_name);
            mTextRole = view.findViewById(R.id.text_card_credit_role);
            view.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    if (callback == null) return;
                    callback.onItemCrewClicked(mCrew);
                }
            });
        }

        public void setData(Crew crew) {
            if (crew == null) return;
            mCrew = crew;
            ImageUtils.loadImageFromUrl(
                mImageView,
                crew.getProfilePath(),
                R.drawable.ic_avatar_man);
            mTextName.setText(crew.getName());
            mTextRole.setText(crew.getDepartment());
        }
    }

    public interface LoadCrewDataCallback {
        void onItemCrewClicked(Crew crew);
    }
}
