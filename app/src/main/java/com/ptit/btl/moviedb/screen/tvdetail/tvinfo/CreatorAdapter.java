package com.ptit.btl.moviedb.screen.tvdetail.tvinfo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.ptit.btl.moviedb.R;
import com.ptit.btl.moviedb.data.model.person.Person;
import com.ptit.btl.moviedb.util.ImageUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by admin on 25/4/18.
 */
public class CreatorAdapter extends RecyclerView.Adapter<CreatorAdapter.ItemViewHolder> {
    private List<Person> creators = new ArrayList<>();

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_credit, parent, false);
        return new ItemViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {
        holder.setData(creators.get(position));
    }

    @Override
    public int getItemCount() {
        return creators == null ? 0 : creators.size();
    }

    void updateData(List<Person> people) {
        if (people == null) return;
        creators.clear();
        creators.addAll(people);
        notifyDataSetChanged();
    }

    static class ItemViewHolder extends RecyclerView.ViewHolder {
        private ImageView mImageView;
        private TextView mTextName, mTextRole;
        private Person creator;

        ItemViewHolder(View view) {
            super(view);
            mImageView = view.findViewById(R.id.image_card_credit);
            mTextName = view.findViewById(R.id.text_card_credit_name);
            mTextRole = view.findViewById(R.id.text_card_credit_role);
        }

        public void setData(Person person) {
            if (person == null) return;
            creator = person;
            ImageUtils.loadImageFromUrl(
                mImageView,
                    person.getProfilePicturePath(),
                R.drawable.ic_avatar_man);
            mTextName.setText(person.getName());
            mTextRole.setVisibility(View.GONE);
        }
    }
}
